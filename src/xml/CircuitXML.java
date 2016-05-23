package xml;

import Port.PortEntree;
import Port.PortSortie;
import Composant.*;
import Composite.*;

import java.io.*;
import java.nio.charset.Charset;

import Circuit.*;

public class CircuitXML {

	public static String ENTETE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE Test SYSTEM \"Circuit.dtd\">";
	public static String DIRECTORY = "/Polytech_s6_AOO_Boole/src/xml/"; 
	
	public void Enregistrer(Circuit c, String nom)
	{
		Writer writer;
		String circuitXML = toSTringXMLCircuit(c,nom);
		File f = new File(DIRECTORY+nom+".xml");
		Charset charset = Charset.forName("UTF-8");
		
		try 
		{
			writer = new OutputStreamWriter(new FileOutputStream(f), charset);
			writer.append(circuitXML);
			writer.close();
		} catch (IOException e) {e.printStackTrace();	}
		System.out.println("Enregistrement effectué");
	}

	
	//PortSortie
	public String toSTringXMLSortie(PortSortie sortie)
	{
		int i;
		String baliseConnexion = "    <Connexion sortie=\""+sortie.getNumPort()+"\">";
		String baliseDestination = "";
		for(i = 1; i <sortie.getNombreEntrees()+1;i++)
		{
			if(sortie.chercherIndice(i).obtenirNumComposant()==-1){return "";}
			else{baliseDestination = baliseDestination + "\n        <Destination composant=\""+sortie.chercherIndice(i).obtenirNumComposant()+"\" entree=\""+sortie.chercherIndice(i).getNumPort()+"\"/>";}
		}
		if(baliseDestination.length()==0){return "";}
		baliseDestination = baliseDestination+"\n    </Connexion>\n";
		baliseConnexion = baliseConnexion+baliseDestination;
		return baliseConnexion;
	}
	
	//Composant
	public String toSTringXMLComposant($Composant compo)	
	{
		String composant,composant2;
		String sortie="";
		int i;
		composant = "<Composant idf=\""+compo.getNum()+"\" type=\""+compo.obtenirType()+"\"/>";
		composant2 = "<Composant idf=\""+compo.getNum()+"\" type=\""+compo.obtenirType()+"\">";
		for(i = 1; i <compo.nombreSorties()+1; i++)
		{
			sortie = sortie + "\n"+toSTringXMLSortie(compo.accederPortSortie(i));
		}
		if(sortie.length()==0){return composant+"\n";}
		composant2 = composant2+sortie + "</Composant>\n";
		return composant2;
	}
	
	//Composite
	public String toSTringXMLComposite(Composite compo)	
	{
		int i,j;
		String entre ="";
		
		String sortie="";
		String interf = "";
		String destination ="";
		String composants="";
		
		String composite = "<Composite name=\""+compo.obtenirType()+"\" entrees=\""+compo.nombreEntrees()+"\" sorties=\""+compo.nombreSorties()+"\">\n";
		
		for(i = 1; i<compo.nombreEntrees()+1;i++)
		{
			destination ="    <Entree port=\""+compo.getPortEntreInterne(i).getNumPort()+"\">\n";
			for(j = 1;j<compo.getPortEntreInterne(i).getNombreEntrees()+1;j++)
			{
				destination = destination+"        <Destination composant=\""+compo.getPortEntreInterne(i).chercherIndice(j).obtenirNumComposant()+"\"entree=\""+compo.getPortEntreInterne(i).chercherIndice(j).getNumPort()+"\"/>\n";
			}
			interf = interf + destination + "    </Entree>\n";
		}
		for(i = 1; i<compo.nombreSorties();i++)
		{
			sortie = sortie+chercherSortieInterface(compo,i);
		}
		interf = interf + sortie;
		for(i = 1; i<=compo.nbComposant();i++)
		{
			System.out.println("Numéro : "+i);
			if(compo.getComposant(i) instanceof Composite){composants = composants+toSTringXMLComposite((Composite)compo.getComposant(i));}
			else{composants = composants+toSTringXMLComposant(compo.getComposant(i));}
		}
		return composite+composants+interf+"</Composite>\n";
	}
	
	public String chercherSortieInterface(Composite c, int numPortSortie)
	{
		int i,j;
		for(i=1;i<=c.nbComposant();i++)
		{
			for(j=1;j<c.getComposant(i).nombreSorties()+1;j++)
			{
				if(j <c.getComposant(i).nombreSorties()+1)
				{
					if((c.getComposant(i).accederPortSortie(j).chercherIndice(j).getNumPort()==numPortSortie)&&(c.getComposant(i).accederPortSortie(j).chercherIndice(j).obtenirNumComposant()==-1))
					{return "<Sortie interface=\""+numPortSortie+"\" composant=\""+c.getComposant(i).accederPortSortie(j).obtenirNumComposant()+"\" port=\""+c.getComposant(i).accederPortSortie(j).getNumPort()+"\"/>\n";}
				}
			}
		}
		return "";
	}
	//Circuit
	public String toSTringXMLCircuit(Circuit compo, String nom)	
	{
		int i;
		String circuit = "<"+nom+">\n";
		for(i = 1; i<compo.nombreComposant();i++)
		{
			if(compo.getComposant(i) instanceof Composite){circuit = circuit + toSTringXMLComposite((Composite) compo.getComposant(i));}
			else{circuit = circuit+toSTringXMLComposant(compo.getComposant(i));}
		}
		return circuit+"</"+nom+">";
	}
	
}
