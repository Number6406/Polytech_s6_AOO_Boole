import java.util.ArrayList;
import java.util.TreeMap;

import jus.util.assertion.Invariant;

/**
 * @author alicia
 * @invariant ETcomplet : this.nombreEntrees() ==2 && this.nombreSorties()==1
 */
public class Et extends $Transformateur {
	
	/**
	 * Constructeur de la classe ET.
	 * Remplit la liste d'entrée avec 2 ports non connectés
	 * Remplit la liste de sortie avec un port de sortie connecté à une liste de ports d'entrée vide
	 * @Ensure ETcomplet : this.nombreEntrees() ==2 && this.nombreSorties()==1
	 */
	public Et() throws Invariant{
		this.nomType = "ET";
		this.listeEntrees = new ArrayList<PortEntree>();
		this.listeSorties = new TreeMap<PortSortie,ArrayList<PortEntree>>();
		
		this.listeEntrees.add(new PortEntree());
		this.listeEntrees.add(new PortEntree());
		this.listeSorties.put(new PortSortie(), null);
		_invariant();
	}
	
	/**
	 * 
	 */
	void calculer(){
		boolean res = ((ArrayList<PortEntree>)listeEntrees).get(1).get() && ((ArrayList<PortEntree>)listeEntrees).get(2).get(); 
		((TreeMap<PortSortie,ArrayList<PortEntree>>)listeSorties).firstKey().majValeur(res);;
	}
	
	void _invariant() throws Invariant{
		if(!( this.nombreEntrees() == 2 && this.nombreSorties() == 1)){
			throw new Invariant("ETcomplet");
		}
	}
}
