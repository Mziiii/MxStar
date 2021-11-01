package FrontEnd;

import AST.Def.FuncDef;
import AST.Def.VarDef;
import AST.Type.ClassType;
import AST.Type.VoidType;
import Util.GlobalScope;
import Util.Position;

import java.util.ArrayList;

public class ScopeBuilder {
    public GlobalScope build(GlobalScope scope) {
        ArrayList<VarDef> varDefArrayList = new ArrayList<VarDef>();

        //class
        scope.defineClass("bool", new GlobalScope(scope));
        scope.defineClass("int", new GlobalScope(scope));

        GlobalScope classScope = new GlobalScope(scope);
        classScope.defineFunction("length", new FuncDef("length", new ClassType("int", new Position(-1, -1)), null, null, new Position(-1, -1)));
        varDefArrayList.add(new VarDef("left", new ClassType("int", new Position(-1, -1)), null, new Position(-1, -1)));
        varDefArrayList.add(new VarDef("right", new ClassType("int", new Position(-1, -1)), null, new Position(-1, -1)));
        classScope.defineFunction("substring", new FuncDef("substring", new ClassType("string", new Position(-1, -1)), varDefArrayList, null, new Position(-1, -1)));
        classScope.defineFunction("parseInt", new FuncDef("parseInt", new ClassType("int", new Position(-1, -1)), null, null, new Position(-1, -1)));

        varDefArrayList = new ArrayList<VarDef>();
        varDefArrayList.add(new VarDef("pos", new ClassType("int", new Position(-1, -1)), null, new Position(-1, -1)));
        classScope.defineFunction("ord", new FuncDef("ord", new ClassType("int", new Position(-1, -1)), varDefArrayList, null, new Position(-1, -1)));

        scope.defineClass("string", classScope);
        //func
        varDefArrayList = new ArrayList<>();
        varDefArrayList.add(new VarDef("str", new ClassType("string", new Position(-1, -1)), null, new Position(-1, -1)));
        scope.defineFunction("print", new FuncDef("print", new VoidType(new Position(-1, -1)), varDefArrayList, null, new Position(-1, -1)));

        scope.defineFunction("println", new FuncDef("println", new VoidType(new Position(-1, -1)), varDefArrayList, null, new Position(-1, -1)));

        varDefArrayList = new ArrayList<VarDef>();
        varDefArrayList.add(new VarDef("n", new ClassType("int", new Position(-1, -1)), null, new Position(-1, -1)));
        scope.defineFunction("printInt", new FuncDef("printInt", new VoidType(new Position(-1, -1)), varDefArrayList, null, new Position(-1, -1)));

        scope.defineFunction("printlnInt", new FuncDef("printlnInt", new VoidType(new Position(-1, -1)), varDefArrayList, null, new Position(-1, -1)));

        scope.defineFunction("getString", new FuncDef("getString", new ClassType("string", new Position(-1, -1)), null, null, new Position(-1, -1)));

        scope.defineFunction("getInt", new FuncDef("getInt", new ClassType("int", new Position(-1, -1)), null, null, new Position(-1, -1)));

        scope.defineFunction("toString", new FuncDef("toString", new ClassType("string", new Position(-1, -1)), varDefArrayList, null, new Position(-1, -1)));
        return scope;
    }
}
