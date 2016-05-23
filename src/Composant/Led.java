package Composant;
import java.util.ArrayList;

import Port.PortEntree;
import Port.PortSortie;
import jus.util.assertion.Invariant;
import jus.util.assertion.Require;
/**
 * @author alicia
 * @invariant Bienconstruit : (listeEntrees.size() == 1) && (listeSorties.size() == 0)
 */
public class Led extends $Recepteur{
	
	/**
	 * Constructeur d'un recepteur Led
	 * Eteinte par défaut.
	 */
	public Led(){
		this.nomType = "LED";
		this.etat = false; //etein
		this.listeEntrees = new ArrayList<PortEntree>();
		this.listeSorties = new ArrayList<PortSortie>();
		
		this.listeEntrees.add(0, new PortEntree(1));
		
		_invariant();
	}
	
	/**
	 * Permet de modifier l'état de la led selon ses entrées
	 * @require !listeEntrees.get(0).estLibre()
	 */
	public void calculer() throws Require{
		if((listeEntrees.get(0).estLibre())){
			throw new Require("Non : PortsEntree non Connectes");
		}
		
		this.etat = listeEntrees.get(0).obtenirValeur();
		
	}
	
	void _invariant() throws Invariant{
		if(! ((listeEntrees.size() == 1) && (listeSorties.size() == 0)) ){ 
			throw new Invariant("BienConstruit");
		}
	}
}
