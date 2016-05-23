package Test_Unitaire;


import Composant.*;
import Circuit.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jus.util.assertion.Ensure;
import jus.util.assertion.Require;

/**
 * Permet de tester un composant en créeant un circuit de test.
 * @author alicia
 * @invariant Bonne Taille : (nbE==Entrees.size()&&nbS==Sorties.size())
 */
public class CircuitTestUnitaire {

	
	/**
	 * Teste le composant Oux en créeant un circuit de test.
	 * @param args,  non necessaires
	 */
	public static void main(String[] args) {
		
		$Composant compo = new Non();
		Circuit c1 = creerCircuit(compo);
		tester(c1);
	}
	
	/**
	 * 
	 * @param G, la liste de générateurs
	 * @param R, la liste de récépteurs
	 * @param nbG, Le nombre de générateurs
	 * @param nbR, LE nombre de récépteurs
	 * @return un CoupleES avec l'état actuel des générateurs et récépteurs fournit.
	 */
	private static CoupleES creerCouple(List<$Generateur> G, List<$Recepteur> R, int nbG, int nbR){
		CoupleES c;
		// On initialise le couple
		c = new CoupleES(nbG, nbR);
		// Transferer les états des générateurs aux entrées
		int i = 0;
		for($Generateur gen : G){
			c.modifierEntree(i, gen.etatGenerateur());
			i++;
		}
		// Transferer les états des récépteurs aux entrées
		i=0;
		for($Recepteur rec : R){
			c.modifierSortie(i, rec.etatRecepteur());
			i++;
		}
		return c;
	}
	/**
	 * 
	 * @param cf, circuit à évaluer récursivement
	 * @param Tab, Tableau/Extension à remplir
	 * @param G, la liste de générateurs
	 * @param R, la liste de récépteurs
	 * @param nbG, Le nombre de générateurs
	 * @param nbR, Le nombre de récépteurs
	 * 
	 * @ensure MemeEtat : G.for(gen -> { (gen.etatGenerateur()) == (old(gen).etatGenerateur()) })
	 */
	private static void remplir(CircuitFerme cf,Extension Tab,List<$Generateur> G, List<$Recepteur> R, int nbG, int nbR) throws Ensure{
		// Copie pour verifier le ensure
		ArrayList<$Generateur> copyG = new ArrayList<$Generateur>(G);
		
		remplirTab(0, cf, Tab, G, R, nbG, nbR);
		
		boolean ensure = true;
		for(int i = 0; i < G.size();i++) {
			ensure = ensure&&(G.get(i).etatGenerateur()) == (copyG.get(i).etatGenerateur());
		}
		if(!(ensure)){
			throw new Ensure("MemeEtat");
		}
	}
	
	
	/**
	 * Remplit l'extension fournie récursivement en modifiant tout les générateurs qui peuvent l'être.
	 * @param n, indice du numéro de composant pour la récursivité
	 * @param cf, circuit à évaluer récursivement
	 * @param Tab, Tableau/Extension à remplir
	 * @param G, la liste de générateurs
	 * @param R, la liste de récépteurs
	 * @param nbG, Le nombre de générateurs
	 * @param nbR, Le nombre de récépteurs
	 */
	private static void remplirTab(int n,CircuitFerme cf,Extension Tab,List<$Generateur> G, List<$Recepteur> R, int nbG, int nbR){
		// Ajouter le cas initial lors du premier passage
		if(n == 0){
			cf.evaluer();
			Tab.ajouterLigne(creerCouple(G,R,nbG,nbR));
		}
		// A chaque passage, appeler le remplissage sur la suite du tableau avant et après avoir modifier 
		// si le générateur est modifiable
		if(n < nbG){
			remplirTab(n+1,cf,Tab, G,R,nbG,nbR);
			// Modifier l'état
			$Generateur gen = G.get(n);
			Iterator<Void> iter = gen.iterator();
			if(iter.hasNext()){
				iter.next();
				cf.evaluer();
				// Dès que l'on modifier on ajoute au tableau puis on appelle la suite
				Tab.ajouterLigne(creerCouple(G,R,nbG,nbR));
				remplirTab(n+1,cf,Tab, G,R,nbG,nbR);
				// On rétablit l'état initial de la valeur
				iter.next();
			}
		}
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
		
		// On créer les listes des générateurs et de récépteurs pour remplir l'extension
		for (int i = 1; i <= nbComposants; i++) {
			if(cf.getComposant(i) instanceof $Generateur){
				nbGenerateurs++;
				listeGenerateur.add(($Generateur)cf.getComposant(i));
			}
			if(cf.getComposant(i) instanceof $Recepteur){
				nbRecepteurs++;
				listeRecepteur.add(($Recepteur)cf.getComposant(i));
			}
		}
		
		// Tableau pour entrer toutes les entrées possibles avec leur sorties correspondantes.
		Extension Tab = new Extension(nbGenerateurs, nbRecepteurs);		
		
		remplir(cf,Tab,listeGenerateur,listeRecepteur,nbGenerateurs, nbRecepteurs);
		
		// Affichage des solutions
		System.out.println(Tab.toString());
		// Affichage du circuit
		String s = "Circuit_Test";
		System.out.println(cf.toString(s));
	}
	
	/**
	 * Crée un circuit de test pour un composant.
	 * @param compo, un composant dont on veut créer le circuit test
	 * @return un circuit avec des interrupteurs en entrées du composant et des leds en sortie.
	 */
	public static Circuit creerCircuit($Composant compo) {
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
