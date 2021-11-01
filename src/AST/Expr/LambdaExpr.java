package AST.Expr;

import AST.ASTVisitor;
import AST.Def.VarDef;
import AST.Stmt.BlockStmt;
import AST.Type.Type;
import Util.Position;

import java.util.ArrayList;

public class LambdaExpr extends Expr {
    public ArrayList<VarDef> parameterList;
    public ArrayList<Expr> exprList;
    public BlockStmt blockStmt;
    public Type returnType;

    public LambdaExpr(ArrayList<VarDef> _parList, ArrayList<Expr> _exprList, BlockStmt _block, Position pos) {
        super(pos);
        returnType = null;
        parameterList = _parList;
        exprList = _exprList;
        blockStmt = _block;
    }


    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
