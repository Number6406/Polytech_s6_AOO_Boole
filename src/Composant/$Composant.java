package Composant;
import java.util.List;

import Port.PortEntree;
import Port.PortSortie;
import jus.util.assertion.Require;

public abstract class $Composant implements _Composant {

	protected List<PortSortie> listeSorties;
	protected List<PortEntree> listeEntrees;
	protected String nomType;
	protected int numero;
	
	/**
	 * Retourne ne nombre de ports de sortie du composant.
	 * @return le nombre de ports de sortie
	 */
	public int nombreSorties(){
		return listeSorties.size();
	}
	
	/**
	 * Retourne le nombre de ports d'entr�e du composant.
	 * @return le nombre de port d'entr�e
	 */
	public int nombreEntrees(){
		return listeEntrees.size();
	}
	
	/**
	 * Retourne le type du composant
	 * @return le type du composant.
	 */
	public String obtenirType(){
		return this.nomType;
	}
	
	/**
	 * Permet de donner un num�ro au composant.
	 * @param i le num�ro que doit prendre le composant.
	 */
	public void ajouterNum(int i){
		this.numero = i;
	}
	
	/**
	 * 
	 * @return le numero du composant
	 */
	public int getNum(){
		return this.numero;
	}
	
	/**
	 * @require PortExiste : numPort >= 0 && numPort < listeSorties.size()
	 * @param numPort : Le numéro de port voulu
	 * @return le PortSortie associé s'il existe
	 */
	public PortSortie accederPortSortie(int numPort) {
		//Require
		numPort-=1;
		if(!(numPort >= 0 && numPort < listeSorties.size())) throw new Require("PortExiste");
		return listeSorties.get(numPort);
	}
	
	/**
	 * 
	 * @return la liste des ports d'entr�e
	 */
	public List<PortEntree> getListeEntrees() {
		return listeEntrees;
	}
	
	/**
	 * 
	 * @return la liste des ports de sortie
	 */
	public List<PortSortie> getListeSortie() {
		return listeSorties;
	}

	/**
	 * 
	 * @param numPort le port d'entr�e auquel on veut acceder
	 * @return le port d'entr�e correspondant.
	 */
	public PortEntree accederPortEntre(int numPort) {
		//Require
		numPort-=1;
		if(!(numPort >= 0 && numPort < listeEntrees.size())) throw new Require("PortExiste");
		return listeEntrees.get(numPort);
	}
	
	/**
	 * Repr�sentation textuelle d'un composant.
	 */
	public String toString(){
		String s = this.nomType+"("+this.nombreEntrees()+","+this.nombreSorties()+")->";
		int j;
		for (j = 0; j < listeSorties.size()-1; j++) {
			s = s+"#"+(j+1)+listeSorties.get(j).toString()+",";
		}
		s = s+"#"+(j+1)+listeSorties.get(j).toString();
		return s;
	}
	

}
