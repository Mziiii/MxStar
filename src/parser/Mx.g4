grammar Mx;

program : subProgram* ;

subProgram : funcDef | varDef | classDef ;

funcDef : funcType Identifier ;
varDef : varType Indentifier ('=' expression)?';';
classDef:'struct' Identifier '{' varDef* '}'';';

suite : '{' statement* '}';

statement
    : suite                                                 #block
    | varDef                                                #vardefStmt
    | If '(' expression ')' trueStmt=statement
        (Else falseStmt=statement)?                         #ifStmt
    | While '(' condition=expression ')' loopBody=statement #whileStmt
    | For '('
    | Return expression? ';'                                #returnStmt
    | expression ';'                                        #pureExprStmt
    | ';'                                                   #emptyStmt
    ;

expression
    : primary                                               #atomExpr
    | expression op=('+' | '-') expression                  #binaryExpr
    | expression op=('==' | '!=' ) expression               #binaryExpr
    | <assoc=right> expression '=' expression               #assignExpr
    ;

primary
    : '(' expression ')'
    | Identifier
    | literal
    ;

literal
    : DecimalInteger
    ;

//type
basicType : Int | Bool | String | Identifier;
varType
    : basicType       #basicVarType
    | varType '[' ']' # arrayVarType
    ;
funcType : varType | Void;

//constant
constant : IntegerConstant | BoolConstant | StringConstant | NullConstant;

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
Modulo :'%';

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
LambdaResult : '->';

//identifier
Identifier
    : [a-zA-Z] [a-zA-Z_0-9]*
    ;

DecimalInteger
    : [1-9] [0-9]*
    | '0'
    ;

//constant
BoolConstant : 'true' | 'false';
IntegerConstant : [1-9] [0-9]* | '0' ;
StringConstant : '"'( ESC | . )*?'"';
fragment
ESC : '\\"' | '\\\\' | '\\n';
NullConstant : 'null';

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

//BlockComment
//    :   '/*' .*? '*/'
//        -> skip
//    ;

//comment
LineComment
    :   '//' ~[\r\n]*
        -> skip
    ;