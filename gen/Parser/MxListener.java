// Generated from F:/3/code/compiler/src/Parser\Mx.g4 by ANTLR 4.9.1
package Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParser}.
 */
public interface MxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#subProgram}.
	 * @param ctx the parse tree
	 */
	void enterSubProgram(MxParser.SubProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#subProgram}.
	 * @param ctx the parse tree
	 */
	void exitSubProgram(MxParser.SubProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(MxParser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(MxParser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(MxParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(MxParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(MxParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(MxParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(MxParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#baseVarDef}.
	 * @param ctx the parse tree
	 */
	void enterBaseVarDef(MxParser.BaseVarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#baseVarDef}.
	 * @param ctx the parse tree
	 */
	void exitBaseVarDef(MxParser.BaseVarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(MxParser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(MxParser.SuiteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code block}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MxParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code block}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MxParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code vardefStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVardefStmt(MxParser.VardefStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code vardefStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVardefStmt(MxParser.VardefStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(MxParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(MxParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(MxParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(MxParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(MxParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(MxParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(MxParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(MxParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(MxParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(MxParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(MxParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(MxParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pureExprStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPureExprStmt(MxParser.PureExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pureExprStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPureExprStmt(MxParser.PureExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStmt(MxParser.EmptyStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStmt(MxParser.EmptyStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpr(MxParser.NewExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpr(MxParser.NewExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIndexExpr(MxParser.IndexExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIndexExpr(MxParser.IndexExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prefixExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrefixExpr(MxParser.PrefixExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prefixExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrefixExpr(MxParser.PrefixExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFuncExpr(MxParser.FuncExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFuncExpr(MxParser.FuncExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lambdaExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpr(MxParser.LambdaExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lambdaExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpr(MxParser.LambdaExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code suffixExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSuffixExpr(MxParser.SuffixExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code suffixExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSuffixExpr(MxParser.SuffixExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpr(MxParser.AtomExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpr(MxParser.AtomExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpr(MxParser.BinaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpr(MxParser.BinaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(MxParser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(MxParser.AssignExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memAccExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMemAccExpr(MxParser.MemAccExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memAccExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMemAccExpr(MxParser.MemAccExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(MxParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(MxParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code basicCreator}
	 * labeled alternative in {@link MxParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterBasicCreator(MxParser.BasicCreatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code basicCreator}
	 * labeled alternative in {@link MxParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitBasicCreator(MxParser.BasicCreatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayCreator}
	 * labeled alternative in {@link MxParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreator(MxParser.ArrayCreatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayCreator}
	 * labeled alternative in {@link MxParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreator(MxParser.ArrayCreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#basicType}.
	 * @param ctx the parse tree
	 */
	void enterBasicType(MxParser.BasicTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#basicType}.
	 * @param ctx the parse tree
	 */
	void exitBasicType(MxParser.BasicTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code basicVarType}
	 * labeled alternative in {@link MxParser#varType}.
	 * @param ctx the parse tree
	 */
	void enterBasicVarType(MxParser.BasicVarTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code basicVarType}
	 * labeled alternative in {@link MxParser#varType}.
	 * @param ctx the parse tree
	 */
	void exitBasicVarType(MxParser.BasicVarTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayVarType}
	 * labeled alternative in {@link MxParser#varType}.
	 * @param ctx the parse tree
	 */
	void enterArrayVarType(MxParser.ArrayVarTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayVarType}
	 * labeled alternative in {@link MxParser#varType}.
	 * @param ctx the parse tree
	 */
	void exitArrayVarType(MxParser.ArrayVarTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcType}.
	 * @param ctx the parse tree
	 */
	void enterFuncType(MxParser.FuncTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcType}.
	 * @param ctx the parse tree
	 */
	void exitFuncType(MxParser.FuncTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(MxParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(MxParser.LiteralContext ctx);
}