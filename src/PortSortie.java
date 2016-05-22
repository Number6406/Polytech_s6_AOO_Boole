import java.util.ArrayList;

import jus.util.assertion.Require;

/**@invariant : ????*/
public class PortSortie extends $Port 
{
	private ArrayList<PortEntree> listeEntree;
	
	/**@ensure : PortLibre libre = true & listeEntree = null*/
	public PortSortie()
	{
		this.majValeur(false);
		this.liberer();
		this.listeEntree = new ArrayList<PortEntree>();
	}
	
	public PortSortie(boolean val, boolean l , ArrayList<PortEntree> liste)
	{
		this.majValeur(val);
		if(l){this.liberer();}
		else{this.reserver();}
		this.listeEntree = liste;
	}
	
	public void add(PortEntree e)
	{this.listeEntree.add(e);}
	
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
	
	// Affiche la liste des composants auxquels le port de sortie est connecté
	//TODO ajouter le numéro de port d'entrée à un port pour la représentation textuelle
	public String toString(){
		String s = "(";
		int i;
		for (i=0;i<listeEntree.size()-1;i++) {
			s = s + listeEntree.get(i).obtenirNumComposant()+"#_,";
		}
		s = s + listeEntree.get(i).obtenirNumComposant()+"#_)";
		return s;
	}
}
