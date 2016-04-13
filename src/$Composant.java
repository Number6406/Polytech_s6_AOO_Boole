import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class $Composant {

	protected Map<PortSortie, ArrayList<PortEntree>> listeSorties;
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
}
