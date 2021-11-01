package AST.Def;

import AST.ASTVisitor;
import AST.Stmt.BlockStmt;
import AST.Type.Type;
import Util.Position;

import java.util.ArrayList;

public class FuncDef extends Def {
    public String funcIdentifier;
    public boolean isConstructor;
    public Type funcType;
    public ArrayList<VarDef> parameterList;
    public BlockStmt block;

    public FuncDef(String _identifier, Type _func, ArrayList<VarDef> _para, BlockStmt _block,Position pos) {
        super(_identifier, pos);
        funcIdentifier = _identifier;
        isConstructor = false;
        funcType = _func;
        parameterList = _para;
        block = _block;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
