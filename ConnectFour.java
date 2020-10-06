/*	DecimalConverter.java
 * 
 * The main purpose for this class is to play a connect four game between two players.
 * The first player will be yellow and the second will be red. They will enter a
 * digit from 0-6 on which column they would like to drop their colored disk into
 * after each turn, the program will display a board. This board will also be evaluated by the 
 * program after each turn, to check if there has been a winner. If the board fills up there will
 * be no winner and the program will declare a draw. After the end of every game, the program will 
 * display who has won, or display a draw. After that, the user will be asked if they would like to 
 * play again. They will enter a 1 to play again and a 0 to end the game. If they enter a 1
 * a new board with nothing on it will appear and the whole game starts all over again.
 * 
 * 	Harnoor Singh
 * 	EECS 1510 - Intro to Object Oriented
 * 	Project 2 - Connect Four
 * 	Dr. Joseph Hobbs
 * 
 */

import java.util.Scanner;

public class ConnectFour
{

	public static void main(String[] args)
    {

        Scanner input = new Scanner(System.in);
        // char[] game is the name of the array which prints the board. 
        char[][] game = new char[6][7];
        //The has won variable is created to determine if there is a winner. This will help
        //create the code clean an easy to interpret. 
        boolean hasWon = false;
        
        //Print the board. 
        printBoard(game);
        
        //No condition is required for the while loop, because all the conditions for playing the game are inside the while loop. 
        while(true)
        {
            for(int i = 0; i < 42; i++)
            {
                
                int column = input.nextInt();
                int row = 0;

                //verify input and make sure that the column is not full.
                //also this will check to make sure that the column the user selects
                //is not out of bounds. 
                if(column < 0 || column >= game[0].length || columnIsFull(game, column))
                {
                    System.out.println("YOU ENTERED AN INVALID INTEGER OR COLUMN IS FULL");
                    i--;
                    continue;
                }

                //i % 2 acts as a counter. If the number of the iteration is even, then it is yellow's turn.
                //if the iteration is odd, then it is red's turn.
                if(i % 2 == 0)
                {
                    //Yellow’s Turn
                    //drop yellow disc
                    //check if column is full
                    row = dropDisk(game, column, 'Y');
                    //check if winner
                    if(isWinner(game, row, column))
                    {
                        printBoard(game);
                        System.out.println("Y has WON the game!");
                        hasWon = true;
                        break;
                    }

                }
                else
                {
                    //red’s turn
                	//drop red disc
                	//check if column is full
                    row = dropDisk(game, column, 'R');
                    //check if winner
                    if(isWinner(game, row, column))
                    {
                        printBoard(game);
                        System.out.println("R has WON the game!");
                        hasWon = true;
                        break;
                    }

                }
                
                printBoard(game); 
                
            }
            //if there is a draw, declare a draw. 
            if(hasWon == false)
            {
                System.out.println("I declare a draw");
            }

            //Ask the user if they would like to play again.
            System.out.println("DO YOU WANT TO PLAY A NEW GAME? (type 1 for yes)");
            if(input.nextInt() == 1)
            {
                //print a new board if the user selects to play again. 
                game = new char[6][7];
                hasWon = false;
            }
            else
            {
                //end GAME
                break;
            }
        }
    }

