package Composant;
import java.util.ArrayList;
import java.util.List;

import Port.PortEntree;
import Port.PortSortie;
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
		
		this.listeEntrees.add(0,new PortEntree(1));
		this.listeEntrees.add(1,new PortEntree(2));
		this.listeSorties.add(0,new PortSortie(1));
		_invariant();
	}
	
	/**
	 * Mets à jour les ports de sortie du composant, ainsi que les ports d'entrées auxquels ce dernier est connecté
	 * @require PortsConnectes : !(listeEntrees.get(1).estLibre()||listeEntrees.get(2).estLibre())
	 * @ensure EntreesRemplies : forall(PortEntree portEntree : listeSorties.get("premier port de sortie")) !(portEntree.estLibre())
	 */
	public void calculer() throws Require{
		// REQUIRE
		if((listeEntrees.get(0).estLibre()||listeEntrees.get(1).estLibre())){
			throw new Require(" ET : Ports non Connectes");
		}
		
		boolean res;
		// Le booleen prends la valeur port1&&port2
		res = (this.listeEntrees.get(0).obtenirValeur())&&(this.listeEntrees.get(1).obtenirValeur());
		
		//Mise a jour du port de sortie
		this.listeSorties.get(0).majValeur(res);
		//Mise a jour de la valeur des ports connecte aux ports de sortie
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
		if(!( this.nombreEntrees() == 2 && this.nombreSorties() == 1)){
			throw new Invariant("ET : Invariant");
		}
	}

}
