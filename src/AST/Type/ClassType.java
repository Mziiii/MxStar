package AST.Type;

import AST.ASTVisitor;
import Util.Position;

public class ClassType extends Type {
    public ClassType(String typeId, Position pos) {
        super(typeId, pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
