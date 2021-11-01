package Util.Symbol;

import AST.Type.ArrayType;
import AST.Type.Type;

public class ArraySymbol extends Symbol {
    private BaseSymbol baseSymbol;
    private int dim;

    public ArraySymbol(BaseSymbol _baseSymbol, int _dim) {
        baseSymbol = _baseSymbol;
        dim = _dim;
    }

    public ArraySymbol(Symbol lowerSymbol) {
        baseSymbol = lowerSymbol.baseSymbol();
        dim = lowerSymbol.dim() - 1;
    }


    @Override
    public TypeCategory typeCategory() {
        return TypeCategory.ARRAY;
    }

    @Override
    public Type type() {
        return null;
    }

    @Override
    public BaseSymbol baseSymbol() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int dim() {
        return 0;
    }

    @Override
    public boolean isSameType(Type type) {
        return false;
    }
}
