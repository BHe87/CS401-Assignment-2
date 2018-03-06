import java.io.*;
import java.util.Scanner;
public class Results {
	File file;
	BufferedReader BR;
	BufferedWriter BW;
	FileWriter FW;
	FileReader FR;
	PrintWriter PW;
	Scanner reader;
	int wins = 0, losses = 0, rounds = 0;
	
	public Results(String results){//constructor
		try{
			file = new File("results.txt");
			FR = new FileReader(file);
			BR = new BufferedReader(FR);
			reader = new Scanner(new File("results.txt"));
			rounds = Integer.parseInt(BR.readLine().trim());
			wins = Integer.parseInt(BR.readLine().trim());
			losses = Integer.parseInt(BR.readLine().trim());
			FW = new FileWriter(file);
			BW = new BufferedWriter(FW);
			PW = new PrintWriter(file);
		}
		catch(IOException e){
			System.out.println("Problem with the file");
		}	
	}
	public void won(){
		wins++;
		rounds++;
	}
	public void lost(){
		losses++;
		rounds++;
	}
	public void save() throws IOException{//saves data to text file by writing in the variables
		PW.print("");
		PW.close();
		BW.write(rounds+"\n");
		BW.write(wins+"\n");
		BW.write(losses+"");
		BW.flush();
	}
	public String toString(){
		String str = "";
		str = "Rounds: " + rounds + "\nWins: " + wins + "\nLosses: " + losses;//nicely formatted string representation of the Results object
		return str;
	}
}