package AST.Type;

import AST.ASTVisitor;
import Util.Position;

public class VoidType extends Type{
    public VoidType(Position pos){
        super("void",pos);
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
