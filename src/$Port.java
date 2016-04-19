public abstract class $Port implements _Port {
	
	private boolean valeur;
	private boolean libre;
	
	public void majValeur(boolean val)
	{
		this.valeur = val;
	}

	public void liberer()
	{
		this.libre = true;
	}
	
	public void reserver()
	{
		this.libre = false;
	}
	
	public boolean obtenirValeur()
	{
		return valeur;
	}
	
	public boolean estLibre()
	{
		return this.libre;
	}
	
	
}
