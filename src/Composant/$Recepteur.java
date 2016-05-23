package Composant;

public abstract class $Recepteur extends $Composant {

	protected boolean etat;

	/**
	 * 
	 * @return l'état du recepteur
	 */
	public boolean etatRecepteur() {
		return this.etat;
	}

	/**
	 * affichage textuel d'un generateur.
	 */
	public String toString(){
		return(this.nomType+"("+this.nombreEntrees()+","+this.nombreSorties()+"){"+etat+"}");
	}
}
