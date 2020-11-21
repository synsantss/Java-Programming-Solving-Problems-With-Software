/**
 * Write a description of Weather here.
 * 
 * @author MarcoSants
 * @version 03/31/2020
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Weather {
    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser){
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }
    
    public CSVRecord hottestInManyDays(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largestSoFar = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }
    
    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar){
        if(largestSoFar == null){
            largestSoFar= currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if (currentTemp > largestTemp){
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
    
    public CSVRecord coldestHourInFile (CSVParser parser){
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser){
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }
    
    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar){
        if(smallestSoFar == null){
            smallestSoFar= currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if (currentTemp < smallestTemp && currentTemp != -9999){
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }
    
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        File smallestTemperatureFileName = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            CSVRecord smallestBeforeCheck = smallestSoFar;
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
            if (smallestSoFar != smallestBeforeCheck){
                smallestTemperatureFileName = f;
            }
        }
        return smallestTemperatureFileName.getName();
    }
    
    public CSVRecord lowestHumidityInFile (CSVParser parser){
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser){
            lowestSoFar = getLowestHumidityOfTwo(currentRow, lowestSoFar);
        }
        return lowestSoFar;        
    }
    
    public CSVRecord getLowestHumidityOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar){
        if(currentRow.get("Humidity") == "N/A")
            return lowestSoFar;
            
        if(lowestSoFar == null){
            lowestSoFar = currentRow;
        } else {
            double currentHumi = Double.parseDouble(currentRow.get("Humidity"));
            double lowestHumi = Double.parseDouble(lowestSoFar.get("Humidity"));
            if (currentHumi < lowestHumi){
                lowestSoFar = currentRow;
            }
        }
        return lowestSoFar;
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getLowestHumidityOfTwo(currentRow, lowestSoFar);
        }
        return lowestSoFar;
    }
    
    public Double averageTemperatureInFile(CSVParser parser){
        Double sumTemperatures = 0.0;
        Double numberHours = 0.0;
        for (CSVRecord currentRow : parser){
            sumTemperatures += Double.parseDouble(currentRow.get("TemperatureF"));
            numberHours += 1;
        }
        return sumTemperatures/numberHours;
    }
    
    public Double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        Double sumTemperatures = 0.0;
        Double numberHours = 0.0;
        for (CSVRecord currentRow : parser){
            if(Integer.parseInt(currentRow.get("Humidity")) >= value){
                sumTemperatures += Double.parseDouble(currentRow.get("TemperatureF"));
                numberHours += 1;
            }
        }
        return sumTemperatures/numberHours;
    }
    
    public void testHottestHourInDay(){
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
    }
    
    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Smallest temperature was " + smallest.get("TemperatureF") + " at " + smallest.get("DateUTC"));
    }
    
    public void testFileWithColdestTemperature(){
        String fileNameWithColdestTemperature = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + fileNameWithColdestTemperature);
        FileResource fr = new FileResource("./nc_weather/2013/" + fileNameWithColdestTemperature);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Smallest temperature was " + smallest.get("TemperatureF") + " at " + smallest.get("TimeEST"));
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumidity = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was " + lowestHumidity.get("Humidity") + " at " + lowestHumidity.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        System.out.println("Average temperature was " + averageTemperatureInFile(fr.getCSVParser()));
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        Double average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (!Double.isNaN(average))
            System.out.println("Average temperature was " + average);
        else 
            System.out.println("No temperatures with that humidity");
    }
}
