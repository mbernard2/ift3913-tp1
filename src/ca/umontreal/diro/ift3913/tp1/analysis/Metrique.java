package ca.umontreal.diro.ift3913.tp1.analysis;
import java.io.FileNotFoundException;
import java.util.Scanner;

//voici comment je le voyais

public class Metrique {
	
	public static int mesureLOCclass(File file, String nomMethode) throws FileNotFoundExeption{
		int lignes =0;
		Scanner scanner = new Scanner(file);
		//...
		return lignes;
	}
	
	public static int mesureCLOCclass(File file, String nomMethode) throws FileNotFoundExeption{
		int lignes =0;
		Scanner scanner = new Scanner(file);
		//...
		return lignes;
	}
	
	public static float mesureDC(int methCLOC, int methLOC) {   //avec une autre classe methode ou il y aura les methCLOC, methLOC,etc
		//return methCLOC/methLOC...
	}
	//...
}