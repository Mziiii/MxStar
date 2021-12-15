package BackEnd;

import MIR.block;
import MIR.mainFn;
public class IRVisitor {

    public interface IRVistor {
        void visitBlock(block b);
        void visitFn(mainFn f);
    }
}
