
public class TestMaximeMAIN {

	public static void main(String[] args) {
		
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
		
		CircuitFerme cF = new CircuitFerme(c);
		
		System.out.println(cF.evaluer());
		
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
		

	}

}
