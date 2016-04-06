
public interface _Composant {

	public int nombreSorties();
	
	public int nombreEntrees();
	
	public String obtenirType();
	
	public void calculer();
	
	public void connecter($Port portEntree, $Port portSortie);
	
}
