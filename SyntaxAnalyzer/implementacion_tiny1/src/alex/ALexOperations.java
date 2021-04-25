package alex;

import asint.cup.ClaseLexica;

public class ALexOperations {
  private AnalizadorLexicoTiny alex;
  public ALexOperations(AnalizadorLexicoTiny alex) {
   this.alex = alex;   
  }
  public UnidadLexica unidadLitCad() {
     return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.LitCad,
                                         alex.lexema()); 
  } 
  public UnidadLexica unidadVariable() {
     return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.Variable,
    		 							alex.lexema()); 
  } 
  
  public UnidadLexica unidadEntNum() {
     return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.EntNum,alex.lexema()); 
  } 
  public UnidadLexica unidadRealNum() {
     return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.RealNum,alex.lexema()); 
  } 
  public UnidadLexica unidadTrue() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.True, alex.lexema()); 
	  }
  public UnidadLexica unidadFalse() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.False,alex.lexema()); 
	  }
  public UnidadLexica unidadNull() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Null,alex.lexema()); 
	  } 
  public UnidadLexica unidadProc() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Proc,alex.lexema()); 
	  } 
  public UnidadLexica unidadIf() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.If,alex.lexema()); 
	  }
  public UnidadLexica unidadThen() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Then,alex.lexema()); 
	  }
  public UnidadLexica unidadElse() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Else,alex.lexema()); 
	  } 
  public UnidadLexica unidadEndif() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Endif,alex.lexema()); 
	  } 
  public UnidadLexica unidadWhile() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.While,alex.lexema()); 
	  } 
  public UnidadLexica unidadDo() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Do,alex.lexema()); 
	  } 
  public UnidadLexica unidadEndwhile() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Endwhile,alex.lexema()); 
	  } 
  public UnidadLexica unidadCall() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Call,alex.lexema()); 
	  } 
  public UnidadLexica unidadType() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Type,alex.lexema()); 
	  } 
  public UnidadLexica unidadRecord() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Record,alex.lexema()); 
	  } 
  public UnidadLexica unidadNew() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.New,alex.lexema()); 
	  } 
  public UnidadLexica unidadDelete() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Delete,alex.lexema()); 
	  } 
  public UnidadLexica unidadRead() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Read,alex.lexema()); 
	  } 
  public UnidadLexica unidadWrite() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Write,alex.lexema()); 
	  } 
  public UnidadLexica unidadNL() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NL,alex.lexema()); 
	  } 
  public UnidadLexica unidadVar() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Var,alex.lexema()); 
	  } 
  public UnidadLexica unidadBool() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Bool,alex.lexema()); 
	  } 
  public UnidadLexica unidadInt() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Int,alex.lexema()); 
	  } 
  public UnidadLexica unidadReal() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Real,alex.lexema()); 
	  } 
  public UnidadLexica unidadString() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.String,alex.lexema()); 
	  } 
  public UnidadLexica unidadPointer() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Pointer,alex.lexema()); 
	  } 
  public UnidadLexica unidadArray() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Array,alex.lexema()); 
	  } 
  public UnidadLexica unidadOf() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Of,alex.lexema()); 
	  } 
  public UnidadLexica unidadSum() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Sum,alex.lexema()); 
	  } 
  public UnidadLexica unidadRes() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Res,alex.lexema()); 
	  } 
  public UnidadLexica unidadMul() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Mul,alex.lexema()); 
	  } 
  public UnidadLexica unidadDiv() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Div,alex.lexema()); 
	  } 
  public UnidadLexica unidadMod() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Mod,alex.lexema()); 
	  } 
  public UnidadLexica unidadAsig() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Asig,alex.lexema()); 
	  } 
  public UnidadLexica unidadMenor() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Menor,alex.lexema()); 
	  } 
  public UnidadLexica unidadMayor() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Mayor,alex.lexema()); 
	  } 
  public UnidadLexica unidadIg() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Ig,alex.lexema()); 
	  } 
  public UnidadLexica unidadMayIg() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MayIg,alex.lexema()); 
	  } 
  public UnidadLexica unidadMenIg() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MenIg,alex.lexema()); 
	  } 
  public UnidadLexica unidadDist() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Dist,alex.lexema()); 
	  } 
  public UnidadLexica unidadAnd() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.And,alex.lexema()); 
	  } 
  public UnidadLexica unidadOr() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Or,alex.lexema()); 
	  } 
  public UnidadLexica unidadNot() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Not,alex.lexema()); 
	  } 
  public UnidadLexica unidadPAp() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PAp,alex.lexema()); 
  } 
  public UnidadLexica unidadPCie() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PCie,alex.lexema()); 
  } 
  public UnidadLexica unidadCAp() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CAp,alex.lexema()); 
	  } 
  public UnidadLexica unidadCCie() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CCie,alex.lexema()); 
	  } 
  public UnidadLexica unidadLAp() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.LAp,alex.lexema()); 
	  } 
  public UnidadLexica unidadLCie() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.LCie,alex.lexema()); 
	  } 
  public UnidadLexica unidadPunto() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Punto,alex.lexema()); 
	  } 
  public UnidadLexica unidadComa() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Coma,alex.lexema()); 
  } 
  public UnidadLexica unidadAndvers() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Andvers,alex.lexema()); 
  } 
  public UnidadLexica unidadFlecha() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Flecha,alex.lexema()); 
	  } 
  public UnidadLexica unidadPunCo() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PunCo,alex.lexema()); 
	  } 
  public UnidadLexica unidadSep() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Sep,alex.lexema()); 
	  } 
  public UnidadLexica unidadEof() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.EOF,alex.lexema()); 
  }
  public void error() {
    System.err.println("***"+alex.fila()+" Caracter inexperado: "+alex.lexema());
  }
}
