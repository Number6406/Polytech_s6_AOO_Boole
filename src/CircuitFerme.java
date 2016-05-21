

import java.util.ArrayList;
import java.util.HashMap;

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
		
		//On creer deux tableaux pour avoir les nb entree et nb sortie de chaque composant
		ArrayList<Integer> nbEntree = new ArrayList<Integer>(); 
		ArrayList<Integer> nbSortie = new ArrayList<Integer>();
		
		listeOperateur.forEach((k,v) -> {
			nbEntree.add(k,v.nombreEntrees());
			nbSortie.add(k,v.nombreSorties());
		});
		
		//On parcours le tableau a la recherche d'un generateur (pas d'entrée)
		int i;
		for(i=0;i<nbEntree.size();i++)
		{
			if(nbEntree.get(i)==0) break;
		}
		if(i==nbEntree.size()) return false; //Pas de point de départ
		//On a maintenant un point de depart
		$Composant composantCourant = listeOperateur.get(i);
		composantCourant.c
		
		return true;
	}
	
	//#TODO Invariant : modification -> toujours evaluable

}
