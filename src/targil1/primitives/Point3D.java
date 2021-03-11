package targil1.primitives;

import java.util.Objects;

public class Point3D {
    Coordinate x;
    Coordinate y;
    Coordinate z;
    public static Point3D ZERO = new Point3D(0,0,0);

    public Point3D(double X, double Y, double Z)
    {
        x = new Coordinate(X);
        y = new Coordinate(Y);
        z = new Coordinate(Z);
    }
    public Point3D(Coordinate X, Coordinate Y, Coordinate Z)
    {
        x = X;
        y = Y;
        z = Z;
    }

    public Point3D add(Vector Vec)
    {
        Point3D Added = new Point3D(x.coord + Vec.head.x.coord, y.coord + Vec.head.y.coord, z.coord + Vec.head.z.coord);
      /* x = Added.x;
        y = Added.y;
        z = Added.z; */
        return Added;
    }

    public Vector subtract(Point3D point)
    {
            Vector Vec = new Vector(x.coord - point.x.coord, y.coord - point.y.coord, z.coord - point.z.coord);
            return Vec;
    }

    public double distanceSquared(Point3D point3D)
    {
        return
                (x.coord - point3D.x.coord) * (x.coord - point3D.x.coord) +
                (y.coord - point3D.y.coord) * (y.coord - point3D.y.coord) +
                (z.coord - point3D.z.coord) * (z.coord - point3D.z.coord);
    }

    public double distance(Point3D point3D)
    {
        return Math.sqrt(this.distanceSquared(point3D));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point3D)) return false;
        Point3D point3D = (Point3D) o;
        return Objects.equals(x, point3D.x) && Objects.equals(y, point3D.y) && Objects.equals(z, point3D.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "(" +
                x +
                ", " + y +
                ", " + z +
                ')';
    }
}
