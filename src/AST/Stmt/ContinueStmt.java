package AST.Stmt;

import AST.ASTVisitor;
import Util.Position;

public class ContinueStmt extends Stmt{

    public ContinueStmt(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
