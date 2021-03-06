package edu.war.robotics.vector;

class WayPoint {
    public Point point;
    private double deltaXFromPrevious;
    private double deltaYFromPrevious;
    private double distanceFromPrevious;
    private double distanceFromStart;

    private WayPoint() {}

    public WayPoint(Point point, double deltaXFromPrevious, double deltaYFromPrevious, double distanceFromPrevious, double distanceFromStart) {
//        this.path = path;
        this.point = point;
        this.deltaXFromPrevious = deltaXFromPrevious;
        this.deltaYFromPrevious = deltaYFromPrevious;
        this.distanceFromPrevious = distanceFromPrevious;
        this.distanceFromStart = distanceFromStart;
    }

    public double getDistanceToEnd(Path path) {
        return path.totalDistance() - distanceFromStart;
    }

    /**
     * Calculates the projection of the vector Vcurrent leading from the supplied robot
     * point to this WayPoint onto the vector Vpath leading from the previous point on the path
     * to this WayPoint.  If the return value is positive, it means that the WayPoint is
     * farther along the path from the robot position.  If the return value is negative, it means
     * that the WayPoint is before the robot position (earlier on the path).
     * The magnitude of the value tells the
     * distance along the path.  The value is computed as the dot product between Vcurrent and
     * Vpath, normalized by the length of vPath
     *
     * @param current The source point to compare to the WayPoint
     */
    private double componentAlongPath(Point robotPosition) {
        double deltaXFromCurrent = point.getX() - robotPosition.getX();
        double deltaYFromCurrent = point.getY() - robotPosition.getY();

        double dp = deltaXFromCurrent * deltaXFromPrevious + deltaYFromCurrent * deltaYFromPrevious;
        return dp / distanceFromPrevious;
    }
}
