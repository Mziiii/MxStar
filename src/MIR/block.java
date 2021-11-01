package MIR;

import Util.Error.internalError;
import Util.Position;

import java.util.ArrayList;
import java.util.LinkedList;

public class block {
    private LinkedList<statement> stmts = new LinkedList<>();
    private terminalStmt tailStmt = null;
    public block() {}
    public void push_back(statement stmt) {
        stmts.add(stmt);
        if (stmt instanceof terminalStmt) {
            if (tailStmt != null)
                throw new internalError("multiple tails of a block",
                        new Position(0, 0));
            tailStmt = (terminalStmt)stmt;
        }
    }
    public ArrayList<statement> stmts() {
        return new ArrayList<>(stmts);
    }
    public ArrayList<block> successors() {
        ArrayList<block> ret = new ArrayList<>();
        if (tailStmt instanceof branch) {
            ret.add(((branch) tailStmt).trueBranch);
            ret.add(((branch) tailStmt).falseBranch);
        }
        else if (tailStmt instanceof jump) {
            ret.add(((jump) tailStmt).destination);
        }
        return ret;
    }
}
