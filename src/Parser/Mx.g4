grammar Mx;

program : subProgram* ;

subProgram : funcDef | varDef | classDef ;

funcDef : funcType? Identifier '(' parameterList? ')' suite;
varDef : varType baseVarDef (',' baseVarDef) * ';';
classDef : Class Identifier '{' (varDef | funcDef)* '}'';';
parameterList : varType Identifier (',' varType Identifier)*;
baseVarDef : Identifier ('=' expression)?;
expressionList : expression (',' expression)*;

suite : '{' statement* '}';

statement
    : suite                                                   #blockStmt
    | varDef                                                  #varDefStmt
    | If '(' condition=expression ')' trueStmt=statement
        (Else falseStmt=statement)?                           #ifStmt
    | For '(' (initExpr=expression|initDef=varDef)? ';'
    condition=expression? ';' incr=expression? ')'
    loopBody=statement                                        #forStmt
    | While '(' condition=expression ')' loopBody=statement   #whileStmt
    | Break ';'                                               #breakStmt
    | Continue ';'                                            #continueStmt
    | Return expression? ';'                                  #returnStmt
    | expression ';'                                          #pureExprStmt
    | ';'                                                     #emptyStmt
    ;

expression
    : '(' expression ')'                                    #parenExpr
    | Identifier                                            #identifierExpr
    | literal                                               #constExpr
    | This                                                  #thisExpr

    | <assoc=right> New creator                             #newExpr
    | expression '.' Identifier                             #memAccExpr
    | expression '(' expressionList? ')'                    #funCallExpr
    | expression '[' expression ']'                         #arrayExpr
    | expression op=('++' | '--')                           #suffixExpr
    | <assoc=right> op=('~' | '!' | '++' | '--' | '+' | '-')
     operand=expression                                     #prefixExpr
    | expression op=('*' | '/' | '%') expression            #binaryExpr
    | expression op=('+' | '-') expression                  #binaryExpr
    | expression op=('>>' | '<<') expression                #binaryExpr
    | expression op=('==' | '!=') expression                #binaryExpr
    | expression op=('<' | '>' | '<=' | '>=') expression    #binaryExpr
    | expression op='&' expression                          #binaryExpr
    | expression op='|' expression                          #binaryExpr
    | expression op='^' expression                          #binaryExpr
    | expression op='&&' expression                         #binaryExpr
    | expression op='||' expression                         #binaryExpr

    | LambdaKey ('(' parameterList? ')')? LambdaResult suite
    '(' (expression (',' expression)*)? ')'                 #lambdaExpr
    | <assoc=right> expression '=' expression               #assignExpr
    ;

creator
    : basicType ('(' ')')?                                               #basicCreator
    | basicType ('[' expression ']')+ ('[' ']')*                         #arrayCreator
    | basicType ('[' expression ']')+ ('[' ']')+ ('[' expression ']')+   #errorCreator
    ;

//type
basicType : Int | Bool | String | Identifier;
varType
    : basicType       #basicVarType
    | varType '[' ']' #arrayVarType
    ;
funcType : varType | Void;

//constant
literal : IntegerConstant | BoolConstant | StringConstant | NullConstant;

//reserved words
Int : 'int';
Bool : 'bool';
String : 'string';
Void : 'void';
If : 'if';
Else : 'else';
For : 'for';
While : 'while';
Break : 'break';
Continue : 'continue';
Return : 'return';
New : 'new';
Class : 'class';
This : 'this';

//symbols
LeftParen : '(';
RightParen : ')';
LeftBracket : '[';
RightBracket : ']';
LeftBrace : '{';
RightBrace : '}';

Less : '<';
LessEqual : '<=';
Greater : '>';
GreaterEqual : '>=';
LeftShift : '<<';
RightShift : '>>';

Plus : '+';
Minus : '-';
Mutiply : '*';
Divide : '/';
Modulo : '%';

And : '&';
Or : '|';
AndAnd : '&&';
OrOr : '||';
Caret : '^';
Not : '!';
Tilde : '~';

Question : '?';
Colon : ':';
Semi : ';';
Comma : ',';

Assign : '=';
Equal : '==';
NotEqual : '!=';

SelfPlus: '++';
SelfMinus : '--';

Dot : '.';
LambdaKey : '[&]';
LambdaResult : '->';


//constant
BoolConstant : 'true' | 'false';
IntegerConstant : [1-9] [0-9]* | '0' ;
StringConstant : '"'( ESC | . )*?'"';
fragment
ESC : '\\"' | '\\\\' | '\\n';
NullConstant : 'null';

//identifier
Identifier
    : [a-zA-Z] [a-zA-Z_0-9]*
    ;

//blank
Whitespace
    :   [ \t]+
        -> skip
    ;

Newline
    :   (   '\r' '\n'?
        |   '\n'
        )
        -> skip
    ;

BlockComment
    :   '/*' .*? '*/'
        -> skip
    ;

//comment
LineComment
    :   '//' ~[\r\n]*
        -> skip
    ;