import java.util.List;

import jus.util.assertion.Require;

public abstract class $Composant implements _Composant {

	protected List<PortSortie> listeSorties;
	protected List<PortEntree> listeEntrees;
	protected String nomType;
	
	public int nombreSorties(){
		return listeSorties.size();
	}
	
	public int nombreEntrees(){
		return listeEntrees.size();
	}
	
	public String obtenirType(){
		return this.nomType;
	}
	
	/**
	 * @require PortExiste : numPort >= 0 && numPort < listeSorties.size()
	 * @param numPort : Le numéro de port voulu
	 * @return le PortSortie associé s'il existe
	 */
	public PortSortie accederPortSortie(int numPort) {
		//Require
		if(!(numPort >= 0 && numPort < listeSorties.size())) throw new Require("PortExiste");
		return listeSorties.get(numPort);
	}
	
	public PortEntree accederPortEntre(int numPort) {
		//Require
		if(!(numPort >= 0 && numPort < listeEntrees.size())) throw new Require("PortExiste");
		return listeEntrees.get(numPort);
	}

}
