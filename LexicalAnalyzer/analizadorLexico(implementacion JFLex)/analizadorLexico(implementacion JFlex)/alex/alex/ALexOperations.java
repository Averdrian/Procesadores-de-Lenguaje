package alex;

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
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.True); 
	  }
  public UnidadLexica unidadFalse() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.False); 
	  }
  public UnidadLexica unidadNull() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Null); 
	  } 
  public UnidadLexica unidadProc() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Proc); 
	  } 
  public UnidadLexica unidadIf() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.If); 
	  }
  public UnidadLexica unidadThen() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Then); 
	  }
  public UnidadLexica unidadElse() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Else); 
	  } 
  public UnidadLexica unidadEndif() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Endif); 
	  } 
  public UnidadLexica unidadWhile() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.While); 
	  } 
  public UnidadLexica unidadDo() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Do); 
	  } 
  public UnidadLexica unidadEndwhile() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Endwhile); 
	  } 
  public UnidadLexica unidadCall() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Call); 
	  } 
  public UnidadLexica unidadType() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Type); 
	  } 
  public UnidadLexica unidadRecord() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Record); 
	  } 
  public UnidadLexica unidadNew() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.New); 
	  } 
  public UnidadLexica unidadDelete() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Delete); 
	  } 
  public UnidadLexica unidadRead() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Read); 
	  } 
  public UnidadLexica unidadWrite() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Write); 
	  } 
  public UnidadLexica unidadNL() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NL); 
	  } 
  public UnidadLexica unidadVar() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Var); 
	  } 
  public UnidadLexica unidadBool() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Bool); 
	  } 
  public UnidadLexica unidadInt() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Int); 
	  } 
  public UnidadLexica unidadReal() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Real); 
	  } 
  public UnidadLexica unidadString() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.String); 
	  } 
  public UnidadLexica unidadPointer() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Pointer); 
	  } 
  public UnidadLexica unidadArray() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Array); 
	  } 
  public UnidadLexica unidadOf() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Of); 
	  } 
  public UnidadLexica unidadSum() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Sum); 
	  } 
  public UnidadLexica unidadRes() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Res); 
	  } 
  public UnidadLexica unidadMul() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Mul); 
	  } 
  public UnidadLexica unidadDiv() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Div); 
	  } 
  public UnidadLexica unidadMod() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Mod); 
	  } 
  public UnidadLexica unidadAsig() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Asig); 
	  } 
  public UnidadLexica unidadMenor() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Menor); 
	  } 
  public UnidadLexica unidadMayor() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Mayor); 
	  } 
  public UnidadLexica unidadIg() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Ig); 
	  } 
  public UnidadLexica unidadMayIg() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MayIg); 
	  } 
  public UnidadLexica unidadMenIg() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MenIg); 
	  } 
  public UnidadLexica unidadDist() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Dist); 
	  } 
  public UnidadLexica unidadAnd() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.And); 
	  } 
  public UnidadLexica unidadOr() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Or); 
	  } 
  public UnidadLexica unidadNot() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Not); 
	  } 
  public UnidadLexica unidadPAp() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PAp); 
  } 
  public UnidadLexica unidadPCie() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PCie); 
  } 
  public UnidadLexica unidadCAp() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CAp); 
	  } 
  public UnidadLexica unidadCCie() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CCie); 
	  } 
  public UnidadLexica unidadLAp() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.LAp); 
	  } 
  public UnidadLexica unidadLCie() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.LCie); 
	  } 
  public UnidadLexica unidadPunto() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Punto); 
	  } 
  public UnidadLexica unidadComa() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Coma); 
  } 
  public UnidadLexica unidadAndvers() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Andvers); 
  } 
  public UnidadLexica unidadFlecha() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Flecha); 
	  } 
  public UnidadLexica unidadPunCo() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PunCo); 
	  } 
  public UnidadLexica unidadSep() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.Sep); 
	  } 
  public UnidadLexica unidadEof() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.EOF); 
  }
  public void error() {
    System.err.println("***"+alex.fila()+" Caracter inexperado: "+alex.lexema());
  }
}
