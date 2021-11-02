package AST.Type;

import AST.ASTVisitor;
import Util.Position;

public class ArrayType extends Type {
    public int dim = 0;

    public ArrayType(int _dim, String baseType, Position pos) {
        super(baseType, pos);
        dim = _dim;
    }

    public ArrayType(Type baseType, Position pos) {
        super(baseType.typeIdentifier, pos);
        if (baseType instanceof ArrayType) {
            dim = ((ArrayType) baseType).dim + 1;
        } else {
            dim = 1;
        }
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
