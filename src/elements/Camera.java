package elements;


import primitives.*;

import java.util.LinkedList;
import java.util.Random;

import static primitives.Util.*;

public class Camera {

    Point3D Position;
    Vector To;
    Vector Up;
    Vector Right;
    double VPHeight = 0; //View Plane height
    double VPWidth = 0; //View Plane width
    double PDistance = 0; //Plane Distance
    double MoveSize = 1;

    public Camera(Point3D position, Vector to, Vector up) {
        if(position == null || to == null || up == null)
        {
            throw new IllegalArgumentException("ERROR: Cannot use null value for Camera constructor");
        }
        Position = position;
        To = to.normalized();
        Up = up.normalized();
        if(!Util.isZero(To.dotProduct(Up))){
            throw new IllegalArgumentException("ERROR: angle between vectors is not 90 degrees");
        }
        Right = To.crossProduct(Up).normalized();
    }
    public Vector getRight(){
        return Right;
    }

    public void setMovementSize(double MS){
        if(MS <= 0)
        {
            throw new IllegalArgumentException("ERROR: Movement size must be more than 0.");
        }
        MoveSize = MS;
    }

    public void MoveForward(double times){
        if(times == 0)
        {
            return;
        }
        Position = Position.add(To.scale(MoveSize * times));
    }

    public void MoveBackwards(double times){
        MoveForward(times * (-1));
    }

    public void MoveRight(double times){
        if(times == 0)
        {
            return;
        }
        Position = Position.add(Right.scale(MoveSize * times));
    }

    public void MoveLeft(double times){
        MoveRight(times * (-1));
    }

    public void MoveUp(double times){
        if(times == 0)
        {
            return;
        }
        Position = Position.add(Up.scale(MoveSize * times));
    }

    public void MoveDown(double times){
        MoveUp(times * (-1));
    }

    public void LookRight(double deg){
        RTwist(deg);
    }

    public void LookLeft(double deg){
        RTwist(-deg);
    }

    public void LookUp(double deg){
        UTwist(deg);
    }

    public void LookDown(double deg){
        UTwist(-deg);
    }

    public void TwistClockWise(double deg){
        Twist(deg);
    }

    public void TwistCounterClockWise(double deg){
        Twist(-deg);
    }

    public Vector getTo() {
        return To;
    }

    public Vector getUp() {
        return Up;
    }

    /**
     * sets size of the view plane
     * on a physical camera its the size of the sensor
     * @param width
     * @param height
     * @return
     */
    public Camera setViewPlaneSize(double width, double height){
        VPHeight = height;
        VPWidth = width;
        return this;
    }

    /**
     * set distance from camera to view plane
     * in a physical camera its mm
     * @param distance
     * @return
     */
    public Camera setDistance(double distance){
        if(distance <= 0){
            throw new IllegalArgumentException("ERROR: Distance from Camera to View Plane has to be more than 0!");
        }
        PDistance = distance;
        return this;
    }

    /**
     * creates a ray from the camera to (j, i) on the view plane's grid
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return
     */
    public Ray constructRayThroughPixel(int nX, int nY, double j, double i){
        Point3D Pc = Position.add(To.scale(PDistance));
        double Ry = VPHeight/nY;
        double Rx = VPWidth/nX;

        double Xj = (j - (double)(nX-1)/2) * Rx;
        double Yi = -(i - (double)(nY-1)/2) * Ry;

        Point3D Pij = Pc;

        if(!isZero(Yi)) Pij = Pij.add(Up.scale(Yi));
        if(!isZero(Xj)) Pij = Pij.add(Right.scale(Xj));

        try{
            return new Ray(Position, Pij.subtract(Position));
        }catch (Exception e)
        {
            throw e;
        }



    }

    void RTwist(double deg)
    {
        deg = deg % 360;
        if(deg < 0) deg = 360 - deg;
        if(deg % 90 == 0)
        {
            if(deg == 0)
                return;
            if(deg == 90)
            {
                Vector temp = To.scale(-1);
                To = Right;
                Right = temp;
            }
            else if(deg == 180)
            {
                To = To.scale(-1);
                Right = Right.scale(-1);
            }
            else //deg = 270
            {
                Vector temp = Right.scale(-1);
                Right = To;
                To = temp;
            }
            return;
        }else
        Up = (Up.scale(Math.cos(deg)).crossProduct(Right.scale(Math.sin(deg)))).normalized();
        Right = To.crossProduct(Up).normalized();
    }

    void UTwist(double deg)
    {
        deg = -(deg % 360);
        if(deg < 0) deg = 360 - deg;
        if(deg % 90 == 0)
        {
            if(deg == 0)
                return;
            if(deg == 90)
            {
                Vector temp = Up.scale(-1);
                Up = To;
                To = temp;
            }
            else if(deg == 180)
            {
                Up = Up.scale(-1);
                To = To.scale(-1);
            }
            else //deg = 270
            {
                Vector temp = To.scale(-1);
                To = Up;
                Up = temp;
            }
            return;
        }
        Up = (Up.scale(Math.cos(deg)).crossProduct(To.scale(Math.sin(deg)))).normalized();
        To = Up.crossProduct(Right).normalized();
    }

    void Twist(double deg)
    {
        deg = deg % 360;
        if(deg < 0) deg = 360 - deg;
        if(deg % 90 == 0)
        {
            if(deg == 0)
                return;
            if(deg == 90)
            {
                Vector temp = Up.scale(-1);
                Up = Right;
                Right = temp;
            }
            else if(deg == 180)
            {
                Up = Up.scale(-1);
                Right = Right.scale(-1);
            }
            else //deg = 270
            {
                Vector temp = Right.scale(-1);
                Right = Up;
                Up = temp;
            }
            return;
        }
        To = (To.scale(Math.cos(deg)).crossProduct(Right.scale(Math.sin(deg)))).normalized();
        Right = To.crossProduct(Up).normalized();
    }

    /**
     * creates RAYS^2 rays from the camera to random points on the pixel's grid on (col, row) of the grid
     * @param nX
     * @param nY
     * @param col
     * @param row
     * @param RAYS
     * @return
     */
    public LinkedList<Ray> constructRaysThroughPixel(int nX, int nY, int col, int row, int RAYS) {
        LinkedList<Ray> L = new LinkedList<>();

        if(RAYS == 1){
            L.add(constructRayThroughPixel(nX, nY, col, row));
            return L;
        }

        // pIJ = pCenter
        Point3D pIJ = Position.add(To.scale(PDistance));
        // Ratio
        double rX = VPWidth/nX;
        double rY = VPHeight/nY;
        // Xj, Yi
        double xJ = (col - (double)(nX-1)/2) * rX;
        double yI = -(row - (double)(nY-1)/2) * rY;
        // adding to pCenter
        if(!isZero(xJ)) pIJ = pIJ.add(Right.scale(xJ));
        if(!isZero(yI)) pIJ = pIJ.add(Up.scale(yI));

        // Bottom Left of pixel
        Point3D bottomLeft = pIJ.add(Right.scale(-rX/2)).add(Up.scale(-rY/2));

        double rXin = rX/RAYS;
        double rYin = rY/RAYS;
        Random offset = new Random(java.time.LocalDateTime.now().getSecond());
        for (int w = 0; w < RAYS; w++) {
            for (int h = 0; h < RAYS; h++) {
                L.add(new Ray(Position ,bottomLeft.add(Right.scale((w + offset.nextDouble())*rXin))
                        .add(Up.scale((h + offset.nextDouble())*rYin)).subtract(Position)));
            }
        }
        return L;
    }
}
