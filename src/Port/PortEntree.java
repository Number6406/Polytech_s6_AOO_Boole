package Port;

/**@invariant : ???*/
public class PortEntree extends $Port 
{
	/**@ensure : PortLibre libre = true*/
	public PortEntree(int num)
	{
		this.majValeur(false);
		this.liberer();
		this.numero= num;
	}
	
	/***/
	public PortEntree(boolean val, boolean l, int num)
	{
		this.majValeur(val);
		if(l){this.liberer();}
		else{this.reserver();}
		this.numero= num;
	}
	
	/**Permet de dupliquer le port courant
	 * @return portEntre identique au port courant*/
	public PortEntree clone()
	{
		PortEntree e = new PortEntree(this.numero);	
		e.majValeur(this.obtenirValeur());
		if(this.estLibre()){e.liberer();}
		else{e.reserver();}
		e.ajouterNumComposant(this.obtenirNumComposant());
		return e;
	}
}
