import java.util.ArrayList;

import jus.util.assertion.Require;

public interface _PortSortie extends _Port{
	public PortEntree enleverUneSortie(PortEntree e);
	
	public PortEntree chercher(PortEntree e);

	public PortEntree chercherIndice(int indice) throws Require;
	
	public ArrayList<PortEntree> getEntrees();

}
