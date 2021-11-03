package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.Expr;
import Util.Position;

public class PureExprStmt extends Stmt {
    public Expr expr;

    public PureExprStmt(Expr expression, Position pos) {
        super(pos);
        expr = expression;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
