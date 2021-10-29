package AST;

import AST.Program.*;
import AST.Expr.*;
import AST.Stmt.*;

public interface ASTVisitor {
    //Program
    void visit(Root root);

    void visit(ClassDef node);

    void visit(FunDef node);

    void visit(TypeNode node);

    //Expr
    void visit(AssignExpr node);

    void visit(BinaryExpr node);

    void visit(BoolLiteralExpr node);

    void visit(FunctionExpr node);

    void visit(IndexExpr node);

    void visit(IntLiteralExpr node);

    void visit(LambdaExpr node);

    void visit(MemberExpr node);

    void visit(NewExpr node);

    void visit(NullLiteralExpr node);

    void visit(PrefixExpr node);

    void visit(StringLiteralExpr node);

    void visit(SuffixExpr node);

    //Stmt
    void visit(BlockStmt node);

    void visit(BreakStmt node);

    void visit(ContinueStmt node);

    void visit(EmptyStmt node);

    void visit(ForStmt node);

    void visit(IfStmt node);

    void visit(PureExprStmt node);

    void visit(ReturnStmt node);

    void visit(VarDefStmt node);

    void visit(VarDefSubStmt node);

    void visit(WhileStmt node);

}