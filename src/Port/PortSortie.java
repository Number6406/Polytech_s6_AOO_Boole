package Port;
import java.util.ArrayList;

import jus.util.assertion.Require;

/**@invariant : ????*/
public class PortSortie extends $Port implements Cloneable 
{
	private ArrayList<PortEntree> listeEntree;
	
	/**@ensure : PortLibre libre = true & listeEntree = null*/
	public PortSortie(int num)
	{
		this.majValeur(false);
		this.liberer();
		this.listeEntree = new ArrayList<PortEntree>();
		this.numero = num;
	}
	
	public PortSortie clone()
	{
		PortSortie e = new PortSortie(this.numero);	
		e.majValeur(this.obtenirValeur());
		if(this.estLibre()){e.liberer();}
		else{e.reserver();}
		e.ajouterNumComposant(this.obtenirNumComposant());
		e.listeEntree = (ArrayList<PortEntree>) this.listeEntree.clone();
		return e;
	}
	
	public PortSortie(boolean val, boolean l , ArrayList<PortEntree> liste, int num)
	{
		this.majValeur(val);
		if(l){this.liberer();}
		else{this.reserver();}
		this.listeEntree = liste;
		this.numero = num;
	}
	
	public void add(PortEntree e)
	{
		this.listeEntree.add(e);
		e.reserver();
		this.reserver();
	}
	
	//#TODO v�rifier que le port est dans la liste (si i==taille retrouner une erreur)
	public PortEntree enleverUneSortie(PortEntree e)
	{
		PortEntree p;
		int taille = this.listeEntree.size();
		int i = 0;
		while((i<taille)&(e!=this.listeEntree.get(i)))
		{i++;}
		p = this.listeEntree.get(i);
		this.listeEntree.remove(i);
		return p;
	}
	
	public PortEntree chercher(PortEntree e)
	{
		int taille = this.listeEntree.size();
		int i = 0;
		while((i<taille)&(e!=this.listeEntree.get(i)))
		{i++;}
		return this.listeEntree.get(i);
	}
	
	/**@require : indice < taille*/
	public PortEntree chercherIndice(int indice) throws Require
	{
		int taille = this.listeEntree.size();
		if(indice>=taille){ throw new Require("Indice");}
		return this.listeEntree.get(indice);
	}
	
	public ArrayList<PortEntree> getEntrees()
	{
		return this.listeEntree;
	}
	
	public int getNombreEntrees()
	{
		return this.listeEntree.size();
	}
	
	// Affiche la liste des composants auxquels le port de sortie est connecté
	//TODO ajouter le numéro de port d'entrée à un port pour la représentation textuelle
	public String toString()
	{
		String s = "(";
		int i;
		for (i=0;i<listeEntree.size();i++) 
		{
			if(listeEntree.get(i).obtenirNumComposant()==-1){s = s +"#"+listeEntree.get(i).getNumPort()+",";}
			else{s = s + listeEntree.get(i).obtenirNumComposant()+"#"+listeEntree.get(i).getNumPort()+",";}
		}
		s = s.substring(0,s.length()-1);
		return s+")";
	}
}
