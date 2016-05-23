package Composant;

import java.util.ArrayList;

import Port.PortEntree;
import Port.PortSortie;

public class Aff4bC2 extends $Recepteur {

	int valeur;
	
	public Aff4bC2() {
		this.nomType = "Aff4bC2";
		this.etat = false;
		this.listeEntrees = new ArrayList<>();
		this.listeSorties = new ArrayList<>();

		this.listeEntrees.add(0, new PortEntree(1));
		this.listeEntrees.add(1, new PortEntree(2));
		this.listeEntrees.add(2, new PortEntree(3));
		this.listeEntrees.add(3, new PortEntree(4));
		
		this.listeSorties.add(0, new PortSortie(1));
	}
	
	@Override
	public void calculer() {
		
		char[] tabBinaire = new char[4];
		
		if(this.accederPortEntre(4).obtenirValeur()) tabBinaire[0] = 1;
		else tabBinaire[0] = 0;
		if (this.accederPortEntre(3).obtenirValeur()) tabBinaire[1] = 1;
		else tabBinaire[1] = 0;
		if(this.accederPortEntre(2).obtenirValeur()) tabBinaire[2] = 1;
		else tabBinaire[2] = 0;
		if(this.accederPortEntre(1).obtenirValeur()) tabBinaire[3] = 1;
		else tabBinaire[3] = 0;

		String strBinaire = tabBinaire.toString();
		int valeur = Integer.parseInt(strBinaire);
		
	}
	
	public String toString() {
		return(this.nomType+"("+this.nombreEntrees()+","+this.nombreSorties()+"){" + valeur + "}");
	}

}
