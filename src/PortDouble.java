
public class PortDouble extends $Port {

	private PortEntree entree;
	private PortSortie sortie;
	
	public PortDouble(boolean libreEntree, boolean libreSortie) {
		this.libreEntree = libreEntree;
		this.libreSortie = libreSortie;
	}
	
	public PortDouble() {
		this.libreEntree = libreEntree;
		this.libreSortie = libreSortie;
	}
	
	public void libererEntree() {
		this.entree.liberer();
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
