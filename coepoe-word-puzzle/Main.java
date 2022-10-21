import java.util.Scanner;
import java.util.Arrays;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		int level = 1;
		int scoreLevel;
		int scoreTotal = 0;
		int rightCounter;
		int answerCounter;
		String answer;
		String[][] answerKey = 
		{	//level 1
			{"die","led","lei","let","lid","lie","lit","tie","deli","diet","edit","idle",
			"idle","lied","tide","tied","tile","tilt","tilde","tiled","title","tilted","titled"},
			//level 2
			{"bet","bin","bit","net","son","sin","sit","tie","set","bent","nest","bits","sent",
			"bets","site","nite","bite","bents","stein","bites"},
			//level 3
			{"friend","finder","fired","fried","diner","dire","find","fire","dine","nerd","ride","rein",
			"dif","fed","fin","die","end","red","fen"}
		};
		
		String answerBuffer[] = new String[10];
		String play;
		boolean replay = true;
		
		System.out.println("");
		System.out.println("==================");
		System.out.println("Coepoe Word Puzzle");
		System.out.println("==================");
		System.out.println("");
		System.out.println("Rules :");
		System.out.println("1. Create a word using given characters, min 3 characters & max 6 characters.");
		System.out.println("2. Each level, You have 10 chances to answer correctly.");
		System.out.println("3. To get through to next level, you have to score 70 points each level.");
		System.out.println("");
		Scanner scanner = new Scanner(System.in);
			
		//Repeat each level until get score >= 70 or until reach level 3 
		while (replay && level <=3){
			System.out.println("Level " + level);
			System.out.println("-------");
				
			getAnswerClue(level-1,answerKey);
				
			System.out.println("");
			System.out.println("");
				
			answerCounter = 1;
			rightCounter = 0;
			scoreLevel = 0;
			
			//Get answer 10 times	
			while (answerCounter <= 10){
				System.out.print("Your Answer : ");
				answer = scanner.nextLine();
				if (checkAnswerLength(answer)) {
					if (!checkDuplicateAnswer(answer, answerBuffer)){
						System.out.println(answerCounter + "> Your Answer is : " + answer);
						if (checkAnswerResult(level, answer, answerKey)) {
							scoreLevel += 10;
							rightCounter += 1;
							System.out.println("#Right. Score : " + scoreLevel );	
						} 
						answerBuffer[answerCounter-1] = answer;
						answerCounter++;
					} else {
						System.out.println("You had already use this word..");
					}
				} else {
					System.out.println("Only words contain 3 to 6 letters are allowed..");
				}
			} 
				
			//Level Done.. check result
			System.out.println("");
			System.out.println("You Had answered 10 times with " + rightCounter + " right answers..");
			System.out.println("");
			System.out.print("Your Answer : ");
				
			for (int i=0;i<10;i++){
				System.out.print(answerBuffer[i] + " ");
			}
			//if score > 70 go to next level
			if (scoreLevel >= 70){
				System.out.println("");
				System.out.println("");
				System.out.println("Correct Answer : ");
				
				int keyLn = answerKey[level-1].length;
				int keyRow = (keyLn / 10) + (keyLn % 10);
				for (int i=0;i < keyRow;i++){
					for (int j=0;j<10;j++){
						int keyCol = (i*10)+j;
						if (keyCol < keyLn) {
							if (j == 9) {
								System.out.println(answerKey[level-1][keyCol] + "  ");
							} else
								System.out.print(answerKey[level-1][keyCol] + "  ");
						}
					}
				}
					
				scoreTotal += scoreLevel;
				level++;
			} else {
				boolean choice=false;
				String finishLevel="";
					
				while (!choice){
					System.out.println("");
					System.out.println("You Lose!! Try Again");
					System.out.print("Do You want to retry ?[y/n]");
					play = scanner.nextLine();
					if (play.equalsIgnoreCase("n")||play.equalsIgnoreCase("y")) {
						choice = true;
						finishLevel = play;
					}
				}
				if (finishLevel.equalsIgnoreCase("n")) {
					replay = false;
				}
				
			}
			System.out.println("");
			System.out.println("");
		}
			
		//3 Levels has done..check final result
		if (level == 4){
			if (scoreTotal >= 70){
				System.out.println("");
				System.out.println("Overal Score : " + scoreTotal);
				System.out.println("You Win!!");
				System.out.print("Press ENTER to Exit..");
				play = scanner.nextLine();
			} 
		}
		
		System.out.println("Game Finished");
	}
	
	//Validate answer length
	public static boolean checkAnswerLength(String answer) {
		if(answer == null){
		return false;
		}
		int length = answer.length();
		return length >= 3 && length <= 6;
	}
	
	//check for duplicate answer
	public static boolean checkDuplicateAnswer(String answer, String[] answerBuffer){
		boolean result = false;
		
		for (int i=0;i<answerBuffer.length;i++){
			if (answer.equalsIgnoreCase(answerBuffer[i])){
				result = true;
			}
		}	
		return result;
	}
	
	// Compare user answer with answers stored in arrays 
	public static boolean checkAnswerResult(int level, String answer, String[][] answerKey) {
		boolean result = false;
		
		for (int i=0;i<answerKey[level-1].length;i++){
			if (answer.equalsIgnoreCase(answerKey[level-1][i])){
				result = true;
			}
		}
		return result;
	}
	
	//List All clue characters from arrays of answer words
	public static void getAnswerClue(int level, String[][] answerKey) {
		int answerLn = answerKey[level].length;
		int clueLn = 0;
		List<String> listClue = new ArrayList<String>();

		for (int i=0;i<answerLn;i++){
			int wordsLn = answerKey[level][i].length();
			for (int j=0;j<wordsLn;j++){		
				char checkValue = answerKey[level][i].charAt(j);
				boolean charExists = listClue.contains(String.valueOf(checkValue));
				
				if (!charExists){
					listClue.add(String.valueOf(checkValue));
					System.out.print("  " + checkValue + "  ");
				}		
			}
		}
		System.out.println("");
	}

}

	