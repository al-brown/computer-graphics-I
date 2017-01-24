package kevin832924.b01.Material;


import kevin832924.b01.Raytracer.Color;
import kevin832924.b01.Raytracer.Tracer;
import kevin832924.b01.Raytracer.Group;
import kevin832924.b01.Shape.Hit;

/**
 * interface of a material
 *
 * @author Mr. Kevin
 */
public interface Material {

    public Color colorFor(final Hit hit, final Group group, final Tracer tracer);

    public Color getAmbient(final Hit hit, final Group group)  ;
}
