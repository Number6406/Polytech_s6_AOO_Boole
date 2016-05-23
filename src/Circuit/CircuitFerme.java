package Circuit;
import Port.PortEntree;
import Port.PortSortie;
import jus.util.assertion.Require;

public class CircuitFerme extends Circuit {
	
	
	public CircuitFerme (Circuit c)
	{
		if(c.evaluable()) this.listeOperateur = c.listeOperateur;
		else throw new Require("CircuitFerme : Require Constructeur");
	}
	public boolean evaluer() {
		//#TODO Attention a l'ordre des composants !!!
		//#TODO chercher les cas d'erreurs !
		//Normalement si on est ici c'est que le circuit est evaluable
		
		//On creer deux tableaux pour avoir les nb entree de chaque composant
		int nbEntree[] = new int[listeOperateur.size()+1]; 
		for (int i = 0; i < nbEntree.length; i++) {
			nbEntree[i] = -1;
		}
		
		listeOperateur.forEach((k,v) -> {
			nbEntree[k] = v.nombreEntrees();
		});
		
		//On parcours le tableau a la recherche d'un generateur (pas d'entr�e)
		int i;
		for(i=0;i<nbEntree.length;i++)
		{
			if(nbEntree[i]==0) break;
		}
		if(i==nbEntree.length) return false; //Pas de point de d�part
		//---------------
		int indice;
		while(!toutCalcule(nbEntree))
		{
			for(indice = 0; indice<nbEntree.length;indice ++)
			{
				if(nbEntree[indice]==0)
				{
					listeOperateur.get(indice).calculer();
					
					for(PortSortie pS : listeOperateur.get(indice).getListeSortie())
					{
						for(PortEntree pE : pS.getEntrees())
						{
							nbEntree[pE.obtenirNumComposant()] =  nbEntree[pE.obtenirNumComposant()] -1;
						}
					}
					
					nbEntree[indice] =  -1;
				}
			}
		}
		
		return true;
	}
	
	private boolean toutCalcule(int[] entree)
	{
		for(int valeur : entree)
		{
			if(valeur!=-1) return false;
		}
		return true;
	}
	
	
	//#TODO Invariant : modification -> toujours evaluable
	
	

}
