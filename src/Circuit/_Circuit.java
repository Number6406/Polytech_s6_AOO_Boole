package Circuit;
import Composant.$Composant;

public interface _Circuit {
	
	public void ajouter($Composant nouveauComposant, int numeroComposant);

	public void connecter(int numComposantSortie, int numPortSortie, int numComposantEntree, int numPortEntree);

	public $Composant getComposant(int numComposant);
	
	
}
