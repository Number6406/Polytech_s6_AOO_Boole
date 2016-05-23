package BaseComposite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.TreeMap;

import Circuit.Circuit;
import Reader.Reader;
import Composite.Composite;

public class Lecture 
{
	
	public static TreeMap<String,Composite> lectureComposite(String path)
	{
		TreeMap<String,Composite> liste = new TreeMap<String,Composite>(); 
		File fichierCircuit = new File(path);
		try 
		{
			Reader read = new Reader(new FileInputStream(fichierCircuit));
			liste = read.readComposite();
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier que vous voulez lire n'existe pas");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Problème de parser");
			e.printStackTrace();
		}
		return liste;
	}

}
