package edu.war.robotics.vector;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<WayPoint> wayPoints = new ArrayList<>();

    private Path() {}
    /**
     * @param wayPoints Array of X,Y points.  Consecutive duplicate points are discarded
     *                  A path must have at least 2 non-identical points
     * @throws IllegalArgumentException for paths with fewer than 2 non-duplicate points.
     */
    private Path(List<WayPoint> wayPoints) {
        this.wayPoints = wayPoints;
    }

    public static Path createPath(Point[] pathPoints) throws IllegalArgumentException {
        if (pathPoints == null || pathPoints.length == 1) {
            throw new IllegalArgumentException("Path requires at least two points.");
        }
        List<WayPoint> wayPoints = new ArrayList<>();
        // adding the first point into our list
        wayPoints.add(new WayPoint(pathPoints[0],0,0,0,0));
        for (int i = 1; i < pathPoints.length; i++) {
            Point currentPoint = pathPoints[i];
            Point previousPoint = pathPoints[i-1];
            if (!currentPoint.equals(previousPoint)) {
                Point deltaPoint = currentPoint.getPointDelta(previousPoint);
                double distanceFromPrevious = currentPoint.getDistance(previousPoint);
                double distanceFromStart = currentPoint.getDistance(pathPoints[0]);
                WayPoint wayPoint = new WayPoint(currentPoint, deltaPoint.getX(), deltaPoint.getY(), distanceFromPrevious, distanceFromStart);
                wayPoints.add(wayPoint);
            }
        }
        // check at the end to make sure we created an array with at least two points
        if (wayPoints.size() > 1) {

        }
        return new Path(pathPoints);
    }

    /**
     * @return total distance along the path
     */
    public double totalDistance() {
        return 0.0;
    }

    /**
     * @return a point at the supplied distance along the path from the supplied robot position
     * Note that the point will usually be interpolated between the points that originally defined the Path
     */
    public TargetPoint getTargetPoint(Point robotPosition, double lookAheadDistance) {
        return new TargetPoint(current, 0, 0);
    }

    List<TargetPoint> getPoints() {
        return new ArrayList<>();
    }

    public static class TargetPoint {
        public Point point;
        public double distanceFromStart;
        public double distanceToEnd;

        private TargetPoint(Point point, double distanceFromStart, double distanceToEnd) {
            this.point = point;
            this.distanceFromStart = distanceFromStart;
            this.distanceToEnd = distanceToEnd;
        }

        private TargetPoint(WayPoint wayPoint) {
            this.point = wayPoint.point;
            this.distanceFromStart = wayPoint.distanceFromStart;
            this.distanceToEnd = wayPoint.getDistanceToEnd();
        }
    }


}