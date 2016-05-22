
public class TestOuX {
	
	public static void tester(Circuit c){
		// Evaluations //
		// Cas 1
		((Itr)c.getComposant(1)).ItrBas();
		((Itr)c.getComposant(2)).ItrBas();
		
		CircuitFerme cF = new CircuitFerme(c);
		cF.evaluer();
		System.out.println("Itr1 bas , Itr2 bas  : "+((Led)cF.getComposant(4)).etatRecepteur());
		
		// Cas 2
		((Itr)c.getComposant(1)).ItrHaut();
		
		cF = new CircuitFerme(c);
		cF.evaluer();
		System.out.println("Itr1 haut, Itr2 bas  : "+((Led)cF.getComposant(4)).etatRecepteur());
		
		// Cas 3
		((Itr)c.getComposant(1)).ItrBas();
		((Itr)c.getComposant(2)).ItrHaut();
		
		cF = new CircuitFerme(c);
		cF.evaluer();
		System.out.println("Itr1 bas , Itr2 haut : "+((Led)cF.getComposant(4)).etatRecepteur());
		

		// Cas 4
		((Itr)c.getComposant(1)).ItrHaut();
		
		cF = new CircuitFerme(c);
		cF.evaluer();
		System.out.println("Itr1 haut, Itr2 haut : "+((Led)cF.getComposant(4)).etatRecepteur());
		
		// Affichage textuel //
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
		c.connecter(1, 0, 3, 0); // interrupteur1 --> oux
		c.connecter(2, 0, 3, 1); // interrupteur2 --> oux
		c.connecter(3, 0, 4, 0); // Oux --> led
		
		tester(c);

	}

}
