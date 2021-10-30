package AST.Type;

import AST.ASTVisitor;
import Util.Position;

public class ClassType extends Type{
    public ClassType(String typeId, int dimension, TypeList t, Position pos) {
        super(typeId, dimension, t, pos);
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
