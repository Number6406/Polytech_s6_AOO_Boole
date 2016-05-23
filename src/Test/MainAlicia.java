package Test;

import java.io.FileInputStream;

import BaseComposite.Lecture;
import Circuit.*;
import Composant.*;
import Reader.Reader;
import Test_Unitaire.CircuitTestUnitaire;

public class MainAlicia {

	public static void main(String[] args) {
		//Creation circuit plus complexe
				Vcc compo1 = new Vcc();
				Et compo2 = new Et();
				Ou compo3 = new Ou();
				Itr compo4 = new Itr();
				Et compo5 = new Et();
				Itr compo6 = new Itr();
				Et compo7 = new Et();
				Led compo8 = new Led();
				
				compo4.ItrHaut();
				Circuit c3 = new Circuit();
				c3.ajouter(compo1, 1);
				c3.ajouter(compo2, 2);
				c3.ajouter(compo3, 3);
				c3.ajouter(compo4, 4);
				c3.ajouter(compo5, 5);
				c3.ajouter(compo6, 6);
				c3.ajouter(compo7, 7);
				c3.ajouter(compo8, 8);
				
				//Branchements
				c3.connecter(1, 1, 5, 1);
				c3.connecter(1, 1, 7, 2);
				c3.connecter(4, 1, 3, 1);
				c3.connecter(6, 1, 3, 2);
				c3.connecter(6, 1, 7, 1);
				c3.connecter(3, 1, 5, 2);
				c3.connecter(5, 1, 2, 1);
				c3.connecter(7, 1, 2, 2);
				c3.connecter(2, 1, 8, 1);
				
				System.out.println(c3.evaluable());
								
				CircuitTestUnitaire.tester(c3);

	}

}
