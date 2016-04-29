package knnalgorithm;

public class Point {
/////////instance variables/////////////
	  
//x coordinate
private int x;
//y coordinate
private int y;

///////////////constructors/////////////

/*
* Name: Point
* Purpose: no arg constructor for point
* @param: none
*/
public Point(){
this(0,0);
}

/*
* Name: Point
* Purpose: creates a point 
* @param: int x, x coordinate of point
* @param: int y, y coordinate of point
*/
public Point(int x, int y){

//sets x coordinate
this.setX(x);
//sets y coordinate
this.setY(y);
}

/*
* Name: Point
* Purpose: creates a point
* @param Point point, the point to get the x and y 
*                     coordinates from
*/
public Point(Point point){

//sets x coordinate
this.setX(point.getX());
//sets y coordinate
this.setY(point.getY());
}
//////////////methods//////////////

/*
* Name: getX
* Purpose: to get the x coordinate
* @param: none
* @return: int, x coordinate
*/
public int getX(){
return this.x;
}

/*
* Name: getY
* Purpose: to get the y coordinate
* @param: none
* @return: int, y coordinate
*/
public int getY(){
return this.y;
}

/*
* Name: setX
* Purpose: to set the x coordinate to the passed parameter
* @param: int x, what to set the coordinate as
* @return none
*/
private void setX(int x){
this.x=x;
}

/*
* Name: setY
* Purpose: To set the y coordinate to the passed parameter
* @param: int y, what the set the coordinate as
* @return none
*/
private void setY(int y){
this.y=y;

}

/*
* Name: move
* Purpose: To move the point around
* @param: xDelta, amount to move x coordinate by
* @param yDelta, amount to move y coordinate by
* @return none
*/
public void move(int xDelta, int yDelta){

//new x coordinate
int newX = this.getX()+xDelta;
//new y coordinate
int newY = this.getY()+yDelta;

//sets the new coordinates
this.setX(newX);
this.setY(newY);
}

/*
* Name: toString
* Purpose: to give the properties of the point
* @param none
* @return: String, to give the properties of the point
*/
@Override
public String toString(){

//properties of point
String returnString = new String( "(" + this.getX() + 
                                "," + this.getY()+ ")" );
return returnString;
}

/*
* Name: equals
* Purpose: To check if the points are the same
* @param: Object o, the object to be checked
* @return: True, it is the same, False, it is not the same
*/
@Override
public boolean equals(Object o){
 
 //checks if it's a point
 if(o instanceof Point){
    
    //checks x,y coordinates
    if(this.getX()==(((Point)o).getX())
       &&this.getY()==(((Point)o).getY())){
          return true;
    }
 }
 return false;
}
}
