package kevin832924.a05.Art;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Vector;

import kevin832924.a05.Bib.Vec3;

public final class RayTracer {

    private final static char[][] art = {
        "  1111      1111111 ".toCharArray(),
        " 1    1    1       1".toCharArray(),
        " 1    1    1       1".toCharArray(),
        " 1         1        ".toCharArray(),
        " 1         1        ".toCharArray(),
        " 1         1   11111".toCharArray(),
        " 1    1    1       1".toCharArray(),
        " 1    1    1       1".toCharArray(),
        "  1111      1111111 ".toCharArray(),
        "                    ".toCharArray(),
        "                    ".toCharArray(),
        "    11       11111  ".toCharArray(),
        "   1 1      1       ".toCharArray(),
        "  1  1     1        ".toCharArray(),
        " 1   1     1        ".toCharArray(),
        "     1     1111111  ".toCharArray(),
        "     1     1      1 ".toCharArray(),
        "     1     1      1 ".toCharArray(),
        "     1     1      1 ".toCharArray(),
        "     1      111111  ".toCharArray()
    };

    static private Vec3[] build() {
        final Vector<Vec3> tmp = new Vector<>(art.length * art[0].length);

        final int nr = art.length;
        for (int j = 0; j < nr; j++) {
            final int nc = art[j].length;
            for (int k = 0; k < nc; k++) {
                if (art[j][k] != ' ') {
                    tmp.add(new Vec3(k, 6.5f, -(nr - j) - 1.0f));
                }
            }
        }

        return tmp.toArray(new Vec3[0]);
    }

    static int size = 512;
    static byte[] bytes;

    static float aspectRatio;

    private static float mp;
    private static int threadNum;
    private static int renderNum;



    /**
     * Parse passed command line arguments.
     *
     * 
     */
    private static void parseArgs(final String[] args) {
        mp = 1;
        renderNum = 1;
        threadNum = Runtime.getRuntime().availableProcessors();

        try {
            if(args.length > 0) {
                mp = Float.parseFloat(args[0]);
            }

            if(args.length > 1) {
                renderNum = Integer.parseInt(args[1]);
            }

            if(args.length > 2) {
                threadNum = Integer.parseInt(args[2]);
            }
        } catch(final NumberFormatException e) {
            System.err.println("Error parsing cmd lines parameter.");
            System.err.println();
            System.exit(1);
        }

        size = (int)(Math.sqrt(mp * 1000000));
        aspectRatio = 512.f / size;
        
    }

    /** Represent exactly one render pass */
    private static void startRenderPass(final Vec3[] objects) throws Exception {
        final Vector<Thread> threads = new Vector<>();
        for (int i = 0; i < threadNum; ++i) {
            final Thread thread = new Thread(new Tracer(objects, i, threadNum));
            thread.start();
            threads.add(thread);
        }

        for(final Thread t : threads) {
            t.join();
        }
    }

    public static void main(final String[] args) throws Exception {
        parseArgs(args);
        final Vec3[] objects = build();
        bytes = new byte[3*size*size];

        long overallDuration= 0;
        for(int i = 0; i < renderNum; i++) {
            System.out.printf("Starting render#%d of size %.2f MP (%dx%d) with %d threads. ",
                    i+1, mp, size, size, threadNum);

            final long startTime = System.currentTimeMillis();
            startRenderPass(objects);
            final long duration = System.currentTimeMillis() - startTime;
            overallDuration += duration;
            System.out.printf("Completed in %d ms%n", duration);
        }

        System.out.printf("time for rendering: %d ms%n", (overallDuration / renderNum));

        final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("render_final.ppm"));
        stream.write(String.format("P6 %d %d 255 ", size, size).getBytes());
        stream.write(bytes);
        stream.flush();
        stream.close();
    }
}
