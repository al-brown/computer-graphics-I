package kevin832924.a05.Material;


import kevin832924.a05.Raytracer.Color;
import kevin832924.a05.Raytracer.Tracer;
import kevin832924.a05.Raytracer.Group;
import kevin832924.a05.Shape.Hit;

/**
 * interface of a material
 *
 * @author Mr. Kevin
 */
public interface Material {

    public Color colorFor(final Hit hit, final Group group, final Tracer tracer);

    public Color getAmbient(final Hit hit, final Group group)  ;
}
