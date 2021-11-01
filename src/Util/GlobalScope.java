package Util;

import AST.Def.ClassDef;
import AST.Def.FuncDef;
import AST.Type.Type;
import Util.Error.semanticError;

import java.util.HashMap;

public class GlobalScope extends Scope {
    private HashMap<String, Type> types = new HashMap<>();
    public HashMap<String, GlobalScope> classes = new HashMap<>();
    public HashMap<String, FuncDef> functions = new HashMap<>();

    public GlobalScope(Scope parentScope) {
        super(parentScope);
    }

    public void defineClass(String name, GlobalScope gScope, Position pos) {
        if (classes.containsKey(name))
            throw new semanticError("Semantic Error: class redefine", pos);
        classes.put(name, gScope);
    }

    public void defineClass(String name, GlobalScope gScope) {
        classes.put(name, gScope);
    }

    public void defineFunction(String name, FuncDef funcDef, Position pos) {
        if (functions.containsKey(name))
            throw new semanticError("Semantic Error: function redefine", pos);
        functions.put(name, funcDef);
    }

    public void defineFunction(String name, FuncDef funcDef) {
        functions.put(name, funcDef);
    }

    public boolean containsClass(String name) {
        return classes.containsKey(name);
    }

    public boolean containFunction(String name) {
        return functions.containsKey(name);
    }

    public FuncDef getFunction(String name) {
        if (containFunction(name)) return functions.get(name);
        return null;
    }

    public void addType(String name, Type t, Position pos) {
        if (types.containsKey(name))
            throw new semanticError("multiple definition of " + name, pos);
        types.put(name, t);
    }

    public Type getTypeFromName(String name, Position pos) {
        if (types.containsKey(name)) return types.get(name);
        throw new semanticError("no such type: " + name, pos);
    }
}