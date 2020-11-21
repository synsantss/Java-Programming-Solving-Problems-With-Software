
/**
 * Write a description of StringsFirstAssignments here.
 * 
 * @author MarcoSantss
 * @version 03/29/2020
 */

import edu.duke.*;

public class StringsFirstAssignments {

    public String findSimpleGene(String dna){
        int startIndex = dna.indexOf("ATG");
        int endIndex = dna.indexOf("TAA", startIndex + 3);
        if (startIndex == -1 || endIndex == -1)
            return "";
        String dnaSelected = dna.substring(startIndex, endIndex + 3);
        if(dnaSelected.length() % 3 == 0)
            return dnaSelected;
        else
            return "";
    }
    
    
    public String findSimpleGeneParameters(String dna, String startCodon, String endCodon ){
        int startIndex = dna.indexOf(startCodon);
        int endIndex = dna.indexOf(endCodon, startIndex + 3);
        if (startIndex == -1 || endIndex == -1)
            return "";
        String dnaSelected = dna.substring(startIndex, endIndex + 3);
        if(dnaSelected.length() % 3 == 0)
            return dnaSelected;
        return "";
    }
    
    public Boolean twoOccurrences (String stringa, String stringb){
        int firstTime = stringb.indexOf(stringa);
        int secondtime = stringb.indexOf(stringa, firstTime + stringa.length());
        if (firstTime != -1 && secondtime != -1)
            return true;
        return false;
    }
    
    public String lastPart (String stringa, String stringb){
        int firstTime = stringb.indexOf(stringa);
        if (firstTime == -1)
            return stringb;
        return stringb.substring(firstTime + stringa.length());
    }
    
    public String findWebLinks (String URL){
        URLResource file = new URLResource(URL);
        for (String item : file.words()){
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");
            if (pos != -1) {
                int beg = item.lastIndexOf("\"", pos);
                int end = item.indexOf("\"", pos + 1);
                return item.substring(beg + 1, end);
            }
        }
        return "";
    }
    
    public void testSimpleGene() {
     String dna1 = "ATGATTTAA";
     System.out.println("Teste " + dna1 + ": " + findSimpleGene(dna1));
     
     String dna2 = "ATGATTTTA";
     System.out.println("Teste " + dna2 + ": " + findSimpleGene(dna2));
     
     String dna3 = "ATAATTTAA";
     System.out.println("Teste " + dna3 + ": " + findSimpleGene(dna3));
     
     String dna4 = "ATAATTTTA";
     System.out.println("Teste " + dna4 + ": " + findSimpleGene(dna4));
     
     String dna5 = "ATGATTAA";
     System.out.println("Teste " + dna5 + ": " + findSimpleGene(dna5));
    }
    
    public void testTwoOccurrences() {
     String stringa = "by";
     String stringb = "A story by Abby Long";
     System.out.println("String A two times in B: " + twoOccurrences(stringa, stringb));
    }
    
    public void testLastPart() {
     String stringa = "an";
     String stringb = "banana";
     System.out.println("Rest of string B without A: " + lastPart(stringa, stringb));
     
     String stringc = "zoo";
     String stringd = "forest";
     System.out.println("Rest of string D without C: " + lastPart(stringc, stringd));
    }
    
    public void testFindWebLinks() {
     String url = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
     System.out.println(findWebLinks(url));
    }
}
