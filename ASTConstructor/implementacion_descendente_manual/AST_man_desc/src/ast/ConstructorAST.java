package ast;

import java.io.IOException;
import java.io.Reader;

import ast.AnalizadorLexicoTiny;
import ast.ClaseLexica;
import ast.GestionErroresTiny;
import semops.SemOps;
import ast.UnidadLexica;
import asint.AnalizadorSintacticoTiny;
import asint.AnalizadorSintacticoTiny.*;

public class ConstructorAST {
	private UnidadLexica anticipo;
	private AnalizadorLexicoTiny alex;
	private GestionErroresTiny errores;
	private SemOps sem;
	
	public ConstructorAST(Reader input) {
		errores = new GestionErroresTiny();
		try {
			alex = new AnalizadorLexicoTiny(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//alex.fijaGestionErrores(errores);
		sigToken();
		sem = new SemOps();
	}
	public Prog Prog() {
		Decs decs = SeccionDec();
		empareja(ClaseLexica.Sep);
		Insts insts = SeccionInst();
		return sem.prog(decs, insts);
	}
	private Decs SeccionDec() {
		switch(anticipo.clase()) {
		case Int:
		case Bool:
		case Real:
			Decs decs = Decs();
			return decs;
		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Int,ClaseLexica.Bool,ClaseLexica.Real);                                            
		}
		return null;
		
	}
	private Decs Decs() {
		switch(anticipo.clase()) {
		case Int: 
		case Bool:
		case Real:
			Dec dec = Dec();
			Decs redecs = ReDecs();
			if(redecs == null) return sem.un_dec(dec);
			return sem.varias_dec(redecs, dec);

		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Int,ClaseLexica.Bool,ClaseLexica.Real);                                       
		} 
		return null;
	}
	private Dec Dec() {
		switch(anticipo.clase()) {
		case Int:
		case Bool:
		case Real: 
			NombreTipo nomb = NombreTipo();
			UnidadLexica anti = anticipo;
			empareja(ClaseLexica.Variable);
			return sem.dec(nomb, sem.str(anti.lexema(), anti.fila(), anti.columna()));
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Int,ClaseLexica.Bool,ClaseLexica.Real);                                       
		}
		return null;
	}    
	private Decs ReDecs() {
		switch(anticipo.clase()) {
		case PunCo:    
			empareja(ClaseLexica.PunCo);
			Dec dec = Dec();
			Decs redecs = ReDecs();
			if(redecs == null) return sem.un_dec(dec);
			return sem.varias_dec(redecs, dec);
		case Sep: break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.PunCo, ClaseLexica.Sep);
		}
		return null;          
	}
	private NombreTipo NombreTipo() {
		switch(anticipo.clase()) {
		case Int:
			empareja(ClaseLexica.Int);
			return sem.tipoInt(sem.str(anticipo.lexema(), anticipo.fila(), anticipo.columna()));
		case Bool:
			empareja(ClaseLexica.Bool);
			return sem.tipoBool(sem.str(anticipo.lexema(), anticipo.fila(), anticipo.columna()));
		case Real:
			empareja(ClaseLexica.Real);
			return sem.tipoReal(sem.str(anticipo.lexema(), anticipo.fila(), anticipo.columna()));
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Int,ClaseLexica.Bool,ClaseLexica.Real);
		}
		return null;
	}
	private Insts SeccionInst() {
		switch(anticipo.clase()) {       
		case Variable:   
			Insts ins = Insts();
			return ins;
		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Variable);                                       
		}
		return null;
	}
	private Insts Insts() {
		switch(anticipo.clase()) {
		case Variable:
			Inst inst = Inst();
			Insts insts = ReInsts();
			if(insts == null) return sem.un_inst(inst);
			return sem.varias_inst(insts, inst);
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Variable);                                    
		}  
		return null;
	}
	private Insts ReInsts() {
		switch(anticipo.clase()) {
		case PunCo:
			empareja(ClaseLexica.PunCo);
			Inst in = Inst();
			Insts rein = ReInsts();
			if(rein == null) return sem.un_inst(in);
			return sem.varias_inst(rein, in);
		case EOF: break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.PunCo);                                    
		} 
		return null;
	}
	private Inst Inst() {
		switch(anticipo.clase()) {
		case Variable:
			UnidadLexica variable = anticipo;
			empareja(ClaseLexica.Variable);
			empareja(ClaseLexica.Asig);
			Exp ex = Expr0();
			return sem.asig(sem.str(variable.lexema(), variable.fila(), variable.columna()), ex);
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Variable);                                    
		}  
		return null;
	}
	private Exp Expr0() {
		switch(anticipo.clase()) {
		case Not: case Res: case PAp: case Variable: case True: case False: case EntNum:
		case RealNum:
			Exp exp1 = Expr1();
			Exp rexp = ReExpr0(exp1);
			return rexp;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.Not,ClaseLexica.Res,ClaseLexica.PAp,ClaseLexica.Variable,
					ClaseLexica.True,ClaseLexica.False, ClaseLexica.EntNum,ClaseLexica.RealNum);                                              
		} 
		return null;
	}
	private Exp ReExpr0(Exp exphe) {
		switch(anticipo.clase()) {
		case Sum:
			empareja(ClaseLexica.Sum);
			Exp ex0 = Expr0();
			return sem.suma(exphe, ex0);
		case Res:
			empareja(ClaseLexica.Res);
			Exp ex1 = Expr1();
			return sem.resta(exphe, ex1);
		case EOF: case PCie: case PunCo:
			return exphe;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Res,ClaseLexica.Sum);  
		}
		return null;
		
	}
	private Exp Expr1() {
		switch(anticipo.clase()) {
		case Not: case Res: case PAp: case Variable: case True: case False: case EntNum:
		case RealNum:
			Exp exp2 = Expr2();
			Exp rex1 = ReExpr1(exp2);
			return rex1;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.Not,ClaseLexica.Res,ClaseLexica.PAp,ClaseLexica.Variable,
					ClaseLexica.True,ClaseLexica.False, ClaseLexica.EntNum,ClaseLexica.RealNum);                                              
		} 
		return null;
	}
	private Exp ReExpr1(Exp exhe) {
		switch(anticipo.clase()) {
		case And: case Or:
			String op = Op1();
			Exp ex2 = Expr2();
			Exp rex = ReExpr1(ex2);
			return sem.Op(op, rex, ex2);
		case EOF: case PCie: case Sum: case Res: case PunCo:
			return exhe;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Res,ClaseLexica.Sum,ClaseLexica.And,ClaseLexica.Or);                                    
		}  
		return null;
	}
	private Exp Expr2() {
		switch(anticipo.clase()) {
		case Not: case Res: case PAp: case Variable: case True: case False: case EntNum:
		case RealNum:
			Exp ex3 = Expr3();
			Exp rex2 = ReExpr2(ex3);
			return rex2;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.Not,ClaseLexica.Res,ClaseLexica.PAp,ClaseLexica.Variable,
					ClaseLexica.True,ClaseLexica.False, ClaseLexica.EntNum,ClaseLexica.RealNum);                                              
		} 
		return null;
	}
	private Exp ReExpr2(Exp exhe) {
		switch(anticipo.clase()) {
		case Menor: case Mayor: case MenIg: case MayIg: case Ig: case Dist:
			String op = Op2();
			Exp ex3 = Expr3();
			Exp rex2 = ReExpr2(ex3);
			return sem.Op(op, exhe, ex3);
		case EOF: case PCie: case Sum: case Res: case PunCo: case And: case Or: 
			return exhe;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Res,ClaseLexica.Sum,ClaseLexica.And,ClaseLexica.Or,
				ClaseLexica.Menor,ClaseLexica.Mayor, ClaseLexica.MayIg, ClaseLexica.MenIg,
				ClaseLexica.Ig,ClaseLexica.Dist);                                    
		}  
		return null;
	}
	private Exp Expr3() {
		switch(anticipo.clase()) {
		case Not: case Res: case PAp: case Variable: case True: case False: case EntNum:
		case RealNum:
			Exp exp4 = Expr4();
			Exp rexp3 = ReExpr3(exp4);
			return rexp3;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.Not,ClaseLexica.Res,ClaseLexica.PAp,ClaseLexica.Variable,
					ClaseLexica.True,ClaseLexica.False, ClaseLexica.EntNum,ClaseLexica.RealNum);                                              
		} 
		return null;
	}
	private Exp ReExpr3(Exp exhe) {
		switch(anticipo.clase()) {
		case Mul: case Div:
			String op = Op3();
			Exp exp4 = Expr4();
			return sem.Op(op, exhe, exp4);
		case EOF: case PCie: case Sum: case Res: case PunCo: case And: case Or:
			return exhe;
		case Menor: case Mayor: case MenIg: case MayIg: case Ig: case Dist: break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Res,ClaseLexica.Sum,ClaseLexica.And,ClaseLexica.Or,
				ClaseLexica.Menor,ClaseLexica.Mayor, ClaseLexica.MayIg, ClaseLexica.MenIg,
				ClaseLexica.Ig,ClaseLexica.Dist,ClaseLexica.Mul,ClaseLexica.Div);                                    
		}
		return null;  
	}
	private Exp Expr4() {
		switch(anticipo.clase()) {
		case Not:
			empareja(ClaseLexica.Not);
			Exp ex4 = Expr4();
			return sem.not(ex4);
		case Res:
			empareja(ClaseLexica.Res);
			Exp ex5 = Expr5();
			return sem.negativo(ex5);
		case PAp: case Variable: case True: case False: case EntNum: case RealNum:
			Exp exp5 = Expr5();
			return exp5;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.Not,ClaseLexica.Res,ClaseLexica.PAp,ClaseLexica.Variable,
					ClaseLexica.True,ClaseLexica.False, ClaseLexica.EntNum,ClaseLexica.RealNum);                                              
		} 
		return null;
	}
	private Exp Expr5() {
		switch(anticipo.clase()) {
		case PAp:
			empareja(ClaseLexica.PAp);
			Exp exp0 = Expr0();
			empareja(ClaseLexica.PCie);
			return exp0;
		case Variable:
			UnidadLexica antiVar = anticipo;
			empareja(ClaseLexica.Variable);
			return sem.variable(sem.str(antiVar.lexema(), antiVar.fila(), antiVar.columna()));
		case True:
			UnidadLexica anticTrue = anticipo;
			empareja(ClaseLexica.True);
			return sem.True(sem.str(anticTrue.lexema(), anticTrue.fila(), anticTrue.columna()));
		case False:
			UnidadLexica anticFalse = anticipo;
			empareja(ClaseLexica.False);
			return sem.False(sem.str(anticFalse.lexema(), anticFalse.fila(), anticFalse.columna()));
		case EntNum:
			UnidadLexica anticEnt = anticipo;
			empareja(ClaseLexica.EntNum);
			return sem.entero(sem.str(anticEnt.lexema(), anticEnt.fila(), anticEnt.columna()));
		case RealNum:
			UnidadLexica anticReal = anticipo;
			empareja(ClaseLexica.RealNum);
			return sem.real(sem.str(anticReal.lexema(), anticReal.fila(), anticReal.columna()));
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.PAp,ClaseLexica.Variable,ClaseLexica.True,
					ClaseLexica.False, ClaseLexica.EntNum,ClaseLexica.RealNum);                                              
		}
		return null; 
	}
	private String Op1() {
		switch(anticipo.clase()) {
		case And: empareja(ClaseLexica.And); return "and";  
		case Or: empareja(ClaseLexica.Or); return "or";  
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.And,ClaseLexica.Or);
		}
		return null;  
	}
	private String Op2() {
		switch(anticipo.clase()) {
		case Menor: empareja(ClaseLexica.Menor); return "<"; 
		case Mayor: empareja(ClaseLexica.Mayor); return ">";
		case MenIg: empareja(ClaseLexica.MenIg); return "<=";
		case MayIg: empareja(ClaseLexica.MayIg); return ">=";
		case Ig: empareja(ClaseLexica.Ig); return "==";
		case Dist: empareja(ClaseLexica.Dist); return "!=";
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.Menor,ClaseLexica.Mayor,ClaseLexica.MenIg,
					ClaseLexica.MayIg,ClaseLexica.Ig,ClaseLexica.Dist);
		}
		return null;
	}
	private String Op3() {
		switch(anticipo.clase()) {
		case Mul: empareja(ClaseLexica.Mul); return "*";  
		case Div: empareja(ClaseLexica.Div); return "/";  
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.Mul,ClaseLexica.Div);
		}  
		return null;
	}

	private void empareja(ClaseLexica claseEsperada) {
		if (anticipo.clase() == claseEsperada)
			sigToken();
		else errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),claseEsperada);
	}
	private void sigToken() {
		try {
			anticipo = alex.sigToken();
		}
		catch(IOException e) {
			errores.errorFatal(e);
		}
	}

}
