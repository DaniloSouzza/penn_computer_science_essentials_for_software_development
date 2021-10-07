import java.util.Random;
import java.util.Scanner;

public class WhackAMole {
	
	int score;
	int molesLeft;
	int attemptsLeft;
	char[][] moleGrid;
	
	WhackAMole(int numAttempts, int gridDimension) {
		
		moleGrid = new char[gridDimension][gridDimension];
		attemptsLeft = numAttempts;
		score = 0;
		
		for(int i = 0; i < moleGrid.length; i++) {
			for(int j = 0; j <  moleGrid[i].length; j++) {
				moleGrid[i][j] = '*';
			}
		}
	}
	
	public boolean place(int x, int y) {		
		if(moleGrid[x][y] != 'M') {
			moleGrid[x][y] = 'M';
            molesLeft++;
            return true;
		}
		return false;
	}
	
	public void whack(int x, int y) {
		if(moleGrid[x][y] == 'M') {
			score++;
			molesLeft--;
			moleGrid[x][y] = 'W';
		}
		attemptsLeft--;
	}

    	public void printGridToUser() {
		for (int i = 0; i < this.moleGrid.length; i++) {
			for (int j = 0; j < this.moleGrid[i].length; j++) {
				if(this.moleGrid[i][j] == 'W') {
					System.out.print(this.moleGrid[i][j] + " ");
				} else {
					System.out.print("* ");
				}
			}
			System.out.print("\n");
		}
	}
	
	public void printGrid() {
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
		int moles = 10;
		
        whack.molesLeft = moles;
        
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
				
		while(whack.attemptsLeft > 0 || whack.molesLeft != 0) {
			
			whack.printGridToUser();
			
			System.out.print("\nInsert the coordinates for x:\n");
			guessX = guess.nextInt();
			
			System.out.print("Insert the coordinates for y:\n");
			guessY = guess.nextInt();
			
			if(guessX > whack.moleGrid.length || guessY > whack.moleGrid[0].length) {
				System.out.println("\nSorry, please choose a value minor than " + whack.moleGrid.length);
				
			}else {
				
				if(guessX == -1 && guessY == -1) {
					whack.attemptsLeft = 0;
					break;
				}
								
				if(whack.moleGrid[guessX - 1][guessY - 1] != '*') {
                    whack.attemptsLeft--;
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
