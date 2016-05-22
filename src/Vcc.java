import java.util.ArrayList;

import jus.util.assertion.Invariant;
import jus.util.assertion.Require;

/**
 * Classe Vcc permettant de générer un niveau haut
 * @author alicia
 * @invariant BienConstruit : (listeEntrees.size() == 0) && (listeSorties.size() == 1)
 */
public class Vcc extends $Generateur{
	
	public Vcc(){
		this.nomType = "VCC";
		this.etat = true;
		this.listeEntrees = new ArrayList<PortEntree>();
		this.listeSorties = new ArrayList<PortSortie>();
		
		this.listeSorties.add(0, new PortSortie(1));
		
		_invariant();
	}
	
	/* Propagation de la valeur */
	public void calculer() throws Require{
		if(!(listeSorties.size() > 0)){
			throw new Require("Vcc : Port de Sortie non existant");
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
