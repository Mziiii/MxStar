package Util.Error;

import Util.Position;

public class semanticError extends error {

    public semanticError(String msg, Position pos) {
        super("Semantic Error: " + msg, pos);
    }

}