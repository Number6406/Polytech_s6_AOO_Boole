package Circuit;
import Composant.$Composant;
import Port.PortEntree;
import Port.PortSortie;
import jus.util.assertion.Invariant;
import jus.util.assertion.Require;

public class CircuitFerme extends Circuit {
	
	/**
	 * Constructeur de la classe CircuitFerme.
	 * @param Un circuit
	 * @require c.evaluable()
	 */
	public CircuitFerme (Circuit c)
	{
		if(c.evaluable()) this.listeOperateur = c.listeOperateur;
		else throw new Require("CircuitFerme : Require Constructeur");
	}
	
	
	/**
	 * Fonction permettant d'evaluer un circuit
	 * @return true, si l'evaluation a fonctionné.
	 */
	public boolean evaluer() {
		//#TODO chercher les cas d'erreurs !
		
		//On creer deux tableaux pour avoir les nb entree de chaque composant
		int nbEntree[] = new int[listeOperateur.size()+1]; 
		for (int i = 0; i < nbEntree.length; i++) {
			nbEntree[i] = -1;
		}
		
		listeOperateur.forEach((k,v) -> {
			nbEntree[k] = v.nombreEntrees();
		});
		
		//On parcours le tableau a la recherche d'un generateur (pas d'entrï¿½e)
		int i;
		for(i=0;i<nbEntree.length;i++)
		{
			if(nbEntree[i]==0) break;
		}
		if(i==nbEntree.length) return false; //Pas de point de dï¿½part
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
	
	/**
	 * Fonction permettant de savoir si on a calcule tous les composants
	 * @param entree le tableau contenant le nombre d'entrée restante a calculer par composant
	 * @return true, si tout a été calculé. false sinon.
	 */
	private boolean toutCalcule(int[] entree)
	{
		for(int valeur : entree)
		{
			if(valeur!=-1) return false;
		}
		return true;
	}
	
	
	//#TODO Invariant : modification -> toujours evaluable
	
	@Override
	public void ajouter($Composant nouveauComposant, int numeroComposant) {
		super.ajouter(nouveauComposant, numeroComposant);
		if(_invariant()) throw new Invariant("CircuitFerme");
		
	}
	
	
	@Override
	public void connecter(int numComposantSortie, int numPortSortie, int numComposantEntree, int numPortEntree){
		super.connecter(numComposantSortie, numPortSortie, numComposantEntree, numPortEntree);
		if(_invariant()) throw new Invariant("CircuitFerme");
	}
	
	/**
	 * Invariant de la classe. Un circuit ferme doit toujours être évaluable
	 * @return vrai si le circuit est evaluable, faux sinon.
	 */
	private boolean _invariant() {
		return this.evaluable();
		
	}
	

}
