package AST.Stmt;

import AST.ASTVisitor;
import Util.Position;

public class EmptyStmt extends Stmt{

    public EmptyStmt(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
