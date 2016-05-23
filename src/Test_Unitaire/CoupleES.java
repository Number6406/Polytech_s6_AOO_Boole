package Test_Unitaire;
import java.util.ArrayList;
import java.util.List;

import jus.util.assertion.Invariant;

/**
	 * Classe de couple deliste d'entrées et de sorties
	 * @author alicia
	 *
	 */
public class CoupleES{
	private int nbE;
	private int nbS;
	private List<Boolean> Entrees;
	private List<Boolean> Sorties;
	
	public CoupleES(int nbe, int nbs){
		this.Entrees = new ArrayList<Boolean>();
		this.Sorties = new ArrayList<Boolean>();
		nbE = nbe;
		nbS = nbs;
		for (int i = 0; i < nbE; i++) {
			ajouterEntree(false);
		}
		for (int i = 0; i < nbS; i++) {
			ajouterSortie(false);
		}
		_invariant();
	}
	
	public CoupleES(CoupleES c){
		Entrees = new ArrayList<Boolean>();
		Sorties = new ArrayList<Boolean>();
		nbE = c.getnbe();
		nbS = c.getnbs();
		for (int i = 0; i < Entrees.size(); i++) {
			ajouterEntree(c.getEntree(i));
		}
		for (int i = 0; i < Sorties.size(); i++) {
			ajouterSortie(c.getSortie(i));
		}
		_invariant();
		
	}
	
	public Boolean getSortie(int i) {
		return Sorties.get(i);
	}

	public Boolean getEntree(int i) {
		return Entrees.get(i);
	}

	public int getnbs() {
		return nbS;
	}

	public int getnbe() {
		return nbE;
	}

	private void ajouterEntree(Boolean b){
		this.Entrees.add(b);
	}
	private void ajouterSortie(Boolean b){
		this.Sorties.add(b);
	}
	
	public void modifierEntree(int i,Boolean b){
		Entrees.set(i, b);
	}
	public void modifierSortie(int i,Boolean b){
		Sorties.set(i, b);
	}
	
	public String toString(){
		String s ="|";
		
		for(Boolean b : Entrees){
			if(b){
				s = s + " 1 |";
			}else{
				s = s + " 0 |";
			}
		}
		s= s+"|";
		for(Boolean b : Sorties){
			if(b){
				s = s + " 1 |";
			}else{
				s = s + " 0 |";
			}
		}
		return s;
	}
	private void _invariant() throws Invariant{
		if(!((nbE==Entrees.size())&&(nbS==Sorties.size()))){
			throw new Invariant("Bonne taille");
		}
	}
	
	
}