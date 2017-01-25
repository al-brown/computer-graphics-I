package kevin832924.b04.Cameras;


import java.util.Objects;
import kevin832924.b04.Bib.Point3;
import kevin832924.b04.Bib.Vec3;
import kevin832924.b04.Raytracer.Ray;

/**
 * abstract class from which all cameras inherit
 *
 * @author Mr. Kevin
 */
public abstract class AbstractCam  {

    /**
     * The e the position.
     */
    public final Point3 e;

    /**
     * The g the gaze direction
     */
    public final Vec3 g;

    /**
     * The t the up-factor
     */
    public final Vec3 t;

    /**
     * The u new x axis
     */
    public final Vec3 u;

    /**
     * The v new y axis
     */
    public final Vec3 v;

    /**
     * The w new z axis.
     */
    public final Vec3 w;
    /**
     * Instantiates a new camera.
     *
     * @param e the position
     * @param g the gaze direction
     * @param t the up vector
     * @param pattern
     */
    public AbstractCam(final Point3 e, final Vec3 g, final Vec3 t) {
        if (e == null) {
            throw new IllegalArgumentException("The Point  e cannot be null!");
        }
        if (g == null) {
            throw new IllegalArgumentException("The Vector g cannot be null!");
        }
        if (t == null) {
            throw new IllegalArgumentException("The Vector t cannot be null!");
        }

        this.e = e;
        this.g = g;
        this.t = t;

        w = g.normalized().multi(-1);

        u = t.cart(w).normalized();

        v = w.cart(u);
    }

    /**
     * Ray for method
     *
     * @param width the width
     * @param height the height
     * @param x the x
     * @param y the y
     * @return the ray
     */
    public abstract Ray generateRay(final int width, final int height, final double x, final double y);
        
    public abstract PerspectiveCam zoomIn();
    
    public abstract PerspectiveCam zoomOut();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.e);
        hash = 29 * hash + Objects.hashCode(this.g);
        hash = 29 * hash + Objects.hashCode(this.t);
        hash = 29 * hash + Objects.hashCode(this.u);
        hash = 29 * hash + Objects.hashCode(this.v);
        hash = 29 * hash + Objects.hashCode(this.w);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PerspectiveCam other = (PerspectiveCam) obj;
        if (!Objects.equals(this.e, other.e)) {
            return false;
        }
        if (!Objects.equals(this.g, other.g)) {
            return false;
        }
        if (!Objects.equals(this.t, other.t)) {
            return false;
        }
        if (!Objects.equals(this.u, other.u)) {
            return false;
        }
        if (!Objects.equals(this.v, other.v)) {
            return false;
        }
        return Objects.equals(this.w, other.w);
    }

    @Override
    public String toString() {
        return "Camera [e=" + e + ", g=" + g + ", t=" + t + ", u=" + u + ", v="
                + v + ", w=" + w + "]";
    }

}
