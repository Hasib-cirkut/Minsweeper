

int row = 20, col = 20;

int w = 20;


Cell[][] grid = new Cell[row][col];
boolean[][] options = new boolean[row][col];

void setup()
{
 
 
  
  size(400, 400);
  
  
  
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


 void mousePressed(){
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


void gameOver()
{
  for(int i=0; i<row; i++)
       {
    for(int j=0; j<col; j++)
       {
         
         grid[i][j].revealed = true;
       }
       
       }
}
  



void setOptions()
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

void draw()
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