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
			empareja(ClaseLexica.Variable);
			return sem.dec(nomb, sem.str(anticipo.lexema(), anticipo.fila(), anticipo.columna()));
			break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Int,ClaseLexica.Bool,ClaseLexica.Real);                                       
		}
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
			empareja(ClaseLexica.Variable);
			empareja(ClaseLexica.Asig);
			Exp ex = Expr0();
			return sem.asig(sem.str(nomb, sem.str(anticipo.lexema(), anticipo.fila(), anticipo.columna())), ex);
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Variable);                                    
		}  
		return null;
	}
	private void Expr0() {
		switch(anticipo.clase()) {
		case Not: case Res: case PAp: case Variable: case True: case False: case EntNum:
		case RealNum:
			Expr1();
			ReExpr0();
			break;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.Not,ClaseLexica.Res,ClaseLexica.PAp,ClaseLexica.Variable,
					ClaseLexica.True,ClaseLexica.False, ClaseLexica.EntNum,ClaseLexica.RealNum);                                              
		} 
	}
	private void ReExpr0() {
		switch(anticipo.clase()) {
		case Sum:
			empareja(ClaseLexica.Sum);
			Expr0();
			break;
		case Res:
			empareja(ClaseLexica.Res);
			Expr1();
			break;
		case EOF: case PCie: case PunCo: break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Res,ClaseLexica.Sum);                                    
		}  
	}
	private void Expr1() {
		switch(anticipo.clase()) {
		case Not: case Res: case PAp: case Variable: case True: case False: case EntNum:
		case RealNum:
			Expr2();
			ReExpr1();
			break;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.Not,ClaseLexica.Res,ClaseLexica.PAp,ClaseLexica.Variable,
					ClaseLexica.True,ClaseLexica.False, ClaseLexica.EntNum,ClaseLexica.RealNum);                                              
		} 
	}
	private void ReExpr1() {
		switch(anticipo.clase()) {
		case And: case Or:
			Op1();
			Expr2();
			ReExpr1();
			break;
		case EOF: case PCie: case Sum: case Res: case PunCo: break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Res,ClaseLexica.Sum,ClaseLexica.And,ClaseLexica.Or);                                    
		}  
	}
	private void Expr2() {
		switch(anticipo.clase()) {
		case Not: case Res: case PAp: case Variable: case True: case False: case EntNum:
		case RealNum:
			Expr3();
			ReExpr2();
			break;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.Not,ClaseLexica.Res,ClaseLexica.PAp,ClaseLexica.Variable,
					ClaseLexica.True,ClaseLexica.False, ClaseLexica.EntNum,ClaseLexica.RealNum);                                              
		} 
	}
	private void ReExpr2() {
		switch(anticipo.clase()) {
		case Menor: case Mayor: case MenIg: case MayIg: case Ig: case Dist:
			Op2();
			Expr3();
			ReExpr2();
			break;
		case EOF: case PCie: case Sum: case Res: case PunCo: case And: case Or: break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Res,ClaseLexica.Sum,ClaseLexica.And,ClaseLexica.Or,
				ClaseLexica.Menor,ClaseLexica.Mayor, ClaseLexica.MayIg, ClaseLexica.MenIg,
				ClaseLexica.Ig,ClaseLexica.Dist);                                    
		}  
	}
	private void Expr3() {
		switch(anticipo.clase()) {
		case Not: case Res: case PAp: case Variable: case True: case False: case EntNum:
		case RealNum:
			Expr4();
			ReExpr3();
			break;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.Not,ClaseLexica.Res,ClaseLexica.PAp,ClaseLexica.Variable,
					ClaseLexica.True,ClaseLexica.False, ClaseLexica.EntNum,ClaseLexica.RealNum);                                              
		} 
	}
	private void ReExpr3() {
		switch(anticipo.clase()) {
		case Mul: case Div:
			Op3();
			Expr4();
			break;
		case EOF: case PCie: case Sum: case Res: case PunCo: case And: case Or:
		case Menor: case Mayor: case MenIg: case MayIg: case Ig: case Dist: break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Res,ClaseLexica.Sum,ClaseLexica.And,ClaseLexica.Or,
				ClaseLexica.Menor,ClaseLexica.Mayor, ClaseLexica.MayIg, ClaseLexica.MenIg,
				ClaseLexica.Ig,ClaseLexica.Dist,ClaseLexica.Mul,ClaseLexica.Div);                                    
		}  
	}
	private void Expr4() {
		switch(anticipo.clase()) {
		case Not:
			empareja(ClaseLexica.Not);
			Expr4();
			break;
		case Res:
			empareja(ClaseLexica.Res);
			Expr5();
			break;
		case PAp: case Variable: case True: case False: case EntNum: case RealNum:
			Expr5();
			break;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.Not,ClaseLexica.Res,ClaseLexica.PAp,ClaseLexica.Variable,
					ClaseLexica.True,ClaseLexica.False, ClaseLexica.EntNum,ClaseLexica.RealNum);                                              
		} 
	}
	private void Expr5() {
		switch(anticipo.clase()) {
		case PAp:
			empareja(ClaseLexica.PAp);
			Expr0();
			empareja(ClaseLexica.PCie);
			break;
		case Variable:
			empareja(ClaseLexica.Variable);
			break;
		case True:
			empareja(ClaseLexica.True);
			break;
		case False:
			empareja(ClaseLexica.False);
			break;
		case EntNum:
			empareja(ClaseLexica.EntNum);
			break;
		case RealNum:
			empareja(ClaseLexica.RealNum);
			break;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.PAp,ClaseLexica.Variable,ClaseLexica.True,
					ClaseLexica.False, ClaseLexica.EntNum,ClaseLexica.RealNum);                                              
		} 
	}
	private void Op1() {
		switch(anticipo.clase()) {
		case And: empareja(ClaseLexica.And); break;  
		case Or: empareja(ClaseLexica.Or); break;  
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.And,ClaseLexica.Or);
		}  
	}
	private void Op2() {
		switch(anticipo.clase()) {
		case Menor: empareja(ClaseLexica.Menor); break;  
		case Mayor: empareja(ClaseLexica.Mayor); break;
		case MenIg: empareja(ClaseLexica.MenIg); break;
		case MayIg: empareja(ClaseLexica.MayIg); break;
		case Ig: empareja(ClaseLexica.Ig); break;
		case Dist: empareja(ClaseLexica.Dist); break;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.Menor,ClaseLexica.Mayor,ClaseLexica.MenIg,
					ClaseLexica.MayIg,ClaseLexica.Ig,ClaseLexica.Dist);
		}  
	}
	private void Op3() {
		switch(anticipo.clase()) {
		case Mul: empareja(ClaseLexica.Mul); break;  
		case Div: empareja(ClaseLexica.Div); break;  
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.Mul,ClaseLexica.Div);
		}  
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
