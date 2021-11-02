package AST.Expr;

import AST.ASTVisitor;
import AST.Type.ClassType;
import AST.Type.Type;
import Util.Position;

public class IntegerConstExpr extends Expr{
    public int integerValue;

    public IntegerConstExpr(int _intVal,Position pos) {
        super(pos);
        integerValue=_intVal;
        this.type=new ClassType("int", pos);
    }

    public int getIntegerValue() {
        return integerValue;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
