package AST.Expr;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.Position;

import java.util.ArrayList;

public class ExprList extends ASTNode {
    public ArrayList<Expr> exprList = new ArrayList<>();

    public ExprList(ArrayList<Expr> _expr, Position pos) {
        super(pos);
        exprList = _expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
