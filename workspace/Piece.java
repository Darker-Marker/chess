
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    protected  final boolean color;
    protected BufferedImage img;
    
    // constructor
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
    
    
    public String toString(){
      if(color){
        return "white";
      }
      else{
        return "black";
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


       public ArrayList<Square> getLegalMoves(Board b, Square start) {
          return null;
      }

       
       public ArrayList<Square> getControlledSquares(Board b, Square start) {
          return null;
       }
}
