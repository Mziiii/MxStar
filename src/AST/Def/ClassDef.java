package AST.Def;

import AST.ASTNode;
import Util.Position;
import AST.ASTVisitor;
import AST.Stmt.VarDefStmt;
import java.util.ArrayList;

public class ClassDef extends Def{
    public String classIdentifier;
    public ArrayList<VarDefStmt> classMember;
    public ArrayList<FuncDef> classFunc;

    public String getClassIdentifier() {
        return classIdentifier;
    }

    public ClassDef(String identifier,ArrayList<VarDefStmt> variables,ArrayList<FuncDef> functions,Position pos){
        super(pos);
        classIdentifier=identifier;
        classMember=variables;
        classFunc=functions;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}