package Composant;

import java.util.ArrayList;

import Port.PortEntree;

public class Aff4bC2 extends $Generateur {

	int valeur;
	
	public public Aff4bC2() {
		this.nomType = "Aff4bC2";
		this.etat = false;
		this.listeEntrees = new ArrayList<>();
		this.listeSorties = new ArrayList<>();

		this.listeEntrees.add(0, new PortEntree(1));
		this.listeEntrees.add(0, new PortEntree(2));
		this.listeEntrees.add(0, new PortEntree(3));
		this.listeEntrees.add(0, new PortEntree(4));
		this.listeEntrees.add(0, new PortEntree(5));
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

}
