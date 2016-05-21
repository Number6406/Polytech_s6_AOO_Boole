import java.util.List;
import java.util.Map;

import jus.util.assertion.*;

public class Circuit implements _Circuit {

	Map<Integer,$Composant> listeOperateur;

	public String toString() {
		return "";
	}

	/**
	 * #TODO Javadoc + test si numero composant deja pris
	 * @param nouveauComposant
	 * @param numeroComposant
	 */
	public void ajouter($Composant nouveauComposant, int numeroComposant) {
		listeOperateur.put(numeroComposant, nouveauComposant);
	}

	/**
	 * @require ComposantsExistent : numComposantSortie >= 0 && numComposantEntree >= 0 && numComposantSortie < listeOperateur.size() && numComposantEntree < listeOperateur.size()
	 * @require PortEntreeExiste : numPortEntree >= 0 && numPortEntree < composantEntree.listeEntrees.size()
	 * @require PortEntreeLibre : portEntree.estLibre()
	 * @require PortSortieExiste : numPortSortie >= 0 && numPortSortie < composantSortie.listeSorties.size()
	 * @require ConnexionExistePas : portSortie.getEntrees().contains(portEntree)
	 * @param numComposantSortie
	 * @param numPortSortie
	 * @param numComposantEntree
	 * @param numPortEntree
	 */
	public void connecter(int numComposantSortie, int numPortSortie, int numComposantEntree, int numPortEntree){
		
		if(!(numComposantSortie >= 0 && numComposantEntree >= 0 && numComposantSortie < listeOperateur.size() && numComposantEntree < listeOperateur.size())) {
			throw new Require("ComposantsExistent");
		}
		
		$Composant composantSortie = listeOperateur.get(numComposantSortie) ;
		$Composant composantEntree = listeOperateur.get(numComposantEntree) ;
		
		if(!(numPortEntree >= 0 && numPortEntree < composantEntree.listeEntrees.size())) throw new Require("PortEntreeExiste");
		PortEntree portEntree = composantEntree.listeEntrees.get(numComposantEntree);
		
		if(!portEntree.estLibre()) throw new Require("PortEntreeLibre");
		portEntree.reserver();
		
		if(!(numPortSortie >= 0 && numPortSortie < composantSortie.listeSorties.size())) throw new Require("PortSortieExiste");
		PortSortie portSortie = composantSortie.listeSorties.get(numPortSortie);
		
		if(!(portSortie.getEntrees().contains(portEntree))) throw new Require("ConnexionExistePas"); 
		portSortie.add(portEntree);
		
	}
	
	public boolean evaluable()
	{
		//this.listeOperateur.forEach((k,v)-> if(v.));
		return false;
	}

}
