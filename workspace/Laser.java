import java.awt.Graphics;
import java.util.ArrayList;
//Parker Lasko
//Lazer
//attacks in a straight line taking out one piece a turn including your pieces whihc doesnt stop till it reaches your opponent's side of the baord. Can only move on the back rank.
public class Laser extends Piece {
   private int backRank;
    public Laser(boolean isWhite, String img_file) {
        super(isWhite, img_file);
        
        if (isWhite) 
       {
        backRank = 7;
       }
       else 
       {
        backRank = 0;
       }


    }

    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
       

    
            g.drawImage(this.img.getScaledInstance(50, 50, 0), x, y, null);
        
    }
    
    @Override
    public String toString() {
        return super.toString() + " Laser";
    }
    
    public ArrayList<Square> getControlledSquares(Board b, Square start) {
        ArrayList<Square> controlled = new ArrayList<>();
        int row = start.getRow();
        int col = start.getCol();
        
        // Vertical firing (up and down)
        for (int r = row - 1; r >= 0; r--) {  // Up
            Square s = b.getSquareArray()[r][col];
            controlled.add(s);
            if (s.isOccupied()) break;
        }
        
        for (int r = row + 1; r < 8; r++) {  // Down
            Square s = b.getSquareArray()[r][col];
            controlled.add(s);
            if (s.isOccupied()) break;
        }
        
        return controlled;
    }

    public ArrayList<Square> getLegalMoves(Board b, Square start) {
        ArrayList<Square> moves = new ArrayList<>();
        int row = start.getRow();
        int col = start.getCol();
        
        // Must stay on back rank for movement
        if (row == backRank) {
            // Horizontal movement (left and right)
            for (int c = col + 1; c < 8; c++) {  // Right
                Square s = b.getSquareArray()[backRank][c];
                moves.add(s);
                if (s.isOccupied()) break;
            }
            
            for (int c = col - 1; c >= 0; c--) {  // Left
                Square s = b.getSquareArray()[backRank][c];
                moves.add(s);
                if (s.isOccupied()) break;
            }
        }
        
        // Vertical firing attacks (up and down)
        for (int r = row - 1; r >= 0; r--) {  // Up
            Square s = b.getSquareArray()[r][col];
            moves.add(s);
            if (s.isOccupied()) break;
        }
        
        for (int r = row + 1; r < 8; r++) {  // Down
            Square s = b.getSquareArray()[r][col];
            moves.add(s);
            if (s.isOccupied()) break;
        }
        
        return moves;
    }
}




    