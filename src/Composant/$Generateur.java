package Composant;
import java.util.Iterator;

public abstract class $Generateur extends $Composant implements Iterable<Void>{

	protected boolean etat;
	
	public boolean etatGenerateur() {
		return this.etat;
	}
	
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
