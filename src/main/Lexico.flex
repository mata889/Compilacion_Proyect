package main;

import java_cup.runtime.Symbol;
%%

%unicode
%class Lexico
%int
%line
%column
%standalone
%cup

%{
    String str = "";
    static int errores=0;
%}

//Caracteres especiales
comillas= \'
comillasDobles= \"

letra = [a-zA-Z_]

int=[0-9]
NUM = {int}+
LETTER = {comillas}{letra}{comillas}
BOOL = "True" | "False"
CADENA = {comillasDobles}{letra}*{comillasDobles}
flecha="->"
espacio  = [ \n\r\t]+
comma = ","
punto="."
puntoC = ";"
parentesisA = "("
parentesisC = ")"
abrirC = "<"
cerrarC = ">"
bracketA = "["
bracketC = "]"
//arrayCant= ["1"]|["2"]

//Operadores
assignment = "="
equals = ":="

//NOT="NOT"
not = "=/="
//and = "~"
or="||"
and = "&&"

Oprel= {not}|{equals}|"<:"|":>"|"<="|">=" |"~"|{or}|{and}

OpeA_sum = "+"|-
OpeA_mult = "*"|"/"
//MOD = "MOD"


//letter_special= "^"|{MOD}|"$"|#|&|"'"|"?"|"!"|{abrirC}|{cerrarC}|"{"|"}"


//identificador
id = {letra}({int}|{letra})*

//valorChar = {LETTER}+
//valorChar = {letra}|{numero}|{letter_special}|" "
//valorStr = {letra}|{numero}|{letter_special}|" "

commentarios_izq="#/"
commentarios_der="/#"

%state BLOCK_COMMENT
%state COMMENT_LINE

%%
<YYINITIAL>{
    //palabras reservadas
    
    "start"         { return new Symbol(sym.START,yycolumn,yyline,yytext()); }
    "num"           { return new Symbol(sym.NUMERITO,yycolumn,yyline,yytext()); }
    "func"          { return new Symbol(sym.FUNC,yycolumn,yyline,yytext()); }
    "reply"         { return new Symbol(sym.REPLY,yycolumn,yyline,yytext()); }  
    "letter "       { return new Symbol(sym.LETRA,yycolumn,yyline,yytext()); }
    "word"          { return new Symbol(sym.WORD,yycolumn,yyline,yytext()); }
    "bool"          { return new Symbol(sym.VERDAD,yycolumn,yyline,yytext()); }
    "void"          { return new Symbol(sym.VOID,yycolumn,yyline,yytext()); }
    "var"           { return new Symbol(sym.VAR,yycolumn,yyline,yytext()); }
    "new"           { return new Symbol(sym.NEW,yycolumn,yyline,yytext()); }
    "for"           { return new Symbol(sym.FOR,yycolumn,yyline,yytext()); }
    "wle"           { return new Symbol(sym.WLE,yycolumn,yyline,yytext()); }
    "in"            { return new Symbol(sym.IN,yycolumn,yyline,yytext()); }
    "if"            { return new Symbol(sym.IF,yycolumn,yyline,yytext()); }
    "eif"           { return new Symbol(sym.EIF,yycolumn,yyline,yytext()); }
    "else"          { return new Symbol(sym.ELSE,yycolumn,yyline,yytext()); }
    "block"         { return new Symbol(sym.BLOCK,yycolumn,yyline,yytext()); }
    "case"          { return new Symbol(sym.CASE,yycolumn,yyline,yytext()); }
    "end"           { return new Symbol(sym.END,yycolumn,yyline,yytext()); }
    "default"       { return new Symbol(sym.DEFAULT,yycolumn,yyline,yytext()); }
    "catch"         { return new Symbol(sym.CATCH,yycolumn,yyline,yytext()); }
    "throw"         { return new Symbol(sym.THROW,yycolumn,yyline,yytext()); }
    "throwDown"     { return new Symbol(sym.THROWDOWN,yycolumn,yyline,yytext()); }
    "Array"         { return new Symbol(sym.ARRAY,yycolumn,yyline,yytext()); }
    "main"         { return new Symbol(sym.MAIN,yycolumn,yyline,yytext()); }
    {BOOL}          { return new Symbol(sym.BOOL,yycolumn,yyline,yytext()); }
    {LETTER}        { return new Symbol(sym.LETTER,yycolumn,yyline,yytext()); }
    ( "\""(.)*"\"" )    { return new Symbol(sym.CADENA,yycolumn,yyline,yytext()); }
    {NUM}           { return new Symbol(sym.NUM,yycolumn,yyline,yytext()); }
    {id}            { return new Symbol(sym.ID,yycolumn,yyline,yytext()); }
    //{valorChar}     { return new Symbol(sym.VALORCHAR,yycolumn,yyline,yytext()); }
    {not}           { return new Symbol(sym.NOT,yycolumn,yyline,yytext()); }
    
    {Oprel}          { return new Symbol(sym.OPER,yycolumn,yyline,yytext()); }
    //{equals}        { return new Symbol(sym.EQUALS,yycolumn,yyline,yytext()); }
    //operador aritmeticos
    {OpeA_sum}      { return new Symbol(sym.OPEA_SUM,yycolumn,yyline,yytext()); }
    {OpeA_mult}     { return new Symbol(sym.OPEA_MULT,yycolumn,yyline,yytext()); }
    //{MOD}           { return new Symbol(sym.MOD,yycolumn,yyline,yytext()); }
    //operador logico
    {assignment}    { return new Symbol(sym.ASSIGNMENT,yycolumn,yyline,yytext()); }
    
    
    {flecha}        { return new Symbol(sym.FLECHA,yycolumn,yyline,yytext()); }
    //{letter_special}   { return new Symbol(sym.LETTER_SPECIAL,yycolumn,yyline,yytext()); }
    {puntoC}        { return new Symbol(sym.PUNTOC,yycolumn,yyline,yytext()); }
    {parentesisA}   { return new Symbol(sym.PARENTESISA,yycolumn,yyline,yytext()); }
    {parentesisC}   { return new Symbol(sym.PARENTESISC,yycolumn,yyline,yytext()); }
    {abrirC}        { return new Symbol(sym.ABRIRC,yycolumn,yyline,yytext()); }
    {cerrarC}       { return new Symbol(sym.CERRARC,yycolumn,yyline,yytext()); }
    {comma}         { return new Symbol(sym.COMMA,yycolumn,yyline,yytext()); }
    {bracketA}      { return new Symbol(sym.BRACKETA,yycolumn,yyline,yytext()); }
    {bracketC}      { return new Symbol(sym.BRACKETC,yycolumn,yyline,yytext()); }
    {punto}         { return new Symbol(sym.PUNTO,yycolumn,yyline,yytext()); }
    
    
    //{valorStr}      { return new Symbol(sym.VALORSTR,yycolumn,yyline,yytext()); }
   
    {commentarios_izq}  { yybegin(BLOCK_COMMENT); }
    "##"                {yybegin(COMMENT_LINE);}
    {espacio}       {}
    .               {System.out.println("Falla en lexico, char o variable no aceptada: " +yytext()+" Linea: "+(yyline + 1)+ ", Columna: "+(yycolumn+1));
							errores++;}
}
<BLOCK_COMMENT>
{
	{commentarios_der}			{ yybegin(YYINITIAL); }
	.								{ /* do nothing */ }
}
<COMMENT_LINE>
{
    "\n"        {yybegin(YYINITIAL);}
    .           {}
}