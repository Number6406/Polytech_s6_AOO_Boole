package Composant;
import java.util.ArrayList;
import java.util.Iterator;

import Port.PortEntree;
import Port.PortSortie;
import jus.util.assertion.Invariant;
import jus.util.assertion.Require;

/**
 * Classe interruptaure permettant de générer un niveau haut ou bas selon son état
 * @author alicia
 * @invariant BienConstruit : (listeEntrees.size() == 0) && (listeSorties.size() == 1)
 */
public class Itr extends $Generateur{
	
	/**
	 * Contructeur d'un interupteur. Niveau bas par d�faut
	 */
	public Itr(){
		this.nomType = "ITR";
		this.etat = false; // Bas par défaut
		this.listeEntrees = new ArrayList<PortEntree>();
		this.listeSorties = new ArrayList<PortSortie>();
		
		this.listeSorties.add(0, new PortSortie(1));
		
		_invariant();
	}
	
	/**
	 * Methode permetant de modifier l'�tat de l'interupteur � bas
	 */
	public void ItrBas(){
		this.etat = false;
	}
	
	/**
	 * Methode permetant de modifier l'�tat de l'interupteur � haut
	 */
	public void ItrHaut(){
		this.etat = true;
	}
	
	/**
	 * Permet de propager la valeur de l'interupteur aux ports connect� � celui-ci
	 * @require (listeSorties.size() > 0)
	 */
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
			s = s+"#"+(j+1)+listeSorties.get(j).toString()+",";
		}
		s = s+"#"+(j+1)+listeSorties.get(j).toString();
		return s;
	}

	@Override
	public Iterator<Void> iterator() {
		Iterator<Void> I = new Iterator<Void>() {
			
			@Override
			public Void next() {
				if(etatGenerateur()){
					ItrBas();
				}else{
					ItrHaut();
				}
				return null;
			}
			
			@Override
			public boolean hasNext() {
				return true;
			}
		};
		return I;
	}
	
}
