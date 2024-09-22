grammar IdnLang;

// Lexer rules
VAR: 'var';
PRINT: 'cetak';
INT: 'bilBulat';
FLOAT: 'bilDesimal';
STRING: 'teks';

ID: [a-zA-Z_][a-zA-Z0-9_]*;
INT_LITERAL: [0-9]+;
FLOAT_LITERAL: [0-9]+'.'[0-9]+;
STRING_LITERAL: '"' .*? '"';

WS: [ \t\r\n]+ -> skip;
COMMENT: '//' ~[\r\n]* -> skip;

// Mathematic operator
PLUS: '+';
MINUS: '-';
MUL: '*';
DIV: '/';
MOD: '%';

// Parser rules
program: (statement)* EOF;

statement
    : variableDeclaration
    | printStatement
    | assignment
    ;

variableDeclaration
    : 'var' ID ':' type '=' expression
    ;

printStatement
    : 'cetak' expression
    ;

assignment
    : ID '=' expression
    ;

type
    : 'bilBulat'
    | 'bilDesimal'
    | 'teks'
    ;

expression
    : expression (MUL | DIV) expression
    | expression (MOD) expression
    | expression (PLUS | MINUS) expression
    | '(' expression ')'
    | INT_LITERAL
    | FLOAT_LITERAL
    | STRING_LITERAL
    | ID
    ;