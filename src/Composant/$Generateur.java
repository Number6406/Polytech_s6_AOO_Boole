package Composant;
import java.util.Iterator;

//TODO généricité pour éditer etat en fonction d'un boolean ou integer
public abstract class $Generateur extends $Composant implements Iterable<Void>{

	protected boolean etat;
	
	/**
	 * 
	 * @return l'�tat du g�n�rateur
	 */
	public boolean etatGenerateur() {
		return this.etat;
	}
	
	/**
	 * Retourne un iterateur sur le generateur
	 */
	@Override
	public Iterator<Void> iterator() {
		
		Iterator<Void> I = new Iterator<Void>() {
			
			@Override
			public Void next() {
				return null;
			}
			
			@Override
			public boolean hasNext() {
				return false;
			}
		};
		
		return I;
		
	}
	
}
