package Composant;
import java.util.ArrayList;

import Port.PortEntree;
import Port.PortSortie;
import jus.util.assertion.Ensure;
import jus.util.assertion.Invariant;
import jus.util.assertion.Require;

/**
 * @author alicia
 * @invariant OUXcomplet : this.nombreEntrees() ==2 && this.nombreSorties()==1
 */
public class Oux extends $Transformateur {
	
	/**
	 * Constructeur de la classe OUX.
	 * Remplit la liste d'entrée avec 2 ports non connectés
	 * Remplit la liste de sortie avec un port de sortie connecté à une liste de ports d'entrée vide
	 * @Ensure OUXcomplet : this.nombreEntrees() == 2 && this.nombreSorties()== 1
	 */
	public Oux() throws Invariant{
		this.nomType = "OUX";
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
			throw new Require(" OUX : Ports non Connectes");
		}
		
		boolean res;
		boolean v1,v2;
		v1 = (this.listeEntrees.get(0).obtenirValeur());
		v2 = (this.listeEntrees.get(1).obtenirValeur());
		// Le booleen prends la valeur port1 oux port2
		res = (v1||v2)&&(!(v1&&v2));
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
		if(!( this.nombreEntrees() == 2 && this.nombreSorties() == 1)){
			throw new Invariant("OUX : Invariant");
		}
	}
}
