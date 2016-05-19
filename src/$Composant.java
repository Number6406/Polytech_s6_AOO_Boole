import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class $Composant {

	protected Map<Integer, PortSortie> listeSorties;
	protected Map<Integer, PortEntree> listeEntrees;
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
	
	public $Port accederPortSortie(int numPort) {
		PortSortie portSortie = listeSorties.get(numPort);
		/**#TODO toutes les assertions pour vérifier si le port existe**/
		return portSortie;
	}

}
