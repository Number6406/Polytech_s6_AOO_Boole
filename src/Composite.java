import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jus.util.assertion.Require;

public class Composite extends $Composant implements _Composite{
	
	private Circuit circuit_interne;
	protected List<PortEntree> listeSortieInterne;
	protected List<PortSortie> listeEntreInterne;
	private boolean valeur; 
	
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
			this.listeEntrees.add(new PortEntree(i+1));
			this.listeEntreInterne.add(new PortSortie(i+1));
		}
		for(j = 0; j < sortie; j++)
		{	
			this.listeSorties.add(new PortSortie(j+1));
			this.listeSortieInterne.add(new PortEntree(j+1));}
		this.circuit_interne = new Circuit();
	}
	
	//ACCEDER AU PORTS ENTRE ET SORTIE INTERNE
	/**@Require : i < nombre sortie*/
	public PortEntree getPortSortieInterne(int i) throws Require
	{
		
		if(i-1>=this.nombreSorties()){throw new Require("Taille");}
		return this.listeSortieInterne.get(i-1);
	}

	/**@Require : i < nombre entre*/
	public PortSortie getPortEntreInterne(int i) throws Require
	{
		if(i-1>=this.nombreEntrees()){throw new Require("Taille");}
		return this.listeEntreInterne.get(i-1);
	}

	//METTRE A JOUR LES VALEURS DES PORTS PAS SURE QUE SE SOIT UTILE
	/*public void mettreAJourPortEntre(int numPort, boolean Valeur )
	{ 
		this.accederPortEntre(numPort).majValeur(valeur);
		this.getPortEntreInterne(numPort).majValeur(valeur);
	}
	
	public void mettreAJourPortSortie(int numPort, boolean Valeur )
	{
		this.accederPortSortie(numPort).majValeur(valeur);
		this.getPortSortieInterne(numPort).majValeur(valeur);
	}*/	
	
	/**Ajouter un composant au circuit interne*/
	public void ajouter($Composant nouveauComposant, int numeroComposant) 
	{this.circuit_interne.ajouter(nouveauComposant, numeroComposant);}

	/**Connecter deux composants*/
	public void connecter(int numComposantSortie, int numPortSortie, int numComposantEntree, int numPortEntree)
	{this.circuit_interne.connecter(numComposantSortie, numPortSortie, numComposantEntree, numPortEntree);}
	
	/**Connecter une entre à un composant*/
	public void connecterEntre(int numEntre, int numComposantEntre, int portEntreCompo)
	{ 
		this.getPortEntreInterne(numEntre).add(circuit_interne.getComposant(numComposantEntre).accederPortEntre(portEntreCompo));
	}
	
	/**Connecter un composant à une sortie*/
	public void connecterSortie(int numComposantSortie, int numSortie, int portSortieCompo)
	{
		circuit_interne.getComposant(numComposantSortie).accederPortSortie(portSortieCompo).add(this.getPortSortieInterne(numSortie));
		this.getPortSortieInterne(numSortie).ajouterNumComposant(-1);
		
	}
	
	/**Transmettre les valeurs d'entrée vers les ports d'entre interne*/
	private void transmettreValeurEntrees()
	{
		int i;
		for(i=0;i < this.nombreEntrees();i++)
		{
			this.accederPortEntre(i).majValeur(this.getPortEntreInterne(i).obtenirValeur());
		}
	}
	
	/**Transmettre les valeurs sortie vers les ports sortie interne*/
	private void transmettreValeurSortie()
	{
		int i;
		for(i=0;i < this.nombreSorties();i++)
		{
			this.accederPortSortie(i).majValeur(this.getPortSortieInterne(i).obtenirValeur());
		}
	}
	
	/**Obtenir un composant selon son indice*/
	public $Composant getComposant(int numComposant)
	{return this.circuit_interne.getComposant(numComposant);}

	/**Obtenir un composant selon son indice*/
	public int nbComposant()
	{return this.circuit_interne.nombreComposant();}
	
	//public boolean evaluable(){return true;}

	@Override
	public void calculer() 
	{
		int nbEntree[] = new int[this.circuit_interne.nombreComposant()+1]; 
		int i,numCompo;
		int indice;
		
		this.transmettreValeurEntrees();
		
		for (i = 0; i < nbEntree.length; i++) {
			nbEntree[i] = -1;
		}
		
		for(i = 0; i <this.circuit_interne.nombreComposant(); i++)
		{
			nbEntree[i] = this.circuit_interne.getComposant(i).nombreEntrees();
		}
		
		for(i = 0; i <this.nombreEntrees(); i++)
		{
			for(indice = 0; indice <this.getPortEntreInterne(i).getNombreEntrees(); indice++)
			{
				numCompo = this.getPortEntreInterne(i).chercherIndice(indice).obtenirNumComposant();
				nbEntree[numCompo] = nbEntree[numCompo]-1;
			}
		}
		
		while(!toutCalcule(nbEntree))
		{
			for(indice = 0; indice<nbEntree.length;indice ++)
			{
				if(nbEntree[indice]==0)
				{
					this.circuit_interne.getComposant(indice).calculer();
					
					for(PortSortie pS : this.circuit_interne.getComposant(indice).listeSorties)
					{
						for(PortEntree pE : pS.getEntrees())
						{
							numCompo = pE.obtenirNumComposant();
							nbEntree[numCompo] =  nbEntree[numCompo] -1;
						}
					}
					
					nbEntree[indice] =  -1;
				}
			}
		}
		
		this.transmettreValeurSortie();
	}
	
	/**Utilisé pour a fonction calculer*/
	private boolean toutCalcule(int[] entree)
	{
		for(int valeur : entree)
		{
			if(valeur!=-1) return false;
		}
		return true;
	}
	
	public String toString() {
		int i,j;
		String listeCompo,listeEntre, composite,listeSortie;
		composite = this.nomType+"("+this.nombreEntrees()+","+this.nombreSorties()+")[";
		listeCompo = "";
		listeEntre = "";
		listeSortie = "";
		for (i = 1; i < this.nombreEntrees()+1; i++) 
		{
			listeEntre = listeEntre+"#"+i+this.getPortEntreInterne(i).toString()+",";
		}
		listeEntre = listeEntre.substring(0,listeEntre.length()-1);
		for(i = 1; i < this.circuit_interne.nombreComposant()+1; i++)
		{
			listeCompo = listeCompo + "<"+i+"|"+this.circuit_interne.getComposant(i).toString()+">\n";
		}
		composite = composite + listeEntre + listeCompo + "]"+/**@TODO : ajouter le facultatif*/"->";
		
		for (i = 1; i < this.nombreSorties()+1; i++) 
		{
			listeSortie = listeSortie+"#"+i+this.accederPortSortie(i).toString()+",";
		}
		if(listeSortie.length()>0){listeSortie = listeSortie.substring(0,listeSortie.length()-1);}
		composite = composite+listeSortie;
		return composite;
	}

}



