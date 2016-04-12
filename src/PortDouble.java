
public class PortDouble extends $Port {

	private boolean libreEntree;
	private boolean libreSortie;
	
	public PortDouble(boolean libreEntree, boolean libreSortie) {
		this.libreEntree = libreEntree;
		this.libreSortie = libreSortie;
	}
	
	public void libererEntree() {
		this.libreEntree = true;
	}
	
	public void reserverEntree() {
		this.libreEntree = false;
	}
	
	public void libererSortie() {
		this.libreSortie = true;
	}
	
	public void reserverSortie() {
		this.libreSortie = false;
	}
	
}
