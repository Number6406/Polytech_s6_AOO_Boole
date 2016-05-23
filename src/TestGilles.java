
public class TestGilles {

	public static void main(String[] args) {
		
		Circuit test = new Circuit();

		Itr i1 = new Itr();
		Itr i2 = new Itr();
		Itr i3 = new Itr();

		test.ajouter(i1, 1);
		test.ajouter(i2, 2);
		test.ajouter(i3, 3);
		
		test.ajouter(new Add3b(), 4);

		Led l1 = new Led();
		Led l2 = new Led();
		
		test.ajouter(l1,5);
		test.ajouter(l2,6);

		test.connecter(1, 1, 4, 1);
		test.connecter(2, 1, 4, 2);
		test.connecter(3, 1, 4, 3);

		test.connecter(4, 1, 5, 1);
		test.connecter(4, 2, 6, 1);
		
		CircuitFerme testF = new CircuitFerme(test);
		
		testF.evaluer();
		
		System.out.println("L1 : " + l1.etatRecepteur() + " L2 : " + l2.etatRecepteur());
		
		i1.ItrHaut();
		testF.evaluer();
		System.out.println("L1 : " + l1.etatRecepteur() + " L2 : " + l2.etatRecepteur());
		
		i2.ItrHaut();
		testF.evaluer();
		System.out.println("L1 : " + l1.etatRecepteur() + " L2 : " + l2.etatRecepteur());
		
	}
	
}
