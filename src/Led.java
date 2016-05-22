import java.util.ArrayList;

import jus.util.assertion.Invariant;
import jus.util.assertion.Require;
/**
 * @author alicia
 * @invariant Bienconstruit : (listeEntrees.size() == 1) && (listeSorties.size() == 0)
 */
public class Led extends $Recepteur{
	
	public Led(){
		this.nomType = "LED";
		this.etat = false; //etein
		this.listeEntrees = new ArrayList<PortEntree>();
		this.listeSorties = new ArrayList<PortSortie>();
		
		this.listeEntrees.add(0, new PortEntree(1));
		
		_invariant();
	}
	
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
