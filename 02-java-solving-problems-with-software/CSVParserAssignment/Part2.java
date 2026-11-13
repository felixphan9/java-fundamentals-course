package CSVParserAssignment;
import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;
import edu.duke.DirectoryResource;

public class Part2 {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser) {
            coldestSoFar = getColderOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }

    public CSVRecord getColderOfTwo (CSVRecord currentRow, CSVRecord coldestSoFar) {
        if (coldestSoFar == null) {
            coldestSoFar = currentRow;
        }
        // Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if (currentTemp < coldestTemp) {
                coldestSoFar = currentRow;
            }
        }
        return coldestSoFar;
    }

    public File fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        File coldestFile = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (coldestFile == null) {
                coldestFile = f;
            }
            coldestSoFar = getColderOfTwo(currentRow, coldestSoFar);
            // if (coldestSoFar != currentRow) {
            //     coldestFile = f;
            // }
        }
        System.out.println(coldestSoFar.get("DateUTC") + " " + coldestSoFar.get("TemperatureF"));
        return coldestFile;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {  
            if ((currentRow.get("Humidity")).equals("N/A")) {
                    continue;
            }
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
            // Otherwise
            else {
                int currentHumid = Integer.parseInt(currentRow.get("Humidity"));
                int lowestHumid = Integer.parseInt(lowestSoFar.get("Humidity"));
                if (currentHumid < lowestHumid) {
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }

    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
            // Otherwise
            else {
                if (currentRow.get("Humidity") == "N/A") {
                    continue;
                }
                int currentHumid = Integer.parseInt(currentRow.get("Humidity"));
                int lowestHumid = Integer.parseInt(lowestSoFar.get("Humidity"));
                if (currentHumid < lowestHumid) {
                    lowestSoFar = currentRow;
                }
            }
        }
        System.out.println("Lowest Humidity was " + lowestSoFar.get("Humidity") + " at " + lowestSoFar.get("DateUTC"));
        return lowestSoFar;
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double averageTemp = 0;
        double sum = 0;
        int count = 0;
        for (CSVRecord currentRow : parser) {
            count ++;
            sum = sum + Double.parseDouble(currentRow.get("TemperatureF"));
        }
        averageTemp = sum/count;
        return averageTemp;
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double averageTemp = 0;
        double sum = 0;
        int count = 0;
        for (CSVRecord currentRow : parser) {
            if (Integer.parseInt(currentRow.get("Humidity")) > value) {
                count ++;
                sum = sum + Double.parseDouble(currentRow.get("TemperatureF"));
            }
        }
        averageTemp = sum/count;
        return averageTemp;
    } 

    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        if( averageTemp == 0.0 ) {
            System.out.println("No temperatures with that humidity");
        }
        System.out.println("Average Temperature " + averageTemp);
    }

    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureInFile(parser);
        System.out.println("Average Temp when high Humidity is " + averageTemp);
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestSoFar.get("Humidity") + " at " + lowestSoFar.get("DateUTC"));
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public void testFileWithColdestTemperature() {
        File coldestFile = fileWithColdestTemperature();
        System.out.println("File with the coldest day " + coldestFile.getName());
        // FileResource fr = new FileResource(coldestFile);
        // CSVParser parser = fr.getCSVParser();
        // CSVRecord coldest = coldestHourInFile(parser);
        // System.out.println("Coldest temperature was: " + coldest.get("TemperatureF"));
        // System.out.println("All the Temperatures on the coldest day were:");
        // parser = fr.getCSVParser();
        // for (CSVRecord currentRow : parser) {
        //     System.out.println(currentRow.get("DateUTC") + " " + currentRow.get("TemperatureF"));
        // }
    }

    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("Coldest temperature was: " + coldest.get("TemperatureF"));
        // System.out.println("Time of occurrence: " + coldest.get("TimeEST"));
        // System.out.println("Humidity: " + coldest.get("Humidity"));
    }

    public static void main(String[] args) {
        Part2 p = new Part2();
        // p.testAverageTemperatureWithHighHumidityInFile();
        // p.testAverageTemperatureInFile();
        // p.lowestHumidityInManyFiles();
        // p.testLowestHumidityInFile();
        p.testFileWithColdestTemperature();
        // p.testColdestHourInFile();
    }
}
