package AST.Type;

import AST.ASTVisitor;
import Util.Position;

public class ArrayType extends Type {
    public int dim = 0;

    public ArrayType(String typeId, TypeList t, int dimension, Position pos) {
        super(typeId, dimension, t, pos);
        dim = dimension;
    }

    public ArrayType(Type type, Position pos) {
        super(type.typeIdentifier, type.dim, type.tType, pos);
        if (type.tType == TypeList.ARRAY) {
            dim = ((ArrayType) type).dim + 1;
            super.dim += 1;
        } else {
            dim = 1;
        }
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
