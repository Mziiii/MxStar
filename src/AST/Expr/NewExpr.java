package AST.Expr;

import AST.ASTVisitor;
import AST.Type.Type;
import Util.Position;

import java.util.ArrayList;

public class NewExpr extends Expr {
    public Type newType;
    public ArrayList<Expr> exprList;

    public NewExpr(Type _type, ArrayList<Expr> _exprs, Position pos) {
        super(pos);
        type = _type;
        exprList = _exprs;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
