package Procesamientos;

import asint.AnalizadorSintacticoTiny.*;

public class Impresion {
	
	public static void imprime(Prog prog) {
	       
	       imprime_decs(prog.decs());
	       System.out.println("&&");
	       imprime_insts(prog.insts());
	    }
	    
	    private static void imprime_exp(Exp exp) {
	        if (exp.tipo() == TNodo.NUM) {
	            System.out.print(exp.num());
	        }
	        else if (exp.tipo() == TNodo.ID) {
	            System.out.print(exp.id());
	        }
	        else if (exp_aditiva(exp)) {
	            imprime_exp(exp.arg0());
	            imprime_op(exp);
	            if (exp_aditiva(exp.arg1())) {
	                imprime_exp_entre_parentesis(exp.arg1());
	            }
	            else {
	                imprime_exp(exp.arg1());
	            }
	        }
	        else {
	            if (exp_aditiva(exp.arg0())) {
	                imprime_exp_entre_parentesis(exp.arg0());
	            }
	            else {
	               imprime_exp(exp.arg0()); 
	            }
	            imprime_op(exp);
	            if (exp_aditiva(exp.arg1()) || exp_multiplicativa(exp.arg1())) {
	                imprime_exp_entre_parentesis(exp.arg1());
	            }
	            else {
	                imprime_exp(exp.arg1());
	            }
	        }
	    }  
	    
	    private static void imprime_exp_entre_parentesis(Exp exp) {
	        System.out.print("(");
	        imprime_exp(exp);
	        System.out.print(")");
	    }
	    
	    private static void imprime_op(Exp exp) {
	        switch(exp.tipo()) {
	            case SUMA: System.out.print("+"); break;
	            case RESTA: System.out.print("-"); break;
	            case MUL: System.out.print("*"); break;
	            case DIV: System.out.print("/"); break;
	         }
	    }
	    
	    private static boolean exp_aditiva(Exp exp) {
	        return exp.tipo() == TNodo.SUMA ||
	               exp.tipo() == TNodo.RESTA;
	    }
	    private static boolean exp_multiplicativa(Exp exp) {
	        return exp.tipo() == TNodo.MUL ||
	               exp.tipo() == TNodo.DIV;
	    }
	    
	    private static void imprime_decs(Decs decs) {
	        if (decs.tipo() == TNodo.varias_dec) {
	          imprime_decs(decs.decs());
	          System.out.println(";");
	          imprime_dec(decs.dec());    
	        }
	        else {
	          imprime_dec(decs.dec());   
	        }
	    }
	    
	    private static void imprime_insts(Insts insts) {
	    	if (insts.tipo()==TNodo.varias_insts) {
	    		imprime_insts(insts.insts());
	    		System.out.print(";");
	    		imprime_inst(insts.inst());
	    	}
	    	else {
	    		imprime_inst(insts.inst());
	    	}
	    }
	    
	    private static void imprime_inst(Inst inst) {
	    	
	    }
	    
	    private static void imprime_dec(Dec dec) {
	    	imprime_tipo(dec.nombreTipo());
	       System.out.print("="+dec.variable());  
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

}
