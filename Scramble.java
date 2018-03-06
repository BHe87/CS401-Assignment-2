import java.io.*;
import java.util.Scanner;
import java.util.Random;
public class Scramble{

	File file;
	Scanner fileReader;
	boolean scrambled = true, firstRead = false, fileEnd = false;
	String lastWord = null;
	
	public Scramble(String words) throws IOException{//constructor
		fileReader = new Scanner(new File("words.txt"));
		file = new File("words.txt");
	}
	public String getRealWord(){
		String realWord;
		if(scrambled == false){
			return lastWord;
		}
		if(!fileReader.hasNext()){
			fileEnd = true;
			return null;
		}
		realWord = fileReader.nextLine();//takes in the word on the next line
		lastWord = realWord;
		scrambled = false;
		firstRead = true;
		return realWord;
	}
	public String getScrambledWord(){
			StringBuilder scrambledWord = new StringBuilder();
			StringBuilder original = new StringBuilder(getRealWord());
			Random rand = new Random(5);	
			while (original.length() != 0){
				if (fileEnd || !firstRead){//special case
					return null;
				}
				int r = rand.nextInt(original.length());//scrambles the word
				scrambledWord.append(original.charAt(r)); 
				original = original.deleteCharAt(r);
		        }
			scrambled = true;
		return scrambledWord.toString();
	}
}