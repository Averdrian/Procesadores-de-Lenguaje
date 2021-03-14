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
Digito = ({DigitoPos}|0)
Ent = ({DigitoPos} {Digito}* | 0)
PDec = \. (({Digito}* {DigitoPos}) | 0)
PExp = [eE] [\-\+]? ({Ent} | 0)
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
PAp = \(
PCie = \)
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
{ignorable}               	{}
{comentario}              	{}
{EntNum}                  	{return ops.unidadEntNum();}
{RealNum}                 	{return ops.unidadRealNum();}
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
{Real}						{return ops.unidadReal();}
{String}					{return ops.unidadString();}
{Pointer}					{return ops.unidadPointer();}
{Array}						{return ops.unidadArray();}
{Of}						{return ops.unidadOf();}
{Sum}						{return ops.unidadSum();}
{Res}						{return ops.unidadRes();}
{Mul}						{return ops.unidadMul();}
{Div}						{return ops.unidadDiv();}
{Mod}						{return ops.unidadMod();}
{Asig}						{return ops.unidadAsig();}
{Menor}						{return ops.unidadMenor();}
{Mayor}						{return ops.unidadMayor();}
{Ig}						{return ops.unidadIg();}
{MayIg}						{return ops.unidadMayIg();}
{MenIg}						{return ops.unidadMenIg();}
{Dist}						{return ops.unidadDist();}
{And}						{return ops.unidadAnd();}
{Or}						{return ops.unidadOr();}
{Not}						{return ops.unidadNot();}
{PAp}						{return ops.unidadPAp();}
{PCie}						{return ops.unidadPCie();}
{CAp}						{return ops.unidadCAp();}
{CCie}						{return ops.unidadCCie();}
{LAp}						{return ops.unidadLAp();}
{LCie}						{return ops.unidadLCie();}
{Punto}						{return ops.unidadPunto();}
{Coma}						{return ops.unidadComa();}
{Andvers}					{return ops.unidadAndvers();}
{Flecha}					{return ops.unidadFlecha();}
{PunCo}						{return ops.unidadPunCo();}
{Sep}						{return ops.unidadSep();}
{Variable}           		{return ops.unidadVariable();}
{LitCad}					{return ops.unidadLitCad();}
[^]                       	{ops.error();}  