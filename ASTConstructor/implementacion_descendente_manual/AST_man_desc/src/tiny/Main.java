package tiny;

import ast.*;
import asint.AnalizadorSintacticoTiny.*;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import Procesamientos.Impresion;

//import Procesamientos.*;

public class Main {
   public static void main(String[] args) throws Exception {
     /*if (args[0].equals("-lex")) {  
         ejecuta_lexico(args[1]);
     }*/
    // else {
         Prog prog = null;
         prog = ejecuta_descendente_manual("input0.txt"); 
         Impresion.imprime(prog);
      //   Evaluacion.evalua(prog);
     //}
   }
   
   private static void ejecuta_lexico(String in) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(in));
     AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
     GestionErroresTiny errores = new GestionErroresTiny();
     UnidadLexica t = (UnidadLexica) alex.sigToken();
     while (t.clase() != ClaseLexica.EOF) {
         System.out.println(t);
         t = (UnidadLexica) alex.sigToken();   
     }
   }
 /*  private static Prog ejecuta_ascendente(String in) throws Exception {       
     Reader input = new InputStreamReader(new FileInputStream(in));
     AnalizadorLexico alex = new AnalizadorLexico(input);
     ConstructorAST constructorast = new ConstructorAST(alex);
     return (Prog)constructorast.parse().value;
  }*/
 /*  private static Prog ejecuta_descendente(String in) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(in));
     c_ast_descendente.ConstructorAST constructorast = new c_ast_descendente.ConstructorAST(input);
     return constructorast.Init();
   }*/
   private static Prog ejecuta_descendente_manual(String in) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(in));
     ConstructorAST constructorast = new ConstructorAST(input);
     return constructorast.Prog();
   }

}   
   
