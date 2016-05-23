package Composant;
import Circuit.Circuit;
import Circuit.CircuitFerme;

public class TestOuX {
	
	/**
	 * Fonction de test du circuit avec un oux, version itérative
	 * @param c, le circuit OuX Uniquement !
	 */
	private static void tester(Circuit c){		
		String aff ;
		
		// Evaluations //
		// Cas 1
		((Itr)c.getComposant(1)).ItrBas();
		((Itr)c.getComposant(2)).ItrBas();
		
		CircuitFerme cF = new CircuitFerme(c);
		cF.evaluer();
		System.out.println("Itr1 bas , Itr2 bas  : "+((Led)cF.getComposant(4)).etatRecepteur());
		
		// Affichage textuel //
				aff = c.toString("Test1");				
				System.out.println("Affichage du circuit : \n"+aff);
		
		// Cas 2
		((Itr)c.getComposant(1)).ItrHaut();
		
		cF = new CircuitFerme(c);
		cF.evaluer();
		System.out.println("Itr1 haut, Itr2 bas  : "+((Led)cF.getComposant(4)).etatRecepteur());
		
		// Affichage textuel //
				aff = c.toString("Test2");		
				System.out.println("Affichage du circuit : \n"+aff);
		
		// Cas 3
		((Itr)c.getComposant(1)).ItrBas();
		((Itr)c.getComposant(2)).ItrHaut();
		
		cF = new CircuitFerme(c);
		cF.evaluer();
		System.out.println("Itr1 bas , Itr2 haut : "+((Led)cF.getComposant(4)).etatRecepteur());
		
		// Affichage textuel //
				aff = c.toString("Test3");		
				System.out.println("Affichage du circuit : \n"+aff);
		

		// Cas 4
		((Itr)c.getComposant(1)).ItrHaut();
		
		cF = new CircuitFerme(c);
		cF.evaluer();
		System.out.println("Itr1 haut, Itr2 haut : "+((Led)cF.getComposant(4)).etatRecepteur());
		
		// Affichage textuel //
				aff = c.toString("Test4");		
				System.out.println("Affichage du circuit : \n"+aff);
				
	}

	public static void main(String[] args) {
		
		Circuit c = new Circuit();
		Led l = new Led();
		Oux oux = new Oux();
		Itr i1 = new Itr();
		Itr i2 = new Itr();
		
		// On ajoute les composants au circuit
		c.ajouter(i1,1);
		c.ajouter(i2,2);
		c.ajouter(oux,3);
		c.ajouter(l,4);
		
		// On les connecte entre eux
		c.connecter(1, 1, 3, 1); // interrupteur1 --> oux
		c.connecter(2, 1, 3, 2); // interrupteur2 --> oux
		c.connecter(3, 1, 4, 1); // Oux --> led
		
		tester(c);

	}

}
