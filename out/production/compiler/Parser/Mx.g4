grammar Mx;

program : subProgram* ;

subProgram : funcDef | varDef | classDef ;

funcDef : funcType? Identifier '(' parameterList? ')' suite;
varDef : varType baseVarDef (',' baseVarDef) * ';';
classDef : Class Identifier '{' (varDef | funcDef)* '}'';';
parameterList : varType Identifier (',' varType Identifier)*;
baseVarDef : Identifier ('=' expression)?;

suite : '{' statement* '}';

statement
    : suite                                                   #block
    | varDef                                                  #vardefStmt
    | If '(' expression ')' trueStmt=statement
        (Else falseStmt=statement)?                           #ifStmt
    | For '(' init=expression? ';'
    condition=expression? ';' incr=expression ')'
    loopBody=statement                                        #forStmt
    | While '(' condition=expression ')' loopBody=statement   #whileStmt
    | Break ';'                                               #breakStmt
    | Continue ';'                                            #continueStmt
    | Return expression? ';'                                  #returnStmt
    | expression ';'                                          #pureExprStmt
    | ';'                                                     #emptyStmt
    ;

expression
    : primary                                               #atomExpr
    | expression op=('*' | '/' | '%') expression            #binaryExpr
    | expression op=('>>' | '<<') expression                #binaryExpr
    | expression op='&' expression                          #binaryExpr
    | expression op='|' expression                          #binaryExpr
    | expression op='^' expression                          #binaryExpr
    | expression op='&&' expression                         #binaryExpr
    | expression op='||' expression                         #binaryExpr
    | expression op=('+' | '-') expression                  #binaryExpr
    | expression op=('==' | '!=') expression                #binaryExpr
    | expression op=('<' | '>' | '<=' | '>=') expression    #binaryExpr
    | <assoc=right> op=('~' | '!' | '++' | '--' | '+' | '-')
     expression                                             #prefixExpr
    | expression op=('++' | '--')                           #suffixExpr
    | expression '.' Identifier                             #memAccExpr
    | expression '[' expression ']'                         #indexExpr
    | expression '(' (expression (',' expression)*)? ')'    #funcExpr
    | <assoc=right> expression '=' expression               #assignExpr
    | <assoc=right> New creator                             #newExpr
    | LambdaKey ('(' parameterList ')')* LambdaResult suite
    '{'expression'}' '(' parameterList? ')'                 #lambdaExpr
    ;

primary
    : '(' expression ')'
    | Identifier
    | literal
    | This
    ;

creator
    : basicType ('(' ')')?                     #basicCreator
    | basicType ('[' expression ']')+('[' ']') #arrayCreator
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
LambdaKey : '[&]';
LambdaResult : '->';


//identifier
Identifier
    : [a-zA-Z] [a-zA-Z_0-9]*
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

BlockComment
    :   '/*' .*? '*/'
        -> skip
    ;

//comment
LineComment
    :   '//' ~[\r\n]*
        -> skip
    ;