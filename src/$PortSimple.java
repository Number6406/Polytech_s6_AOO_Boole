
public abstract class $PortSimple extends $Port {
	
	private $Composant composant;
	private boolean libre;
	
	public void liberer() {
		this.libre = true;
	}
	
	public void reserver() {
		this.libre = false;
	}
	
	public $Composant composant() {
		return this.composant;
	}
	
}
