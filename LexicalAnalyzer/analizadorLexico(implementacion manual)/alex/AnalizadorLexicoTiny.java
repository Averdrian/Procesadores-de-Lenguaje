package alex;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;

public class AnalizadorLexicoTiny {

   private Reader input;
   private StringBuffer lex;
   private int sigCar;
   private int filaInicio;
   private int columnaInicio;
   private int filaActual;
   private int columnaActual;
   private static String NL = System.getProperty("line.separator");
   
   private static enum Estado {
    INICIO, REC_ID, REC_MUL, REC_DIV, REC_PUNCO, REC_PSEP, REC_SEP, REC_PCIE, REC_PAP,
    REC_MEN, REC_MENIG, REC_MAY, REC_MAYIG, REC_ASIG, REC_IG, REC_EXC, REC_DIST, REC_MENOS, REC_MAS,
    REC_0, REC_ENT, REC_PUNTO, REC_REAL, REC_CERO_DR, REC_EXP, REC_REAL_EXP, REC_EXP_SIGNO, REC_EXP_0, REC_EOF
   }

   private Estado estado;

   public AnalizadorLexicoTiny(Reader input) throws IOException {
    this.input = input;
    lex = new StringBuffer();
    sigCar = input.read();
    filaActual=1;
    columnaActual=1;
   }
   
   public UnidadLexica sigToken() throws IOException {
     estado = Estado.INICIO;
     filaInicio = filaActual;
     columnaInicio = columnaActual;
     lex.delete(0,lex.length());
     while(true) {
         switch(estado) { 
         case INICIO:
        	 if(hayLetra()) transita(Estado.REC_ID);
        	 else if (hayDigitoPos()) transita(Estado.REC_ENT);
             else if (hayCero()) transita(Estado.REC_0);
             else if (haySuma()) transita(Estado.REC_MAS);
             else if (hayResta()) transita(Estado.REC_MENOS);
             else if (hayMul()) transita(Estado.REC_MUL);
             else if (hayDiv()) transita(Estado.REC_DIV);
             else if (hayPAp()) transita(Estado.REC_PAP);
             else if (hayPCierre()) transita(Estado.REC_PCIE);
             else if (hayAnd()) transita(Estado.REC_PSEP);
             else if (hayMen()) transita(Estado.REC_MEN);
             else if (hayMay()) transita(Estado.REC_MAY);
             else if (hayExc()) transita(Estado.REC_EXC);
             else if (hayIgual()) transita(Estado.REC_ASIG);
             else if (hayPuntoComa()) transita(Estado.REC_PUNCO);
             else if (haySep()) transitaIgnorando(Estado.INICIO);
        	 else if(hayEOF()) transita(Estado.REC_EOF);    
        	 else error();
        	 break;
         case REC_ID:
        	 if(hayLetra() || hayDigito() || hayBarraBaj()) transita(Estado.REC_ID);
        	 else return unidadId();
        	 break;
         case REC_MUL: return unidadMul();
         case REC_DIV: return unidadDiv();
         case REC_PUNCO: return unidadPunCo();
         case REC_PSEP:
        	 if(hayAnd()) transita(Estado.REC_SEP);
        	 else error();
        	 break;
         case REC_SEP: return unidadSep();
         case REC_PCIE: return unidadPCie();
         case REC_PAP: return unidadPAp();
         case REC_MEN:
        	 if(hayIgual()) transita(Estado.REC_MENIG);
        	 else return unidadMenor();
        	 break;
         case REC_MENIG: return unidadMenIg();
         case REC_MAY:
        	 if(hayIgual()) transita(Estado.REC_MAYIG);
        	 else return unidadMayor();
        	 break;
         case REC_MAYIG: return unidadMayIg();
         case REC_ASIG:
        	 if(hayIgual()) transita(Estado.REC_IG);
        	 else return unidadAsig();
        	 break;
         case REC_IG: return unidadIg();
         case REC_EXC:
        	 if(hayIgual()) transita(Estado.REC_DIST);
        	 else error();
        	 break;
         case REC_DIST: return unidadDist();
         case REC_MENOS:
        	 if(hayCero()) transita(Estado.REC_0);
        	 else if(hayDigitoPos()) transita(Estado.REC_ENT);
        	 else return unidadRes();
        	 break;
         case REC_MAS:
        	 if(hayCero()) transita(Estado.REC_0);
        	 else if(hayDigitoPos()) transita(Estado.REC_ENT);
        	 else return unidadSum();
        	 break;
         case REC_0:
        	 if(hayExp()) transita(Estado.REC_EXP);
        	 else if(hayPunto()) transita(Estado.REC_PUNTO);
        	 else if (hayDigito()) error();
        	 else return unidadEntNum();
        	 break;
         case REC_ENT:
        	 if(hayDigito()) transita(Estado.REC_ENT);
        	 else if(hayPunto()) transita(Estado.REC_PUNTO);
        	 else if(hayExp()) transita(Estado.REC_EXP);
        	 else return unidadEntNum();
        	 break;
         case REC_PUNTO:
        	 if(hayDigito()) transita(Estado.REC_REAL);
        	 else error();
        	 break;
         case REC_REAL:
        	 if(hayDigitoPos()) transita(Estado.REC_REAL);
        	 else if(hayCero()) transita(Estado.REC_CERO_DR);
        	 else if(hayExp()) transita(Estado.REC_EXP);
        	 else return unidadRealNum();
        	 break;
         case REC_CERO_DR:
        	 if(hayDigitoPos()) transita(Estado.REC_REAL);
        	 else if(hayCero()) transita(Estado.REC_CERO_DR);
        	 else error();
        	 break;
         case REC_EXP:
        	 if(hayDigitoPos()) transita(Estado.REC_REAL_EXP);
        	 else if(hayCero()) transita(Estado.REC_EXP_0);
        	 else if(haySuma() || hayResta()) transita(Estado.REC_EXP_SIGNO);
        	 else error();
        	 break;
         case REC_EXP_SIGNO:
        	 if(hayDigitoPos()) transita(Estado.REC_REAL_EXP);
        	 else if(hayCero()) transita(Estado.REC_EXP_0);
        	 else error();
        	 break;
         case REC_REAL_EXP:
        	 if(hayDigito()) transita(Estado.REC_REAL_EXP);
        	 else return unidadRealNum();
        	 break;
         case REC_EXP_0: return unidadRealNum();
         case REC_EOF: return unidadEOF();
         }
     }    
   }

private void transita(Estado sig) throws IOException {
     lex.append((char)sigCar);
     sigCar();         
     estado = sig;
   }
   private void transitaIgnorando(Estado sig) throws IOException {
     sigCar();         
     filaInicio = filaActual;
     columnaInicio = columnaActual;
     estado = sig;
   }
   private void sigCar() throws IOException {
     sigCar = input.read();
     if (sigCar == NL.charAt(0)) saltaFinDeLinea();
     if (sigCar == '\n') {
        filaActual++;
        columnaActual=0;
     }
     else {
       columnaActual++;  
     }
   }
   private void saltaFinDeLinea() throws IOException {
      for (int i=1; i < NL.length(); i++) {
          sigCar = input.read();
          if (sigCar != NL.charAt(i)) error();
      }
      sigCar = '\n';
   }
   
