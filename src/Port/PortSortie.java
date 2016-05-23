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
	
	
	
	public PortSortie(boolean val, boolean l , ArrayList<PortEntree> liste, int num)
	{
		this.majValeur(val);
		if(l){this.liberer();}
		else{this.reserver();}
		this.listeEntree = liste;
		this.numero = num;
	}
	
	/**
	 * Ajoute une connexion avec un port d'entree
	 * @param e le port d'entr�e � connecter � ce port de sortie.
	 */
	public void add(PortEntree e)
	{
		this.listeEntree.add(e);
		e.reserver();
		this.reserver();
	}
	
	/**Permet de dupliquer le port courant
	 * @return portSortie identique au port courant*/
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
	
	/**
	 * Permet de supprimer la connection avec un port d'entr�e.
	 * @param e le port d'entr�e � deconnecter
	 * @return le port d'entr�e d�connect�.
	 * @require PortEntree est connect� au port de sortie
	 */
	public PortEntree enleverUneSortie(PortEntree e)
	{
		PortEntree p;
		int taille = this.listeEntree.size();
		int i = 0;
		while((i<taille)&(e!=this.listeEntree.get(i)))
		{i++;}
		if(i==taille) throw new Require ("PortSortie : Le port � enlever n'existe pas");
		p = this.listeEntree.get(i);
		this.listeEntree.remove(i);
		return p;
	}
	
	/**
	 * Cherche si un port d'entree est connect� 
	 * @param e Le port a verifier
	 * @return true si les deux ports sont connect�, false sinon.
	 */
	public boolean chercher(PortEntree e)
	{
		int taille = this.listeEntree.size();
		int i = 0;
		while((i<taille)&(e!=this.listeEntree.get(i)))
		{i++;}
		if(i==taille) return false;
		return true;
	}
	
	/**@require : indice < taille*/
	public PortEntree chercherIndice(int indice) throws Require
	{
		int taille = this.listeEntree.size();
		if(indice-1>=taille){ throw new Require("Indice");}
		return this.listeEntree.get(indice-1);
	}
	
	/**
	 * 
	 * @return les ports d'entr�es connect� � ce port.
	 */
	public ArrayList<PortEntree> getEntrees()
	{
		return this.listeEntree;
	}
	
	/**
	 * retourne le nombre de port d'entr�es connect� � ce port.
	 * @return
	 */
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