	//This is the method that is required to display the board. This was given by Dr. Hobbs. 
    private static void printBoard(char[][] board)
    {
        for(int i = board.length - 1; i >= 0; i--)
        {
            System.out.print("| ");
            for(int j = 0; j <board[i].length; j++)
            {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();//new line
        }
    }


    //The main purpose of this method is to drop a disk in the selected column that user selects. 
    //The method then runs and determines where the disk should be dropped. The disk dropped
    //will check the column and drop a disk above another existing disk, or all the way to the 
    //bottom of that column. 
    public static int dropDisk(char[][] board, int column, char color)
    {

        //Use a for loop to asses if there is a disk at certain spot, and drop disk at spot +1.
        for(int row = 0; row < board.length; row++)
        {

            if(board[row][column] == 0)
            {
                //place "color"
                board[row][column] = color;
                return row;
            }
        }
        return -1;
        //fail safe
    }

    //The main idea for this method, is to check if the selected column is full.
    //if the column is full, then the user is prompted to enter a valid column, and
    //will be told their selection was invalid. 
    public static boolean columnIsFull(char[][] board, int column)
    {
        //if there is a top disk at this specific column, return true, and if not return false.
        if(board[board.length - 1][column] != 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }


    /* Quick note. board.length refers to the max amount of rows (6).
    ** While board[0].length refers to the max amount of columns (7).
    */
    
    /*The main purpose of the is winner method, is to check every possible scenario
     * and detect if a there is a winner. This method will be ran after every turn 
     * make sure to keep track of the last dropped disk. With this keeping track, the
     * program can check the diagonal, vertical, horizontal of every turn 
     * and determine if there is a winner situation. 
     */
  
    public static boolean isWinner(char[][] board, int row, int col)
    {
        char color = board[row][col];
        //sum is created to check if there is a winner during the isWinner method checks.
        //if the sum ends up equaling 4, then there is a winner declared. It is set to 1,
        //because the isWinner is checking for a winner from the most recent drop, which is going
        //to either be a Y or a R disk. 
        int sum = 1;


        //horizontal
        sum = 1;
        
        //evaluating the left horizontal
        for(int i = 1; i < 4 ; i++)
        {
            //check if the left characters are color
            //stop when empty/or if there is an opposing Character(colored disk)
            //stop when out of bounds (> 6 || < 0)
            if(!((col - i) < 0 || (col - i) > board.length) && board[row][col - i] == color)
            {
                sum++;
            }
            else
            {
                break;
            }
        }

        //evaluating the right horizontal
        for(int i = 1; i < 4 ; i++)
        {

            if(!((col + i) < 0 || (col + i) >= board.length) && board[row][col + i] == color)
            {
                sum++;
            }
            else
            {
                break;
            }
        }


        //if the sum red or yellow disks = 4, then there is a connect four. If
        //not then, reset the sum value equal to 1.
        //This will be carried out throughout the isWinner method as a way to determine if 
        // a player has won. 
        if(sum >= 4)
        {
            return true;
        }
        else
        {
            sum = 1;
        }

        //Evaluates the vertical and checks for winner.
        for(int i = 1; i < 4 ; i++)
        {

            if(!((row - i) < 0)  && board[row - i][col] == color)
            {
                sum++;
            }
            else
            {
                break;
            }
        }

        if(sum >= 4)
        {
            return true;
        }
        else
        {
            sum = 1;
        }

        // Now from here till the end the method will do diagonal checks.  
       
        //left(up) column - 1 && row + 1

         /*row + i  < 0 checks where on the board we are, and asses that the row we are on is not less than 0.
            **row + i >= board.length checks to make sure we aren't surpassing the last row when assessing the row.
            **(col + i) < 0 checks where on the board we are, and asses that the column we are on is not less than 0
            **(col + i) >= board[0].length checks to make sure we aren't surpassing the last column when assessing the column.
            **board[row + i][col + i] this assess if the specific spot on the board matches the color.
          */
        for(int i = 1; i < 4 ; i++)
        {

            if(!((row + i) < 0 || (row + i) >= board.length || (col - i) < 0 || (col - i) >= board[0].length) && board[row + i][col - i] == color)
            {
                sum++;
            }
            else
            {
                break;
            }
        }

        //right(down) column + 1 && row - 1
        for(int i = 1; i < 4 ; i++)
        {
            if(!((row - i) < 0 || (row - i) >= board.length || (col + i) < 0 || (col + i) >= board[0].length) && board[row - i][col + i] == color)
            {
                sum++;
            }
            else
            {
                break;
            }
        }

        if(sum >= 4)
        {
            return true;
        }
        else
        {
            sum = 1;
        }


        //left(down) column - 1 && row - 1

        for(int i = 1; i < 4 ; i++)
        {
            if(!((row + i) < 0 || (row + i) >= board.length || (col + i) < 0 || (col + i) >= board[0].length) && board[row + i][col + i] == color)
            {
                sum++;
            }
            else
            {
                break;
            }
        }


        //right(up) column + 1 && row + 1
        for(int i = 1; i < 4 ; i++)
        {
            if(!((row - i) < 0 || (row - i) >= board.length || (col - i) < 0 || (col - i) >= board[0].length) && board[row - i][col - i] == color)
            {
                sum++;
            }
            else
            {
                break;
            }
        }

        if(sum >= 4)
        {
            return true;
        }
        else {
            sum = 1;
        }

        //If no connect four's are found return false.
        return false;


    }
}






