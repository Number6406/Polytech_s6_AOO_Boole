import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TestMaximeMAIN {

	public static void main(String[] args) {
		
		String cheminFichier = (new File("")).getAbsolutePath()+"/File";
		Circuit c = new Circuit();
		Led l = new Led();
		Non n = new Non();
		n.listeEntrees.get(0).majValeur(false);
		n.listeEntrees.get(0).reserver();
		n.listeSorties.get(0).add(l.listeEntrees.get(0));
		l.listeEntrees.get(0).reserver();
		n.listeSorties.get(0).reserver();
		c.ajouter(n, 0);
		c.ajouter(l, 1);
		
		boolean evaluable = c.evaluable();
		System.out.println(evaluable);
		
		//CircuitFerme cF = new CircuitFerme(c);
		
		//System.out.println(cF.evaluer());
		
		//Test connecter par rapport a la version au dessus.
		Circuit c2 = new Circuit();
		Led l2 = new Led();
		Non n2 = new Non();
		//Simule le fait que il y ait un generateur par exemple #TODO ce doit pas etre possible en version finale.
		n2.listeEntrees.get(0).majValeur(false);
		n2.listeEntrees.get(0).reserver();
		
		c2.ajouter(n2, 0);
		c2.ajouter(l2, 1);
		c2.connecter(0, 0, 1, 0);
		
		evaluable = c2.evaluable();
		System.out.println(evaluable);
		
		
		//Creation circuit plus complexe
		Vcc compo1 = new Vcc();
		Et compo2 = new Et();
		Ou compo3 = new Ou();
		Gnd compo4 = new Gnd();
		Et compo5 = new Et();
		Itr compo6 = new Itr();
		Et compo7 = new Et();
		Led compo8 = new Led();
		
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
		c3.connecter(1, 0, 5, 0);
		c3.connecter(1, 0, 7, 1);
		c3.connecter(4, 0, 3, 0);
		c3.connecter(6, 0, 3, 1);
		c3.connecter(6, 0, 7, 0);
		c3.connecter(3, 0, 5, 1);
		c3.connecter(5, 0, 2, 0);
		c3.connecter(7, 0, 2, 1);
		c3.connecter(2, 0, 8, 0);
		
		System.out.println(c3.evaluable());
		
		CircuitFerme cF2 = new CircuitFerme(c3);
		
		System.out.println(cF2.evaluer());
		Led ledR = (Led) cF2.getComposant(8);
		
		System.out.println("La LED est : "+ ledR.etat);
		compo6.ItrHaut();
		System.out.println(cF2.evaluer());
		System.out.println("La LED est : "+ ledR.etat);
		
		File fichierCircuit = new File(cheminFichier+"/circuit1.txt");
		try 
		{
			//Reader2 read = new Reader2(new FileInputStream(fichierCircuit));
			Reader read = new Reader(new FileInputStream(fichierCircuit));
			Circuit c4 = (Circuit) read.read();
			System.out.println(c4.toString());
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Ouuups");
			e.printStackTrace();
		}
		
	}

}
