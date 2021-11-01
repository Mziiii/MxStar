package Util;

import Util.Error.semanticError;
import Util.Error.syntaxError;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class MxErrorListener extends BaseErrorListener {
    public void syntax(String msg, Position pos) {
        throw new syntaxError(msg, pos);
    }

    public void semantic(String msg, Position pos) {
        throw new semanticError(msg, pos);
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg,
                            RecognitionException e) {

        throw new syntaxError(msg, new Position(line, charPositionInLine));
    }
}