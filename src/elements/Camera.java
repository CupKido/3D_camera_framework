package elements;


import primitives.*;

public class Camera {
    Point3D Position;
    Vector To;
    Vector Up;
    Vector Right;
    double VPHeight = 0; //View Plane height
    double VPWidth = 0; //View Plane width
    double PDistance = 0; //Plane Distance
    public Camera(Point3D position, Vector to, Vector up) {
        if(position == null || to == null || up == null)
        {
            throw new IllegalArgumentException("ERROR: Cannot use null value for Camera constructor");
        }
        Position = position;
        To = to.normalized();
        Up = up.normalized();
        if(To.dotProduct(Up) != 0){
            throw new IllegalArgumentException("ERROR: angle between vectors is not 90 degrees");
        }
        Right = To.crossProduct(Up).normalized();
    }
    public Vector getRight(){
        return Right;
    }



    public Vector getTo() {
        return To;
    }

    public Vector getUp() {
        return Up;
    }

    public Camera setViewPlaneSize(double width, double height){
        VPHeight = height;
        VPWidth = width;
        return this;
    }

    public Camera setDistance(double distance){
        if(distance <= 0){
            throw new IllegalArgumentException("ERROR: Distance from Camera to View Plane has to be more than 0!");
        }
        PDistance = distance;
        return this;
    }

    public Ray constructRayThroughPixel(int nX, int nY, int j, int i){
        Point3D Pc = Position.add(To.scale(PDistance));
        double Ry = VPHeight/nY;
        double Rx = VPWidth/nX;

        double Yi = -(i - (Ry - 1)/2) * Ry;
        double Xj = (j - (Rx - 1) / 2) * Rx;
        Point3D Pij = Pc;

        if(Yi != 0) Pij = Pij.add(Up.scale(Yi));
        if(Xj != 0) Pij = Pij.add(Right.scale(Xj));

        try{
            return new Ray(Position, Pij.subtract(Position));
        }catch (Exception e)
        {
            throw e;
        }



    }
}
