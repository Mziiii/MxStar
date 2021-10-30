package AST.Expr;

import AST.ASTVisitor;
import AST.Type.ClassType;
import AST.Type.Type;
import Util.Position;

public class StringConstExpr extends Expr {
    public String stringValue;

    public StringConstExpr(String _strVal, Position pos) {
        super(pos);
        stringValue = _strVal;
        this.type = new ClassType("string", 0, Type.TypeList.STRING, pos);
    }

    public String getStringValue() {
        return stringValue;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
