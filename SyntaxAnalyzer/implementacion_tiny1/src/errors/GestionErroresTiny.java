package errors;

import alex.UnidadLexica;

public class GestionErroresTiny {

	public void errorLexico(int fila, String lexema) {
		System.err.println("Error lexico");
		System.err.println("Fila: " + fila + " no se esperaba \"" + lexema + "\"");
		System.exit(1);
	}
	
	public void errorSintactico(UnidadLexica unidadLexica) {
		System.err.println("Error en la sintaxis");
		System.err.println("Fila: " + unidadLexica.fila() + " Columna: " + unidadLexica.columna());
		System.exit(1);
	}

}
