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
int=[0-9]
NUM = {int}+
LETTER = [a-zA-Z]|"_"
BOOL = "True" | "False"
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
OpeR = {not}|{equals}|":<"|":>"|"<="|">=" |"~"|{or}
OpeA_sum = "+"|-
OpeA_mult = "*"|"/"
//MOD = "MOD"


//letter_special= "^"|"$"|#|&|"'"|"?"|{abrirC}|{cerrarC}|"{"|"}"
signo_string="!"

//identificador
id = {LETTER}({int}|{LETTER})*

//valorChar = {LETTER}+
//valorChar = {letra}|{numero}|{letter_special}|" "
WORD ={signo_string}{LETTER}({int}|{LETTER}|{espacio})*{signo_string}

commentarios_izq="#/"
commentarios_der="/#"

%state BLOCK_COMMENT
%state COMMENT_LINE

%%
<YYINITIAL>{
    //palabras reservadas
    
    "start"         { System.out.println("start"); return new Symbol(sym.START,yycolumn,yyline,yytext()); }
    "num"           { System.out.println("num");return new Symbol(sym.NUMERITO,yycolumn,yyline,yytext()); }
    "func"          { System.out.println("func");return new Symbol(sym.FUNC,yycolumn,yyline,yytext()); }
    "reply"         { System.out.println("reply");return new Symbol(sym.REPLY,yycolumn,yyline,yytext()); }  
    "letter "       { System.out.println("letter");return new Symbol(sym.LETRA,yycolumn,yyline,yytext()); }
    "words"          { System.out.println("word");return new Symbol(sym.WORDS,yycolumn,yyline,yytext()); }
    "bool"          { System.out.println("bool");return new Symbol(sym.VERDAD,yycolumn,yyline,yytext()); }
    "var"           { System.out.println("var");return new Symbol(sym.VAR,yycolumn,yyline,yytext()); }
    "new"           { System.out.println("new");return new Symbol(sym.NEW,yycolumn,yyline,yytext()); }
    "for"           { System.out.println("for");return new Symbol(sym.FOR,yycolumn,yyline,yytext()); }
    "wle"           { System.out.println("wle");return new Symbol(sym.WLE,yycolumn,yyline,yytext()); }
    "in"            { System.out.println("in");return new Symbol(sym.IN,yycolumn,yyline,yytext()); }
    "if"            { System.out.println("if");return new Symbol(sym.IF,yycolumn,yyline,yytext()); }
    "eif"           { System.out.println("eif");return new Symbol(sym.EIF,yycolumn,yyline,yytext()); }
    "else"          { System.out.println("else");return new Symbol(sym.ELSE,yycolumn,yyline,yytext()); }
    "block"         { System.out.println("block");return new Symbol(sym.BLOCK,yycolumn,yyline,yytext()); }
    "case"          { System.out.println("case");return new Symbol(sym.CASE,yycolumn,yyline,yytext()); }
    "end"           { System.out.println("end");return new Symbol(sym.END,yycolumn,yyline,yytext()); }
    "default"       { System.out.println("default");return new Symbol(sym.DEFAULT,yycolumn,yyline,yytext()); }
    "catch"         { System.out.println("catch");return new Symbol(sym.CATCH,yycolumn,yyline,yytext()); }
    "throw"         { System.out.println("throw");return new Symbol(sym.THROW,yycolumn,yyline,yytext()); }
    "throwDown"     { System.out.println("throwDown");return new Symbol(sym.THROWDOWN,yycolumn,yyline,yytext()); }
    "catch"         { System.out.println("catch");return new Symbol(sym.CATCH,yycolumn,yyline,yytext()); }
    "Array"         { System.out.println("Array");return new Symbol(sym.ARRAY,yycolumn,yyline,yytext()); }
    {BOOL}          { System.out.println("bool");return new Symbol(sym.BOOL,yycolumn,yyline,yytext()); }
    {LETTER}        { System.out.println("letter");return new Symbol(sym.LETTER,yycolumn,yyline,yytext()); }
    {NUM}           { System.out.println("num");return new Symbol(sym.NUM,yycolumn,yyline,yytext()); }
    {id}            { System.out.println("id ");return new Symbol(sym.ID,yycolumn,yyline,yytext()); }
    //{valorChar}     { return new Symbol(sym.VALORCHAR,yycolumn,yyline,yytext()); }
    {not}           { return new Symbol(sym.NOT,yycolumn,yyline,yytext()); }
    
    {OpeR}          { System.out.println("operador relacional"); return new Symbol(sym.OPER,yycolumn,yyline,yytext()); }
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
    {WORD}          { System.out.println("word"); return new Symbol(sym.WORD,yycolumn,yyline,yytext()); }
    
    
    
   
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