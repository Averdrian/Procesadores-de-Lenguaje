package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import asint.cc.AnalizadorSintacticoTinyCC;
import asint.cup.AnalizadorSintacticoTiny;


public class Main {
   public static void main(String[] args) throws Exception {
	   
	  	if(args.length < 2 || args[1] != "asc" || args[1] != "desc") {
		   	System.err.println("Error en los argumentos del programa");
		   	System.exit(1);
	   	}
	   
	   	if(args[1].equals("desc") ) {
		   try {
		   		AnalizadorSintacticoTinyCC asint = new AnalizadorSintacticoTinyCC(new FileReader(args[0]));
		   		asint.Start();
		 	   System.out.println("OK");
	   		} catch(Exception e) {
	   			System.out.println(e.getMessage());
				System.out.println("Error in lexical analyzer");
			}
	   	}
	   
	   	if(args[1].equals("asc")) { 
		   Reader input = new InputStreamReader(new FileInputStream(args[0]));
		   AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
		   AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(alex);
		   asint.parse();
		   System.out.println("OK");
	   }
	   	

   }
} 