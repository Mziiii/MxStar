package AST.Type;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.Position;

public class Type extends ASTNode {
    public enum TypeList {INT, BOOLEAN, STRING, NULL, VOID, VARIABLE, CLASS, FUNCTION, ARRAY}

    ;

    public int dim = 0;
    public String typeIdentifier;
    public TypeList tType;

    public Type(String typeId, int dimension, TypeList t, Position pos) {
        super(pos);
        typeIdentifier = typeId;
        dim = dimension;
        tType = t;
    }

    public boolean isEqual(Type _type) {
        if (dim != _type.dim) return false;
        if (tType != _type.tType) return false;
        return typeIdentifier.equals(_type.typeIdentifier);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
