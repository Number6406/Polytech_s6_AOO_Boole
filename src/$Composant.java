import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class $Composant {

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
	
	public $Port accederPortSortie(int numPort) {
		PortSortie portSortie = listeSorties.get(numPort);
		/**#TODO toutes les assertions pour v�rifier si le port existe**/
		return portSortie;
	}

}
