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
}
