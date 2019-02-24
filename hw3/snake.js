var gameCanvas = document.getElementById("canvasID");
var ctx = gameCanvas.getContext("2d");
ctx.fillStyle = "#088A29"; //set snake to green
ctx.lineWidth = 2.5;

var timer;
var rect = 8;
var up = 0;
var right = 1;
var down = 2;
var left = 3;
var xPos = 0;
//start the snake at the halfway point of the canvas
var yPos = (canvasID.height/2); 
//the snake will begin moving in the right direction
var dir = right;

/* basic function is to start the timer and begin the game */
function drawSnake() { 
  //I found that 70 was a good speed to avoid gliching but also avoid moving too fast
  timer = setInterval(moveSnake, 70); 
}

/*  this function takes care of drawing the actual snake and 
    providing a message/stopping the game if a wall is hit
*/
function moveSnake() { 
  if (xPos >= 0 && yPos >= 0 && xPos <= 1000 && yPos <= 500){ //checks to make sure the gam eis contained within the canvas size
    ctx.fillRect(xPos,yPos,rect,rect); //begins the green snake
    //these if statements control the effects of left and right turns upon multiple clicks
    if(dir == up){
      yPos = yPos - rect;
    }
    else if(dir == left) {
      xPos = xPos - rect;
    }
    else if(dir == down) {
      yPos = yPos + rect;
    }
    else {
      xPos = xPos + rect;
    }
  }
  else{
    clearInterval(timer);
    alert("You hit the wall!");
  }
}

/* controls the left turn and setting the correct direction */
function turnLeft(){
  if(dir == up){
    dir = left;
  }
  else{
    dir--;
  }
}

/* controls the right turn and setting the correct direction */
function turnRight(){
  if(dir == left){
    dir = up;
  }
  else{
    dir++;
  }
}