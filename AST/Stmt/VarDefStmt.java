package AST.Stmt;

import AST.ASTVisitor;
import AST.Def.VarDef;
import Util.Position;

import java.util.ArrayList;

public class VarDefStmt extends Stmt{
    public ArrayList<VarDef> varList;

    public VarDefStmt(ArrayList<VarDef> _varList, Position pos) {
        super(pos);
        varList=_varList;
    }

    @Override
    public void accept(ASTVisitor visitor){
        visitor.visit(this);
    }
}
