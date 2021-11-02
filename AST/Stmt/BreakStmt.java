package AST.Stmt;

import AST.ASTVisitor;
import Util.Position;

public class BreakStmt extends Stmt{

    public BreakStmt(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
