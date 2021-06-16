package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.LinkedList;
import java.util.MissingResourceException;
import java.util.Random;

public class Render {


    private int RAYS = 6;
    ImageWriter imageWriter;
    public Camera camera;
    RayTracerBasic rayTracerBasic;



    private boolean partialAdaptive = true;
    private boolean reject = true;


    public Render setPartialAdaptive(boolean partialAdaptive) {
        this.partialAdaptive = partialAdaptive;
        return this;
    }

    /**
     * set if Bounding volume is working or not
     * @param reject
     * @return
     */
    public Render setReject(boolean reject) {
        this.reject = reject;
        return this;
    }


    /**
     * set the amount of shadow rays
     * the more there is, the more precise it is
     * the number must be odd!
     * @param SHADOWRAYS
     * @return
     */
    public Render setSHADOWRAYS(int SHADOWRAYS) {
        rayTracerBasic.setSHADOWRAYS(SHADOWRAYS);
        return this;
    }


    public Render setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Render setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    public Render setRayTracer(RayTracerBasic rayTracerBasic) {
        this.rayTracerBasic = rayTracerBasic;
        return this;
    }

    /**
     * set amout of anti alaising rays
     * the more, the more precise
     * number must be even!
     * @param RAYS
     * @return
     */
    public Render setRAYS(int RAYS) {
        if (RAYS <= 0){
            this.RAYS = 1;
        }
        else if(RAYS % 2 == 1 && RAYS > 1){
            this.RAYS = RAYS - 1;
            return this;
        }
        else this.RAYS = RAYS;
        return this;
    }

    public void printGrid(int interval, Color color){
        if (imageWriter == null) {
            throw new MissingResourceException("ERROR: imageWriter resource is missing in renderer.", "ImageWriter", "");
        }

        for(int j = 0; j <imageWriter.getNy() ; j = j + 1) {
            for(int i = 0; i < imageWriter.getNx() ; i = i + interval){
                    imageWriter.writePixel(i, j, color);
            }
        }

        for(int j = 0; j < imageWriter.getNx() ; j = j + 1) {
            for(int i = 0; i < imageWriter.getNy() ; i = i + interval){
                imageWriter.writePixel(j, i, color);
            }
        }

    }

    public void writeToImage(){

        if (imageWriter == null) {
            throw new MissingResourceException("ERROR: imageWriter resource is missing in renderer.", "ImageWriter", "");
        }
        imageWriter.writeToImage();
    }

    private int _threads = 1;
    private final int SPARE_THREADS = 2;
    private boolean _print = false;

    /**
     * Pixel is an internal helper class whose objects are associated with a Render object that
     * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
     * its progress.<br/>
     * There is a main follow up object and several secondary objects - one in each thread.
     * @author Dan
     *
     */
    private class Pixel {
        private long _maxRows = 0;
        private long _maxCols = 0;
        private long _pixels = 0;
        public volatile int row = 0;
        public volatile int col = -1;
        private long _counter = 0;
        private int _percents = 0;
        private long _nextCounter = 0;

        /**
         * The constructor for initializing the main follow up Pixel object
         * @param maxRows the amount of pixel rows
         * @param maxCols the amount of pixel columns
         */
        public Pixel(int maxRows, int maxCols) {
            _maxRows = maxRows;
            _maxCols = maxCols;
            _pixels = maxRows * maxCols;
            _nextCounter = _pixels / 100;
            if (Render.this._print) System.out.println(_percents + "%");
        }

        /**
         *  Default constructor for secondary Pixel objects
         */
        public Pixel() {}

        /**
         * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
         * critical section for all the threads, and main Pixel object data is the shared data of this critical
         * section.<br/>
         * The function provides next pixel number each call.
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
         * finished, any other value - the progress percentage (only when it changes)
         */
        private synchronized int nextP(Pixel target) {
            ++col;
            ++_counter;
            if (col < _maxCols) {
                target.row = this.row;
                target.col = this.col;
                if (_counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            ++row;
            if (row < _maxRows) {
                col = 0;
                target.row = this.row;
                target.col = this.col;
                if (_counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            return -1;
        }

        /**
         * Public function for getting next pixel number into secondary Pixel object.
         * The function prints also progress percentage in the console window.
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return true if the work still in progress, -1 if it's done
         */
        public boolean nextPixel(Pixel target) {
            int percents = nextP(target);
            if (percents > 0)
                if (Render.this._print) System.out.println(percents + "%");
            if (percents >= 0)
                return true;
            if (Render.this._print) System.out.printf("\r %02d%%", 100);
            return false;
        }
    }

    /**
     * This function renders image's pixel color map from the scene included with
     * the Renderer object
     */
    public void renderImage() {
        if (imageWriter == null) {
            throw new MissingResourceException("ERROR: imageWriter resource is missing in renderer.", "ImageWriter", "");
        }
        if (camera == null) {
            throw new MissingResourceException("ERROR: camera resource is missing in renderer.", "Camera", "");
        }
        if (rayTracerBasic == null) {
            throw new MissingResourceException("ERROR: rayTracerBasic resource is missing in renderer.", "RayTracerBasic", "");
        }
        if(reject){
            rayTracerBasic.CreateBVH();
        }
        rayTracerBasic.setPartialAdaptive(partialAdaptive);

        final int nX = imageWriter.getNx();
        final int nY = imageWriter.getNy();

        final Pixel thePixel = new Pixel(nY, nX);

        // Generate threads
        Thread[] threads = new Thread[_threads];
        for (int i = _threads - 1; i >= 0; --i) {
            threads[i] = new Thread(() -> {
                Pixel pixel = new Pixel();
                while (thePixel.nextPixel(pixel)) {
                    LinkedList<Ray> rays = camera.constructRaysThroughPixel(nX, nY, pixel.col, pixel.row, RAYS);
                    imageWriter.writePixel(pixel.col, pixel.row, rayTracerBasic.traceRays(rays));
                }
            });
        }

        // Start threads
        for (Thread thread : threads) thread.start();

        // Wait for all threads to finish
        for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
        if (_print) System.out.printf("\r100%%\n");
    }

    /**
     * Set multithreading <br>
     * - if the parameter is 0 - number of coress less 2 is taken
     *
     * @param threads number of threads
     * @return the Render object itself
     */
    public Render setMultithreading(int threads) {
        if (threads < 0)
            throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
        if (threads != 0)
            _threads = threads;
        else {
            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
            if (cores <= 2)
                _threads = 1;
            else
                _threads = cores;
        }
        return this;
    }

    /**
     * Set debug printing on
     *
     * @return the Render object itself
     */
    public Render setDebugPrint() {
        _print = true;
        return this;
    }
}