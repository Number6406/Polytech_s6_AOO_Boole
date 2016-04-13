import java.util.List;

public abstract class $Circuit implements _Circuit {

	List<$Composant> listeOperateur;
	
	public String toString(){
		return "";
	}
	
	public $Composant ajouter($Composant nouveauComposant) {
		
		listeOperateur.add(nouveauComposant);
		
	}
	
}
