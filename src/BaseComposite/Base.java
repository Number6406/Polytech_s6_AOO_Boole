package BaseComposite;
import Composite.Composite;
import jus.util.assertion.Require;

import java.util.ArrayList;
import java.util.TreeMap;

public class Base {
	
	private TreeMap<String, Composite> baseComposite;
	
	public Base(String path)
	{
		this.baseComposite = Lecture.lectureComposite(path);
	}
	
	public Composite getComposite(String s) throws Require
	{
		Composite composite = baseComposite.get(s);
		if(composite==null){throw new Require("Existe");}
		return composite.duplicate();
	}
	
	public boolean existe(String s) throws Require
	{
		Composite composite = baseComposite.get(s);
		if(composite==null){return false;}
		return true;
	}
	
	
	

}
