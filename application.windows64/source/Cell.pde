
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
   
   void show()
   {
     
     stroke(100);
     strokeWeight(2);
     fill(255);
     rect(x, y, w, w);
     
     
     if(revealed){
     if(mine)
     {
       stroke(1);
       ellipse( x + w*.5, y + w*.5, w*.5, w*.5);
     }
     else
     {
      fill(200);
      noStroke();
      rect(x, y, w, w);
      //textAlign();
      if(neighbourCount > 0){
      fill(0);
      text(neighbourCount, x + w*.5-3, y+14);
      }  
   }
     
     
     }
   }
   
   void checkMines()
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
   
   
   void reveal(){
    
     revealed = true;
     
     if(neighbourCount == 0)
     {
       
       //FloodFill
       
       floodFill();
       
     }
     
   }
   
   void floodFill()
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
   
   boolean contains(int x, int y)
   {
     return (x > this.x && x < this.x+w && y > this.y && y < this.y+w);   
   }
   
 }
   
   
   
   
   
   
 