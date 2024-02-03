/*
 *
 * @author Aleksey Gelezov
 * Student number: sba23107
 */
package ooa_ca1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *
 * @author aleksey
 */
public class OOA_CA1 {

    /**
     * @param args the command line arguments
     * @return 
     * @throws java.io.IOException
     */
    
    
    public static void main(String[] args) throws IOException {
        //  COUNTS THE LINES IN INPUT FILE
        Stream<String> fileStream = Files.lines(Paths.get("student_orig.txt"));
  	int numOfLines = (int) fileStream.count();
        System.out.println("num of lines: " + numOfLines);
       
        FileReader file = new FileReader("student_orig.txt");
        BufferedReader reader = new BufferedReader(file);
        
        FileWriter outputFile = new FileWriter("status.txt");

        ArrayList<String> stRecordValidation = new ArrayList<String>();
        
        
     // FOR LOOP REPEATS ACTIONS FOR EVERY LINE IN TEXT FILE
     for (int i = 0; i < numOfLines; i++) {
        String line = reader.readLine();
        String[] pair = line.split(" ");

        
        var name = pair[0];
        var surname = pair[1];
        var studentnum = pair[2];
        var numofclasses = pair[3];
        
        
        // SPLITS STUDENT NUMBER INTO PARTS FOR VALIDATION
        ArrayList<String> stNumberValidation = new ArrayList<String>();
        ArrayList<String> stNumYearValidation = new ArrayList<String>();
        
        
        
        Pattern stnum = Pattern.compile("[0-9]+|[A-Z]+|[\\d]+");
        Matcher stnumMatch = stnum.matcher(pair[2]);
        ArrayList<String> stnumArray = new ArrayList<>();
        while (stnumMatch.find()) {
            stnumArray.add(stnumMatch.group());
        }
        var stnumYear = stnumArray.get(0);
        var stnumCollege = stnumArray.get(1);
        var stnumIntNumber = stnumArray.get(2);
        
        
        // VALIDATES YEAR IN STUDENT NUMBER IS IN CORRECT FORMAT BY MATCHING WITH REGEX
        if (stnumYear.matches("[22-24]+")){ 
            stNumYearValidation.add("ok");
        }
        
        
        // VALIDATES LENGTH OF STUDENT NUMBER YEAR
        if (stnumYear.length() == 2) { 
            stNumYearValidation.add("ok");
        }else{
            System.out.println("Invalid Year on Student Number");
        }
        
        if (stNumYearValidation.size() == 2){
              stNumberValidation.add("ok");
        }
        
        
        // VALIDATES COLLEGE INITIALS ARE IN THE CORRECT FORMAT BY MATCHING WITH LENGTH
        if (stnumCollege.length() == 3) {
            stNumberValidation.add("ok");
        }else{
            System.out.println("Invalid College on Student Number");
        }
        
        
        // VALIDATES INNER STUDENT NUMBER IS IN CORRECT FORMAT BY MATCHING WITH REGEX
        if (stnumIntNumber.matches("[0-9]+")) {
            stNumberValidation.add("ok");
        }else{
            System.out.println("Invalid Student Number");
        }
        
        // VALIDATES WHOLE STUDENT NUMBER BY ADDING TO AN ARRAY
        if (stNumberValidation.size() == 3){
              stRecordValidation.add("ok");
        }
        
        
        // VALIDATES FIRST NAME IS IN CORRECT FORMAT BY MATCHING WITH REGEX
        if (name.matches("[A-Za-z]+")){
            stRecordValidation.add("ok");
        }else{
            System.out.println("Invalid First Name");
        }
        
        
        // VALIDATES SURNAME IS IN CORRECT FORMAT WITH REGEX     
        if (surname.matches("[A-Za-z]+|[0-9]+|\\D+|\\s+")){
           stRecordValidation.add("ok");
        }else{
            System.out.println("Invalid Surname");
        }
        
        
        // VALIDATES NUMBER OF CLASSES IS IN CORRECT FORMAT WITH REGEX, CONVERTS INTO WORKLOADS   
        if (numofclasses.matches("[1-8]")){
           stRecordValidation.add("ok");

           
        if (numofclasses.equals("1")){
            numofclasses = "Very Light";
        }

        
        if (numofclasses.equals("2")){
            numofclasses = "Light";
        }
        
        
        if (numofclasses.equals("3") || numofclasses.equals("4") || numofclasses.equals("5")){
            numofclasses = "Part Time";
        }

        
        if (numofclasses.equals("6") || numofclasses.equals("7") || numofclasses.equals("8")){
            numofclasses = "Full Time";
        }
        
        }else{
           System.out.println("Invalid Number Of Classes");
        }
        
        
        // WRITES EVERYTHING TO OUTPUT FILE (status.txt)
        if (stRecordValidation.size() == 4){
            System.out.println("TADA! " + line);
            outputFile.write(name + " " + surname + " " + studentnum + " " + numofclasses + "\n");
            stRecordValidation.removeAll(stRecordValidation);
           }
        }   
    outputFile.close();
    }

}
    

