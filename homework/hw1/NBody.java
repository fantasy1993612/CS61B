/*
Drawing the Initial Universe State
Let's head back to the main method. Add code so that after reading in the number of planets and the size of the universe, your main method does the following:

Read all the planets into an array of the appropriate size.
Set the scale of the universe. You will need to see the StdDraw documentation for how to do this.
Draw the initial state of the universe. First draw the image starfield.jpgand then draw all the planets in their appropriate locations (using the draw method of each individual planet in your array).
Test that your main method works with the following command:

java NBody 157788000.0 25000.0 data/planets.txt
You should see the sun and four planets sitting motionless. You are almost done.
*/
public class NBody{

	public static Planet getPlanet(In in){
		   Planet p = new Planet(0,0,0,0,0,"");
		   p.x = in.readDouble();
		   p.y = in.readDouble();
		   p.xVelocity = in.readDouble();
		   p.yVelocity = in.readDouble();
		   p.mass = in.readDouble();
		   p.img = "images/"+in.readString();
		   return p;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]); 
		String filename = args[2];

		In in = new In(filename);
		int N = in.readInt();
		double R = in.readDouble();
		Planet[] planets = new Planet[N];
 
		for(int i = 0;i < N;i++){
			Planet p = NBody.getPlanet(in);
            //planets[i] = new Planet(p.x,p.y,p.xVelocity,p.yVelocity,p.mass,p.img);
            planets[i] = p;
			
		}
		

    	StdDraw.setCanvasSize();
    	StdDraw.setScale(-R,R);
    	

    	for (int i = 0;i < T; i += dt){
    		StdDraw.picture(0, 0, "images/starfield.jpg");
    		for (Planet p:planets){
    			p.setNetForce(planets);
    			p.update(dt);
    			p.draw();
    		}
    		StdDraw.show(10);
    	}

		StdOut.printf("%d\n", N);
        StdOut.printf("%.2e\n", R);
        for (Planet planet:planets) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",planet.x, planet.y, planet.xVelocity,planet.yVelocity, planet.mass,planet.img);
		}
	}
}