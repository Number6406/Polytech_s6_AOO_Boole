import java.util.ArrayList;
import jus.util.assertion.Invariant;
import jus.util.assertion.Require;

/**
 * @author alicia
 * @invariant ETcomplet : this.nombreEntrees() ==2 && this.nombreSorties()==1
 */
public class Et extends $Transformateur {
	
	/**
	 * Constructeur de la classe ET.
	 * Remplit la liste d'entrée avec 2 ports non connectés
	 * Remplit la liste de sortie avec un port de sortie connecté à une liste de ports d'entrée vide
	 * @Ensure ETcomplet : this.nombreEntrees() == 2 && this.nombreSorties()== 1
	 */
	public Et() throws Invariant{
		this.nomType = "ET";
		this.listeEntrees = new ArrayList<PortEntree>();
		this.listeSorties = new ArrayList<PortSortie>();
		
		this.listeEntrees.add(0,new PortEntree());
		this.listeEntrees.add(1,new PortEntree());
		this.listeSorties.add(0,new PortSortie());
		_invariant();
	}
	
	/**
	 * Mets à jour les ports de sortie du composant, ainsi que les ports d'entrées auxquels ce dernier est connecté
	 * @require PortsConnectes : !(listeEntrees.get(1).estLibre()||listeEntrees.get(2).estLibre())
	 * @ensure EntreesRemplies : forall(PortEntree portEntree : listeSorties.get("premier port de sortie")) !(portEntree.estLibre())
	 */
	void calculer() throws Require{
		// REQUIRE
		if(!(listeEntrees.get(0).estLibre()||listeEntrees.get(1).estLibre())){
			throw new Require(" ET : Ports non Connectes");
		}
		
		boolean res;
		// Le booleen prends la valeur port1&&port2
		res = (this.listeEntrees.get(0).obtenirValeur())&&(this.listeEntrees.get(1).obtenirValeur());
		this.listeSorties.get(0).majValeur(res);
		

		listeSorties.get(0).getEntrees().forEach(portEntree -> {
			portEntree.majValeur(res);
			portEntree.reserver(); //#TODO Pas sur que se soit utile de reserver le port...
		});
	
//		// ENSURE
//		ensure = true;
//		for (PortEntree portEntree : destinations) {
//			ensure = ensure && !(portEntree.estLibre());
//		}
//		if(!(ensure)){ throw new Ensure("EntreesRemplies");}
		
		_invariant();
	}
	
	void _invariant() throws Invariant{
		if(!( this.nombreEntrees() == 2 && this.nombreSorties() == 1)){
			throw new Invariant("ET : Invariant");
		}
	}
}
