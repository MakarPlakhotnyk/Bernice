package geom;

import java.awt.Point;

/** Segment in Cartesian plain
 *
 * @author Makar Plakhotnyk
 */
public class Segment {
    
    private final Point a,b;
    
    public Segment(Point a, Point b){
        this.a =a;
        this.b =b;
        }
    
    public Point getClosest(Point p){
        double t = ((float)((this.b.x -this.a.x)*(p.x -this.a.x)
            +(this.b.y -this.a.y)*(p.y -this.a.y)))/
            (   (this.b.x -this.a.x)*(this.b.x -this.a.x)+
                (this.b.y-this.a.y)*(this.b.y -this.a.y));
        if ((t<0)||(t>1)){
            int d1 =(p.x-a.x)*(p.x-a.x) +(p.y-a.y)*(p.y-a.y);
            int d2 =(p.x-b.x)*(p.x-b.x) +(p.y-b.y)*(p.y-b.y);
            if (d1<d2){return a; }
            return b;
            }
        return new Point(
            (int) (a.x +t*(b.x-a.x)),
            (int) (a.y +t*(b.y-a.y)));
        }    
    
    public Point inters(Point c, Point d){
        double denom =(c.x-d.x)*(b.y-a.y)-(c.y-d.y)*(b.x-a.x);
        if (denom ==0){return null;}
        float t = (
            (float)(
                (c.x-a.x)*(b.y-a.y)-(c.y-a.y)*(b.x -a.x)))/
            ((float)denom);
        if (t<0 || t>1){
            return null;}
        float s =(
            (float)(
                (c.y-a.y)*(c.x-d.x) -(c.x-a.x)*(c.y -d.y))
                )/((float)denom);
        if (s<0 || s>1){
            return null;}
        return new Point(
            Math.round(a.x +s*(b.x-a.x)),
            Math.round(a.y +s*(b.y-a.y)));
        }     
    
    
}
