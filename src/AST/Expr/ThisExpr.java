package AST.Expr;

import AST.ASTVisitor;
import Util.Position;

public class ThisExpr extends Expr{
    public ThisExpr(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
