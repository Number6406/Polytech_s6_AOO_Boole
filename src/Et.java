import java.util.ArrayList;
import java.util.TreeMap;

import jus.util.assertion.Ensure;
import jus.util.assertion.Invariant;
import jus.util.assertion.Require;

/**
 * @author alicia
 * @invariant ETcomplet : this.nombreEntrees() ==2 && this.nombreSorties()==1
 */
public class Et extends $Transformateur {
	
	/**
	 * Constructeur de la classe ET.
	 * Remplit la liste d'entrée avec 2 ports non connectés
	 * Remplit la liste de sortie avec un port de sortie connecté à une liste de ports d'entrée vide
	 * @Ensure ETcomplet : this.nombreEntrees() == 2 && this.nombreSorties()== 1
	 */
	public Et() throws Invariant{
		this.nomType = "ET";
		this.listeEntrees = new ArrayList<PortEntree>();
		this.listeSorties = new ArrayList<PortSortie>();
		
		this.listeEntrees.add(new PortEntree());
		this.listeEntrees.add(new PortEntree());
		this.listeSorties.add(new PortSortie());
		_invariant();
	}
	
	/**
	 * Mets à jour les ports de sortie du composant, ainsi que les ports d'entrées auxquels ce dernier est connecté
	 * @require PortsConnectes : !(listeEntrees.get(1).estLibre()||listeEntrees.get(2).estLibre())
	 * @ensure EntreesRemplies : forall(PortEntree portEntree : listeSorties.get("premier port de sortie")) !(portEntree.estLibre())
	 */
	void calculer() throws Require{
		// REQUIRE
		if(!(!(listeEntrees.get(1).estLibre()||listeEntrees.get(2).estLibre()))){
			throw new Require("PortsConnectes");
		}
		
		boolean res, ensure;
		ArrayList<PortEntree> destinations, entrees;
		ArrayList<PortSortie> sorties;
		// On caste les listes dans le format choisit
		entrees = ((ArrayList<PortEntree>)listeEntrees);
		sorties = ((ArrayList<PortSortie>)listeSorties);
		
		// Le booleen prends la valeur du port1 & port2
		res = entrees.get(1).obtenirValeur() && entrees.get(2).obtenirValeur(); 
		
		sorties.get(1).majValeur(res);
		destinations = sorties.get(1).getEntrees();
		for (PortEntree portEntree : destinations) {
			portEntree.reserver();
			portEntree.majValeur(res);
		}
	
		// ENSURE
		ensure = true;
		for (PortEntree portEntree : destinations) {
			ensure = ensure && !(portEntree.estLibre());
		}
		if(!(ensure)){ throw new Ensure("EntreesRemplies");}
		
		_invariant();
	}
	
	void _invariant() throws Invariant{
		if(!( this.nombreEntrees() == 2 && this.nombreSorties() == 1)){
			throw new Invariant("ETcomplet");
		}
	}
}
