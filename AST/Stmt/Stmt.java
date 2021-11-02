package AST.Stmt;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.Position;

public class Stmt extends ASTNode {
    public Stmt(Position pos){
        super(pos);
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
