package Composant;
import java.util.ArrayList;

import Port.PortEntree;
import Port.PortSortie;
import jus.util.assertion.Invariant;
import jus.util.assertion.Require;

/**
 * Classe Gnd permettant de gÃ©nÃ©rer un niveau bas
 * @author alicia
 * @invariant BienConstruit : (listeEntrees.size() == 0) && (listeSorties.size() == 1)
 */
public class Gnd extends $Generateur{
	/**
	 * Constructeur d'un générateur de niveau bas.
	 */
	public Gnd(){
		this.nomType = "GND";
		this.etat = false;
		this.listeEntrees = new ArrayList<PortEntree>();
		this.listeSorties = new ArrayList<PortSortie>();
		
		this.listeSorties.add(0, new PortSortie(1));
		
		_invariant();
	}
	
	/**
	 * Permet de propager la valeur du Gnd aux ports connecté à celui-ci
	 * @require (listeSorties.size() > 0)
	 */
	public void calculer() throws Require{
		if(!(listeSorties.size() > 0)){
			throw new Require("Gnd : Port de Sortie non existant");
		}

		boolean res;
		// Le booleen prends la valeur port1&&port2
		res = this.etatGenerateur();
		
		//Mise a jour du port de sortie
		this.listeSorties.get(0).majValeur(res);
		//Mise a jour de la valeur des ports connecte aux ports de sortie
		listeSorties.get(0).getEntrees().forEach(portEntree -> {
			portEntree.majValeur(res);
		});
	}
	
	void _invariant() throws Invariant{
		if(! ((listeEntrees.size() == 0) && (listeSorties.size() == 1))){ 
			throw new Invariant("BienConstruit");
		}
	}
}
