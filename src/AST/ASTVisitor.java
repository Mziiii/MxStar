package AST;

import AST.Def.*;
import AST.Stmt.*;
import AST.Type.*;
import AST.Expr.*;

public interface ASTVisitor {
    //Def
    void visit(Root root);

    void visit(Def node);

    void visit(ClassDef node);

    void visit(FuncDef node);

    void visit(VarDef node);

    //Expr
    void visit(ArrayExpr node);

    void visit(AssignExpr node);

    void visit(BinaryExpr node);

    void visit(BoolConstExpr node);

    void visit(Expr node);

    void visit(ExprList node);

    void visit(FunCallExpr node);

    void visit(IdentifierExpr node);

    void visit(IntegerConstExpr node);

    void visit(LambdaExpr node);

    void visit(MemAccExpr node);

    void visit(NewExpr node);

    void visit(NullConstExpr node);

    void visit(PrefixExpr node);

    void visit(StringConstExpr node);

    void visit(SuffixExpr node);

    void visit(ThisExpr node);

    //Stmt
    void visit(Stmt node);

    void visit(BlockStmt node);

    void visit(BreakStmt node);

    void visit(ContinueStmt node);

    void visit(EmptyStmt node);

    void visit(ForStmt node);

    void visit(IfStmt node);

    void visit(PureExprStmt node);

    void visit(ReturnStmt node);

    void visit(VarDefStmt node);

    void visit(WhileStmt node);

    //type
    void visit(Type node);

    void visit(VoidType node);

    void visit(ClassType node);

    void visit(ArrayType node);
}