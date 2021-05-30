/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asint;

import ast.UnidadLexica;
import ast.AnalizadorLexicoTiny;
import ast.ClaseLexica;
import ast.GestionErroresTiny;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnalizadorSintacticoTiny {
	
	public enum TNodo {programa, un_dec, varias_dec, dec,
		tipoInt, tipoReal, tipoBool, un_inst, varias_insts,
		asig, suma, resta, and, or, menor, mayor, menor_ig,
		mayor_ig, ig, dist, mul, div, not, negativo, variable,
		True, False, entero, real};
		
		public static abstract class Exp  {
			public Exp() {
			}   
			public abstract TNodo tipo();
			public Exp arg0() {throw new UnsupportedOperationException("arg0");}
			public Exp arg1() {throw new UnsupportedOperationException("arg1");}
			public StringLocalizado variable() {throw new UnsupportedOperationException("variable"); }
			public StringLocalizado entero() {throw new UnsupportedOperationException("entero");}
			public StringLocalizado real() {throw new UnsupportedOperationException("real");}
			public StringLocalizado False() {throw new UnsupportedOperationException("false");}
			public StringLocalizado True() {throw new UnsupportedOperationException("true");}
		}
		
		public static class StringLocalizado {
			private String s;
			private int fila;
			private int col;
			public StringLocalizado(String s, int fila, int col) {
		         this.s = s;
		         this.fila = fila;
		         this.col = col;
		     }
		     public int fila() {return fila;}
		     public int col() {return col;}
		     public String toString() {
		       return s;
		     }
		     public boolean equals(Object o) {
		         return (o == this) || (
		                (o instanceof StringLocalizado) &&
		                (((StringLocalizado)o).s.equals(s)));                
		     }
		     public int hashCode() {
		         return s.hashCode();
		     }
		}
		
		private static abstract class ExpBin extends Exp {
	        private Exp arg0;
	        private Exp arg1;
	        public ExpBin(Exp arg0, Exp arg1) {
	            super();
	            this.arg0 = arg0;
	            this.arg1 = arg1;
	        }
	        public Exp arg0() {return arg0;}
	        public Exp arg1() {return arg1;}
	    }
		
		private static abstract class ExpUn extends Exp {
	        private Exp arg0;
	        public ExpUn(Exp arg0) {
	            super();
	            this.arg0 = arg0;
	        }
	        public Exp arg0() {return arg0;}
	    }
		
		public static class suma extends ExpBin {
	        public suma(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public TNodo tipo() {return TNodo.suma;}
	    }
		
		public static class resta extends ExpBin {
	        public resta(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public TNodo tipo() {return TNodo.resta;}
	    }
		
		public static class and extends ExpBin {
	        public and(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public TNodo tipo() {return TNodo.and;}
	    }
		
		public static class or extends ExpBin {
	        public or(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public TNodo tipo() {return TNodo.or;}
	    }
		
		public static class menor extends ExpBin {
	        public menor(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public TNodo tipo() {return TNodo.menor;}
	    }
		
		public static class mayor extends ExpBin {
	        public mayor(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public TNodo tipo() {return TNodo.mayor;}
	    }
		
		public static class menor_ig extends ExpBin {
	        public menor_ig(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public TNodo tipo() {return TNodo.menor_ig;}
	    }
		
		public static class mayor_ig extends ExpBin {
	        public mayor_ig(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public TNodo tipo() {return TNodo.mayor_ig;}
	    }
		
		public static class ig extends ExpBin {
	        public ig(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public TNodo tipo() {return TNodo.ig;}
	    }
		
		public static class dist extends ExpBin {
	        public dist(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public TNodo tipo() {return TNodo.dist;}
	    }
		
		public static class mul extends ExpBin {
	        public mul(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public TNodo tipo() {return TNodo.mul;}
	    }
		
		public static class div extends ExpBin {
	        public div(Exp arg0, Exp arg1) {
	            super(arg0,arg1);
	        }
	        public TNodo tipo() {return TNodo.div;}
	    }
		
		public static class not extends ExpUn {
	        public not(Exp arg0) {
	            super(arg0);
	        }
	        public TNodo tipo() {return TNodo.not;}
	    }
		
		public static class negativo extends ExpUn {
	        public negativo(Exp arg0) {
	            super(arg0);
	        }
	        public TNodo tipo() {return TNodo.negativo;}
	    }
		
		public static class variable extends Exp {
	        private StringLocalizado variable;
	        public variable(StringLocalizado variable) {
	            super();
	            this.variable = variable;
	        }
	        public TNodo tipo() {return TNodo.variable;}
	        public StringLocalizado variable() {
	            return variable;
	        }
	    }
		
		public static class True extends Exp {
	        private StringLocalizado True;
	        public True(StringLocalizado True) {
	            super();
	            this.True = True;
	        }
	        public TNodo tipo() {return TNodo.True;}
	        public StringLocalizado True() {
	            return True;
	        }
	    }
		
		public static class False extends Exp {
	        private StringLocalizado False;
	        public False(StringLocalizado False) {
	            super();
	            this.False = False;
	        }
	        public TNodo tipo() {return TNodo.False;}
	        public StringLocalizado False() {
	            return False;
	        }
	    }
		
		public static class entero extends Exp {
	        private StringLocalizado entero;
	        public entero(StringLocalizado entero) {
	            super();
	            this.entero = entero;
	        }
	        public TNodo tipo() {return TNodo.entero;}
	        public StringLocalizado entero() {
	            return entero;
	        }
	    }
		
		public static class real extends Exp {
	        private StringLocalizado real;
	        public real(StringLocalizado real) {
	            super();
	            this.real = real;
	        }
	        public TNodo tipo() {return TNodo.real;}
	        public StringLocalizado real() {
	            return real;
	        }
	    }
		
		public static class Prog {
				private Decs decs;
				private Insts insts;
		       public Prog(Decs decs, Insts insts) {
		    	   this.decs = decs;
		    	   this.insts = insts;
		       }   
		       public TNodo tipo() {return TNodo.programa;} 
		       public Decs decs() {return decs;}
		       public Insts insts() {return insts;}
		}
		
		public static abstract class Decs  {
		       public Decs() {
		       }   
		       public abstract TNodo tipo();  
		       public Decs decs() {throw new UnsupportedOperationException("decs");}
		       public Dec dec() {throw new UnsupportedOperationException("dec");}
		}
		
		public static class un_dec extends Decs  {
			private Dec dec;
		       public un_dec(Dec dec) {
		    	   super();
		    	   this.dec=dec;
		       }   
		       public TNodo tipo() {return TNodo.un_dec;}  
		       public Dec dec() {return dec;}
		}
		
		public static class varias_dec extends Decs  {
			private Dec dec;
			private Decs decs;
		       public varias_dec(Decs decs, Dec dec) {
		    	   super();
		    	   this.dec=dec;
		    	   this.decs = decs;
		       }   
		       public TNodo tipo() {return TNodo.varias_dec;}  
		       public Dec dec() {return dec;}
		       public Decs decs() {return decs;}
		}
		
		 public  class Dec  {
		        private NombreTipo tipo;
		        private StringLocalizado variable;
		        public Dec(NombreTipo tipo, StringLocalizado variable) {
		            this.tipo = tipo;
		            this.variable = variable;
		        }
		        public TNodo tipo() {return TNodo.dec;}
		        public NombreTipo nombreTipo() {return tipo;}
		        public StringLocalizado variable() {return variable;}
		    }
		 
		 public static abstract class NombreTipo {
			 	public NombreTipo() {}
			 	public abstract TNodo tipo();
			 	public StringLocalizado nombreTipo() {throw new UnsupportedOperationException("NombreTipo");}
		 }
		 
		 public static class tipoInt extends NombreTipo{
			 private StringLocalizado tipo;
			 public tipoInt(StringLocalizado tipo) {
				 this.tipo = tipo;
			 }
			 public TNodo tipo() {return TNodo.tipoInt;}
			 public StringLocalizado nombreTipo() {return tipo;}
		 }
		 
		 public static class tipoBool extends NombreTipo{
			 private StringLocalizado tipo;
			 public tipoBool(StringLocalizado tipo) {
				 this.tipo = tipo;
			 }
			 public TNodo tipo() {return TNodo.tipoBool;}
			 public StringLocalizado nombreTipo() {return tipo;}
		 }
		 
		 public static class tipoReal extends NombreTipo{
			 private StringLocalizado tipo;
			 public tipoReal(StringLocalizado tipo) {
				 this.tipo = tipo;
			 }
			 public TNodo tipo() {return TNodo.tipoReal;}
			 public StringLocalizado nombreTipo() {return tipo;}
		 }
		
		public static abstract class Insts  {
		       public Insts() {
		       }   
		       public abstract TNodo tipo();  
		       public Insts insts() {throw new UnsupportedOperationException("insts");}
		       public Inst inst() {throw new UnsupportedOperationException("inst");}
		}
		
		public static class un_inst extends Insts{
			private Inst inst;
			public un_inst(Inst inst) {
				this.inst = inst;
			}
			public TNodo tipo() {return TNodo.un_inst;}
			public Inst inst() {return inst;}
		}
		
		public static class varias_inst extends Insts{
			private Inst inst;
			private Insts insts;
			public varias_inst(Insts insts, Inst inst) {
				this.inst = inst;
				this.insts = insts;
			}
			public TNodo tipo() {return TNodo.un_inst;}
			public Inst inst() {return inst;}
			public Insts insts() {return insts;}
		}
		
		public static class Inst{
			private StringLocalizado variable;
			private Exp exp;
			public Inst(StringLocalizado variable, Exp exp) {
				this.variable = variable;
				this.exp = exp;
			}
			public TNodo tipo() {return TNodo.asig;}
			public StringLocalizado variable() {return variable;};
			public Exp exp() {return exp;}
		}
		
		
	//Constructoras
		
		
		public Prog prog(Decs decs,Insts insts) {
			return new Prog(decs,insts);
		}
		
		public Decs un_dec(Dec dec) {
			return new un_dec(dec);
		}
		
		public Decs varias_dec(Decs decs,Dec dec) {
			return new varias_dec(decs,dec);
		}
		
		public Dec dec(NombreTipo tipo, StringLocalizado variable) {
			return new Dec(tipo,variable);
		}
		
		public NombreTipo tipoInt(StringLocalizado tipo) {
			return new tipoInt(tipo);
		}
		
		public NombreTipo tipoReal(StringLocalizado tipo) {
			return new tipoReal(tipo);
		}
		
		public NombreTipo tipoBool(StringLocalizado tipo) {
			return new tipoBool(tipo);
		}
		
		public Insts un_inst(Inst inst) {
			return new un_inst(inst);
		}
		
		public Insts varias_inst(Insts insts,Inst inst) {
			return new varias_inst(insts,inst);
		}
		
		public Inst asig(StringLocalizado variable, Exp exp) {
			return new Inst(variable,exp);
		}
		
		public Exp suma(Exp exp1,Exp exp2) {
			return new suma(exp1,exp2);
		}
		
		public Exp resta(Exp exp1,Exp exp2) {
			return new resta(exp1,exp2);
		}
		
		public Exp and(Exp exp1,Exp exp2) {
			return new and(exp1,exp2);
		}
		
		public Exp or(Exp exp1,Exp exp2) {
			return new or(exp1,exp2);
		}
		
		public Exp menor(Exp exp1,Exp exp2) {
			return new menor(exp1,exp2);
		}
		
		public Exp mayor(Exp exp1,Exp exp2) {
			return new mayor(exp1,exp2);
		}
		
		public Exp menor_ig(Exp exp1,Exp exp2) {
			return new menor_ig(exp1,exp2);
		}
		
		public Exp mayor_ig(Exp exp1,Exp exp2) {
			return new mayor_ig(exp1,exp2);
		}
		
		public Exp ig(Exp exp1,Exp exp2) {
			return new ig(exp1,exp2);
		}
		
		public Exp dist(Exp exp1,Exp exp2) {
			return new dist(exp1,exp2);
		}
		
		public Exp mul(Exp exp1,Exp exp2) {
			return new mul(exp1,exp2);
		}
		
		public Exp div(Exp exp1,Exp exp2) {
			return new div(exp1,exp2);
		}
		
		public Exp not(Exp exp) {
			return new not(exp);
		}
		
		public Exp negativo(Exp exp) {
			return new negativo(exp);
		}
		
		public Exp variable(StringLocalizado variable) {
			return new variable(variable);
		}
		
		public Exp True(StringLocalizado True) {
			return new True(True);
		}
		
		public Exp False(StringLocalizado False) {
			return new False(False);
		}
		
		public Exp entero(StringLocalizado entero) {
			return new entero(entero);
		}
		
		public Exp real(StringLocalizado real) {
			return new real(real);
		}
		
		public StringLocalizado str(String s, int fila, int col) {
	        return new StringLocalizado(s,fila,col);
	    }


}
