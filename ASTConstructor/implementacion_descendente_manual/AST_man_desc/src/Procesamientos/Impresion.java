package Procesamientos;

import asint.AnalizadorSintacticoTiny.*;

public class Impresion {
	
	public static void imprime(Prog prog) {
	       
	       imprime_decs(prog.decs());
	       System.out.println("\n&&");
	       imprime_insts(prog.insts());
	    }
	    
	    private static void imprime_exp(Exp exp) {
	    	if (exp.tipo() == TNodo.real) {
	        	System.out.print(exp.real());
	        }
	    	else if (exp.tipo() == TNodo.entero) {
	        	System.out.print(exp.entero());
	        }
	    	else if (exp.tipo() == TNodo.variable) {
	        	System.out.print(exp.variable());
	        }
	    	else if (exp.tipo() == TNodo.True) {
	        	System.out.print(exp.True());
	        }
	    	else if (exp.tipo() == TNodo.False) {
	        	System.out.print(exp.False());
	        }
	    	
	    	else if (exp.tipo() == TNodo.suma) {
	        	imprime_exp(exp.arg0());
	            System.out.print("+");
	            imprime_exp(exp.arg1());
	        }
	        else if (exp.tipo() == TNodo.resta) {
	        	imprime_exp(exp.arg0());
	            System.out.print("-");
	            if (exp.tipo() == TNodo.suma || exp.tipo() == TNodo.resta) {
	                imprime_exp_entre_parentesis(exp.arg1());
	            }
	            else {
	                imprime_exp(exp.arg1());
	            }
	        }
	        else if (exp_1(exp)) {
	            imprime_exp(exp.arg0());
	            imprime_op(exp);
	            if (exp_1(exp.arg1())|| exp.tipo() == TNodo.suma || exp.tipo() == TNodo.resta) {
	                imprime_exp_entre_parentesis(exp.arg1());
	            }
	            else {
	                imprime_exp(exp.arg1());
	            }
	        }
	        else if (exp_2(exp)) {
	            imprime_exp(exp.arg0());
	            imprime_op(exp);
	            if (exp_2(exp.arg1()) || exp_1(exp.arg1())|| exp.tipo() == TNodo.suma || exp.tipo() == TNodo.resta) {
	                imprime_exp_entre_parentesis(exp.arg1());
	            }
	            else {
	                imprime_exp(exp.arg1());
	            }
	        }
	        else if (exp_3(exp)) {
	        	if (exp_3(exp.arg0()) || exp_2(exp.arg0()) || exp_1(exp.arg0())
	    	            || exp.tipo() == TNodo.suma || exp.tipo() == TNodo.resta) {
	    	                imprime_exp_entre_parentesis(exp.arg0());
	    	            }
	    	            else {
	    	                imprime_exp(exp.arg0());
	    	            }

	            imprime_op(exp);
	            if (exp_3(exp.arg1()) || exp_2(exp.arg1()) || exp_1(exp.arg1())
	            || exp.tipo() == TNodo.suma || exp.tipo() == TNodo.resta) {
	                imprime_exp_entre_parentesis(exp.arg1());
	            }
	            else {
	                imprime_exp(exp.arg1());
	            }
	        }
	        else if (exp.tipo() == TNodo.negativo) {
	        	System.out.print("-");
	            imprime_exp(exp.arg0());
	        }
	        else if(exp.tipo() == TNodo.not) {
	        	System.out.print("not");
	        	imprime_exp(exp.arg0());
	        }
	    }  
	    
	    private static void imprime_op(Exp exp) {
	    	switch(exp.tipo()) {
            case and: System.out.print("and"); break;
            case or: System.out.print("or"); break;
            case menor: System.out.print("<"); break;
            case mayor: System.out.print(">"); break;
            case menor_ig: System.out.print("<="); break;
            case mayor_ig: System.out.print(">="); break;
            case ig: System.out.print("=="); break;
            case dist: System.out.print("!="); break;
            case mul: System.out.print("*"); break;
            case div: System.out.print("/"); break;
         }
			
		}

		private static void imprime_exp_entre_parentesis(Exp exp) {
	        System.out.print("(");
	        imprime_exp(exp);
	        System.out.print(")");
	    }
	    
	    private static void imprime_decs(Decs decs) {
	        if (decs.tipo() == TNodo.varias_dec) {
	          imprime_dec(decs.dec());
	          System.out.println(";");
	          imprime_decs(decs.decs());    
	        }
	        else {
	          imprime_dec(decs.dec());   
	        }
	    }
	    
	    private static void imprime_insts(Insts insts) {
	    	if (insts.tipo()==TNodo.varias_insts) {
	    		imprime_inst(insts.inst());
	    		System.out.println(";");
	    		imprime_insts(insts.insts());
	    	}
	    	else {
	    		imprime_inst(insts.inst());
	    	}
	    }
	    
	    private static void imprime_inst(Inst inst) {
	    	System.out.print(inst.variable().toString());
	    	System.out.print("=");
	    	imprime_exp(inst.exp());
	    }
	    
	    private static void imprime_dec(Dec dec) {
	    imprime_tipo(dec.nombreTipo());
	       System.out.print(" "+dec.variable());  
	    }
	    
	    private static void imprime_tipo(NombreTipo tipo) {
	    	switch (tipo.tipo()) {
	    	case tipoInt:
	    		System.out.print("int");
	    		break;
	    	case tipoReal:
	    		System.out.print("real");
	    		break;
	    	case tipoBool:
	    		System.out.print("bool");
	    		break;
			default:
				System.out.print("?");
				break;
	    	}
	    }
	    
	    private static boolean exp_1(Exp exp) {
	        return exp.tipo() == TNodo.and ||
	               exp.tipo() == TNodo.or;
	    }
	    private static boolean exp_2(Exp exp) {
	        return exp.tipo() == TNodo.menor ||
	               exp.tipo() == TNodo.mayor ||
	               exp.tipo() == TNodo.menor_ig ||
	               exp.tipo() == TNodo.mayor_ig ||
	               exp.tipo() == TNodo.ig ||
	               exp.tipo() == TNodo.dist;
	    }
	    
	    private static boolean exp_3(Exp exp) {
	        return exp.tipo() == TNodo.mul ||
	               exp.tipo() == TNodo.div;
	    }
	    
	    


}
