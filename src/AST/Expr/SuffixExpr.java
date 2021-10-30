package AST.Expr;

import AST.ASTVisitor;
import Util.Position;

public class SuffixExpr extends Expr {
    public String op;
    public Expr operand;

    public SuffixExpr(String _op, Expr _operand, Position pos) {
        super(pos);
        op = _op;
        operand = _operand;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
