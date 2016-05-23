package Composant;

import java.util.ArrayList;

import Port.PortSortie;
import jus.util.assertion.Require;

public class Gen4bC2 extends $Generateur {
	
	int valeur;
	
	/**
	 * @ensure CodableSur4Bits : val < 8 && val >= -8
	 * @param val entrée entre -8 et 7
	 */
	public Gen4bC2(int val) {
		if(!(val < 8 && val >= -8)) throw new Require("CodableSur4Bits");
		
		this.nomType="GEN4BC2";
		this.etat=false;
		this.valeur = val;
		
		this.listeEntrees = new ArrayList<>();
		this.listeSorties = new ArrayList<>();

		this.listeSorties.add(0, new PortSortie(1));
		this.listeSorties.add(1, new PortSortie(2));
		this.listeSorties.add(2, new PortSortie(3));
		this.listeSorties.add(3, new PortSortie(4));
		
	}
	
	/**
	 * @ensure CodableSur4Bits : val < 8 && val >= -8
	 * @param val
	 */
	public void maj(int val) {
		if(!(val < 8 && val >= -8)) throw new Require("CodableSur4Bits");
		this.valeur = val;
	}

	@Override
	public void calculer() {
		String valBinaire = Integer.toBinaryString(this.valeur);
		char[] valBinaire_a = valBinaire.toCharArray();
		int lg = valBinaire_a.length;
		
		this.accederPortSortie(4).majValeur(valBinaire_a[lg-1] == '1');
		this.accederPortSortie(3).majValeur(valBinaire_a[lg-2] == '1');
		this.accederPortSortie(2).majValeur(valBinaire_a[lg-3] == '1');
		this.accederPortSortie(1).majValeur(valBinaire_a[lg-4] == '1');
	}

}
