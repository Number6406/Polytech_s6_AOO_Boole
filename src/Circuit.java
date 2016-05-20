import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Circuit implements _Circuit {

	Map<Integer,$Composant> listeOperateur;

	public String toString() {
		return "";
	}
	
	public Circuit()
	{
		listeOperateur = new HashMap<Integer,$Composant>(); //#TODO a verifier le type, mais il faut l'initialiser qqpart...
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
		for (Map.Entry<Integer, $Composant> entry : listeOperateur.entrySet()) {
		    int nbPortEntree = entry.getValue().nombreEntrees();
		    int nbPortSortie = entry.getValue().nombreSorties();
		    PortEntree pE;
		    for (int i =0; i< nbPortEntree; i++)
		    {
		    	pE = entry.getValue().listeEntrees.get(i);
		    	if (pE.estLibre()) return false;
		    }
		    PortSortie pS;
		    for (int i =0; i< nbPortSortie; i++)
		    {
		    	pS = entry.getValue().listeSorties.get(i);
		    	if (pS.estLibre()) return false;
		    }
		}
		return true;
	}

}
