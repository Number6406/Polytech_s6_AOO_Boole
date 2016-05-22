
public class Add3b extends Composite {

	public Add3b() {
		super(3,2,"Add3b");
		
		this.ajouter(new Oux(), 0);
		this.ajouter(new Oux(), 1);
		this.ajouter(new Et(), 2);
		this.ajouter(new Et(), 3);
		this.ajouter(new Et(), 4);
		this.ajouter(new Ou(), 5);
		this.ajouter(new Ou(), 6);

		this.listeEntreInterne.get(0).ajouterNumComposant(0);
		this.listeEntreInterne.get(1).ajouterNumComposant(0);
		
		this.listeEntreInterne.get(2).ajouterNumComposant(1);
		this.connecter(0, 0, 1, 0);
		
		this.listeSortieInterne.get(0).ajouterNumComposant(1);

		this.listeEntreInterne.get(0).ajouterNumComposant(2);
		this.listeEntreInterne.get(1).ajouterNumComposant(2);
		
		this.listeEntreInterne.get(1).ajouterNumComposant(3);
		this.listeEntreInterne.get(2).ajouterNumComposant(3);
		

		this.listeEntreInterne.get(2).ajouterNumComposant(4);
		this.listeEntreInterne.get(0).ajouterNumComposant(4);
		
		this.connecter(2, 0, 5, 0);
		this.connecter(3, 0, 5, 1);
		
		this.connecter(5, 0, 6, 0);
		this.connecter(4, 0, 6, 1);
		
		this.listeSortieInterne.get(1).ajouterNumComposant(6);
	}
}
