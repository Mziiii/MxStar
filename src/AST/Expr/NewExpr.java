package AST.Expr;

import AST.ASTVisitor;
import AST.Type.Type;
import Util.Position;

import java.util.ArrayList;

public class NewExpr extends Expr {
    public Type newType;
    public ArrayList<Expr> sizeList;
    public int dim;

    public NewExpr(Type _type, ArrayList<Expr> _exprs, int dimension, Position pos) {
        super(pos);
        newType = _type;
        sizeList = _exprs;
        dim = dimension;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
