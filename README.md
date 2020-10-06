# Connect-Four
How the Game Will Be Played:
The yellow player will go first, and players will alternate turns. If you count the turns, then you can
determine not only which player is active in any given turn, but you can also detect a tie (if the board is
full and neither player has achieved four-in-a-row). Number your turns starting at 1, so yellow gets the
odd-numbered turns; red gets the even-numbered ones.
This is the overall structure for the game:
Set up an empty board.
For Each Turn:
Determine which player is active (“Yellow” or “Red”, which means they will drop a ‘Y’ or ‘R’ disk).
Display the board, so the player can see where they stand.
If the board is full, declare a draw and exit.
Otherwise, prompt the current player for a column number. It is possible that the player will respond
with a column number that is not between 0 and 6. You should check for this. You don’t have to
check to make sure that they respond with an integer (and you don’t have to make sure that their input
is numeric at all). It’s also possible that the player specified a column that IS numbered between 0
and 6, but the column is full (and therefore unavailable). In either case (a full column, or an illegallynumbered
column), display the following message: “YOU ENTERED AN INVALID INTEGER OR
THE COLUMN IS FULL.”, then you should prompt the user for another column number (Scanner is
‘open’ waiting for input. No message prompt!)
Place a disk of the appropriate color in the specified column (i.e., drop the disk).
If the just-dropped disk results in four-in-a-row, display the board, declare the winner, and the game
ends.
Otherwise, switch players and go on to the next turn.
You MUST support play again function by displaying the message: “DO YOU WANT TO PLAY A
NEW GAME?”. If the user enters ‘1’, then go back to the very beginning (reset the turn counter to 1,
and clear the board). If the user enters ‘0’, the game ends.


You should have the following methods (and it may make sense for you to write them in this order):
displayBoard(char[][] board)
This method takes the current board and displays it on the console. I HAVE PROVIDED THE CODE
FOR THIS METHOD. USE THIS CODE EXACTLY AS WRITTEN.
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
dropDisk(char[][] board, int column, char color)
This method attempts to drop a disk of the specified color into the specified column. In order to do
this, you will have to examine the appropriate column of the array. If the column is completely
empty (i.e., nothing has been dropped into that column already), then the disk will fall all the way to
the bottom of that column (depending on how you interpret the rows of your board, it will “fall” to
row zero or to row 5). If that column is already completely full, then it’s not possible to drop a disk
into this column, and the move should not be allowed (the player should be prompted for a different
column number). Otherwise, we drop a disk of the appropriate color until it lands in the bottommost
empty position in that column. Your dropDisk method will need to report back if it was
successful or not.
isWinner(char[][] board)
Checks the board to see if there's a winning four-in-a-row pattern. A player can win with four
adjacent disks in a column (vertically), in a row (horizontally), or diagonally (either going up or
going down as we go to the right). Hint: in all, there are 69 ways to win – 21 vertical, 24 horizontal,
and 12 diagonal (each way)
columnIsFull(char[][] board, int col)
Will check the board and tell us if the specified column is already full
You may decide that you need another method or two, depending on how you structure your solution.
