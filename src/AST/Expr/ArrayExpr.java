package AST.Expr;

import Util.Position;
import AST.ASTVisitor;

public class ArrayExpr extends Expr {
    public Expr arrIdentifier, index;

    public ArrayExpr(Expr _arr, Expr _idx, Position pos) {
        super(pos);
        arrIdentifier = _arr;
        index = _idx;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}