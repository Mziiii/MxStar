package AST.Expr;

import AST.ASTVisitor;
import AST.Type.ClassType;
import AST.Type.Type;
import Util.Position;

public class BoolConstExpr extends Expr{
    public boolean boolValue;

    public BoolConstExpr(boolean _boolValue,Position pos) {
        super(pos);
        boolValue=_boolValue;
        this.type=new ClassType("bool",pos);
    }

    public boolean getBool(){
        return boolValue;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
