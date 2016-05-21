import java.util.ArrayList;

import jus.util.assertion.Invariant;
import jus.util.assertion.Require;

/**
 * Classe interruptaure permettant de générer un niveau haut ou bas selon son état
 * @author alicia
 * @invariant BienConstruit : (listeEntrees.size() == 0) && (listeSorties.size() == 1)
 */
public class Itr extends $Generateur{
	
	public Itr(){
		this.nomType = "ITR";
		this.etat = false; // Bas par défaut
		this.listeEntrees = new ArrayList<PortEntree>();
		this.listeSorties = new ArrayList<PortSortie>();
		
		this.listeSorties.add(0, new PortSortie());
		
		_invariant();
	}
	
	/* Méthodes de modifications de l'état de l'interrupteur */
	public void ItrBas(){
		this.etat = false;
	}
	
	public void ItrHaut(){
		this.etat = true;
	}
	
	/* Propagation de la valeur */
	void calculer() throws Require{
		if(!(listeSorties.size() > 0)){
			throw new Require("Non : Port de Sortie non existant");
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
