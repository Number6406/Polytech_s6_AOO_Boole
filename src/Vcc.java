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
		
		this.listeSorties.add(0, new PortSortie());
		
		_invariant();
	}
	
	/* Propagation de la valeur */
	public void calculer() throws Require{
		if(!(listeSorties.size() > 0)){
			throw new Require("Vcc : Port de Sortie non existant");
		}
		PortSortie p = this.listeSorties.get(0);
		p.majValeur(this.etatGenerateur());
		this.listeSorties.add(0, p);
	}
	
	void _invariant() throws Invariant{
		if(! ((listeEntrees.size() == 0) && (listeSorties.size() == 1))){ 
			throw new Invariant("BienConstruit");
		}
	}
}
