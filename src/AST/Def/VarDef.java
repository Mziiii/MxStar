package AST.Def;

import AST.ASTVisitor;
import AST.Expr.Expr;
import AST.Type.Type;
import Util.Position;

//varDef->VarDefStmt
//varDef/VarDefStmt->consist of baseVarDef
//baseVarDef
public class VarDef extends Def {
    public String varIdentifier;
    public Type varType;
    public Expr initAssign;

    public VarDef(String _identifier, Type _type, Expr _assign, Position pos) {
        super(_identifier, pos);
        varIdentifier = _identifier;
        varType = _type;
        initAssign = _assign;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}