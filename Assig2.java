import java.util.Scanner;
import java.io.*;
public class Assig2{
	
	public static int tries = 3;
	public static String str;
	
	public static void main (String [] args) throws IOException{
		Scanner reader = new Scanner(System.in);
		System.out.println("Greetings!, What Is Your Name?");
		String response = reader.nextLine();
		do{
			Scramble S = new Scramble("words.txt");//instantiating Scramble Object
			Results R = new Results("results.txt");//instantiating Scramble Object
			String scrambledWord = "", playerGuess = "", correctAnswer = "";
			System.out.println("Welcome " + response + " To The Game! You Have 3 Tries to Guess a Word Given a Scrambled Version of It");
				
			while (tries > 0 ){
				scrambledWord = S.getScrambledWord();//Calling method to scramble the word
				System.out.println(scrambledWord);
				System.out.println("What Is This Word? You Have " + tries + " Tries Left");
				playerGuess = reader.nextLine();
				correctAnswer = S.lastWord;
					if (playerGuess.equalsIgnoreCase(correctAnswer)){//If the user gets it correct
						System.out.println("Correct!");
						R.won();
						R.save();
						tries = 3;
						System.out.println(R.toString());
						if(!PlayAgain(reader)){
							System.out.println("Thanks for Playing!");
							System.out.println(R.toString());
							tries = 0;
							System.exit(0);
						}
						else{
							System.out.println("Let's Play Again!");
						}
					}
					else if (!playerGuess.equalsIgnoreCase(correctAnswer)){//if the user doesn't get it correct	
						tries--;
						while(!playerGuess.equalsIgnoreCase(correctAnswer) && tries > 0){
							System.out.println("Try Again! You Have " + tries + " Tries Left. Here Are The Letters You Got Correct: ");	
							StringBuilder correctAnswerBuilder = new StringBuilder(correctAnswer);
							StringBuilder playerGuessBuilder = new StringBuilder(playerGuess);
							LetterCheck(correctAnswerBuilder, playerGuessBuilder);
							playerGuess = reader.nextLine();
							if (playerGuess.equalsIgnoreCase(correctAnswer)){
								System.out.println("Correct!");
								R.won();
								R.save();
								System.out.println(R.toString());
								tries = 3;
								PlayAgain(reader);
							}
							else if (!playerGuess.equalsIgnoreCase(correctAnswer)){
								tries--;
								if(tries == 0){
								System.out.println("Out of Tries!");
								R.lost();
								R.save();
								System.out.println(R.toString());
								}
							}
						}
						R.save();
					}
				}
				System.out.println("The Word Was: " + correctAnswer);
				R.toString();
				if(!PlayAgain(reader)){
					System.out.println("Thanks for Playing!");
					System.out.println(R.toString());
					tries = 0;
					System.exit(0);
				}
				else{
					System.out.println("Let's Play Again!");
				}
		}
		while(tries != 0);
	}
	public static void LetterCheck(StringBuilder correctAnswerBuilder, StringBuilder playerGuessBuilder){//shows the letters the user got correct in their guess
		while(playerGuessBuilder.length() != correctAnswerBuilder.length()){
			if(playerGuessBuilder.length() < correctAnswerBuilder.length()){
				playerGuessBuilder.append("_");
			}
			else if(playerGuessBuilder.length() > correctAnswerBuilder.length()){
				playerGuessBuilder.delete(correctAnswerBuilder.length(), playerGuessBuilder.length());
			}
		}
		for(int i = 0; i < correctAnswerBuilder.length(); i++){
			if(correctAnswerBuilder.charAt(i) == playerGuessBuilder.charAt(i)){
				System.out.print(correctAnswerBuilder.charAt(i));
			}
			else if (correctAnswerBuilder.charAt(i) != playerGuessBuilder.charAt(i))
				System.out.print("_");
		}
		System.out.println("");
	}
	public static boolean PlayAgain(Scanner reader){//Continues the program if they say Y
		System.out.println("Would You Like To Play Again? (Y/N)");
		String playerAgain = reader.nextLine();
		if(playerAgain.equalsIgnoreCase("Y")){
			return true;
		}
		else{
			return false;
		}
	}
} 