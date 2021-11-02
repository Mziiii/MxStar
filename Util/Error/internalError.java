package Util.Error;

import Util.Position;

public class internalError extends error {

    public internalError(String msg, Position pos) {
        super("Internal Error:" + msg, pos);
    }

}