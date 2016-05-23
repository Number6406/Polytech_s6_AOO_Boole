package Composite;

import Composant.Et;
import Composant.Ou;
import Composant.Oux;

public class Add1b extends Composite {
	
	/**
	 * Creer un Additionneur 1 bit
	 */
	public Add1b() {
		super(3, 2, "Add1b");
		
		this.ajouter(new Oux(), 1);
		this.ajouter(new Oux(), 2);

		this.ajouter(new Et(), 3);
		this.ajouter(new Et(), 4);
		this.ajouter(new Ou(), 5);
		
		//entrées composant 1 : pe1 OUX pe2
		this.connecterEntre(1, 1, 1);
		this.connecterEntre(2, 1, 2);
		
		//entrées composant 2 : c1 OUX pe3
		this.connecter(1, 1, 2, 1);
		this.connecterEntre(3, 2, 2);
		
		//entrées composant 3 : pe3 ET c1
		this.connecterEntre(3, 3, 1);
		this.connecter(1, 1, 3, 2);
		
		//entrées composant 4 : pe1 ET pe2
		this.connecterEntre(1, 4, 1);
		this.connecterEntre(2, 4, 2);
		
		//entrées composant 5 : c3 OU c2
		this.connecter(3, 1, 5, 1);
		this.connecter(4, 1, 5, 2);
		
		//sortie composant 2
		this.connecterSortie(2, 1, 1);
		//sortie composant 5
		this.connecterSortie(5, 1, 2);
	}
	
}
