package primitives;

public class Material {

    double kD = 0.0;
    double kS = 0.0;
    double kT = 0.0; //שקיפות
    double kR = 0.0; //השתקפות
    int nShininess = 0;

    public double getkT() {
        return kT;
    }

    public double getkR() {
        return kR;
    }

    public double getkD() {
        return kD;
    }

    public double getkS() {
        return kS;
    }

    public int getnShininess() {
        return nShininess;
    }

    public Material setKt(double kT) {
        this.kT = kT;
        return this;
    }

    public Material setKr(double kR) {
        this.kR = kR;
        return this;
    }

    public Material setKd(double kD) {
        this.kD = kD;
        return this;
    }

    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }

    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
