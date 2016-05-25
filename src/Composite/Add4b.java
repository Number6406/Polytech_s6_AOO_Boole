package Composite;

import Composant.Aff4bC2;
import Composant.Gen4bC2;
import Composant.Gnd;

public class Add4b extends Composite {

	public Add4b() {
		super(0,1,"Add4x4");

		this.ajouter(new Gen4bC2(0), 1);
		this.ajouter(new Gen4bC2(0), 2);
		this.ajouter(new Aff4bC2(), 3);

		this.ajouter(new Add3b(), 4);
		this.ajouter(new Add3b(), 5);
		this.ajouter(new Add3b(), 6);
		this.ajouter(new Add3b(), 7);
		
		this.ajouter(new Gnd(), 8);
		
		//Addition sur 3è bit
		this.connecter(1, 1, 4, 1); //On connecte sortie 1 de Gen1
		this.connecter(2, 1, 4, 2); //Idem pour Gen2
		this.connecter(8, 1, 4, 3); //1è retenue à 0 (Gnd)
		this.connecter(4, 1, 3, 1); //PortSortie1 connecté à Aff
		//Addition sur 2è bit
		this.connecter(1, 2, 5, 1);
		this.connecter(2, 2, 5, 2);
		this.connecter(4, 2, 5, 3); //La retenue est celle en sortie de l'Add précédent
		this.connecter(5, 2, 3, 2);
		//Addition sur 3è bit
		this.connecter(1, 3, 6, 1);
		this.connecter(2, 3, 6, 2);
		this.connecter(5, 2, 6, 3);
		this.connecter(6, 1, 3, 3);
		//Addition sur 4è bit
		this.connecter(1, 4, 7, 1);
		this.connecter(2, 4, 7, 2);
		this.connecter(6, 2, 7, 3);
		this.connecter(7, 1, 3, 4);
		
		//Bit de dépassement
		this.connecterSortie(7, 2, 1);
		
	}
	
}
