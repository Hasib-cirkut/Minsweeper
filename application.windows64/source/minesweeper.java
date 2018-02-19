import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class minesweeper extends PApplet {



int row = 20, col = 20;

int w = 20;


Cell[][] grid = new Cell[row][col];
boolean[][] options = new boolean[row][col];

public void setup()
{
 
 
  
  
  
  
  
  for(int i=0; i<row; i++)
  {
    for(int j=0; j<col; j++)
    {
      grid[i][j] = new Cell(i, j, w);
    }
    
  }
  
  setOptions();
  
   for(int i=0; i<row; i++)
  {
    for(int j=0; j<col; j++)
    {
      grid[i][j].checkMines();
    }
    
  }
  
  
}


 public void mousePressed(){
    for(int i=0; i<row; i++)
       {
    for(int j=0; j<col; j++)
       {

       if(grid[i][j].contains(mouseX, mouseY))
       {
         grid[i][j].reveal();
         
         if(grid[i][j].mine)
         gameOver();
         
       }
      
      
      }
  }
  
}


public void gameOver()
{
  for(int i=0; i<row; i++)
       {
    for(int j=0; j<col; j++)
       {
         
         grid[i][j].revealed = true;
       }
       
       }
}
  



public void setOptions()
{
  for(int i=0; i<row; i++)
  {
    for(int j=0; j<col; j++)
    {
      options[i][j] = false;
    }
  }
  
  int n = 0;
  
  while(n != 80)
  {
    int i = floor(random(0, 20));
    int j = floor(random(0, 20));
    
    if(grid[i][j].mine)
    {
     continue; 
    }
    else
    {
     grid[i][j].mine = true;
     n++;
    }
  }
  
}

public void draw()
{
   background(120);
  
  for(int i=0; i<row; i++)
  {
    for(int j=0; j<col; j++)
    {
      if(grid[i][j] != null){
        
        
      grid[i][j].show(); 
      }
      else
      {
       println("Null"); 
      }
    }
    
  }
  
}

 class Cell
 {
   float x, y, w;
   int neighbourCount = 0;
   int i, j;
   
   boolean mine;
   boolean revealed;
   
   Cell(int a, int b,  int c)
   {
     i = a;
     j = b;
     w = c;
     
     x = i * w;
     y = j * w;
     
     
       mine = false;
       
       revealed = false;
 
 }
   
   public void show()
   {
     
     stroke(100);
     strokeWeight(2);
     fill(255);
     rect(x, y, w, w);
     
     
     if(revealed){
     if(mine)
     {
       stroke(1);
       ellipse( x + w*.5f, y + w*.5f, w*.5f, w*.5f);
     }
     else
     {
      fill(200);
      noStroke();
      rect(x, y, w, w);
      //textAlign();
      if(neighbourCount > 0){
      fill(0);
      text(neighbourCount, x + w*.5f-3, y+14);
      }  
   }
     
     
     }
   }
   
   public void checkMines()
   {
     
     if(this.mine){
      
       neighbourCount = -1;
       
       return;  
          
     }
    
     int xoff, yoff;
     int total = 0;
     int i, j;
     
     for(xoff = -1; xoff<=1; xoff++)
     {
       for(yoff = -1; yoff <=1; yoff++)
       {
          i = this.i + xoff;
          j = this.j + yoff;
          
          if(i > -1 && i < row && j > -1 && j <col)
          {
            
            
            if(grid[i][j].mine == true)
              total++;
          }
       }
     }
     
     neighbourCount = total;
     
   }
   
   
   public void reveal(){
    
     revealed = true;
     
     if(neighbourCount == 0)
     {
       
       //FloodFill
       
       floodFill();
       
     }
     
   }
   
   public void floodFill()
   {
     for(int xoff = -1; xoff<=1; xoff++)
     {
       for(int yoff = -1; yoff <=1; yoff++)
       {
        int  i = this.i + xoff;
        int  j = this.j + yoff;
          
          if(i > -1 && i < row && j > -1 && j <col)
          {
            
            if(!grid[i][j].mine && !grid[i][j].revealed)
            {
              grid[i][j].reveal();
            }
          }
       }
   }
}
   
   public boolean contains(int x, int y)
   {
     return (x > this.x && x < this.x+w && y > this.y && y < this.y+w);   
   }
   
 }
   
   
   
   
   
   
 
  public void settings() {  size(400, 400); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "minesweeper" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
