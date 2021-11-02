package Util.Symbol;

import AST.Type.Type;

abstract public class BaseSymbol extends Symbol{
    private String Identifier;

    public BaseSymbol(String identifier){
        super();
        Identifier=identifier;
    }

    public String getIdentifier(){
        return Identifier;
    }

    public int allocSize(){
        return size();
    }

    @Override
    public BaseSymbol baseSymbol() {
        return this;
    }

    @Override
    public String toString() {
        return Identifier;
    }

    @Override
    public int dim() {
        return 0;
    }
}
