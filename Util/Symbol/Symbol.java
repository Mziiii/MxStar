package Util.Symbol;

import AST.Type.Type;

abstract public class Symbol {
    public enum TypeCategory {
        INTEGER, BOOLEAN, ARRAY, CLASS, NULL, CONSTRUCTOR, VOID, FUNCTION
    }

    public abstract TypeCategory typeCategory();

    public abstract Type type();

    public abstract BaseSymbol baseSymbol();

    public abstract String toString();

    public abstract int size();

    public abstract int dim();

    public abstract boolean isSameType(Type type);

    public boolean isInt() {
        return typeCategory() == TypeCategory.INTEGER;
    }

    public boolean isBool() {
        return typeCategory() == TypeCategory.BOOLEAN;
    }

    public boolean isArray() {
        return typeCategory() == TypeCategory.ARRAY;
    }

    public boolean isClass() {
        return typeCategory() == TypeCategory.CLASS;
    }

    public boolean isNull() {
        return typeCategory() == TypeCategory.NULL;
    }

    public boolean isConstructor() {
        return typeCategory() == TypeCategory.CONSTRUCTOR;
    }

    public boolean isVoid() {
        return typeCategory() == TypeCategory.VOID;
    }

    public boolean isFunction() {
        return typeCategory() == TypeCategory.FUNCTION;
    }
}
