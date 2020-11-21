/**
 * Write a description of Babys here.
 * 
 * @author MarcoSants
 * @version 04/02/2020
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Babys {
    
    public int getRank(int year, String name, String gender){
        FileResource fr = new FileResource("./datasets/us_babynames_by_year/yob" + year + ".csv");
        int rank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender))
                rank += 1;
            if(rec.get(0).equals(name) && rec.get(1).equals(gender))
                 return rank;
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("./datasets/us_babynames_by_year/yob" + year + ".csv");
        int currentlyRank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender))
                currentlyRank += 1;
            if(currentlyRank == rank)
                 return rec.get(0);
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rankCurrentlyName = getRank(year, name, gender);
        String newName = getName(newYear, rankCurrentlyName, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear + "."); 
    }
    
    public int yearOfHighestRank (String name, String gender){
        int year = 0;
        int rank = Integer.MAX_VALUE;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            int currentYear = Integer.parseInt(f.getName().substring(3, 7));
            int currentRank = getRank(currentYear, name, gender);
            if (currentRank != -1 && currentRank < rank) {
                rank = currentRank;
                year = currentYear;
            }
        } 
        if (year == 0) 
            return -1;
        else 
            return year;
    }
    
    public Double getAverageRank (String name, String gender){
        double totalRank = 0;
        int recordCount = 0;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            int currentYear = Integer.parseInt(f.getName().substring(3, 7));
            int currentRank = getRank(currentYear, name, gender);
            
            if (currentRank != -1) {
                totalRank += currentRank;
                recordCount++;
            }
        } 
        
        if (recordCount == 0) {
            return -1.0;
        } else {
            return totalRank / recordCount;
        }
    }
    
    public void printNames(){
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)){
            System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1) + " Number " + rec.get(2));
        }
    }
    
    public void totalBirths (FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int girlsNames = 0;
        int boysNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(rec.get(1).equals("M")){
                totalBoys += numBorn;
                boysNames += 1;
            } else {
                totalGirls += numBorn;
                girlsNames += 1;
            }
        }
        System.out.println("Boys: " + totalBoys + "\nboysNames: " + boysNames);
        System.out.println("Girls: " + totalGirls + "\ngirlsNames: " + girlsNames);
        System.out.println("\nTotal: " + totalBirths);
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public void testGetRank(){
        int position = getRank(1971, "Frank", "M");
        System.out.println("Position: " + position);
    }
    
    public void testGetName(){
        String name = getName(1982, 450, "M");
        System.out.println("Name: " + name);
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public void testYearOfHighestRank(){
        int year = yearOfHighestRank("Mich", "M");
        System.out.println("Year: " + year);
    }
    
    public void testGetAverageRank(){
        Double rank = getAverageRank("Robert", "M");
        System.out.println("Average rank: " + rank);
    }
}
