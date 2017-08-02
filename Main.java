package whackamole;

import java.util.Random;
import java.util.Scanner;
import whackamole.WhackAMole;

public class Main 
{
	public static void main(String[] args)
	{
		// ---------------------- introduction, rules and objectives ------------------------ //
		
		System.out.println("\n\n//====================* WELCOME TO WHACK-A-MOLE *=================\\\n");
		System.out.println("MOLES are PLACED RANDOMLY in a square girded game board (whose size is chosen by you)");
		System.out.println("Your OBJECTIVE is to WHACK ALL THE MOLES in the grid!\nYou can do so by entering COORDINATES of the place where you want to WHACK");
		System.out.println("Note: The indexing starts from 0 not 1 i.e the Coordinate of first element is (0,0)\n");
		System.out.println("You are given a LIMITED NUMBER OF ATTEMPTS\nIf you WHACK ALL THE MOLES within the given number of attempts you WIN else you LOSE");
		System.out.println("You get +10 score if you WHACK a mole. -1 if you MISS a mole");
		System.out.println("You get extra score if you WHACK MOLES CONSECUTIVELY!");
		System.out.println("You can QUIT at any point in the game by entering -1 as both x and y Co-Ordinate\n");
		System.out.println("!!! ---------- !! HAVE A GOOD GAME !! --------- !!\n\n");
		
		// ------------------------------ entering size --------------------------------//
		
		Scanner input = new Scanner(System.in);
		int size;
		System.out.println("Enter the size of the grid where you want to play (greater than or equal to 2)");
		while(true)
		{	
			size = input.nextInt();
			if(size < 2)
				System.out.println("The size should be greater than or equal to 2, please try again");
			else
				break;
		}
			
		WhackAMole newGame = new WhackAMole(size); // creating a new game of Whack-A-Mole
		
		// ------------------------ placing the moles ---------------------------- //
		
		int counter = 0, xAxis, yAxis;
		Random numGenerator = new Random(); // to generate random coordinates
		boolean key;
		while (counter < size)
		{
			xAxis = numGenerator.nextInt(size);
			yAxis = numGenerator.nextInt(size);
			key = newGame.place(xAxis,yAxis);
			if (key)
				counter++;	
		}
		
		// ---------------------------- whacking the moles ---------------------------//
		
		System.out.println("You have to WHACK " + newGame.getMolesLeft() + " moles");
		
		while (true)
		{	
			System.out.println("\nEnter the co-ordinates where you want to whack (you have " + newGame.getAttemptsLeft() + " attempts) (Enter -1,-1 coordinate to exit): ");
			System.out.println("Enter y-coordinate(0-" + (newGame.getGridDimension() - 1) + "): ");
			xAxis = input.nextInt();
			System.out.println("Enter x-coordinate(0-" + (newGame.getGridDimension() - 1) + "): ");
			yAxis = input.nextInt();
			System.out.println();
			
			if((xAxis == -1) && (yAxis == -1)) // to exit
			{
				System.out.println("You have left the game !\nHere is the solution gameboard\n");
				newGame.printGrid();
				break;
			}
			
			boolean isWhack = newGame.whack(xAxis, yAxis);
			
			if(isWhack) // checking if correct coordinates were entered
			{
				System.out.println("Let's have a look at the game board");
				newGame.printGridToUser();
				
				if (newGame.getMolesLeft() == 0)
				{
					System.out.println("You WHACKED!! all the moles! Congrajulations!!");
					break;
				}
				
				else if (newGame.getAttemptsLeft() == 0)
				{
					System.out.println("You LOST all of your ATTEMPTS! Sorry!\nHere is the solution game board");
					newGame.printGrid();
					break;
				}
				
				else
				{
					System.out.println("There are still " + newGame.getMolesLeft() +" moles left. Please try again!");
				}
			}
		}
		
		input.close();
	}

}
