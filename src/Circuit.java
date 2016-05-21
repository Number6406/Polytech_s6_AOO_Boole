import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jus.util.assertion.*;

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
		nouveauComposant.listeEntrees.forEach((port) -> {
			port.ajouterNumComposant(numeroComposant);
		});
		nouveauComposant.listeSorties.forEach(port -> {
			port.ajouterNumComposant(numeroComposant);
		});
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
		
		//Cette assertion ne fonctionne pas, si on veut avoir les numeros qu'on veut.
		//Cette assertion ne fonctionne pas, si on veut avoir des numeros de composants qui commencent a 1
//		if(!(numComposantSortie >= 0 && numComposantEntree >= 0 && numComposantSortie < listeOperateur.size() && numComposantEntree < listeOperateur.size())) {
//			throw new Require("ComposantsExistent");
//		}
		
		//celle ci prend en compte si les composant commence a 1 et se suivent (mais ne verifie pas qu'ils se suivent)
		if(!(numComposantSortie >= 0 && numComposantEntree >= 0 && numComposantSortie <= listeOperateur.size() && numComposantEntree <= listeOperateur.size())) {
			throw new Require("ComposantsExistent");
		}
		
		$Composant composantSortie = listeOperateur.get(numComposantSortie) ;
		$Composant composantEntree = listeOperateur.get(numComposantEntree) ;
		
		if(!(numPortEntree >= 0 && numPortEntree < composantEntree.listeEntrees.size())) throw new Require("PortEntreeExiste");
		PortEntree portEntree = composantEntree.listeEntrees.get(numPortEntree);
		
		if(!portEntree.estLibre()) throw new Require("PortEntreeLibre");
		portEntree.reserver();
		
		if(!(numPortSortie >= 0 && numPortSortie < composantSortie.listeSorties.size())) throw new Require("PortSortieExiste");
		PortSortie portSortie = composantSortie.listeSorties.get(numPortSortie);
		
		//#TODO if(!(portSortie.getEntrees().contains(portEntree))) throw new Require("ConnexionExistePas"); 
		portSortie.add(portEntree);
		
		if(portSortie.estLibre()) portSortie.reserver();
		
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
	
	public $Composant getComposant(int i)
	{
		for(Map.Entry<Integer, $Composant> entry : this.listeOperateur.entrySet())
		{
			if(entry.getKey()==i){return entry.getValue();}
		}
		return null;
	}
	
	public int nombreComposant()
	{return this.listeOperateur.size();}


}
