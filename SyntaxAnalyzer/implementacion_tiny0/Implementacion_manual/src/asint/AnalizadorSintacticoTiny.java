/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asint;

import alex.UnidadLexica;
import alex.AnalizadorLexicoTiny;
import alex.ClaseLexica;
import errors.GestionErroresTiny;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnalizadorSintacticoTiny {
	private UnidadLexica anticipo;
	private AnalizadorLexicoTiny alex;
	private GestionErroresTiny errores;
	public AnalizadorSintacticoTiny(Reader input) {
		errores = new GestionErroresTiny();
		try {
			alex = new AnalizadorLexicoTiny(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//alex.fijaGestionErrores(errores);
		sigToken();
	}
	public void Prog() {
		SeccionDec();
		empareja(ClaseLexica.Sep);
		SeccionInst();
	}
	private void SeccionDec() {
		switch(anticipo.clase()) {
		case Int:
		case Bool:
		case Real:
			Decs();
			break;
		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Int,ClaseLexica.Bool,ClaseLexica.Real);                                            
		}
	}
	private void Decs() {
		switch(anticipo.clase()) {
		case Int:
		case Bool:
		case Real:
			Dec();
			ReDecs();
			break;
		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Int,ClaseLexica.Bool,ClaseLexica.Real);                                       
		} 
	}
	private void Dec() {
		switch(anticipo.clase()) {
		case Int:
		case Bool:
		case Real:    
			NombreTipo();
			empareja(ClaseLexica.Variable);
			break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Int,ClaseLexica.Bool,ClaseLexica.Real);                                       
		}
	}    
	private void ReDecs() {
		switch(anticipo.clase()) {
		case PunCo:    
			empareja(ClaseLexica.PunCo);
			Dec();
			ReDecs();
			break;
		case Sep: break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.PunCo, ClaseLexica.Sep);
		}          
	}
	private void NombreTipo() {
		switch(anticipo.clase()) {
		case Int:
			empareja(ClaseLexica.Int);
			break;
		case Bool:
			empareja(ClaseLexica.Bool);
			break;
		case Real:
			empareja(ClaseLexica.Real);
			break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Int,ClaseLexica.Bool,ClaseLexica.Real);
		}          
	}
	private void SeccionInst() {
		switch(anticipo.clase()) {       
		case Variable:   
			Insts();
			break;
		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Variable);                                       
		}
	}
	private void Insts() {
		switch(anticipo.clase()) {
		case Variable:
			Inst();
			ReInsts();
			break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Variable);                                    
		}  
	}
	private void ReInsts() {
		switch(anticipo.clase()) {
		case PunCo:
			empareja(ClaseLexica.PunCo);
			Inst();
			ReInsts();
			break;
		case EOF: break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.PunCo);                                    
		}  
	}
	private void Inst() {
		switch(anticipo.clase()) {
		case Variable:
			empareja(ClaseLexica.Variable);
			empareja(ClaseLexica.Asig);
			Expr0();
			break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.Variable);                                    
		}  
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
