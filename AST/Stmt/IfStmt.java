package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.Expr;
import Util.Position;

public class IfStmt extends Stmt{
    public Expr condition;
    public Stmt trueStmt,falseStmt;

    public IfStmt(Expr _condition,Stmt _trueStmt,Stmt _falseStmt,Position pos) {
        super(pos);
        condition=_condition;
        trueStmt=_trueStmt;
        falseStmt=_falseStmt;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
