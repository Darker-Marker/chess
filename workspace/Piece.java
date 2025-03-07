
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;
    
    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;
        
        try {
            if (this.img == null) {
              this.img = ImageIO.read(getClass().getResource(img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }
    
    

    
    public boolean getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }
    
    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
        g.drawImage(this.img, x, y, null);
    }
    
    //my peice can't leave the back rank and attack from range with a laser
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
       
    //it will return all controlled squares 
    //it will take the pieces current position and the places of other peices on the board
    public ArrayList<Square> getControlledSquares(Board b, Square start) {
      ArrayList<Square> controlled = new ArrayList<Square>();
      int sCol = start.getCol();
      int sRow = start.getRow();
      for (int z = sRow; z < 8-sRow;z++ )
      {
        if(b.getSquareArray()[z][sCol].isOccupied() != true)
        {
          controlled.add(b.getSquareArray()[z][sCol]);
        }
        else{
          controlled.add(b.getSquareArray()[z][sCol]);
          
          break;
        }




        for (int y = sRow; y > 0;y-- )
      {
        if(b.getSquareArray()[y][sCol].isOccupied() != true)
        {
          controlled.add(b.getSquareArray()[y][sCol]);
        }
        else{
         if (y != sRow)
         {

         }
          controlled.add(b.getSquareArray()[y][sCol]);
          
          break;
        }
      
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.

    //my peice can only travel amoung the back row of what ever color it is
    //it will return all possible moves 
    //it will take the pieces current position and the places of other peices on the board
    public ArrayList<Square> getLegalMoves(Board b, Square start){
      ArrayList<Square> moves = new ArrayList<Square>();
      int sCol = start.getCol();
      int sRow = start.getRow();
    
//possible moves
      for (int i = sCol; i < 8-sCol;i++ )
{
  if(b.getSquareArray()[sRow][i].isOccupied() != true)
  {
    moves.add(b.getSquareArray()[sRow][i]);
  }
  else{
    break;
  }
  for (int x = sCol; x < 0;x-- )
  {
    if(b.getSquareArray()[sRow][x].isOccupied() != true)
    {
      moves.add(b.getSquareArray()[sRow][x]);
    }
    else{
      break;
    }
  }





        }
       
    	return moves;
    }
  }