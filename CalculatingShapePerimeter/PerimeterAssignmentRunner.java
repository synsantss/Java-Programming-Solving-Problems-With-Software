import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int numPoints = 0;
        for (Point currPt : s.getPoints()) {
           numPoints += 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        double averageLengths = getPerimeter(s)/getNumPoints(s);
        return averageLengths;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currSide = prevPt.distance(currPt);
            if (currSide > largestSide)
               largestSide = currSide;
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        Point largestX = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            if (currPt.getX() > largestX.getX())
                largestX = currPt;
        }
        return largestX.getX();
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double fileLength = getPerimeter(s);
            if (fileLength > largestPerimeter) 
                largestPerimeter = fileLength;
        }
        
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        File largestPerimeterFile = null;
        double largestPerimeter = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double fileLength = getPerimeter(s);
            if (fileLength > largestPerimeter) {
                largestPerimeter = fileLength;
                largestPerimeterFile = f;
            }
        }

        return largestPerimeterFile.getName();
    }

    public void testPerimeter () {
        // Test perimeter
        //FileResource fr = new FileResource("./datatest1.txt");
        //Shape s = new Shape(fr);
        //double length = getPerimeter(s);
        //System.out.println("perimeter = " + length);
        
        // Test number of points
        //Shape triangle = new Shape();
        //triangle.addPoint(new Point(0,0));
        //triangle.addPoint(new Point(6,0));
        //triangle.addPoint(new Point(3,6));
        //System.out.println("number of points = " + getNumPoints(triangle)); 
        
        // Test getAverageLength
        //FileResource fr = new FileResource("./datatest4.txt");
        //Shape s = new Shape(fr);
        //double length = getAverageLength(s);
        //System.out.println("perimeter = " + length);
        
        // Test getLargestSide
        //FileResource fr = new FileResource("./datatest1.txt");
        //Shape s = new Shape(fr);
        //double length = getLargestSide(s);
        //System.out.println("Largest Side = " + length);
        
        // Test getLargestX
        //FileResource fr = new FileResource("./example1.txt");
        //Shape s = new Shape(fr);
        //double largestX = getLargestX(s);
        //System.out.println("Largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        getLargestPerimeterMultipleFiles();
    }

    public void testFileWithLargestPerimeter() {
        getFileWithLargestPerimeter();
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
