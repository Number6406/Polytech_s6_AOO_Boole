package Composant;
import java.util.ArrayList;

import Port.PortEntree;
import Port.PortSortie;
import jus.util.assertion.Ensure;
import jus.util.assertion.Invariant;
import jus.util.assertion.Require;

/**
 * @author alicia
 * @invariant NONcomplet : this.nombreEntrees() == 1 && this.nombreSorties()==1
 */
public class Non extends $Transformateur {
	
	/**
	 * Constructeur de la classe NON.
	 * Remplit la liste d'entrée avec 1 ports non connectés
	 * Remplit la liste de sortie avec 1 port de sortie connecté à une liste de ports d'entrée vide
	 * @Ensure NONcomplet : this.nombreEntrees() == 1 && this.nombreSorties()==1
	 */
	public Non(){
		this.nomType = "NON";
		this.listeEntrees = new ArrayList<PortEntree>();
		this.listeSorties = new ArrayList<PortSortie>();
		
		this.listeEntrees.add(0, new PortEntree(1));
		this.listeSorties.add(0, new PortSortie(1));
		_invariant();
	}
	
	/**
	 * Mets à jour les ports de sortie du composant, ainsi que les ports d'entrées auxquels ce dernier est connecté
	 * @require PortsConnectes : !(listeEntrees.get(0).estLibre())
	 * @ensure EntreesRemplies : forall(PortEntree portEntree : listeSorties.get("premier port de sortie")) !(portEntree.estLibre())
	 */
	public void calculer() throws Require{
		// REQUIRE
		if((listeEntrees.get(0).estLibre())){
			throw new Require("Non : PortsEntree non Connectes");
		}
		
		boolean res;
		// Le booleen prends la valeur inverse du port1 
		res = !(this.listeEntrees.get(0).obtenirValeur());
		this.listeSorties.get(0).majValeur(res);
		
		listeSorties.get(0).getEntrees().forEach(portEntree -> {
			portEntree.majValeur(res);
		});
		
		// ENSURE
		boolean ensure = true;
		for (PortEntree portEntree : listeSorties.get(0).getEntrees()) {
			ensure = ensure && !(portEntree.estLibre());
		}
		if(!(ensure)){ throw new Ensure("EntreesRemplies");}
		
		_invariant();
	}
	
	void _invariant() throws Invariant{
		if(!( this.nombreEntrees() == 1 && this.nombreSorties() == 1)){
			throw new Invariant("NON : invariant");
		}
	}
}