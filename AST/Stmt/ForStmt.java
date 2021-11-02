package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.Expr;
import Util.Position;

public class ForStmt extends Stmt {
    public Expr condition, incr;
    public Stmt init,loopBody;

    public ForStmt(Stmt _init, Expr _condition, Expr _incr, Stmt _loopBody, Position pos) {
        super(pos);
        init = _init;
        condition = _condition;
        incr = _incr;
        loopBody = _loopBody;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
