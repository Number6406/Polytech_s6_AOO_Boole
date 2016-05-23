import java.util.ArrayList;
import java.util.List;

import jus.util.assertion.Invariant;
import jus.util.assertion.Require;

/**
 * Permet de tester un composant en créeant un circuit de test.
 * @author alicia
 * @invariant Bonne Taille : (nbE==Entrees.size()&&nbS==Sorties.size())
 */
public class CircuitTestUnitaire {

	
	public static void main(String[] args) {
		
		$Composant compo = new Oux();
		Circuit c1 = creerCircuit(compo);
		
		System.out.println(c1.toString());
		
		TableauTest T = new TableauTest(2, 1);
		CoupleES c = new CoupleES(2,1);
		T.ajouterLigne(c);
		System.out.println(T.toString());
		
		
	}
	
	/**
	 * Affiche la totalité des combinaisons (entrées, sorties) possibles
	 * @param c un circuit à tester
	 * @require CircuitFerme : c.evaluable()
	 */
	public static void tester(Circuit c) throws Require{
		
		if(!(c.evaluable())){
			throw new Require("tester : CircuitFerme");
		}
		
		CircuitFerme cf =new CircuitFerme(c);
		int nbComposants = cf.nombreComposant();
		int nbGenerateurs = 0;
		int nbRecepteurs = 0;
		ArrayList<$Generateur> listeGenerateur = new ArrayList<$Generateur>(); 
		ArrayList<$Recepteur> listeRecepteur = new ArrayList<$Recepteur>(); 
		
		for (int i = 0; i < nbComposants; i++) {
			if(cf.getComposant(i) instanceof $Generateur){
				nbGenerateurs++;
				listeGenerateur.add(($Generateur)cf.getComposant(i));
			}
			if(cf.getComposant(i) instanceof $Recepteur){
				nbRecepteurs++;
				listeRecepteur.add(($Recepteur)cf.getComposant(i));
			}
		}
		
		TableauTest Tab = new TableauTest(nbGenerateurs, nbRecepteurs);
		
		
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
			c.connecter(i,1,nbcmp,i);
		}
		for(j=i+1;j<compo.nombreSorties()+i+1;j++){
			c.connecter(nbcmp, j-i, j, 1);
		}
		
		return c;
	}

}
