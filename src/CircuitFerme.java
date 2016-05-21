

import jus.util.assertion.Require;

public class CircuitFerme extends Circuit {
	
	
	public CircuitFerme (Circuit c)
	{
		if(c.evaluable()) this.listeOperateur = c.listeOperateur;
		else throw new Require("CircuitFerme : Require Constructeur");
	}
	public boolean evaluer() {
		//#TODO Attention a l'ordre des composants !!!
		//Normalement si on est ici c'est que le circuit est evaluable
		
		return false;
	}
	
	//#TODO Invariant : modification -> toujours evaluable

}
