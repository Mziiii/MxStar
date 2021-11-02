package AST.Expr;

import AST.ASTVisitor;
import AST.Type.Type;
import Util.Position;
import AST.ASTNode;

public class Expr extends ASTNode {
    public Type type = null;

    public boolean isAssignable = false;

    public Expr(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
