package FrontEnd;

import AST.ASTVisitor;
import AST.Def.ClassDef;
import AST.Def.Def;
import AST.Def.FuncDef;
import AST.Def.VarDef;
import AST.Expr.*;
import AST.Root;
import AST.Stmt.*;
import AST.Type.ClassType;
import AST.Type.Type;
import Util.GlobalScope;
import Util.MxErrorListener;
import Util.Position;
import Util.Scope;

import java.util.Currency;

public class SymbolCollector implements ASTVisitor {
    private MxErrorListener err;
    private GlobalScope globalScope = null;

    public SymbolCollector(GlobalScope gScope) {
        globalScope = gScope;
    }

    @Override
    public void visit(Root root) {
        root.nodes.forEach(node -> node.accept(this));
        if (!globalScope.containFunction("main")) err.semantic("No main function!", root.pos);
        if (!globalScope.getFunction("main").funcType.isEqual(new ClassType("int", new Position(-1, -1))))
            err.semantic("Main function doesn't look like this!", root.pos);
        if (globalScope.getFunction("main").parameterList != null)
            err.semantic("SOS:Main function has parameters!", root.pos);
    }

    @Override
    public void visit(ClassDef node) {
        if (globalScope.containsClass(node.classIdentifier))
            err.semantic("Class identifier existed already : " + node.classIdentifier, node.pos);
        GlobalScope classScope = new GlobalScope(globalScope);
        //Class variable members
        for (VarDefStmt varList : node.classMember) {
            varList.varList.forEach(varDef -> {
                if (classScope.containsVariable(varDef.varIdentifier))
                    err.semantic("Variable identifier existed already : " + varDef.varIdentifier, varDef.pos);
                classScope.defineVariable(varDef.varIdentifier, varDef.varType);
            });
        }
        //Class function members
        node.classFunc.forEach(funcDef -> {
            if ((funcDef.funcType == null || funcDef.isConstructor) && !funcDef.funcIdentifier.equals(node.classIdentifier))
                err.semantic("Constructor in class Error : " + funcDef.funcIdentifier + " in Class : " + node.classIdentifier, funcDef.pos);
            if (classScope.containFunction(funcDef.funcIdentifier))
                err.semantic(funcDef.funcIdentifier + "has been defined in : " + node.classIdentifier, funcDef.pos);
            classScope.defineFunction(funcDef.funcIdentifier, funcDef);
        });
        globalScope.defineClass(node.classIdentifier, classScope);
    }

    @Override
    public void visit(FuncDef node) {
        if (globalScope.containFunction(node.funcIdentifier))
            err.semantic("Function identifier existed already : " + node.funcIdentifier, node.pos);
        if (globalScope.containsClass(node.funcIdentifier))
            err.semantic("Function identifier existed already in Class: " + node.funcIdentifier, node.pos);
        if (node.funcType == null) err.semantic("A function out of class wants to be a Constructor!", node.pos);
        globalScope.defineFunction(node.funcIdentifier, node);
    }

    @Override
    public void visit(AssignExpr node) {

    }

    @Override
    public void visit(BinaryExpr node) {

    }

    @Override
    public void visit(BoolConstExpr node) {

    }

    @Override
    public void visit(FunCallExpr node) {

    }

    @Override
    public void visit(ArrayExpr node) {

    }

    @Override
    public void visit(IntegerConstExpr node) {

    }

    @Override
    public void visit(LambdaExpr node) {

    }

    @Override
    public void visit(MemAccExpr node) {

    }

    @Override
    public void visit(NewExpr node) {

    }

    @Override
    public void visit(NullConstExpr node) {

    }

    @Override
    public void visit(PrefixExpr node) {

    }

    @Override
    public void visit(StringConstExpr node) {

    }

    @Override
    public void visit(SuffixExpr node) {

    }

    @Override
    public void visit(BlockStmt node) {

    }

    @Override
    public void visit(BreakStmt node) {

    }

    @Override
    public void visit(ContinueStmt node) {

    }

    @Override
    public void visit(EmptyStmt node) {

    }

    @Override
    public void visit(ForStmt node) {

    }

    @Override
    public void visit(IfStmt node) {

    }

    @Override
    public void visit(PureExprStmt node) {

    }

    @Override
    public void visit(ReturnStmt node) {

    }

    @Override
    public void visit(VarDefStmt node) {

    }

    @Override
    public void visit(WhileStmt node) {

    }

    @Override
    public void visit(Type node) {

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

    }
}