   private boolean hayLetra() {return sigCar >= 'a' && sigCar <= 'z' ||
                                      sigCar >= 'A' && sigCar <= 'z';}
   private boolean hayDigitoPos() {return sigCar >= '1' && sigCar <= '9';}
   private boolean hayCero() {return sigCar == '0';}
   private boolean hayDigito() {return hayDigitoPos() || hayCero();}
   private boolean haySuma() {return sigCar == '+';}
   private boolean hayResta() {return sigCar == '-';}
   private boolean hayMul() {return sigCar == '*';}
   private boolean hayDiv() {return sigCar == '/';}
   private boolean hayPAp() {return sigCar == '(';}
   private boolean hayPuntoComa() {return sigCar == ';';}
   private boolean hayMen() {return sigCar == '<';}
   private boolean hayMay() {return sigCar == '>';}
   private boolean hayExc() {return sigCar == '!';}
   private boolean hayExp() {return sigCar == 'e' || sigCar == 'E';}
   private boolean hayPCierre() {return sigCar == ')';}
   private boolean hayIgual() {return sigCar == '=';}
   private boolean hayPunto() {return sigCar == '.';}
   private boolean hayAnd() {return sigCar == '&';}
   private boolean hayBarraBaj() {return sigCar == '_';}
   private boolean haySep() {return sigCar == ' ' || sigCar == '\t' || sigCar=='\n' || sigCar == '\r' || sigCar == '\b';}
   //private boolean hayNL() {return sigCar == '\r' || sigCar == '\b' || sigCar == '\n';}
   private boolean hayEOF() {return sigCar == -1;} 
   
   private UnidadLexica unidadId() {
     switch(lex.toString()) {
         case "true":  
            return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.True);
         case "false":    
            return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.False);
         case "bool":    
             return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.Bool);
         case "int":    
             return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.Int);
         case "real":    
             return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.Real);
         case "and":    
             return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.And);
         case "or":    
             return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.Or);
         case "not":    
             return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.Not);
         default:    
            return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.Variable,lex.toString());     
      }
   }  
   private UnidadLexica unidadEntNum() {
     return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.EntNum,lex.toString());     
   }    
   private UnidadLexica unidadRealNum() {
     return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.RealNum,lex.toString());     
   }    
   private UnidadLexica unidadSum() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.Sum);     
   }    
   private UnidadLexica unidadRes() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.Res);     
   }    
   private UnidadLexica unidadMul() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.Mul);     
   }    
   private UnidadLexica unidadDiv() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.Div);     
   }   
   private UnidadLexica unidadPunCo() {
	     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PunCo);     
	   } 
   private UnidadLexica unidadPAp() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PAp);     
   }    
   private UnidadLexica unidadPCie() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PCie);     
   } 
   private UnidadLexica unidadSep() {
	     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.Sep);     
   }
   private UnidadLexica unidadAsig() {
	     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.Asig);     
   }
   private UnidadLexica unidadMenor() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.Menor);     
   }  
   private UnidadLexica unidadMenIg() {
	     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MenIg);     
	   } 
   private UnidadLexica unidadMayor() {
	     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.Mayor);     
	   } 
   private UnidadLexica unidadMayIg() {
	     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MayIg);     
	   } 
   private UnidadLexica unidadIg() {
	     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.Ig);     
	   } 
   private UnidadLexica unidadDist() {
	     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.Dist);     
   }  
   private UnidadLexica unidadEOF() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.EOF);     
   }    
   private void error() {
     System.err.println("("+filaActual+','+columnaActual+"):Caracter inexperado");  
     System.exit(1);
   }

   public static void main(String arg[]) throws IOException {
     Reader input = new InputStreamReader(new FileInputStream("input2.txt"));
     AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
     UnidadLexica unidad;
     do {
       unidad = al.sigToken();
       System.out.println(unidad);
     }
     while (unidad.clase() != ClaseLexica.EOF);
    } 
}