import java.util.HashMap;

import jus.util.assertion.Require;

public class Led extends $Recepteur{
	
	public Led(){
		this.nomType = "LED";
		this.etat = false; //etein
		//#TODO ne vaut-il pas mieux fair ca dans le contructeur classe mere et faire un appel a super() ?
		this.listeEntrees = new HashMap<Integer,PortEntree>();
		this.listeSorties = new HashMap<Integer,PortSortie>();; //#TODO je sais pas si vaut meiux ca ou une map vide
		
		this.listeEntrees.put(0, new PortEntree());
		
		//#TODO invariant
	}
	
	void calculer() throws Require{
		if(!(listeEntrees.get(0).estLibre())){
			throw new Require("Non : PortsEntree non Connectes");
		}
		
		this.etat = listeEntrees.get(0).obtenirValeur();
		
	}
	
}
