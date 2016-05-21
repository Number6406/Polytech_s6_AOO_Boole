import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jus.util.assertion.Require;

public class Composite extends $Composant implements _Composite{
	
	//private CircuitOuvert circuit_interne;
	private Circuit circuit_interne;
	protected List<PortEntree> listeSortieInterne;
	protected List<PortSortie> listeEntreInterne;
	
	public Composite(int entre, int sortie , String type)
	{
		int i,j;
		this.nomType = type;
		this.listeEntrees = new ArrayList<PortEntree>();
		this.listeSorties = new ArrayList<PortSortie>();
		this.listeSortieInterne = new ArrayList<PortEntree>();
		this.listeEntreInterne = new ArrayList<PortSortie>();
		
		for(i = 0; i < entre; i++)
		{	
			this.listeEntrees.add(new PortEntree());
			this.listeEntreInterne.add(new PortSortie());
		}
		for(j = 0; j < entre; j++)
		{	
			this.listeSorties.add(new PortSortie());
			this.listeSortieInterne.add(new PortEntree());}
		this.circuit_interne = new Circuit();
	}
	
	public PortEntree getPortSortieInterne(int i)
	{
		return this.listeSortieInterne.get(i);
	}
	
	public PortEntree getPortEntre(int i)
	{
		return this.listeEntrees.get(i);
	}
	
	public PortSortie getPortEntreInterne(int i)
	{
		return this.listeEntreInterne.get(i);
	}
	
	public PortSortie getPortSortie(int i)
	{
		return this.listeSorties.get(i);
	}
	
	public String toString() {
		return "";
	}
	
	public void ajouter($Composant nouveauComposant, int numeroComposant) 
	{this.circuit_interne.ajouter(nouveauComposant, numeroComposant);}

	public void connecter(int numComposantSortie, int numPortSortie, int numComposantEntree, int numPortEntree)
	{this.circuit_interne.connecter(numComposantSortie, numPortSortie, numComposantEntree, numPortEntree);}

	
	public void getCompo(int numComposant)
	{this.circuit_interne.getComposant(numComposant);}

	public boolean evaluable(){return true;}

}



