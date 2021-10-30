package AST.Expr;

import AST.ASTVisitor;
import AST.Type.ClassType;
import AST.Type.Type;
import Util.Position;

public class NullConstExpr extends Expr{

    public NullConstExpr(Position pos) {
        super(pos);
        this.type=new ClassType("null", 0, Type.TypeList.NULL, pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
