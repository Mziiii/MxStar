package FrontEnd;

import AST.ASTNode;
import AST.Def.ClassDef;
import AST.Def.FuncDef;
import AST.Def.VarDef;
import AST.Expr.*;
import AST.Root;
import AST.Stmt.*;
import AST.Type.ArrayType;
import AST.Type.ClassType;
import AST.Type.Type;
import AST.Type.VoidType;
import Parser.MxBaseVisitor;
import Parser.MxParser;
import Util.GlobalScope;
import Util.MxErrorListener;
import Util.Position;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {
    private MxErrorListener err;

    private GlobalScope globalScope;

    public ASTBuilder(GlobalScope gScope) {
        globalScope = gScope;
    }

    @Override
    public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        ArrayList<ASTNode> nodeArrayList = new ArrayList<>();
        for (MxParser.SubProgramContext nodes : ctx.subProgram()) {
            if (nodes.funcDef() != null) nodeArrayList.add(visit(nodes.funcDef()));
            if (nodes.varDef() != null) nodeArrayList.add(visit(nodes.varDef()));
            if (nodes.classDef() != null) nodeArrayList.add(visit(nodes.classDef()));
        }
        ASTNode root = new Root(nodeArrayList, new Position(ctx));
        return root;
    }


    //subProgram
    @Override
    public ASTNode visitFuncDef(MxParser.FuncDefContext ctx) {
        String funcIdentifier = ctx.Identifier().getText();
        boolean isConstructor = false;
        Type funcType = null;
        ArrayList<VarDef> parameterList = new ArrayList<VarDef>();
        BlockStmt block = (BlockStmt) visit(ctx.suite());
        if (ctx.funcType() != null) funcType = (Type) visit(ctx.funcType());
        else isConstructor = true;
        if (ctx.parameterList() != null) {
            List<MxParser.VarTypeContext> varTypeList = ctx.parameterList().varType();
            List<TerminalNode> varIdentifierList = ctx.parameterList().Identifier();
            for (int i = 0; i < varTypeList.size(); ++i) {
                parameterList.add(new VarDef(varIdentifierList.get(i).getText(), (Type) visit(varTypeList.get(i)), null, new Position(varTypeList.get(i))));
            }
        } else parameterList = null;
        FuncDef funcDef = new FuncDef(funcIdentifier, funcType, parameterList, block, new Position(ctx));
        funcDef.isConstructor = isConstructor;
        return funcDef;
    }

    @Override
    public ASTNode visitVarDef(MxParser.VarDefContext ctx) {
        Type varType = (Type) visit(ctx.varType());
        ArrayList<VarDef> varDefArrayList = new ArrayList<VarDef>();
        for (MxParser.BaseVarDefContext baseVarDefContext : ctx.baseVarDef()) {
            String varIdentifier = baseVarDefContext.Identifier().getText();
            Expr initAssign = null;
            if (baseVarDefContext.expression() != null) initAssign = (Expr) visit(baseVarDefContext.expression());
            varDefArrayList.add(new VarDef(varIdentifier, varType, initAssign, new Position(baseVarDefContext)));
        }
        ASTNode varDefStmt = new VarDefStmt(varDefArrayList, new Position(ctx));
        return varDefStmt;
    }

    @Override
    public ASTNode visitClassDef(MxParser.ClassDefContext ctx) {
        String classIdentifier = ctx.Identifier().getText();
        ArrayList<VarDefStmt> classMember = new ArrayList<VarDefStmt>();
        ArrayList<FuncDef> classFunc = new ArrayList<FuncDef>();
        if (ctx.varDef() != null) ctx.varDef().forEach(vardef -> classMember.add((VarDefStmt) visit(vardef)));
        if (ctx.funcDef() != null) ctx.funcDef().forEach(funcdef -> classFunc.add((FuncDef) visit(funcdef)));
        ASTNode classDef = new ClassDef(classIdentifier, classMember, classFunc, new Position(ctx));
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitParameterList(MxParser.ParameterListContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitBaseVarDef(MxParser.BaseVarDefContext ctx) {
        String varIdentifier = ctx.Identifier().getText();
        Expr initAssign = null;
        if (ctx.expression() != null) initAssign = (Expr) visit(ctx.expression());
        VarDef varDef = new VarDef(varIdentifier, null, initAssign, new Position(ctx));
        return varDef;
    }

    @Override
    public ASTNode visitExpressionList(MxParser.ExpressionListContext ctx) {
        ArrayList<Expr> exprArrayList = new ArrayList<Expr>();
        for (ParserRuleContext expr : ctx.expression()) exprArrayList.add((Expr) visit(expr));
        ASTNode exprList = new ExprList(exprArrayList, new Position(ctx));
        return exprList;
    }

    @Override
    public ASTNode visitSuite(MxParser.SuiteContext ctx) {
        ArrayList<Stmt> stmtArrayList = null;
        if (ctx.statement() != null)
            for (MxParser.StatementContext stmtCtx : ctx.statement()) {
                if (stmtCtx instanceof MxParser.EmptyStmtContext) continue;
                if (stmtArrayList == null) stmtArrayList = new ArrayList<>();
                stmtArrayList.add((Stmt) visit(stmtCtx));
            }
        ASTNode block = new BlockStmt(stmtArrayList, new Position(ctx));
        return block;
    }

    @Override
    public ASTNode visitBlockStmt(MxParser.BlockStmtContext ctx) {
        return visit(ctx.suite());
    }

    @Override
    public ASTNode visitVarDefStmt(MxParser.VarDefStmtContext ctx) {
        return visitVarDef(ctx.varDef());
    }

    @Override
    public ASTNode visitIfStmt(MxParser.IfStmtContext ctx) {
        Expr condition = (Expr) visit(ctx.expression());
        Stmt trueStmt = (Stmt) visit(ctx.trueStmt), falseStmt = null;
        if (ctx.falseStmt != null) falseStmt = (Stmt) visit(ctx.falseStmt);
        ASTNode ifStmt = new IfStmt(condition, trueStmt, falseStmt, new Position(ctx));
        return ifStmt;
    }

    @Override
    public ASTNode visitForStmt(MxParser.ForStmtContext ctx) {
        Expr condition = null, incr = null;
        Stmt init = null, loopBody;
        if (ctx.initDef != null) init = (Stmt) visit(ctx.initDef);
        else if (ctx.initExpr != null) init = new PureExprStmt((Expr) visit(ctx.initExpr), new Position(ctx.initExpr));
        if (ctx.condition != null) condition = (Expr) visit(ctx.condition);
        if (ctx.incr != null) incr = (Expr) visit(ctx.incr);
        loopBody = (Stmt) visit(ctx.loopBody);
        ASTNode forStmt = new ForStmt(init, condition, incr, loopBody, new Position(ctx));
        return forStmt;
    }

    @Override
    public ASTNode visitWhileStmt(MxParser.WhileStmtContext ctx) {
        Expr condition = (Expr) visit(ctx.condition);
        Stmt loopBody = (Stmt) visit(ctx.loopBody);
        ASTNode whileStmt = new WhileStmt(condition, loopBody, new Position(ctx));
        return whileStmt;
    }

    @Override
    public ASTNode visitBreakStmt(MxParser.BreakStmtContext ctx) {
        ASTNode breakStmt = new BreakStmt(new Position(ctx));
        return breakStmt;
    }

    @Override
    public ASTNode visitContinueStmt(MxParser.ContinueStmtContext ctx) {
        ASTNode continueStmt = new ContinueStmt(new Position(ctx));
        return continueStmt;
    }

    @Override
    public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
        Expr returnExpr = null;
        if (ctx.expression() != null) returnExpr = (Expr) visit(ctx.expression());
        ASTNode returnStmt = new ReturnStmt(returnExpr, new Position(ctx));
        return returnStmt;
    }

    @Override
    public ASTNode visitPureExprStmt(MxParser.PureExprStmtContext ctx) {
        ASTNode pureExprStmt = new PureExprStmt((Expr) visit(ctx.expression()), new Position(ctx));
        return pureExprStmt;
    }

    @Override
    public ASTNode visitEmptyStmt(MxParser.EmptyStmtContext ctx) {
        ASTNode emptyStmt = new EmptyStmt(new Position(ctx));
        return emptyStmt;
    }

    @Override
    public ASTNode visitNewExpr(MxParser.NewExprContext ctx) {
        return visit(ctx.creator());
    }

    @Override
    public ASTNode visitThisExpr(MxParser.ThisExprContext ctx) {
        ASTNode thisExpr = new ThisExpr(new Position(ctx));
        return thisExpr;
    }

    @Override
    public ASTNode visitArrayExpr(MxParser.ArrayExprContext ctx) {
        ASTNode arrayExpr = new ArrayExpr((Expr) visit(ctx.expression(0)), (Expr) visit(ctx.expression(1)), new Position(ctx));
        return arrayExpr;
    }

    @Override
    public ASTNode visitSuffixExpr(MxParser.SuffixExprContext ctx) {
        String op = ctx.op.getText();
        switch (op) {
            case "++":
            case "--":
                break;
            default:
                err.syntax("SuffixExpr op error" + op, new Position(ctx));
        }
        ASTNode suffixExpr = new SuffixExpr(op, (Expr) visit(ctx.expression()), new Position(ctx));
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitBinaryExpr(MxParser.BinaryExprContext ctx) {
        String op = ctx.op.getText();
        switch (op) {
            case "*":
            case "/":
            case "%":
            case ">>":
            case "<<":
            case "&":
            case "|":
            case "^":
            case "&&":
            case "||":
            case "+":
            case "-":
            case "==":
            case "!=":
            case ">":
            case "<":
            case "<=":
            case ">=":
                break;
            default:
                err.syntax("BinaryExpr op error:" + op, new Position(ctx));
        }
        Expr operand1 = (Expr) visit(ctx.expression(0)), operand2 = (Expr) visit(ctx.expression(1));
        ASTNode binaryExpr = new BinaryExpr(op, operand1, operand2, new Position(ctx));
        return binaryExpr;
    }

    @Override
    public ASTNode visitParenExpr(MxParser.ParenExprContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public ASTNode visitPrefixExpr(MxParser.PrefixExprContext ctx) {
        String op = ctx.op.getText();
        switch (op) {
            case "~":
            case "!":
            case "++":
            case "--":
            case "+":
            case "-":
                break;
            default:
                err.syntax("PrefixExpr op error:" + op, new Position(ctx));
        }
        ASTNode prefixExpr = new PrefixExpr(op, (Expr) visit(ctx.expression()), new Position(ctx));
        return prefixExpr;
    }

    @Override
    public ASTNode visitFunCallExpr(MxParser.FunCallExprContext ctx) {
        ArrayList<Expr> exprArrayList = new ArrayList<Expr>();
        if (ctx.expressionList() != null) {
            ctx.expressionList().expression().forEach(expr -> exprArrayList.add((Expr) visit(expr)));
            ASTNode funCallExpr = new FunCallExpr((Expr) visit(ctx.expression()), exprArrayList, new Position(ctx));
            return funCallExpr;
        }
        return new FunCallExpr((Expr) visit(ctx.expression()), null, new Position(ctx));
    }

    @Override
    public ASTNode visitLambdaExpr(MxParser.LambdaExprContext ctx) {
        ArrayList<VarDef> parameterList = null;
        ArrayList<Expr> exprArrayList = null;
        BlockStmt blockStmt = (BlockStmt) visit(ctx.suite());
        if (ctx.parameterList() != null) {
            parameterList = new ArrayList<>();
            List<MxParser.VarTypeContext> varTypeContextList = ctx.parameterList().varType();
            List<TerminalNode> varIdentifierList = ctx.parameterList().Identifier();
            for (int i = 0; i < varTypeContextList.size(); ++i) {
                parameterList.add(new VarDef(varIdentifierList.get(i).getText(), (Type) visit(varTypeContextList.get(i)), null, new Position(ctx)));
            }
        }
        if (ctx.expression() != null) {
            exprArrayList = new ArrayList<>();
            for (MxParser.ExpressionContext eCtx : ctx.expression()) exprArrayList.add((Expr) visit(eCtx));
        }
        ASTNode lambdaExpr = new LambdaExpr(parameterList, exprArrayList, blockStmt, new Position(ctx));
        return visitChildren(ctx);
    }

    @Override
    public ASTNode visitAssignExpr(MxParser.AssignExprContext ctx) {
        Expr expr1 = (Expr) visit(ctx.expression(0)), expr2 = (Expr) visit(ctx.expression(1));
        ASTNode assignExpr = new AssignExpr(expr1, expr2, new Position(ctx));
        return assignExpr;
    }

    @Override
    public ASTNode visitIdentifierExpr(MxParser.IdentifierExprContext ctx) {
        ASTNode identifierExpr = new IdentifierExpr(ctx.getText(), new Position(ctx));
        return identifierExpr;
    }

    @Override
    public ASTNode visitConstExpr(MxParser.ConstExprContext ctx) {
        return visit(ctx.literal());
    }

    @Override
    public ASTNode visitMemAccExpr(MxParser.MemAccExprContext ctx) {
        ASTNode memAccExpr = new MemAccExpr((Expr) visit(ctx.expression()), ctx.Identifier().getText(), new Position(ctx));
        return memAccExpr;
    }

    @Override
    public ASTNode visitBasicCreator(MxParser.BasicCreatorContext ctx) {
        ASTNode newExpr = new NewExpr((Type) visit(ctx.basicType()), null, 0, new Position(ctx));
        return newExpr;
    }

    @Override
    public ASTNode visitArrayCreator(MxParser.ArrayCreatorContext ctx) {
        ArrayList<Expr> sizeList = new ArrayList<Expr>();
        ctx.expression().forEach(expr -> sizeList.add((Expr) visit(expr)));
        int dim = (ctx.getChildCount() - sizeList.size() - 1) / 2;
        ASTNode newExpr = new NewExpr((Type) visit(ctx.basicType()), sizeList, dim, new Position(ctx));
        return newExpr;
    }

    @Override
    public ASTNode visitErrorCreator(MxParser.ErrorCreatorContext ctx) {
        err.syntax("ErrorArrayCreator:e.g.[3][][3]", new Position(ctx));
        return null;
    }

    @Override
    public ASTNode visitBasicType(MxParser.BasicTypeContext ctx) {
        return new ClassType(ctx.getText(), new Position(ctx));
    }

    @Override
    public ASTNode visitBasicVarType(MxParser.BasicVarTypeContext ctx) {
        return visit(ctx.basicType());
    }

    @Override
    public ASTNode visitArrayVarType(MxParser.ArrayVarTypeContext ctx) {
        return new ArrayType((Type) visit(ctx.varType()), new Position(ctx));
    }

    @Override
    public ASTNode visitFuncType(MxParser.FuncTypeContext ctx) {
        if (ctx.Void() != null) return new VoidType(new Position(ctx));
        return visit(ctx.varType());
    }

    @Override
    public ASTNode visitLiteral(MxParser.LiteralContext ctx) {
        if (ctx.IntegerConstant() != null)
            return new IntegerConstExpr(Integer.parseInt(ctx.getText()), new Position(ctx));
        if (ctx.BoolConstant() != null) return new BoolConstExpr(ctx.getText().equals("true"), new Position(ctx));
        if (ctx.StringConstant() != null) return new StringConstExpr(ctx.getText(), new Position(ctx));
        if (ctx.NullConstant() != null) return new NullConstExpr(new Position(ctx));
        err.syntax("Constant Error: not integer/bool/string/null", new Position(ctx));
        return null;
    }
}
