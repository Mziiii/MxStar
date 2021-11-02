package AST;

import Util.Position;

import java.util.ArrayList;

public class Root extends ASTNode {
    public ArrayList<ASTNode> nodes;

    public Root(ArrayList<ASTNode> node, Position pos) {
        super(pos);
        nodes = node;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
