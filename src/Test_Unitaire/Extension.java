package Test_Unitaire;
import java.util.ArrayList;
import java.util.List;

import jus.util.assertion.Require;

public class Extension{
		private List<CoupleES> Tab;
		private int nbE;
		private int nbS;
		
		public Extension(int nbe,int nbs){
			Tab = new ArrayList<CoupleES>();
			nbE = nbe;
			nbS = nbs;
		}
		/**
	 * 
	 * @param c, coupleES
	 * @require TailleLigneOK : (c.getnbe() == nbE) && (c.getnbs() == nbS)
	 */
	public void ajouterLigne(CoupleES c) throws Require{
		if(!((c.getnbe() == nbE) && (c.getnbs() == nbS))){
			throw new Require("TailleLigneOK");
		}
		Tab.add(c);
	}
	
	public String toString(){
		String s ="|";
		for (int i = 0; i < nbE; i++) {
			s = s + " E" + (i+1) +"|";
		}
		s = s+"|";
		for (int i = 0; i < nbS; i++) {
			s = s + " S" + (i+1) +"|";
		}
		s = s + "\n";
		for(CoupleES couple : Tab){
			s = s + couple.toString()+"\n";
		}
		return s;
	}
}