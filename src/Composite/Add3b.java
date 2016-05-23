package Composite;
import Composant.Et;
import Composant.Ou;
import Composant.Oux;

public class Add3b extends Composite {

	/**
	 * Creer un additioneur trois bits
	 */
	public Add3b() {
		super(3,2,"Add3b");
		
		this.ajouter(new Oux(), 1);
		this.ajouter(new Oux(), 2);
		this.ajouter(new Et(), 3);
		this.ajouter(new Et(), 4);
		this.ajouter(new Et(), 5);
		this.ajouter(new Ou(), 6);
		this.ajouter(new Ou(), 7);

		//entrées composant 1 : pe1 OUX pe2
		this.connecterEntre(1, 1, 1);
		this.connecterEntre(2, 1, 2);		
		
		//entrées composant 6 : c1 OUX pe3
		this.connecter(1, 1, 2, 1);
		this.connecterEntre(3, 2, 2);
		
		//entrées composant 3 : pe1 ET pe2
		this.connecterEntre(1, 3, 1);
		this.connecterEntre(2, 3, 2);
		
		//entrées composant 4 : pe1 ET pe3
		this.connecterEntre(1, 4, 1);
		this.connecterEntre(3, 4, 2);

		//entrées composant 5 : pe2 ET pe3
		this.connecterEntre(2, 5, 1);
		this.connecterEntre(3, 5, 2);
		
		//entrées composant 6 : c3 OU c4
		this.connecter(3, 1, 6, 1);
		this.connecter(4, 1, 6, 2);
		
		//entrées composant 7 : c6 ET c5
		this.connecter(6, 1, 7, 1);
		this.connecter(5, 1, 7, 2);
		
		//sortie composant 2 vers ps1
		this.connecterSortie(2, 1, 1);
		
		//sortie composant 7 vers ps2
		this.connecterSortie(7, 1, 2);
	}
}
