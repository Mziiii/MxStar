package AST.Def;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.Position;

public class Def extends ASTNode {
    public String identifier;

    public Def(String _identifier, Position pos) {
        super(pos);
        identifier = _identifier;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
