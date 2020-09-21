import java_cup_runtime.*;
%%

%unicode
%class proyecto
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
numero = [0-9]
letra = [a-zA-Z]|"_"
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
and = "~"
or="||"
OpeR = {not}|{equals}|":<"|":>"|"<="|">=" |{and}|{or}
OpeA_sum = "+"|-
OpeA_mult = "*"|"/"
OpeA_mod = "%"


letter_special= "^"|@|"$"|#|&|"'"|"?"|"!"|{abrirC}|{cerrarC}|"{"|"}"

//inicio
start="start"

//palabras reservadas
var="var"
num = {numero}+
letter = "letter"
word="word"
bool="bool"
in="in"

//ciclos
for = "for"
wle = "wle"
if = "if"
eif = "eif"
else = "else"
//blocks
block = "block"
end = "end"
new = "new"

//arreglo
array = "array"


//escritura
catch = "catch"
throw = "throw"
throwDown = "throwDown"

//funcion
func="func"

//identificador
id = {letra}+({numero}*|{letter_special}*)*


valorChar = '({letra}|{numero}|{letter_special}|" ")'
valorStr='({letra}|{numero}|{letter_special}|" ")+'

commentarios_izq="#/"
commentarios_der="/#"

%state BLOCK_COMMENT

%%
<YYINITIAL>{
    {id}            { return new Symbol(sym.ID,yycolumn,yyline,yytext()); }
    {start}         { return new Symbol(sym.START,yycolumn,yyline,yytext()); }
    {flecha}        { return new Symbol(sym.FLECHA,yycolumn,yyline,yytext()); }
    {valorChar}     { return new Symbol(sym.VALORCHAR,yycolumn,yyline,yytext()); }
    {func}          { return new Symbol(sym.FUNC,yycolumn,yyline,yytext()); }
    {valorStr}      { return new Symbol(sym.VALORSTR,yycolumn,yyline,yytext()); }

    //types
    {num}           { return new Symbol(sym.NUM,yycolumn,yyline,yytext()); }
    {letter}        { return new Symbol(sym.LETTER,yycolumn,yyline,yytext()); }
    {word}          { return new Symbol(sym.WORD,yycolumn,yyline,yytext()); }
    {bool}          { return new Symbol(sym.BOOL,yycolumn,yyline,yytext()); }
    {var}           { return new Symbol(sym.VAR,yycolumn,yyline,yytext()); }
    {new}           { return new Symbol(sym.NEW,yycolumn,yyline,yytext()); }
    //ciclos
    {for}           { return new Symbol(sym.FOR,yycolumn,yyline,yytext()); }
    {wle}         { return new Symbol(sym.WLE,yycolumn,yyline,yytext()); }
    {in}            { return new Symbol(sym.IN,yycolumn,yyline,yytext()); }
    //decision
    {if}            { return new Symbol(sym.IF,yycolumn,yyline,yytext()); }
    {eif}           { return new Symbol(sym.EIF,yycolumn,yyline,yytext()); }
    {else}          { return new Symbol(sym.ELSE,yycolumn,yyline,yytext()); }

    //block
    {block}         { return new Symbol(sym.BLOCK,yycolumn,yyline,yytext()); }
    {end}           { return new Symbol(sym.END,yycolumn,yyline,yytext()); }

    //impresiones
    {catch}         { return new Symbol(sym.CATCH,yycolumn,yyline,yytext()); }
    {throw}         { return new Symbol(sym.THROW,yycolumn,yyline,yytext()); }
    {throwDown}     { return new Symbol(sym.THROWDOWN,yycolumn,yyline,yytext()); }

    //operador relaciona
    {OpeR}          { return new Symbol(sym.OPER,yycolumn,yyline,yytext()); }

    //operador aritmeticos
    {OpeA_sum}      { return new Symbol(sym.OPEA_SUM,yycolumn,yyline,yytext()); }
    {OpeA_mult}     { return new Symbol(sym.OPEA_MULT,yycolumn,yyline,yytext()); }
    {OpeA_mod}      { return new Symbol(sym.OPEA_MOD,yycolumn,yyline,yytext()); }
    //operador logico
    
    {assignment}    { return new Symbol(sym.ASSIGNMENT,yycolumn,yyline,yytext()); }
    {equals}        { return new Symbol(sym.EQUALS,yycolumn,yyline,yytext()); }
    {not}           { return new Symbol(sym.NOT,yycolumn,yyline,yytext()); }
    
    //letrasfinales
    
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
    {new}           { return new Symbol(sym.NEW,yycolumn,yyline,yytext()); }
    {array}         { return new Symbol(sym.ARRAY,yycolumn,yyline,yytext()); }

    {commentarios_izq}  { yybegin(BLOCK_COMMENT); }
    {espacio}       {}
    .               {System.out.println("Falla en lexico, char o variable no aceptada: " +yytext()+" Linea: "+(yyline + 1)+ ", Columna: "+(yycolumn+1));
							errores++;}
}
<BLOCK_COMMENT>
{
	{commentarios_der}			{ yybegin(YYINITIAL); }
	.								{ /* do nothing */ }
}