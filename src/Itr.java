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
		
		this.listeSorties.add(0, new PortSortie(1));
		
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
	public void calculer() throws Require{
		if(!(listeSorties.size() > 0)){
			throw new Require("Non : Port de Sortie non existant");
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
	
	public String toString(){
		String s = this.nomType+"("+this.nombreEntrees()+","+this.nombreSorties()+"){"+this.etat+"}->";
		int j;
		for (j = 0; j < listeSorties.size()-1; j++) {
			s = s+"#"+j+listeSorties.get(j).toString()+",";
		}
		s = s+"#"+j+listeSorties.get(j).toString();
		return s;
	}
	
}
