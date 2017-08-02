package homeWorkAssignment1;

public class WhackAMole 
{
	// ------------------ Instance variables ----------------------------- //
	
	private int score;
	private int molesLeft;
	private int attemptsLeft;
	private int gridDimension;
	boolean isConsecutive;
	private char[][] moleGrid;
	int multiplier;
	
	// --------------------------- Constructor ------------------------ //
	
	public WhackAMole (int gridDimension)
	{
		this.gridDimension = gridDimension;
		attemptsLeft = (int)((gridDimension * gridDimension)/2);
		moleGrid = new char[gridDimension][gridDimension];
		
		for (int i = 0; i < gridDimension; i++)
			for (int j=0; j < gridDimension; j++)
				moleGrid[i][j] = '*';
		
		molesLeft = 0;
		score = 0;
		isConsecutive = false;
		multiplier = 1;
	}
	
	// ------------------------------ Methods --------------------------- //
	
	// to place the moles in the correct coordinates
	public boolean place (int x, int y)
	{
		if ((x > gridDimension) || (y > gridDimension) || (moleGrid[x][y] == 'M'))
			return false;
		else
		{
			moleGrid[x][y] = 'M';
			molesLeft++;
			return true;
		}
		
	}
	
	// to whack the mole at the coordinate entered by the user
	public boolean whack (int x, int y)
	{
		if ((x >= gridDimension) || (y >= gridDimension) || (x < 0) || (y < 0))
		{
			System.out.println("Invalid Co-ordinates, Please enter x and y between 0-" + gridDimension);
			return false;
		}
		
		else if ((moleGrid[x][y] == 'W'))
		{
			System.out.println("Already WHACKED, please enter a different Co-ordinate");
			return false;
		}
		
		else
		{
			if (moleGrid[x][y] == 'M')
			{
				System.out.println("You WHACKED a mole");
				moleGrid[x][y] = 'W';
				molesLeft --;
				attemptsLeft--;
				if(isConsecutive)
				{	
					multiplier++;
					score = score + (10 * multiplier);
				}
				else
					score += 10;
				isConsecutive = true;
			}
			else
			{
				System.out.println("You MISSED the mole");
				moleGrid[x][y] = 'W';
				attemptsLeft--;
				score--;
				isConsecutive = false;
			}
			
			return true;
		}
	}
	
	//to print grid, hiding the locations of the moles
	public void printGridToUser ()
	{
		for (int i = 0; i < moleGrid[0].length; i++)
		{
			for(int j=0; j < moleGrid[0].length; j++)
			{
				if (moleGrid[i][j] == 'M')
					System.out.print("* ");
				else
					System.out.print(moleGrid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("Your SCORE is "+score);
	}
	
	// to print grid, showing the location of the moles
	public void printGrid()
	{
		for (int i = 0; i < moleGrid[0].length; i++)
		{
			for(int j=0; j < moleGrid[0].length; j++)
				System.out.print(moleGrid[i][j] + " ");
			System.out.println();
		}
		System.out.println("Your FINAL SCORE is "+score);
	}
	
	// --------------------------- Getters ------------------------------ //

	public int getMolesLeft() 
	{
		return molesLeft;
	}

	public int getAttemptsLeft() 
	{
		return attemptsLeft;
	}
	
	public int getGridDimension()
	{
		return gridDimension;
	}
}
