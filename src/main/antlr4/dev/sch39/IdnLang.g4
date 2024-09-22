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

// Parser rules
program: (statement)* EOF;

statement
    : variableDeclaration
    | printStatement
    ;

variableDeclaration
    : 'var' ID ':' type '=' expression
    ;

printStatement
    : 'cetak' expression
    ;

type
    : 'bilBulat'
    | 'bilDesimal'
    | 'teks'
    ;

expression
    : INT_LITERAL
    | FLOAT_LITERAL
    | STRING_LITERAL
    | ID
    ;