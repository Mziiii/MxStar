package AST.Type;

import Util.Position;

import java.util.ArrayList;

public class FuncType extends Type{
    public Type returnType;
    public ArrayList<Type> parameters;


    public FuncType(String typeId, int dimension, TypeList t, Position pos) {
        super(typeId, dimension, t, pos);
    }
}
