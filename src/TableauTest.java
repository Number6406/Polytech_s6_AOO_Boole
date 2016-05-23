import java.util.ArrayList;
import java.util.List;

import jus.util.assertion.Require;

public class TableauTest{
		private List<CoupleES> Tab;
		private int nbE;
		private int nbS;
		
		public TableauTest(int nbe,int nbs){
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
			s = s + "E" + i+"|";
		}
		s = s + "\n";
		for(CoupleES couple : Tab){
			s = s + couple.toString()+"\n";
		}
		return s;
	}
}