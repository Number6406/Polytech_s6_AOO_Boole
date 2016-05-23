package Composite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Circuit.Circuit;
import Composant.$Composant;
import Port.PortEntree;
import Port.PortSortie;
import jus.util.assertion.Require;

public class Composite extends $Composant implements _Composite, Cloneable{
	
	private Circuit circuit_interne;
	protected List<PortEntree> listeSortieInterne;
	protected List<PortSortie> listeEntreInterne;
	private boolean valeur;
	private String type; 
	
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
		
	//================================================================ ACCEDER AU PORTS ENTRE ET SORTIE INTERNE, GETTER/SETTER
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
	
	
	
	/**Obtenir un composant selon son indice*/
	/**@Require : Composant-1<NbComposant*/
	public $Composant getComposant(int numComposant)throws Require
	{	if(this.circuit_interne.nombreComposant()<=numComposant-1){throw new Require("Existe");}
		return this.circuit_interne.getComposant(numComposant);}

	/**Obtenir un composant selon son indice*/
	public int nbComposant()
	{return this.circuit_interne.nombreComposant();}
	
	//================================================================= AJOUTER 
	/**Ajouter un composant au circuit interne*/
	public void ajouter($Composant nouveauComposant, int numeroComposant) 
	{this.circuit_interne.ajouter(nouveauComposant, numeroComposant);}

	//================================================================= CONNEXION
	/**Connecter deux composants*/
	public void connecter(int numComposantSortie, int numPortSortie, int numComposantEntree, int numPortEntree)
	{this.circuit_interne.connecter(numComposantSortie, numPortSortie, numComposantEntree, numPortEntree);}
	
	/**Connecter une entre à un composant*/
	public void connecterEntre(int numEntre, int numComposantEntre, int portEntreCompo)
	{ 
		this.getPortEntreInterne(numEntre).add(circuit_interne.getComposant(numComposantEntre).accederPortEntre(portEntreCompo));
	}
	
	/**Connecter un composant à une sortie*/
	public void connecterSortie(int numComposantSortie, int portSortieCompo, int numSortie)
	{
		circuit_interne.getComposant(numComposantSortie).accederPortSortie(portSortieCompo).add(this.getPortSortieInterne(numSortie));
		this.getPortSortieInterne(numSortie).ajouterNumComposant(-1);		
	}
	
	//================================================================= CALCUL
	/**Transmettre les valeurs d'entrée vers les ports d'entre interne*/
	private void transmettreValeurEntrees()
	{
		int i;
		boolean val;
		for(i=1;i <= this.nombreEntrees();i++)
		{
			val = this.accederPortEntre(i).obtenirValeur();
			this.getPortEntreInterne(i).majValeur(val);
			for(PortEntree p : this.getPortEntreInterne(i).getEntrees())
			{
				p.majValeur(val);
			}
			
		}
	}
	
	/**Transmettre les valeurs sortie vers les ports sortie interne*/
	private void transmettreValeurSortie()
	{
		int i;
		boolean val;
		for(i=1;i <= this.nombreSorties();i++)
		{
			val = this.getPortSortieInterne(i).obtenirValeur();
			this.accederPortSortie(i).majValeur(val);
			for(PortEntree p : this.accederPortSortie(i).getEntrees())
			{
				p.majValeur(val);
			}
			
		}
	}

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
		
		for(i = 1; i <=this.circuit_interne.nombreComposant(); i++)
		{
			nbEntree[i] = this.circuit_interne.getComposant(i).nombreEntrees();
		}
		
		for(i = 1; i <=this.nombreEntrees(); i++)
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
					
					for(PortSortie pS : this.circuit_interne.getComposant(indice).getListeSortie())
					{
						for(PortEntree pE : pS.getEntrees())
						{
							numCompo = pE.obtenirNumComposant();
							if(numCompo!=-1)
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
	
	//================================================================= DUPLIQUER
	
	/**Fonction qui permet de dupliquer le composite courant
	 * @return Composite identique au composite courant*/
	public Composite duplicate()
	{
		int i;
		Composite compo = new Composite(this.nombreEntrees(), this.nombreSorties(), this.obtenirType());
		compo.type = this.nomType;
		
		for(i = 0; i < this.nombreEntrees(); i++)
		{	
			compo.listeEntrees.add(this.accederPortEntre(i+1).clone());
			compo.listeEntreInterne.add(this.getPortEntreInterne(i+1).clone());
		}
		for(i = 0; i < this.nombreSorties(); i++)
		{	
			compo.listeSorties.add(this.accederPortSortie(i+1).clone());
			compo.listeSortieInterne.add(this.getPortSortieInterne(i+1).clone());}
		
		compo.circuit_interne = this.circuit_interne;
		return compo;
	}
	
	//================================================================= REPRESENTATION TEXTUEL
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



