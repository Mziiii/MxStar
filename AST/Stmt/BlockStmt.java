package AST.Stmt;

import AST.ASTVisitor;
import Util.Position;

import java.util.ArrayList;

public class BlockStmt extends Stmt{
    public ArrayList<Stmt> stmtList =new ArrayList<>();

    public BlockStmt(ArrayList<Stmt> stmts,Position pos) {
        super(pos);
        stmtList=stmts;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
