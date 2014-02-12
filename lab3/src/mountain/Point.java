package mountain;

public class Point {
	private double x, y;

	/** Constructs and initializes a point at the specified (x,y) location. */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/** 
	 * Returns the x coordinate. 
	 * @return the x coordinate
	 */
	public double getX() {
		return x;
	}

	/** 
	 * Returns the y coordinate. 
	 * @return the y coordinate
	 */
	public double getY() {
		return y;
	}

	/** Moves this point to the specified (x,y) location.
	 * post: the point is moved to the location (x,y)
	 * @param  x the x coordinate of the new location
	 * @param  y the y coordinate of the new location
	 */
	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}

    public boolean equals(Object obj) {
        Point p = (Point) obj;
        if (p.x == x && p.y == y) {
            return true;
        }
        return false;
    }

    public static Point middle(Point p1, Point p2) {
        return new Point((p1.getX() + p2.getX())/2, (p1.getY() + p2.getY())/2);
    }

    public static Point middleRand(Point p1, Point p2, double dev) {
        return new Point((p1.getX() + p2.getX())/2,
                         (p1.getY() + p2.getY() + RandomUtilities.randFunc(dev))/2);
    }
}
