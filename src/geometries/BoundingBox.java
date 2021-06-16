package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;

public class BoundingBox {

    public Point3D getMin() {
        return min;
    }

    public Point3D getMax() {
        return max;
    }

    Point3D min, max;

    /**
     * saves the min and max points of the bounding box
     * @param _min
     * @param _max
     */
    public BoundingBox(Point3D _min, Point3D _max){
        min = _min;
        max = _max;
    }

    /**
     * finds if ray intersects with the box, if yes than returns empty list, if not returns null
     * @param ray
     * @return
     */

    public Boolean findGeoIntersections(Ray ray) {
        Point3D tempMin = min.add(new Point3D(0,0,0).subtract(ray.getP0()));
        Point3D tempMax = max.add(new Point3D(0,0,0).subtract(ray.getP0()));

        double Tx0 = tempMin.getX().getCoord() / ray.getDir().getHead().getX().getCoord();
        double Tx1 = tempMax.getX().getCoord() / ray.getDir().getHead().getX().getCoord();

        double Ty0 = tempMin.getY().getCoord() / ray.getDir().getHead().getY().getCoord();
        double Ty1 = tempMax.getY().getCoord() / ray.getDir().getHead().getY().getCoord();

        double Tz0 = tempMin.getZ().getCoord() / ray.getDir().getHead().getZ().getCoord();
        double Tz1 = tempMax.getZ().getCoord() / ray.getDir().getHead().getZ().getCoord();

        if(!checkIfInter(Tx0, Tx1, Ty0, Ty1) || !checkIfInter(Tx0, Tx1, Tz0, Tz1) || !checkIfInter(Ty0, Ty1, Tz0, Tz1)){
            return false;
        }
        return true;
    }

    /**
     * check if the ranges x0 - x1 and y0 - y1 intersects
     * @param x0
     * @param x1
     * @param y0
     * @param y1
     * @return
     */
    public boolean checkIfInter(double x0, double x1, double y0, double y1){
//        if(x1 == Double.POSITIVE_INFINITY && x0 == Double.NEGATIVE_INFINITY || y1 == Double.POSITIVE_INFINITY && y0 == Double.NEGATIVE_INFINITY){
//            return true;
//        }

        if(Math.min(x0, x1) > Math.max(y0,y1) || Math.min(y0,y1) > Math.max(x0,x1)){
            return false;
        }
        return true;
    }

    /**
     * unites two bounding box by saving the max coords and min coords
     * @param a
     * @return
     */
    public BoundingBox add(BoundingBox a){
        if(a == null){
            return this;
        }
        min = new Point3D(
                Math.min(min.getX().getCoord(), a.min.getX().getCoord()),
                Math.min(min.getY().getCoord(), a.min.getY().getCoord()),
                Math.min(min.getZ().getCoord(), a.min.getZ().getCoord())
        );

        max = new Point3D(
                Math.max(max.getX().getCoord(), a.max.getX().getCoord()),
                Math.max(max.getY().getCoord(), a.max.getY().getCoord()),
                Math.max(max.getZ().getCoord(), a.max.getZ().getCoord())
        );
        return this;
    }
}
