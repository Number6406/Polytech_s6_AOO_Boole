package Circuit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Composant.$Composant;
import Port.PortEntree;
import Port.PortSortie;
import jus.util.assertion.*;


public class Circuit implements _Circuit{

	Map<Integer,$Composant> listeOperateur;
	
	/**
	 * Constructeur de la classe circuit.
	 */
	public Circuit()
	{
		listeOperateur = new HashMap<Integer,$Composant>();
	}
	
	/** Fonction permettant d'ajouter un composant dans un circuit.
	 * @param nouveauComposant Composant � ajouter au circuit
	 * @param numeroComposant Numeros du composant � ajouter dans le circuit.
	 * @require getComposant(numeroComposant)==null && nouveauComposant!=null && numeroComposant>=1
	 */
	public void ajouter($Composant nouveauComposant, int numeroComposant) {
		if(getComposant(numeroComposant)!=null) throw new Require("Circuit : Numero composant deja pris");
		if(nouveauComposant==null) throw new Require("Circuit : Composant null");
		if(numeroComposant<1) throw new Require("Circuit : numero composant invalide");
		
		nouveauComposant.getListeEntrees().forEach((port) -> {
			port.ajouterNumComposant(numeroComposant);
		});
		nouveauComposant.getListeSortie().forEach(port -> {
			port.ajouterNumComposant(numeroComposant);
		});
		listeOperateur.put(numeroComposant, nouveauComposant);
	}

	/**
	 * Fonction permettant de connecter deux composant dans un circuit via leurs num�ros de port.
	 * @require ComposantsExistent : numComposantSortie >= 0 && numComposantEntree >= 0 && numComposantSortie < listeOperateur.size() && numComposantEntree < listeOperateur.size()
	 * @require PortEntreeExiste : numPortEntree >= 0 && numPortEntree < composantEntree.listeEntrees.size()
	 * @require PortEntreeLibre : portEntree.estLibre()
	 * @require PortSortieExiste : numPortSortie >= 0 && numPortSortie < composantSortie.listeSorties.size()
	 * @require ConnexionExistePas : portSortie.getEntrees().contains(portEntree)
	 * @param numComposantSortie Num�ro du composant dont on veut la sortie
	 * @param numPortSortie Port de sortie de numComposantSortie � connecter
	 * @param numComposantEntree Num�ro du composant en entr�e
	 * @param numPortEntree Port d'entr�e de numComposantEntree � connecter
	 */
	public void connecter(int numComposantSortie, int numPortSortie, int numComposantEntree, int numPortEntree){
		
		numPortEntree-=1;
		numPortSortie-=1;
		
		//celle ci prend en compte si les composant commence a 1 et se suivent (mais ne verifie pas qu'ils se suivent)
		if(!(numComposantSortie >= 0 && numComposantEntree >= 0 && numComposantSortie <= listeOperateur.size() && numComposantEntree <= listeOperateur.size())) {
			throw new Require("ComposantsExistent");
		}
		
		$Composant composantSortie = listeOperateur.get(numComposantSortie) ;
		$Composant composantEntree = listeOperateur.get(numComposantEntree) ;
		
		if(!(numPortEntree >= 0 && numPortEntree < composantEntree.getListeEntrees().size())) throw new Require("PortEntreeExiste");
		PortEntree portEntree = composantEntree.getListeEntrees().get(numPortEntree);
		
		if(!portEntree.estLibre()) throw new Require("PortEntreeLibre");
		portEntree.reserver();
		
		if(!(numPortSortie >= 0 && numPortSortie < composantSortie.getListeSortie().size())) throw new Require("PortSortieExiste");
		PortSortie portSortie = composantSortie.getListeSortie().get(numPortSortie);
		
		//#TODO if(!(portSortie.getEntrees().contains(portEntree))) throw new Require("ConnexionExistePas"); 
		portSortie.add(portEntree);
		
		if(portSortie.estLibre()) portSortie.reserver();
		
	}
	
	/**
	 * Fonction permettant de savoir si un circuit est evaluable
	 * @return true : le circuit est �valuable, false : le circuit n'est pas �valuable.
	 */
	public boolean evaluable()
	{
		for (Map.Entry<Integer, $Composant> entry : listeOperateur.entrySet()) {
		    int nbPortEntree = entry.getValue().nombreEntrees();
		    int nbPortSortie = entry.getValue().nombreSorties();
		    PortEntree pE;
		    for (int i =0; i< nbPortEntree; i++)
		    {
		    	pE = entry.getValue().getListeEntrees().get(i);
		    	if (pE.estLibre()) return false;
		    }
		    PortSortie pS;
		    for (int i =0; i< nbPortSortie; i++)
		    {
		    	pS = entry.getValue().getListeSortie().get(i);
		    	if (pS.estLibre()) return false;
		    }
		}
		return true;
	}
	
	/**
	 * Fonction permettant de r�cuperer un composant � partir de son num�ro dans un circuit.
	 * @return le composant numero i, null si aucun composant porte se numero.
	 */
	public $Composant getComposant(int i)
	{
		for(Map.Entry<Integer, $Composant> entry : this.listeOperateur.entrySet())
		{
			if(entry.getKey()==i){return entry.getValue();}
		}
		return null;
	}
	
	/**
	 * Fonction permettant de savoir combien de composants composent le circuit
	 * @return le nombre de composant dans le circuit.
	 */
	public int nombreComposant()
	{return this.listeOperateur.size();}

	
	public String toString() 
	{
		String listeCompo, debutCiruit;
		debutCiruit = "Nom_Inconnu[";
		listeCompo = "";
		for(Map.Entry<Integer,$Composant> entry : this.listeOperateur.entrySet())
		{
			listeCompo = listeCompo + "<"+entry.getKey()+"|"+entry.getValue().toString()+">\n";
		}
		return debutCiruit+"\n"+listeCompo+"]";
	}
	
	/**
	 * toString avec un paramètre (le nom du circuit)
	 * @param nom, le nom du circuit
	 * @return la description textuelle du circuit
	 */
	public String toString(String nom) 
	{
		String listeCompo, debutCiruit;
		debutCiruit = nom+"[";
		listeCompo = "";
		for(Map.Entry<Integer,$Composant> entry : this.listeOperateur.entrySet())
		{
			listeCompo = listeCompo + "<"+entry.getKey()+"|"+entry.getValue().toString()+">\n";
		}
		return debutCiruit+"\n"+listeCompo+"]";
	}
}
