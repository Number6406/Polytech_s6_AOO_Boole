import java.util.List;

import jus.util.assertion.Require;

public abstract class $Composant implements _Composant {

	protected List<PortSortie> listeSorties;
	protected List<PortEntree> listeEntrees;
	protected String nomType;
	protected int numero;
	
	public int nombreSorties(){
		return listeSorties.size();
	}
	
	public int nombreEntrees(){
		return listeEntrees.size();
	}
	
	public String obtenirType(){
		return this.nomType;
	}
	
	public void ajouterNum(int i){
		this.numero = i;
	}
	
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
	
	public PortEntree accederPortEntre(int numPort) {
		//Require
		numPort-=1;
		if(!(numPort >= 0 && numPort < listeEntrees.size())) throw new Require("PortExiste");
		return listeEntrees.get(numPort);
	}
	
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
