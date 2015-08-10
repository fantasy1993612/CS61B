public class Planet{
	public double x;
	public double y;
	public double xVelocity;
	public double yVelocity;
	public double mass;
	public String img;
	public double xNetForce;
	public double yNetForce;
	public double xAccel;
	public double yAccel;
	
	public Planet(double x, double y, double xVelocity, double yVelocity, double mass, String img){
			super();
			this.x = x;
			this.y = y;
			this.xVelocity = xVelocity;
			this.yVelocity = yVelocity;
			this.mass = mass;
			this.img = img;
	}

	public double calcDistance(Planet p){
			return Math.sqrt((this.x - p.x)*(this.x - p.x) + (this.y - p.y)*(this.y - p.y));
	}

	public double calcPairwiseForce(Planet p){
			double G = 6.67E-11; 
			return G*this.mass* p.mass/(this.calcDistance(p)*this.calcDistance(p));
	}

	public double calcPairwiseForceX(Planet p){
			return this.calcPairwiseForce(p)*(p.x-this.x)/this.calcDistance(p);
	}

	public double calcPairwiseForceY(Planet p){
			return this.calcPairwiseForce(p)*(p.y-this.y)/this.calcDistance(p);
	}

	public void setNetForce(Planet[] p){
			xNetForce = 0;
			yNetForce = 0;
			for (Planet planet:p){
				if(planet.x == this.x && planet.y == this.y){

				}else{
					xNetForce += this.calcPairwiseForceX(planet);
					yNetForce += this.calcPairwiseForceY(planet);
				}
			}
	}

	public void update(double dt){
		if(mass != 0){
			xAccel = xNetForce/mass;
			yAccel = yNetForce/mass;
		}	
		xVelocity = xVelocity + dt*xAccel;
		yVelocity = yVelocity + dt*yAccel;
		x = x + dt*xVelocity;
		y = y + dt*yVelocity;
	}

	
	public void draw(){
		StdDraw.picture(x, y, img);
	}
}
