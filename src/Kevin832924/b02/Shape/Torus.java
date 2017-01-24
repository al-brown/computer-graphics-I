package kevin832924.b01.Shape;


import java.util.Set;

import kevin832924.b01.Bib.Normal3;
import kevin832924.b01.Bib.Point3;
import kevin832924.b01.Bib.Polynomial;
import kevin832924.b01.Bib.Vec3;
import kevin832924.b01.Material.Scattered;
import kevin832924.b01.Raytracer.Color;
import kevin832924.b01.Raytracer.Hit;
import kevin832924.b01.Raytracer.Ray;

public class Torus extends Shape {
    private double innerRadius, outerRadius;
    private Vec3 position;
    private Color color;

    public Torus(Vec3 position, Vec3 normal, double innerRadius, double outerRadius, Color color, Scattered m) {
        super(m);
        this.setPosition(position);
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        this.setColor(color);
        
        //  if (normal.equals(new Vec3(1, 0, 0))) {
        //    this.localToWorld = new Transform(position);
        // 	} else {
        //    this.localToWorld = new Rotate(new Vec3(1, 0, 0), normal).add(new Translate(position));
        // 	}
    }

    @Override
	public Hit hit(Ray ray) {
        double R = outerRadius;
        double r = innerRadius;
        Vec3 d = ray.d;
        Vec3 p = new Vec3(ray.o.x,ray.o.y,ray.o.z);

        double alpha = d.dot(d);
        double beta = 2* p.dot(d);
        double gamma = p.dot(p) - r * r - R * R;

        Polynomial polynomial = new Polynomial (
                gamma*gamma + 4*R*R*p.x*p.x - 4*R*R*r*r,
                2*beta*gamma + 8*R*R*p.x*d.x, // Oh..
                beta*beta + 2*alpha*gamma + 4*R*R*d.x*d.x, 
                2*alpha*beta,
                alpha*alpha 
        ); 

        Set<Double> solutions = polynomial.solve();
        if (solutions.size() == 0) {
            return null;
        }
        
        Point3 closestIntersection = null;
        double smallestDistance = Double.POSITIVE_INFINITY;
        double distance = 0;
        for (Double t: solutions) {
            Point3 intersectionPoint = ray.o.add(ray.d.multi(t));
           distance = ray.o.distanceTo(intersectionPoint);
            if (distance < smallestDistance) {
                smallestDistance = distance;
                closestIntersection = intersectionPoint;
               //System.out.println (closestIntersection.toString()); 
               
            }
          distance =  t; 
        }
        if(closestIntersection==null)
        	return null;

        return new Hit(distance,ray,this,localNormal(closestIntersection)) ;
    }

   
    protected Normal3 localNormal(Point3 pos) {
        double common = pos.x*pos.x + pos.y*pos.y + pos.z*pos.z - innerRadius*innerRadius - outerRadius*outerRadius;

        return new Normal3 (
                4*pos.x*common + 8*outerRadius*outerRadius*pos.x,
                4*pos.y*common,
                4*pos.z*common
        );
    }


	java.awt.Color getCol() {
		return null;
	}

	public Vec3 getPosition() {
		return position;
	}

	public void setPosition(Vec3 position) {
		this.position = position;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}


