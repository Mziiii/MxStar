package Util;

import AST.Type.Type;
import MIR.register;
import Util.Error.semanticError;

import java.util.HashMap;

public class Scope {

    public HashMap<String, Type> variables;
    private HashMap<String, register> entities = new HashMap<>();
    private Scope parentScope;


    public Scope(Scope parentScope) {
        variables = new HashMap<>();
        this.parentScope = parentScope;
    }

    public Scope parentScope() {
        return parentScope;
    }

    public void defineVariable(String name, Type t, Position pos) {
        if (variables.containsKey(name))
            throw new semanticError("Semantic Error: variable redefine", pos);
        variables.put(name, t);
    }

    public boolean containsVariable(String name, boolean lookUpon) {
        if (variables.containsKey(name)) return true;
        else if (parentScope != null && lookUpon)
            return parentScope.containsVariable(name, true);
        else return false;
    }

    public Type getType(String name) {
        if (variables.containsKey(name)) return variables.get(name);
        else if (parentScope != null)
            return parentScope.getType(name);
        return null;
    }

    public Type getType(String name, boolean lookUpon) {
        if (variables.containsKey(name)) return variables.get(name);
        else if (parentScope != null && lookUpon)
            return parentScope.getType(name, true);
        return null;
    }

    public register getEntity(String name, boolean lookUpon) {
        if (entities.containsKey(name)) return entities.get(name);
        else if (parentScope != null && lookUpon)
            return parentScope.getEntity(name, true);
        return null;
    }

    public void defineVariable(String name,Type t) {
        variables.put(name, t);
    }

    public boolean containsVariable(String name){
        return variables.containsKey(name);
    }
}