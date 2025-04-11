

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.*;

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
	// Resource location constants for piece images
    private static final String RESOURCES_WBISHOP_PNG = "wbishop.png";
	private static final String RESOURCES_BBISHOP_PNG = "bbishop.png";
	private static final String RESOURCES_WKNIGHT_PNG = "wknight.png";
	private static final String RESOURCES_BKNIGHT_PNG = "bknight.png";
	private static final String RESOURCES_WROOK_PNG = "wrook.png";
	private static final String RESOURCES_BROOK_PNG = "brook.png";
	private static final String RESOURCES_WKING_PNG = "wking.png";
	private static final String RESOURCES_BKING_PNG = "bking.png";
	private static final String RESOURCES_BQUEEN_PNG = "bqueen.png";
	private static final String RESOURCES_WQUEEN_PNG = "wqueen.png";
	private static final String RESOURCES_WPAWN_PNG = "wpawn.png";
	private static final String RESOURCES_BPAWN_PNG = "bpawn.png";
    private static final String RESOURCES_WLASER_PNG = "wlaser.png";
    private static final String RESOURCES_BLASER_PNG = "blaser.png";
    private static final String RESOURCES_BSLIME_PNG = "blackSlime.png";
    private static final String RESOURCES_WSLIME_PNG = "whiteSlime.png";
    private static final String RESOURCES_BPOPE_PNG = "bpope.png";
    private static final String RESOURCES_WPOPE_PNG = "wpope.png";
    private static final String RESOURCES_BHELICOPTER_PNG = "bhelicopter.png";
    private static final String RESOURCES_WHELICOPTER_PNG = "whelicopter.png";
	// Logical and graphical representations of board
	private final Square[][] board;
    private final GameWindow g;
 
    //contains true if it's white's turn.
    private boolean whiteTurn;

    //if the player is currently dragging a piece this variable contains it.
    private Piece currPiece;
    private Square fromMoveSquare;
    
    //used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;
    

    
    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //TO BE IMPLEMENTED FIRST
       int i =0;
      for (int row = 0; row < 8;row++)  
      {
        i++;
        for(int col = 0; col < 8;col++)
        {
            if(i%2 ==0)
            {
                board[row][col]= new Square(this, false, row,col);

            }
            else
            {
                board[row][col]= new Square(this, true, row,col);
            }
            this.add(board[row][col]);
            i++;
        }
    }
     
      
