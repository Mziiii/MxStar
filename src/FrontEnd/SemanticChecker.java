package FrontEnd;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.Def.ClassDef;
import AST.Def.Def;
import AST.Def.FuncDef;
import AST.Def.VarDef;
import AST.Expr.*;
import AST.Root;
import AST.Stmt.*;
import AST.Type.ArrayType;
import AST.Type.ClassType;
import AST.Type.Type;
import AST.Type.VoidType;
import Util.GlobalScope;
import Util.MxErrorListener;
import Util.Position;
import Util.Scope;

import java.util.Stack;

public class SemanticChecker implements ASTVisitor {
    private MxErrorListener err = new MxErrorListener();

    public Scope currentScope = null;
    public GlobalScope globalScope = null;

    //curState
    public Stack<ASTNode> funcStack = new Stack<ASTNode>();
    public String curClassId = null;

    //memFunction
    public FuncDef funcInto = null;
    //loop jump
    public Stack<ASTNode> loopBodies = new Stack<ASTNode>();

    public SemanticChecker(GlobalScope gScope) {
        currentScope = globalScope = gScope;
        funcInto = new FuncDef("size", new ClassType("int", new Position(-1, -1)), null, null, new Position(-1, -1));
    }

    @Override
    public void visit(Root root) {
        root.nodes.forEach(node -> node.accept(this));
    }

    @Override
    public void visit(ClassDef node) {

        curClassId = node.classIdentifier;
        currentScope = globalScope.classes.get(curClassId);

        //class Variables Member
        for (VarDefStmt varList : node.classMember)
            for (VarDef varNode : varList.varList) {
                if (!globalScope.containsClass(varNode.varType.typeIdentifier))
                    err.semantic("Class variable member type undefined : " + varNode.varType.typeIdentifier, varNode.pos);
                if (varNode.initAssign != null) {
                    varNode.initAssign.accept(this);
                    if (!varNode.initAssign.type.isEqual(NullType))
                        if (!varNode.initAssign.type.isEqual(varNode.varType))
                            err.semantic("Class member initial assign Type mismatched : " + varNode.varType.typeIdentifier, varNode.pos);
                }
            }
        //class Functions Member
        node.classFunc.forEach(funcMem -> funcMem.accept(this));
        curClassId = null;
        currentScope = currentScope.parentScope();
    }

    @Override
    public void visit(FuncDef node) {
        currentScope = new Scope(currentScope);
        funcStack.push(node);
        if (node.funcType != null)
            if (!node.funcType.isEqual(VoidType))
                if (!globalScope.containsClass(node.funcType.typeIdentifier))
                    err.semantic("Function missed its return type : " + node.funcIdentifier, node.pos);
        if (node.parameterList != null)
            node.parameterList.forEach(parameter -> parameter.accept(this));
        if (node.block.stmtList != null) node.block.stmtList.forEach(stmt -> stmt.accept(this));
        if (node.funcType != null)
            if (!node.funcType.isEqual(VoidType))
                if (!node.funcIdentifier.equals("main"))
                    if (!node.isConstructor)
                        err.semantic("The function lost its return stmt : " + node.funcIdentifier, node.pos);
        funcStack.pop();
        currentScope = currentScope.parentScope();
    }

    @Override
    public void visit(VarDef node) {
        if (currentScope.containsVariable(node.identifier) || globalScope.containsClass(node.identifier))
            err.semantic("VarDef Variable has benn defined : " + node.varIdentifier, node.pos);
        if (!globalScope.containsClass(node.varType.typeIdentifier)) err.semantic("Var type undefined", node.pos);
        if (node.initAssign != null) {
            node.initAssign.accept(this);
            if (!node.initAssign.type.isEqual(NullType))
                if (!node.initAssign.type.isEqual(node.varType))
                    err.semantic("VarDef class type mismatched ", node.pos);
        }
        currentScope.defineVariable(node.varIdentifier, node.varType);
    }

    @Override
    public void visit(AssignExpr node) {
        node.left.accept(this);
        node.right.accept(this);
        node.isAssignable = false;
        if (!node.left.isAssignable) err.semantic("Left Value!!", node.pos);
        if (!node.left.type.isEqual(node.right.type))
            if (!node.right.type.isEqual(NullType))
                err.semantic("Binary operator requires type : ***", node.pos);
        if (node.right.type.isEqual(NullType))
            if (node.left.type.isEqual(IntType) || node.left.type.isEqual(BoolType) || node.left.type.isEqual(StringType))
                err.semantic("Binary operator requires type : **** Null", node.pos);
        node.isAssignable = true;
        node.type = node.left.type;
    }

