/**
 * Write a description of Countries here.
 * 
 * @author MarcoSants
 * @version 03/30/2020
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Countries {
   public void listExporters(CSVParser parser, String exportOfInterest) {
       for (CSVRecord record : parser) {
           String export = record.get("Exports");
           if(export.contains(exportOfInterest)) {
               String country = record.get("Country");
               System.out.println(country);
           }
       }
   }
   
   public void exportCoffee() {
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       listExporters(parser, "coffee");
   }
   
   public String countryInfo(CSVParser parser, String country){
       for (CSVRecord record : parser) {
           String recordCountry = record.get("Country");
           if (recordCountry.equals(country)){
               String recordExports = record.get("Exports");
               String recordValue = record.get("Value (dollars)");
               return recordCountry + ": " + recordExports + ": " + recordValue;
           }
       }
       return "NOT FOUND";
   }
   
   public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2){
       for (CSVRecord record : parser) {
           String recordExports = record.get("Exports");
           if (recordExports.contains(exportItem1) && recordExports.contains(exportItem2)){
               System.out.println(record.get("Country"));
           }
       }
   }
   
   public int numberOfExporters(CSVParser parser, String exportItem){
       int numberOfCountries = 0;
       for (CSVRecord record : parser) {
           String recordExports = record.get("Exports");
           if (recordExports.contains(exportItem)){
               numberOfCountries += 1;
           }
       }
       return numberOfCountries;
   }
   
   public void bigExporters(CSVParser parser, String amount){
       for (CSVRecord record : parser) {
           String recordValue = record.get("Value (dollars)");
           if (amount.length() < recordValue.length()){
               System.out.println(record.get("Country") + " " + recordValue);
           }
       }
   }
   
   public void testCountryInfo (){
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       System.out.println(countryInfo(parser, "cocoa"));
   }
   
   public void testListExportersTwoProducts (){
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       listExportersTwoProducts(parser, "cotton", "flowers");
   }
   
   public void testNumberOfExporters (){
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       System.out.println(numberOfExporters(parser, "cocoa"));
   }
   
   public void testBigExporters (){
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       bigExporters(parser, "$999,999,999,999");
   }
}
