package AST.Expr;

import AST.ASTVisitor;
import Util.Position;

import java.util.ArrayList;

public class FuncExpr extends Expr{
    public Expr funcIdentifier;
    public ArrayList<Expr> exprList;

    public FuncExpr(Expr _func,ArrayList<Expr> _exprList,Position pos) {
        super(pos);
        funcIdentifier=_func;
        exprList=_exprList;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
