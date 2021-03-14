package alex;

%%
%line
%column
%class AnalizadorLexicoTiny
%type  UnidadLexica
%unicode

%{
  private ALexOperations ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
DigitoPos = [1-9]
Digito = ({digitoPositivo}|0)
Ent = ({DigitoPos} {Digito}* | 0)
PDec = . (({Digito}* {DigitoPos}) | 0)
PExp = [eE] [\-, \+]? ({Ent} | 0)
EntNum = [\-\+]? {Ent}
RealNum = [\-\+]? {Ent} ({PDec} | {PExp} | {PDec} {PExp})
Variable = {letra} ({letra} | {Digito} | \_)*
LitCad = \" ([^\"\b\r\n])* \"
True = true
False = false
Null = null
Proc = proc
If = if
Then = then
Else = else
Endif = endif
While = while
Do = do
Endwhile = endwhile
Call = call
Type = type
Record = record
New = new
Delete = delete
Read = read
Write = write
NL = nl
Var = var
Bool = bool
Int = int
Real = real
String = string
Pointer = pointer
Array = array
Of = of
Sum = \+
Res = \-
Mul = \*
Div = \/
Mod = \%
Asig = \=
Menor = \<
Mayor = \>
Ig = \= \=
MayIg = \> \=
MenIg = \< \=
Dist = \! \=
And = and
Or = or
Not = not
PAp = /(
PCie = /)
CAp = \[
CCie = \]
LAp = \{
LCie = \}
Punto = \.
Coma = \,
Andvers = \&
Flecha = \- \>
PunCo = \;
Sep = \&\&
ignorable = [\t\r\b\n]
comentario = #[^\n]* 
%%
{separador}               {}
{comentario}              {}
{EntNum}                  {return ops.unidadEntNum();}
{RealNum}                 {return ops.unidadRealNum();}
{True}						{return ops.unidadTrue();}
{False}						{return ops.unidadFalse();}
{Null}						{return ops.unidadNull();}
{Proc}						{return ops.unidadProc();}
{If}						{return ops.unidadIf();}
{Then}						{return ops.unidadThen();}
{Else}						{return ops.unidadElse();}
{Endif}						{return ops.unidadEndif();}
{While}						{return ops.unidadWhile();}
{Do}						{return ops.unidadDo();}
{Endwhile}					{return ops.unidadEndwhile();}
{Call}						{return ops.unidadCall();}
{Type}						{return ops.unidadType();}
{Record}					{return ops.unidadRecord();}
{New}						{return ops.unidadNew();}
{Delete}					{return ops.unidadDelete();}
{Read}						{return ops.unidadRead();}
{Write}						{return ops.unidadWrite();}
{NL}						{return ops.unidadNL();}
{Var}						{return ops.unidadVar();}
{Bool}						{return ops.unidadBool();}
{Int}						{return ops.unidadInt();}
{Real}						{return ops.
{identificador}           {return ops.unidadId();}
{numeroEntero}            {return ops.unidadEnt();}
{numeroReal}              {return ops.unidadReal();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMultiplicacion}  {return ops.unidadMul();}
{operadorDivision}        {return ops.unidadDiv();}
{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCierre();} 
{igual}                   {return ops.unidadIgual();} 
{coma}                    {return ops.unidadComa();}
[^]                       {ops.error();}  