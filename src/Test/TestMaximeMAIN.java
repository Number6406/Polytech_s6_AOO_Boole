package Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import BaseComposite.Base;
import BaseComposite.Lecture;
import Reader.Reader;
import xml.CircuitXML;
import Circuit.Circuit;
import Circuit.CircuitFerme;
import Composant.Et;
import Composant.Gnd;
import Composant.Itr;
import Composant.Led;
import Composant.Non;
import Composant.Ou;
import Composant.Vcc;
import Composite.Composite;

public class TestMaximeMAIN {

	public static void main(String[] args) {
		
		String cheminFichier = (new File("")).getAbsolutePath()+"/File";
		/*Circuit c = new Circuit();
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
		c2.connecter(1, 1, 2, 1);
		
		evaluable = c2.evaluable();
		System.out.println(evaluable);
		*/
		
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
		
		CircuitFerme cF2 = new CircuitFerme(c3);
		
		System.out.println(cF2.evaluer());
		Led ledR = (Led) cF2.getComposant(8);
		
		System.out.println("La LED est : "+ ledR.etatRecepteur());
		compo6.ItrHaut();
		System.out.println(cF2.evaluer());
		System.out.println("La LED est : "+ ledR.etatRecepteur());
		
		/*File fichierCircuit = new File(cheminFichier+"/circuit1.txt");
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
		
		TreeMap<String,Composite> liste = Lecture.lectureComposite(cheminFichier+"/Base.txt");
		int i =0;
		for(Map.Entry<String, Composite> entry: liste.entrySet())
		{
			System.out.println(entry.getKey()+"====> "+entry.getValue());
		}*/
		
		/*CircuitTest[
<7|Led(1,0){Eteint}>
<6|Oux(2,1)[#1(1#1,4#1),#2(3#2,2#1)
<5|Ou(2,1)->#1(#1)>
<3|Et(2,1)->#1(5#1)>
<1|Non(1,1)->#1(3#1)>
<4|Et(2,1)->#1(5#2)>
<2|Non(1,1)->#1(4#2)>
]->#1(7#1)>
<3|Ou(2,1)->#1(6#1)>
<1|Itr(0,1){bas}->#1(3#1)>
<4|Non(1,1)->#1(6#2)>
<2|Itr(0,1){bas}->#1(4#1)>
<5|Vcc(0,1)->#1(3#2)>
]*/
		
		String s = cheminFichier+"/Base.txt";
		Base b = new Base(s);
		Itr cp1 = new Itr();
		Itr cp2 = new Itr();
		Et cp3 = new Et();
		Vcc cp5 = new Vcc();
		Non cp4 = new Non();
		Composite cp6 = b.getComposite("Implique");
		Led cp7 = new Led();
		
		Circuit c4 = new Circuit();
		c4.ajouter(cp1, 1);
		c4.ajouter(cp2, 2);
		c4.ajouter(cp3, 3);
		c4.ajouter(cp4, 4);
		c4.ajouter(cp5, 5);
		c4.ajouter(cp6, 6);
		c4.ajouter(cp7, 7);
		
		//Branchements
		c4.connecter(1, 1, 3, 1);
		c4.connecter(2, 1, 4, 1);
		c4.connecter(5, 1, 3, 2);
		c4.connecter(3, 1, 6, 1);
		c4.connecter(4, 1, 6, 2);
		c4.connecter(6, 1, 7, 1);
		System.out.println(c4);
		
		Itr cpo1 = new Itr();
		Itr cpo2 = new Itr();
		Et cpo3 = new Et();
		Vcc cpo5 = new Vcc();
		Ou cpo4 = new Ou();
		Composite cpo6 = b.getComposite("Implique");
		Led cpo7 = new Led();
		
		Circuit c5 = new Circuit();
		c5.ajouter(cpo1, 1);
		c5.ajouter(cpo2, 2);
		c5.ajouter(cpo3, 3);
		c5.ajouter(cpo4, 4);
		c5.ajouter(cpo5, 5);
		c5.ajouter(cpo6, 6);
		c5.ajouter(cpo7, 7);
		
		//Branchements
		c5.connecter(1, 1, 3, 1);
		c5.connecter(2, 1, 4, 1);
		c5.connecter(1, 1, 4, 2);
		c5.connecter(5, 1, 3, 2);
		c5.connecter(3, 1, 6, 1);
		c5.connecter(4, 1, 6, 2);
		c5.connecter(6, 1, 7, 1);
		//System.out.println(c5);
		//System.out.println(c4);
		
		CircuitXML xml = new CircuitXML();
		System.out.println(xml.toSTringXMLCircuit(c5, "blop"));
		//System.out.println(xml.toSTringXMLComposant(cpo1));
		//System.out.println(xml.toSTringXMLComposant(cpo2));
		//System.out.println(xml.toSTringXMLComposite(cpo6));
		
	}

}
