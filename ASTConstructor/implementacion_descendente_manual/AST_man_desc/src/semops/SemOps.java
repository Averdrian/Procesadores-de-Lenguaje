package semops;

import asint.AnalizadorSintacticoTiny;


public class SemOps extends AnalizadorSintacticoTiny {
	public SemOps() {
		super();
	
	}

	public Exp Op(String Op, Exp a, Exp b){
		switch (Op) {
			case "and": return and(a,b);
			case "or": return or(a,b);
			case "<": return menor(a,b);
			case ">": return mayor(a,b);
			case "<=": return menor_ig(a,b);
			case ">=": return mayor_ig(a,b);
			case "==": return ig(a,b);
			case "!=": return dist(a,b);
			case "*": return mul(a,b);
			case "/": return div(a,b);
			default: throw new UnsupportedOperationException("exp" + Op);
		}
		
		
	}
}
