package AST.Expr;

import AST.ASTVisitor;
import Util.Position;

public class AssignExpr extends Expr {
    public Expr left, right;

    public AssignExpr(Expr _left, Expr _right, Position pos) {
        super(pos);
        left = _left;
        right = _right;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
