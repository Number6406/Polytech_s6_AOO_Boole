
public abstract class $Recepteur extends $Composant {

	protected boolean etat;

	public boolean etatRecepteur() {
		return this.etat;
	}

	public String toString(){
		return(this.nomType+"("+this.nombreEntrees()+","+this.nombreSorties()+"){"+etat+"}");
	}
}
