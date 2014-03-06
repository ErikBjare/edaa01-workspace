package mountain;

import fractal.*;

import java.util.ArrayList;
import java.util.Objects;

public class Mountain extends Fractal {
    private double dev;
    private ArrayList<Side> sides;

    /**
     * Creates an object that handles a Mountain fractal
     *
     * @param dev the deviation of the y-offset
     */
    public Mountain(double dev) {
        super();
        this.dev = dev;
    }

    /**
     * Returns the title.
     *
     * @return the title
     */
    public String getTitle() {
        return "Mountain fractal";
    }

    /**
     * Draws the fractal.
     *
     * @param turtle the turtle graphic object
     */
    public void draw(TurtleGraphics turtle) {
        this.sides = new ArrayList<Side>();
        fractalTriangle(turtle, order, firstTriangle(), dev);
    }

    private Triangle firstTriangle() {
        return new Triangle(new Point[]{new Point(50, 500),
                new Point(250, 100),
                new Point(450, 500)});
    }

    /*
     * Reursive method: Draws a recursive line of the triangle.
     */
    private void fractalTriangle(TurtleGraphics turtle, int order, Triangle tri, double dev) {
        if (order == 0) {
            for (Triangle t : tri.subdivide(dev)) {
                t.draw(turtle);
            }
        } else {
            for (Triangle t : tri.subdivide(dev)) {
                fractalTriangle(turtle, order - 1, t, dev / 2);
            }
        }
    }

    class Triangle {
        private Point[] points;

        public Triangle(Point p1, Point p2, Point p3) {
            this.points = new Point[]{p1, p2, p3};
        }

        public Triangle(Point[] points) {
            this.points = new Point[]{points[0], points[1], points[2]};
        }

        public Point[] getPoints() {
            return points;
        }

        public Side[] getSides() {
            Side[] retsides = new Side[]{
                    new Side(points[0], points[1]),
                    new Side(points[1], points[2]),
                    new Side(points[2], points[0]),
            };

            for(int i = 0; i<3; i++) {
                int idx = sides.indexOf(retsides[i]);
                if (idx != -1){
                    retsides[i] = sides.get(idx);
                    sides.remove(idx);
                } else {
                    sides.add(retsides[i]);
                }
            }

            return retsides;
        }

        public Point[] getMiddlePoints(double dev) {
            Side[] retsides = getSides();

            Point[] retpoints = new Point[]{
                    retsides[0].getMidPoint(dev),
                    retsides[1].getMidPoint(dev),
                    retsides[2].getMidPoint(dev)
            };

            return retpoints;
        }

        public Triangle[] subdivide(double dev) {
            Point[] ps = getMiddlePoints(dev);

            return new Triangle[]{new Triangle(points[0], ps[0], ps[2]),
                    new Triangle(points[1], ps[0], ps[1]),
                    new Triangle(points[2], ps[1], ps[2]),
                    new Triangle(ps[0], ps[1], ps[2])};
        }

        /**
        public void draw(TurtleGraphics t) {
            t.moveTo(points[0].getX(), points[0].getY());
            t.penDown();
            for (Point p : points) {
                if (p == points[0]) continue;
                t.forwardTo(p.getX(), p.getY());
            }
        }
         */

        public void draw(TurtleGraphics t) {
            for(Side s : getSides()) {
                t.moveTo(s.points[0].getX(), s.points[0].getY());
                t.forwardTo(s.points[1].getX(), s.points[1].getY());
            }
        }
    }
}

class Side {
    public Point[] points;
    public Point midpoint;

    public Side(Point p1, Point p2) {
        points = new Point[]{p1, p2};
    }

    public Point getMidPoint(double dev) {
        if (midpoint == null) {
            midpoint = Point.middleRand(points[0], points[1], dev);
        }
        return midpoint;
    }

    public boolean equals(Object obj) {
        Side s = (Side) obj;
        if (s.points[0].equals(points[0]) && s.points[1].equals(points[1]) ||
            s.points[0].equals(points[1]) && s.points[1].equals(points[0])) {
            return true;
        }
        return false;
    }
}