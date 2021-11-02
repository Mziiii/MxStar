package AST.Expr;

import AST.ASTVisitor;
import Util.Position;

public class IdentifierExpr extends Expr {
    public String identifier;

    public IdentifierExpr(String _identifier, Position pos) {
        super(pos);
        identifier = _identifier;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
