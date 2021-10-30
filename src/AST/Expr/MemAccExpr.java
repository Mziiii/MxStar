package AST.Expr;

import AST.ASTVisitor;
import Util.Position;

public class MemAccExpr extends Expr{
    public Expr operand;
    public String member;

    public MemAccExpr(Expr _operand,String _mem,Position pos) {
        super(pos);
        operand=_operand;
        member=_mem;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