//        	populate the board with squares here. Note that the board is composed of 64 squares alternating from 
//        	white to black.

        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }

    
	//set up the board such that the black pieces are on one side and the white pieces are on the other.
	//since we only have one kind of piece for now you need only set the same number of pieces on either side.
	//it's up to you how you wish to arrange your pieces.
    private void initializePieces() {
    	
        //back line
        board[0][0].put(new Laser(false, RESOURCES_BLASER_PNG));
        board[0][1].put(new Helicopter(false, RESOURCES_BHELICOPTER_PNG));
        board[0][2].put(new Pope(false, RESOURCES_BPOPE_PNG));
        board[0][3].put(new Queen(false, RESOURCES_BQUEEN_PNG));
        board[0][4].put(new King(false, RESOURCES_BKING_PNG));
        board[0][5].put(new Pope(false, RESOURCES_BPOPE_PNG));
        board[0][6].put(new Helicopter(false, RESOURCES_BHELICOPTER_PNG));
        board[0][7].put(new Laser(false, RESOURCES_BLASER_PNG));
        //front line
        
        
        board[1][0].put(new Slime(false, RESOURCES_BSLIME_PNG));
        board[1][1].put(new Slime(false, RESOURCES_BSLIME_PNG));
        board[1][2].put(new Slime(false, RESOURCES_BSLIME_PNG));
        board[1][3].put(new Slime(false, RESOURCES_BSLIME_PNG));
        board[1][4].put(new Slime(false, RESOURCES_BSLIME_PNG));
        board[1][5].put(new Slime(false, RESOURCES_BSLIME_PNG));
        board[1][6].put(new Slime(false, RESOURCES_BSLIME_PNG));
        board[1][7].put(new Slime(false, RESOURCES_BSLIME_PNG));



        //white:
        //back line
        board[7][0].put(new Laser(true, RESOURCES_WLASER_PNG));
        board[7][1].put(new Helicopter(true, RESOURCES_WHELICOPTER_PNG));
        board[7][2].put(new Pope(true, RESOURCES_WPOPE_PNG));
        board[7][3].put(new Queen(true, RESOURCES_WQUEEN_PNG));
        board[7][4].put(new King(true, RESOURCES_WKING_PNG));
        board[7][5].put(new Pope(true, RESOURCES_WPOPE_PNG));
        board[7][6].put(new Helicopter(false, RESOURCES_WHELICOPTER_PNG));
        board[7][7].put(new Laser(true, RESOURCES_WLASER_PNG));
        //front line
        
        
        board[6][0].put(new Slime(true, RESOURCES_WSLIME_PNG));
        board[6][1].put(new Slime(true, RESOURCES_WSLIME_PNG));
        board[6][2].put(new Slime(true, RESOURCES_WSLIME_PNG));
        board[6][3].put(new Slime(true, RESOURCES_WSLIME_PNG));
        board[6][4].put(new Slime(true, RESOURCES_WSLIME_PNG));
        board[6][5].put(new Slime(true, RESOURCES_WSLIME_PNG));
        board[6][6].put(new Slime(true, RESOURCES_WSLIME_PNG));
        board[6][7].put(new Slime(true, RESOURCES_WSLIME_PNG));

    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {
     
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square sq = board[x][y];
                if(sq == fromMoveSquare)
                	 sq.setBorder(BorderFactory.createLineBorder(Color.blue));
                sq.paintComponent(g);
                
            }
        }
    	if (currPiece != null) {
            if ((currPiece.getColor() && whiteTurn) || (!currPiece.getColor()&& !whiteTurn)) {
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (sq.isOccupied()) {
            currPiece = sq.getOccupyingPiece();
            fromMoveSquare = sq;
            if (!currPiece.getColor() && whiteTurn)
                return;
            if (currPiece.getColor() && !whiteTurn)
                return;
            sq.setDisplay(false);
        }
        repaint();
    }
public boolean isInCheck(boolean color) {
    // Loop through the board to find the king of the specified color
    Square kingSquare = null;
    for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
            Piece piece = board[row][col].getOccupyingPiece();
            if (piece instanceof King && piece.getColor() == color) {
                kingSquare = board[row][col];
                break;
            }
        }
        if (kingSquare != null) break;
    }
    
    if (kingSquare == null) {
        return false; // No king found (shouldn't happen in normal chess)
    }

    // Loop through all opponent pieces and check if they control the king's square
    for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
            Piece piece = board[row][col].getOccupyingPiece();
            // Check if the piece exists and is of the opponent's color
            if (piece != null && piece.getColor() != color) {
                // Get all squares this piece controls
                List<Square> controlledSquares = piece.getLegalMoves(this, board[row][col]);
                // Check if any controlled square is the king's square
                if (controlledSquares.contains(kingSquare)) {
                    return true;
                }
            }
        }
    }
    
    return false;
}
    //TO BE IMPLEMENTED!
    //should move the piece to the desired location only if this is a legal move.
    //use the pieces "legal move" function to determine if this move is legal, then complete it by
    //moving the new piece to it's new board location. 
    @Override
    public void mouseReleased(MouseEvent e) {
        Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
        
        if (currPiece != null && currPiece.getColor() == whiteTurn) {
            List<Square> legalMoves = currPiece.getLegalMoves(this, fromMoveSquare);
            if (legalMoves.contains(endSquare)) {
                // Save original state of squares
                Piece originalFromPiece = fromMoveSquare.getOccupyingPiece();
                Piece originalToPiece = endSquare.getOccupyingPiece();
                boolean isLaser = originalFromPiece instanceof Laser;
                int originalCol = fromMoveSquare.getCol();
                int newCol = endSquare.getCol();
                
                // Simulate the move
                fromMoveSquare.removePiece();
                endSquare.put(originalFromPiece);
                
                // Handle Laser special case during simulation
                if (isLaser && originalCol == newCol) {
                    fromMoveSquare.put(originalFromPiece);
                    endSquare.removePiece();
                }
                
                // Check if the move puts the current player in check
                boolean inCheckAfterMove = isInCheck(whiteTurn);
                
                // Undo simulation
                if (isLaser && originalCol == newCol) {
                    endSquare.put(originalToPiece);
                } else {
                    fromMoveSquare.put(originalFromPiece);
                    endSquare.put(originalToPiece);
                }
                
                // Proceed only if not in check after the move
                if (!inCheckAfterMove) {
                    fromMoveSquare.removePiece();
                    endSquare.put(originalFromPiece);
                    whiteTurn = !whiteTurn;
                    
                    // Handle Laser special case after actual move
                    if (isLaser && endSquare.getCol() == fromMoveSquare.getCol()) {
                        fromMoveSquare.put(originalFromPiece);
                        endSquare.removePiece();
                    }
                }
            }
        }
        
        fromMoveSquare.setDisplay(true);
        currPiece = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - 24;
        currY = e.getY() - 24;
        if(currPiece!= null){
            for(Square s: currPiece.getLegalMoves(this, fromMoveSquare)){
                s.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }


        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}