    @Override
    public void visit(BinaryExpr node) {
        node.operand1.accept(this);
        node.operand2.accept(this);
        if (!node.operand1.type.isEqual(node.operand2.type))
            if (!node.op.equals("==") && !node.op.equals("!="))
                err.semantic("Operand types mismatched", node.pos);
        Type type = node.operand1.type;

        switch (node.op) {
            case "+":
            case "<":
            case ">":
            case "<=":
            case ">=":
                if (!type.isEqual(IntType) && !type.isEqual(StringType))
                    err.semantic("Binary operator requires type:int/string", node.pos);
                break;
            case "-":
            case "*":
            case "/":
            case "%":
            case "<<":
            case ">>":
            case "&":
            case "|":
            case "^":
                if (!type.isEqual(IntType)) err.semantic("Binary operator requires type:int", node.pos);
                break;
            case "&&":
            case "||":
                if (!type.isEqual(BoolType)) err.semantic("Binary operator requires type:bool", node.pos);
                break;
            case "==":
            case "!=":
                if (!node.operand1.type.isEqual(node.operand2.type))
                    if (!node.operand2.type.isEqual(NullType))
                        err.semantic("Binary operator requires type : **", node.pos);
                break;
        }
        switch (node.op) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
            case "<<":
            case ">>":
            case "&":
            case "|":
            case "^":
                node.type = type;
                break;
            case "<":
            case ">":
            case "<=":
            case ">=":
            case "&&":
            case "||":
            case "==":
            case "!=":
                node.type = BoolType;
        }
    }

    @Override
    public void visit(BoolConstExpr node) {
        /*
        empty
         */
    }

    @Override
    public void visit(FunCallExpr node) {
        FuncDef funcCall;
        if (node.funcIdentifier instanceof MemAccExpr) {
            ((MemAccExpr) node.funcIdentifier).isMethod = true;
            node.funcIdentifier.accept(this);
            funcCall = (((MemAccExpr) node.funcIdentifier).funcMember);
        } else {
            String funcId = ((IdentifierExpr) node.funcIdentifier).identifier;
            if (curClassId == null) funcCall = globalScope.getFunction(funcId);
            else {
                funcCall = globalScope.classes.get(curClassId).getFunction(funcId);
                if (funcCall == null) funcCall = globalScope.getFunction(funcId);
            }
            if (funcCall == null) err.semantic("Function not found : " + funcId, node.pos);
        }
        if (node.exprList != null) node.exprList.forEach(expr -> expr.accept(this));
        if (funcCall.parameterList == null || node.exprList == null) {
            if (!(funcCall.parameterList == null && node.exprList == null))
                err.semantic("Parameters miamatched ", node.pos);
        } else {
            if (funcCall.parameterList.size() != node.exprList.size()) err.semantic("Parameters size wrong", node.pos);
            for (int i = 0; i < node.exprList.size(); ++i)
                if (!funcCall.parameterList.get(i).varType.isEqual(node.exprList.get(i).type))
                    if (!node.exprList.get(i).type.isEqual(NullType))
                        err.semantic("Parameters type wrong", node.pos);

        }
        node.type = funcCall.funcType;
        node.isAssignable = false;
    }

    @Override
    public void visit(ArrayExpr node) {
        node.arrIdentifier.accept(this);
        if (!(node.arrIdentifier.type instanceof ArrayType))
            err.semantic("Get Index not from a array type ", node.pos);
        node.index.accept(this);
        if (!node.index.type.isEqual(IntType))
            err.semantic("Array index should be int! " + node.index.type.typeIdentifier, node.pos);
        if (((ArrayType) node.arrIdentifier.type).dim == 1)
            node.type = new ClassType(node.arrIdentifier.type.typeIdentifier, node.pos);
        else
            node.type = new ArrayType(((ArrayType) node.arrIdentifier.type).dim - 1, node.arrIdentifier.type.typeIdentifier, node.pos);
        node.isAssignable = true;
    }

    @Override
    public void visit(IdentifierExpr node) {
        if (currentScope.getType(node.identifier) == null) err.semantic("Variable undefined ", node.pos);
        node.type = currentScope.getType(node.identifier);
        node.isAssignable = true;
    }

    @Override
    public void visit(IntegerConstExpr node) {
        /*
        empty
         */
    }

    @Override
    public void visit(LambdaExpr node) {
        currentScope = new Scope(currentScope);
        funcStack.push(node);
        if (node.parameterList != null) node.parameterList.forEach(parameter -> parameter.accept(this));
        if (node.exprList != null) node.exprList.forEach(expr -> expr.accept(this));
        if (node.parameterList == null || node.exprList == null) {
            if (!(node.parameterList == null && node.exprList == null))
                err.semantic("Lambda parameters wrong", node.pos);
        } else {
            if (node.exprList.size() != node.parameterList.size())
                err.semantic("Lambda parameters size wrong", node.pos);
            for (int i = 0; i < node.exprList.size(); ++i) {
                if (!node.parameterList.get(i).varType.isEqual(node.exprList.get(i).type))
                    err.semantic("Lambda parameters type wrong", node.pos);
            }
            node.blockStmt.stmtList.forEach(stmt -> stmt.accept(this));
            if (node.returnType == null) err.semantic("Lambada needs a return type", node.pos);
            node.type = node.returnType;
            node.isAssignable = false;
            funcStack.pop();
            currentScope = currentScope.parentScope();
        }
    }

    @Override
    public void visit(MemAccExpr node) {
        node.operand.accept(this);
        if (node.operand.type instanceof ArrayType) {
            if (!node.isMethod) err.semantic("MemAcc Array no func members", node.pos);
            if (!node.member.equals("size")) err.semantic("MemAcc Array cannot have two size()", node.pos);
            node.funcMember = funcInto;
        } else {
            if (node.isMethod) {
                node.funcMember = globalScope.classes.get(node.operand.type.typeIdentifier).getFunction(node.member);
                if (node.funcMember == null)
                    err.semantic("No functions in Class : " + node.operand.type.typeIdentifier, node.pos);
            } else {
                node.type = globalScope.classes.get(node.operand.type.typeIdentifier).variables.get(node.member);
                if (node.type == null)
                    err.semantic("No variables in Class : " + node.operand.type.typeIdentifier, node.pos);
                node.isAssignable = true;
            }
        }
    }

    @Override
    public void visit(NewExpr node) {
        if (!globalScope.containsClass(node.newType.typeIdentifier)) err.semantic("Class does not exist", node.pos);
        if (node.sizeList != null) {
            node.sizeList.forEach(size -> {
                size.accept(this);
                if (!size.type.isEqual(IntType)) err.semantic("New Expr : size should be int!", node.pos);
            });
        }
        if (node.dim > 0) node.type = new ArrayType(node.dim, node.newType.typeIdentifier, node.pos);
        else node.type = new ClassType(node.newType.typeIdentifier, node.pos);
        node.isAssignable = false;
    }

    @Override
    public void visit(NullConstExpr node) {
        /*
        empty
         */
    }

    @Override
    public void visit(PrefixExpr node) {
        node.operand.accept(this);
        switch (node.op) {
            case "++":
            case "--":
                if (!node.operand.type.isEqual(IntType))
                    err.semantic("Prefix operand type error : " + node.op, node.pos);
                if (!node.operand.isAssignable)
                    err.semantic("Prefix operand must be assignable : " + node.op, node.pos);
                break;
            case "+":
            case "-":
            case "~":
                if (!node.operand.type.isEqual(IntType))
                    err.semantic("Prefix operand type error:int " + node.op, node.pos);
                break;
            case "!":
                if (!node.operand.type.isEqual(BoolType))
                    err.semantic("Prefix operand type error:bool " + node.op, node.pos);
        }
        node.type = node.operand.type;
        if (node.op.equals("++") || node.op.equals("--")) node.isAssignable = true;
    }

    @Override
    public void visit(StringConstExpr node) {
        /*
        empty
         */
    }

    @Override
    public void visit(SuffixExpr node) {
        node.operand.accept(this);
        if (!node.operand.type.isEqual(IntType)) err.semantic("Suffix operand type error : " + node.op, node.pos);
        if (!node.operand.isAssignable) err.semantic("Suffix operand must be assignable : " + node.op, node.pos);
        node.type = node.operand.type;
    }

    @Override
    public void visit(ThisExpr node) {
        if (curClassId == null) err.semantic("This should be used in Claas", node.pos);
        node.type = new ClassType(curClassId, node.pos);
        node.isAssignable = false;
    }

    @Override
    public void visit(BlockStmt node) {
        currentScope = new Scope(currentScope);
        if (node.stmtList != null) node.stmtList.forEach(stmt -> stmt.accept(this));
        currentScope = currentScope.parentScope();
    }

    @Override
    public void visit(BreakStmt node) {
        if (loopBodies.isEmpty()) err.semantic("Break out of LoopBodies", node.pos);
    }

    @Override
    public void visit(ContinueStmt node) {
        if (loopBodies.isEmpty()) err.semantic("Continue out of LoopBodies", node.pos);
    }

    @Override
    public void visit(EmptyStmt node) {
        /*
        empty
         */
    }

    @Override
    public void visit(ForStmt node) {
        currentScope = new Scope(currentScope);
        loopBodies.push(node);
        if (node.init != null) {
            if (!(node.init instanceof PureExprStmt))
                if (!(node.init instanceof VarDefStmt)) err.semantic("Initial part invalid in For Stmt", node.pos);
            node.init.accept(this);
        }
        if (node.condition != null) {
            if (!node.condition.type.isEqual(BoolType))
                err.semantic("Condition cannot be null in For Stmt", node.pos);
            node.condition.accept(this);
        }
        if (node.incr != null) node.incr.accept(this);
        if (node.loopBody != null) if (node.loopBody instanceof BlockStmt)
            ((BlockStmt) node.loopBody).stmtList.forEach(stmt -> stmt.accept(this));
        else node.loopBody.accept(this);
        loopBodies.pop();
        currentScope = currentScope.parentScope();
    }

    @Override
    public void visit(IfStmt node) {
        if (node.condition == null) err.semantic("If Stmt lost its condition", node.pos);
        node.condition.accept(this);
        if (!node.condition.type.isEqual(BoolType)) err.semantic("If Stmt must have a boolType condition", node.pos);
        currentScope = new Scope(currentScope);
        if (node.trueStmt != null) node.trueStmt.accept(this);
        currentScope = currentScope.parentScope();
        if (node.falseStmt != null) {
            currentScope = new Scope(currentScope);
            node.falseStmt.accept(this);
            currentScope = currentScope.parentScope();
        }
    }

    @Override
    public void visit(PureExprStmt node) {
        node.expr.accept(this);
    }

    @Override
    public void visit(ReturnStmt node) {
        if (funcStack.isEmpty()) err.semantic("Return Stmt out of function", node.pos);
        if (funcStack.peek() instanceof FuncDef) {
            FuncDef funcDef = (FuncDef) funcStack.peek();
            if (node.returnExpr == null) {
                if (funcDef.funcType != null)
                    if (!funcDef.funcType.isEqual(VoidType))
                        err.semantic("Return Stmt:lost", node.pos);
            } else {
                node.returnExpr.accept(this);
                if (funcDef.funcType == null) err.semantic("Return Stmt should be null here!", node.pos);
                if (!funcDef.funcType.isEqual(node.returnExpr.type))
                    if (!node.returnExpr.type.isEqual(NullType))
                        err.semantic("Return Stmt type mismatched with the function type  ", node.pos);
            }
            funcDef.isConstructor = true;
        } else {
            LambdaExpr funcDef = (LambdaExpr) funcStack.peek();
            if (node.returnExpr == null) err.semantic("Return Stmt lambda needs return type", node.pos);
            node.returnExpr.accept(this);
            if (funcDef.returnType == null) funcDef.returnType = node.returnExpr.type;
            if (funcDef.returnType != null)
                if (!funcDef.returnType.isEqual(node.returnExpr.type))
                    err.semantic("Return Stmt lambda return type mismatched", node.pos);
        }
    }

    @Override
    public void visit(VarDefStmt node) {
        node.varList.forEach(varDef -> varDef.accept(this));
    }

    @Override
    public void visit(WhileStmt node) {
        loopBodies.push(node);
        if (node.condition == null) err.semantic("Condition cannot be null in While Stmt", node.pos);
        node.condition.accept(this);
        if (!node.condition.type.isEqual(BoolType))
            err.semantic("If Stmt must have a boolType condition", node.pos);
        currentScope = new Scope(currentScope);
        node.loopBody.accept(this);
        loopBodies.pop();
        currentScope = currentScope.parentScope();
    }

    @Override
    public void visit(Type node) {
        /*
        empty
         */
    }

    @Override
    public void visit(VoidType node) {
        /*
        empty
         */
    }

    @Override
    public void visit(ClassType node) {
        /*
        empty
         */
    }

    @Override
    public void visit(ArrayType node) {
        /*
        empty
         */
    }

    @Override
    public void visit(Stmt node) {

    }

    @Override
    public void visit(Def node) {

    }

    @Override
    public void visit(Expr node) {

    }

    @Override
    public void visit(ExprList node) {
        /*
        empty
         */
    }

    //type
    private final Type IntType = new ClassType("int", new Position(-1, -1)), BoolType = new ClassType("bool", new Position(-1, -1)), StringType = new ClassType("string", new Position(-1, -1)), VoidType = new VoidType(new Position(-1, -1)), NullType = new ClassType("null", new Position(-1, -1));
}
