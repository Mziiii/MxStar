package AST.Type;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.Position;

public abstract class Type extends ASTNode {

    public String typeIdentifier;

    public Type(String typeId, Position pos) {
        super(pos);
        typeIdentifier = typeId;
    }

    public boolean isEqual(Type _type) {
        if (this instanceof ClassType && _type instanceof ClassType)
            return this.typeIdentifier.equals(_type.typeIdentifier);
        if (this instanceof ArrayType && _type instanceof ArrayType)
            return (this.typeIdentifier.equals(_type.typeIdentifier)) && ((ArrayType) this).dim == ((ArrayType) _type).dim;
        return this instanceof VoidType && _type instanceof VoidType;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
