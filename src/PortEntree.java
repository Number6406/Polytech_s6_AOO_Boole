/**@invariant : ???*/
public class PortEntree extends $Port 
{
	/**@ensure : PortLibre libre = true*/
	public PortEntree()
	{
		this.majValeur(false);
		this.liberer();
	}
	
	/***/
	public PortEntree(boolean val, boolean l)
	{
		this.majValeur(val);
		if(l){this.liberer();}
		else{this.reserver();}
	}
}
