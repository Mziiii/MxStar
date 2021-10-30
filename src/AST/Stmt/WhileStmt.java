package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.Expr;
import Util.Position;

public class WhileStmt extends Stmt{
    Expr condition;
    Stmt loopBody;
    public WhileStmt(Expr _condition,Stmt _loopBody,Position pos) {
        super(pos);
        condition=_condition;
        loopBody=_loopBody;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
