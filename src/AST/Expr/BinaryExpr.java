package AST.Expr;

import AST.ASTVisitor;
import Util.Position;

public class BinaryExpr extends Expr {
    String op;
    Expr operand1, operand2;

    public BinaryExpr(Expr _operand1, Expr _operand2, Position pos) {
        super(pos);
        operand1 = _operand1;
        operand2 = _operand2;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
