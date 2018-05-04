package pixLab.classes;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
  
  
  /**
   * A method to add a picture as a filter over another picture. 
   * It only copies that part of a picture that isn't transparent.
   * @param startRow The row to start.
   * @param startCol The col to start.
   */
  public void austinFilter(int startRow, int startCol)
  {
	  Pixel fromPixel = null;
	  Pixel toPixel = null;
	  Picture austin = new Picture("austin.png");
	  Pixel [][] toPixels = this.getPixels2D(); // The base layer of the picture.
	  Pixel [][] fromPixels = austin.getPixels2D(); // The layer we are adding to the picture.
	  int fromRow = 0;
	  for (int toRow = startRow; fromRow < fromPixels.length && toRow < toPixels.length; 
			  toRow++)
	  {
		  int fromCol = 0;
		  for (int toCol = startCol; fromCol < fromPixels[0].length && toCol < 
				  toPixels[0].length; toCol++)
		  {
			  fromPixel = fromPixels[fromRow][fromCol];
			  toPixel = toPixels[toRow][toCol];
			  if(!fromPixel.isTransparent())
			  {
				  toPixel.setColor(fromPixel.getColor());
			  }
			  fromCol++;
		  }
		  
		  fromRow++;
	  }
	 
  }
  
  /**
   * A method that is suppose to wrap around vertical axis, copy colors onto other pixels of only red and blue values, and randomize 2-4 regions.
   */
  public void glitchFilter()
  {
//	  Pixel[][] firstPixels = this.getPixels2D();
//	  int width = firstPixels.length;
//	  
//	  for (int startCol = 0; startCol < firstPixels[0].length; startCol++)
//	  {
//		  for (int startRow = 0; startRow < firstPixels.length; startRow++)
//		  {
//			  firstPixels[startCol][startRow] = firstPixels[(startCol + 5) % width ][(startRow + 5) % width];
//		  }
//	  }
//	  
////	  for (int startCol = 0; startCol < firstPixels.length; startCol++)
////	  {
////		  for (int startRow = 0; startRow < firstPixels[0].length; startRow++)
////		  {
////			  int blue = firstPixels[startCol][startRow].getBlue();
////			  int red = firstPixels[startCol][startRow].getRed();
////			  Color temp = (blue, 0, red);
////			  firstPixels[startCol + 15][startRow + 15].setColor(temp);
////		  }
////	  }
	  
	  Pixel [][] pixels = this.getPixels2D();
	  int shiftAmount = (int) (.35 * pixels[0].length);
	  int width = pixels[0].length;
	  
	  for (int row = 0; row < pixels.length; row++)
	  {
		  Color [] currentColors = new Color[pixels[0].length];
		  
		  for (int col = 0; col < pixels[row].length; col++)
		  {
			  currentColors[col] = pixels[row][col].getColor();
		  }
		  
		  for (int col = 0; col < pixels[0].length; col++)
		  {
			  pixels[row][col].setColor(currentColors[(col + shiftAmount) % width]);
		  }
	  }
	  
	  
	  	int endHeight = 300;
	  	int endWidth = 300;
	    Pixel leftPixel = null;
	    Pixel rightPixel = null;
	    Pixel [][] pixs = this.getPixels2D();
	    
	    // loop 5 just before mirror through the rows
	    for (int row = 5; row < endWidth; row++)
	    {
	      // loop from 5 to just before the mirror point
	      for (int col = 5; col < endHeight; col++)
	      {
	        
	        leftPixel = pixs[row][col];      
	        rightPixel = pixs[endWidth - row + endWidth]                       
	                         [endHeight - col + endHeight];
	        rightPixel.setColor(leftPixel.getBlue(), rightPixel.getGreen(), leftPixel.getRed());
	      }
	    }
	    
	    int minHeight = 20;
	    int minWidth = 20;
	    int maxHeight = 260;
	    int maxWidth = 260;
	    Pixel leftPix = null;
	    Pixel rightPix = null;
	    Pixel [][] pixels2D = this.getPixels2D();
	    
	    for (int row = 20; row < maxWidth; row++)
	    {
	    	for (int col = 20; col < maxHeight; col++)
	    	{
	    		if (!(row % minWidth == 0) && !(col % minHeight == 0))
	    		{
	    			leftPix = pixels2D[row][col];
	    			rightPix = pixels2D[maxWidth - row + maxWidth][maxHeight - col + maxHeight];
	    			leftPix.setColor((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
	    			rightPix.setColor((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
	    		}
	    	}
	    }
  }
  
  /**
   * Method for copying the color of a Bob Ross picture to another picture that has a color distance less than 100 to orange. It also mirrors 80% of the picture.
   * @param fromPic The picture to copy/original picture.
   * @param startRow The starting row.
   * @param startCow The starting col.
   */
  public void bobRoss() //(Picture fromPic, int startRow, int startCow)
  {
	  Pixel [][] pixels = this.getPixels2D();
	  Picture imageRoss = new Picture("BobRoss.png");
	  Pixel [][] rossPixels = imageRoss.getPixels2D();
	  
	  for (int c = 0; c < pixels.length; c++)
	  {
		  for (int a = 0; a < pixels[0].length; a++)
		  {
			  if (pixels[c][a].colorDistance(Color.ORANGE) < 180)
			  {
				  for (int i = 0; i < rossPixels.length; i++)
				  {
					  for (int b = 0; b < rossPixels[0].length; i++)
					  {
						  if (!(rossPixels[c][a].isTransparent()))
						  {
							 pixels[c][a] = rossPixels[i][b]; 
						  }
					  }  
				  }
			  }
		  }
	  }
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
    beach.glitchFilter();
  }
  
} // this } is the end of class Picture, put all new methods before this
