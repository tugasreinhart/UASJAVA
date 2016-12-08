package breakBreaker;

class Ball {
    public double x, y;    // 2d position coordinates of the ball
    public double dx, dy;  /* x and y components of velocity
                              both the components are kept equal 
                              through out the game (to achieve a 45 degree 
                              angle of impact) */
    public double radius;  /* radius of the Ball (kept constant 
			      through out the game) */
    public Ball(double x, double y, double dx, double dy, double radius) {
	this.x = x;
	this.y = y;
	this.dx = dx;
	this.dy = dy;
	this.radius = radius;
    }
}
