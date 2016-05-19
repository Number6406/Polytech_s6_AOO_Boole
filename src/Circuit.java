import java.util.List;
import java.util.Map;

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

	public void connecter(int numComposantSortie, int numPortSortie, int numComposantEntree, int numPortEntree){
		$Composant composantSortie = listeOperateur.get(numComposantSortie) ;
		$Composant composantEntree = listeOperateur.get(numComposantEntree) ;
		
		//#TODO verifier les types
		PortEntree portEntree = composantEntree.listeEntrees.get(composantEntree);
		if(!portEntree.estLibre())
		{
			//TODO Erreur, un portEntree n'a qu'une seule entree
		}
		else
		{
			portEntree.reserver();
		}
		PortSortie portSortie = composantSortie.listeSorties.get(numPortSortie);
		
		portSortie.add(portEntree);
		
	}
	
	public boolean evaluable()
	{
		//this.listeOperateur.forEach((k,v)-> if(v.));
		return false;
	}

}
