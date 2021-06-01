package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

public class Square extends Polygon {
    public Square(Point3D a,Point3D b,Point3D c,Point3D d){
        super(new Point3D(0,0,0), new Point3D(0,0,1), new Point3D(0,1,0));
        if(a.equals(b) || b.equals(c) || c.equals(d)){
            throw new IllegalArgumentException("Some Vertices are the same");
        }
        double closest = a.distanceSquared(b) , ac = a.distanceSquared(c), ad = a.distanceSquared(d);
        Point3D notneighbor = b, otherone = c, othertwo = d;

        if(closest < ac)
        {
            closest = ac;
            notneighbor = c;
            otherone = b;
            othertwo = d;
        }
        if(closest < ad)
        {
            closest = ad;
            notneighbor = d;
            otherone = b;
            othertwo = c;
        }
        if(!Util.isZero(a.subtract(otherone).dotProduct(a.subtract(othertwo)))){
            throw new IllegalArgumentException("Angle between vertices is not 90");
        }
        this.vertices = List.of(a, notneighbor, otherone, othertwo);
        plane = new Plane(a,otherone,othertwo);
        if(!Util.isZero(notneighbor.subtract(a).dotProduct(plane.getNormal(a)))){
            throw new IllegalArgumentException("Vertices are not in plane");
        }
        if(!Util.isZero(notneighbor.subtract(otherone).dotProduct(notneighbor.subtract(othertwo)))){
            throw new IllegalArgumentException("Angle between vertices is not 90");
        }
    }

    @Override
    public Vector getNormal(Point3D point) {
        return super.getNormal();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }

    @Override
    public LinkedList<GeoPoint> findGeoIntersections(Ray ray) {
        LinkedList<GeoPoint> L = plane.findGeoIntersections(ray);
        if(L == null){
            return null;
        }
        if(L.isEmpty()){
            return null;
        }
        L.getFirst().geometry = this;
        if(inorout(L.getFirst().point, vertices.get(0),vertices.get(1),vertices.get(2), vertices.get(3))){
            return L;
        }
        return null;
    }
    boolean inoroutForTri(Point3D p, Point3D p0, Point3D p1, Point3D p2)
    {
        //tri 1 - 0,1,2
        //tri 1 - 0,1,3

        Vector v0 = p1.subtract(p0);
        Vector v1 = p2.subtract(p0);
        Vector v2 = p.subtract(p0);
        double dot00 = v0.dotProduct(v0);
        double dot01 = v0.dotProduct(v1);
        double dot02 = v0.dotProduct(v2);
        double dot11 = v1.dotProduct(v1);
        double dot12 = v1.dotProduct(v2);

        double invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
        double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
        double v = (dot00 * dot12 - dot01 * dot02) * invDenom;
// Check if point is in triangle
        if((u >= 0) && (v >= 0) && (u + v < 1))
        {
            return true;
        }
        return false;
    }

    public boolean inorout(Point3D p, Point3D p0, Point3D p1, Point3D p2, Point3D p22)
    {
        //tri 1 - 0,1,2
        //tri 1 - 0,1,3

        Vector v0 = p1.subtract(p0);
        Vector v1 = p2.subtract(p0);
        Vector v2 = p.subtract(p0);
        double dot00 = v0.dotProduct(v0);
        double dot01 = v0.dotProduct(v1);
        double dot02 = v0.dotProduct(v2);
        double dot11 = v1.dotProduct(v1);
        double dot12 = v1.dotProduct(v2);

        double invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
        double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
        double v = (dot00 * dot12 - dot01 * dot02) * invDenom;
// Check if point is in triangle
        if((u >= 0) && (v >= 0) && (u + v < 1))
        {
            return true;
        }

         v0 = p1.subtract(p0);
         v1 = p22.subtract(p0);
         v2 = p.subtract(p0);
         dot00 = v0.dotProduct(v0);
         dot01 = v0.dotProduct(v1);
         dot02 = v0.dotProduct(v2);
         dot11 = v1.dotProduct(v1);
         dot12 = v1.dotProduct(v2);

         invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
         u = (dot11 * dot02 - dot01 * dot12) * invDenom;
         v = (dot00 * dot12 - dot01 * dot02) * invDenom;
// Check if point is in triangle
        if((u >= 0) && (v >= 0) && (u + v < 1))
        {
            return true;
        }
        return false;
    }


    public boolean inoroutNoExtra(Point3D p)
    {
        //tri 1 - 0,1,2
        //tri 1 - 0,1,3

        Vector v0 = vertices.get(1).subtract(vertices.get(0));
        Vector v1 = vertices.get(2).subtract(vertices.get(0));
        Vector v2 = p.subtract(vertices.get(0));
        double dot00 = v0.dotProduct(v0);
        double dot01 = v0.dotProduct(v1);
        double dot02 = v0.dotProduct(v2);
        double dot11 = v1.dotProduct(v1);
        double dot12 = v1.dotProduct(v2);

        double invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
        double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
        double v = (dot00 * dot12 - dot01 * dot02) * invDenom;
// Check if point is in triangle
        if((u >= 0) && (v >= 0) && (u + v < 1))
        {
            return true;
        }

        v0 = vertices.get(1).subtract(vertices.get(0));
        v1 = vertices.get(3).subtract(vertices.get(0));
        v2 = p.subtract(vertices.get(0));
        dot00 = v0.dotProduct(v0);
        dot01 = v0.dotProduct(v1);
        dot02 = v0.dotProduct(v2);
        dot11 = v1.dotProduct(v1);
        dot12 = v1.dotProduct(v2);

        invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
        u = (dot11 * dot02 - dot01 * dot12) * invDenom;
        v = (dot00 * dot12 - dot01 * dot02) * invDenom;
// Check if point is in triangle
        if((u >= 0) && (v >= 0) && (u + v < 1))
        {
            return true;
        }
        return false;
    }
}
