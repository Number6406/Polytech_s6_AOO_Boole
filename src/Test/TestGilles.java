package Test;

import Circuit.*;
import Composant.*;
import Composite.*;

public class TestGilles {

	public static void main(String[] args) {
		
		//testAdd1b();
		String tests = Integer.toBinaryString(-8);
		System.out.println(tests);
		char[] test = tests.toCharArray();
		int lg = test.length;
		System.out.println(test[lg-1] + "" + test[lg-2] + test[lg-3] + test[lg-4]);
	}
	
	public static void testAdd3b() {
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
	
	public static void testAdd1b() {
		Circuit test = new Circuit();

		Itr a = new Itr();
		Itr b = new Itr();
		Itr ce = new Itr();
		
		test.ajouter(a, 1);
		test.ajouter(b, 2);
		test.ajouter(ce, 3);
		
		test.ajouter(new Add1b(), 4);
		
		Led s = new Led();
		Led cs = new Led();
		
		test.ajouter(s, 5);
		test.ajouter(cs, 6);

		test.connecter(1, 1, 4, 1);
		test.connecter(2, 1, 4, 2);
		test.connecter(3, 1, 4, 3);
		test.connecter(4, 1, 5, 1);
		test.connecter(4, 2, 6, 1);
		
		CircuitFerme testF = new CircuitFerme(test);
		
		testF.evaluer();
		System.out.println("A : " + a.etatGenerateur() + " B : " + b.etatGenerateur() + " CE : " + ce.etatGenerateur() + " ; S : " + s.etatRecepteur() + "  CS : " + cs.etatRecepteur() + "\n");
		
		a.ItrHaut();
		//b.ItrHaut();
		ce.ItrHaut();
		testF.evaluer();
		System.out.println("A : " + a.etatGenerateur() + " B : " + b.etatGenerateur() + " CE : " + ce.etatGenerateur() + " ; S : " + s.etatRecepteur() + "  CS : " + cs.etatRecepteur() + "\n");
		
		
	}
	
}