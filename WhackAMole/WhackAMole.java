import java.util.Random;
import java.util.Scanner;

public class WhackAMole {
	
	int score;
	int molesLeft;
	int attemptsLeft;
	char[][] moleGrid;
	
	public WhackAMole(int numAttempts, int gridDimension) {
		attemptsLeft = numAttempts;
		molesLeft = gridDimension;
		
		moleGrid = new char[gridDimension][gridDimension];
		
		for(int i = 0; i < moleGrid.length; i++) {
			for(int j = 0;j <  moleGrid[i].length; j++) {
				moleGrid[i][j] = '*';
			}
		}
	}
	
	boolean place(int x, int y) {		
		if(moleGrid[x][y] != 'M') {
			moleGrid[x][y] = 'M';
			return true;
		}
		return false;
	}
	
	void whack(int x, int y) {
		if(moleGrid[x][y] == 'M') {
			score++;
			molesLeft--;
			moleGrid[x][y] = 'W';
		}
		attemptsLeft--;
	}

	void printGridToUser() {
		for(int i = 0; i < moleGrid.length; i++) {
			for(int j = 0; j <  moleGrid[i].length; j++) {
				if(moleGrid[i][j] == 'M') {
					System.out.print("* ");	
				}else {
					System.out.print(moleGrid[i][j] + " ");					
				}
			}
			System.out.print("\n");
		}
	}
	
	void printGrid() {
		for(int i = 0; i < moleGrid.length; i++) {
			for(int j = 0; j <  moleGrid[i].length; j++) {
				System.out.print(moleGrid[i][j] + " ");					
			}
			System.out.print("\n");
		}
	}
	
	public static void main(String[] args) {
		WhackAMole whack = new WhackAMole(50, 10);
		Random rnd = new Random();
		
		int i;
		int j;
		int moles = whack.molesLeft;
		
		while(moles > 0) {
			i = rnd.nextInt(9);
			j = rnd.nextInt(9);
			
			if(whack.moleGrid[i][j] == '*') {
				whack.place(i, j);
				moles--;
			}
		}
		
		Scanner guess = new Scanner(System.in);
		
		int guessX;
		int guessY;
				
		while(whack.attemptsLeft > 0 || whack.attemptsLeft > 0) {
			
			whack.printGridToUser();
			
			System.out.print("\nInsert the coordinates for x:\n");
			guessX = guess.nextInt();
			
			System.out.print("Insert the coordinates for y:\n");
			guessY = guess.nextInt();
			
			if(guessX > whack.moleGrid.length - 1 && guessY == whack.moleGrid[0].length - 1) {
				System.out.println("\nSorry, please choose a value minor than " + whack.moleGrid.length);
				
			}else {
				
				if(guessX == -1 && guessY == -1) {
					whack.attemptsLeft = 0;
					break;
				}
				
				whack.attemptsLeft--;
				
				if(whack.moleGrid[guessX - 1][guessY - 1] != '*') {
					whack.whack(guessX - 1, guessY - 1);
					System.out.println("\nYou scored +1!\nRemaining attempts: " + whack.attemptsLeft + "\n\n");
					
				}else {
					System.out.print("\nYou missed :(\nRemaining attempts: " + whack.attemptsLeft + "\n\n");
				}		
			}
		}
		
		guess.close();
		
		for(int line = 0; line < 3; line++) {
			System.out.println("");			
		}
		
		System.out.println("Thanks for playing!\nScore: " + whack.score + 
						   "\nAttempts Left: " + whack.attemptsLeft);
		whack.printGrid();
	}
}
