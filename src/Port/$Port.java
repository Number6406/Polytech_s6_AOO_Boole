package Port;
public abstract class $Port implements _Port,Cloneable {
	
	private boolean valeur;
	private boolean libre;
	private int numComposant;
	protected int numero;
	
	/**
	 * Met a jour la valeur du port.
	 * @param la nouvelle valeur du port
	 */
	public void majValeur(boolean val)
	{
		this.valeur = val;
	}

	/**
	 * Permet de liberer un port
	 */
	public void liberer()
	{
		this.libre = true;
	}
	
	/**
	 * Permet de reserver un port.
	 */
	public void reserver()
	{
		this.libre = false;
	}
	
	/**
	 * Permet de connaitre la valeur du port
	 * @return la valeur du port
	 */
	public boolean obtenirValeur()
	{
		return valeur;
	}
	
	/**
	 * Permet de savoir si un port est libre
	 * @return true si le port est libre, false sinon
	 */
	public boolean estLibre()
	{
		return this.libre;
	}
	
	/**
	 * Permet de savoir a quel numero de composant le port appartient
	 * @return Le numero du composant auquel le port appartient
	 */
	public int obtenirNumComposant()
	{
		return this.numComposant;
	}
	
	/**
	 * Permet d'indiquer au port à quel numero de composant il appartient
	 * @param c le numero du composant
	 */
	public void ajouterNumComposant(int c)
	{
		this.numComposant = c;
	}
	
	/**
	 * 
	 * @return le numero du port.
	 */
	public int getNumPort()
	{
		return this.numero;
	}
	
}
