package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.Expr;
import Util.Position;

public class ReturnStmt extends Stmt{
    Expr returnExpr;

    public ReturnStmt(Expr _returnExpr,Position pos) {
        super(pos);
        returnExpr=_returnExpr;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
