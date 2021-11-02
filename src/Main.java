import AST.Root;
import FrontEnd.ASTBuilder;
import FrontEnd.ScopeBuilder;
import FrontEnd.SemanticChecker;
import FrontEnd.SymbolCollector;
import MIR.mainFn;
import Parser.MxLexer;
import Parser.MxParser;
import Util.Error.error;
import Util.GlobalScope;
import Util.MxErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
//         String name="src/text.mx";
//         InputStream input= new FileInputStream(name);

       InputStream input = System.in;

        try {
            Root ASTRoot;
            GlobalScope gScope = new GlobalScope(null);

            MxLexer lexer = new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());
            MxParser parser = new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());
            ParseTree parseTreeRoot = parser.program();

            ASTBuilder astBuilder = new ASTBuilder(gScope);
            ASTRoot = (Root) astBuilder.visit(parseTreeRoot);
            gScope = new GlobalScope(null);
            ScopeBuilder scopeBuilder = new ScopeBuilder();
            gScope = scopeBuilder.build(gScope);
            new SymbolCollector(gScope).visit(ASTRoot);
            new SemanticChecker(gScope).visit(ASTRoot);

//            mainFn f = new mainFn();
//            new IRBuilder(f, gScope).visit(ASTRoot);
//            // new IRPrinter(System.out).visitFn(f);
//
//            AsmFn asmF = new AsmFn();
//            new InstSelector(asmF).visitFn(f);
//            new RegAlloc(asmF).work();
//            new AsmPrinter(asmF, System.out).print();
        } catch (error er) {
            System.err.println(er.toString());
            throw new RuntimeException();
        }
    }
}
