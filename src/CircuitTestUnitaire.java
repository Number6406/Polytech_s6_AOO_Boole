/**
 * Permet de tester un composant en créeant un circuit de test.
 * @author alicia
 *
 */
public class CircuitTestUnitaire {

	public static void main(String[] args) {
		
		$Composant compo = new Non();
		Circuit c1 = creerCircuit(compo);
		
		System.out.println(c1.toString());

	}
	
	public static void tester(Circuit c){
		
	}
	

	private static Circuit creerCircuit($Composant compo) {
		int i,j,nbcmp;
		Circuit c = new Circuit();
		
		// Créations des interrupteurs et Leds
		for(i=1;i<=compo.nombreEntrees();i++){
			c.ajouter(new Itr(), i);
		}
		c.ajouter(compo, i); // Composant principal
		nbcmp=i;
		for(j=i+1;j<compo.nombreSorties()+i+1;j++){
			c.ajouter(new Led(), j);
		}
		
		// Création des connexions
		for(i=1;i<=compo.nombreEntrees();i++){
			c.connecter(i,0,nbcmp,i-1);
		}
		for(j=i+1;j<compo.nombreSorties()+i+1;j++){
			c.connecter(nbcmp, j-i-1, j, 0);
		}
		
		return c;
	}

}
