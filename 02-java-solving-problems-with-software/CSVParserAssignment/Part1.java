package CSVParserAssignment;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Part1 {
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // String info = countryInfo(parser, "Nauru");
        // System.out.println(info);

        // // 2:
        // parser = fr.getCSVParser();
        // listExportersTwoProducts(parser, "cotton", "flowers");
        // // 3:
        // parser = fr.getCSVParser();
        // int count = numberOfExporters(parser, "cocoa");
        // System.out.println("Number of Exporters: " + count);
        // 4:
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
    public void listExportersTwoProducts(CSVParser parser, String exportItem1,String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int numberOfExporters = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                numberOfExporters++;
            }
        }
        return numberOfExporters;
    }
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                String country = record.get("Country");
                System.out.println(country + " " +  value);
            }
        }
    }
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String countryName = record.get("Country");
            if (countryName.equals(country)) {
               String exports = record.get("Exports");
               String value = record.get("Value (dollars)");
               return countryName + ":" + exports + ": " + value;
            }
        }
        return "NOT FOUND";
    }
    public static void main(String[] args) {
        Part1 p = new Part1();
        p.tester();
    }
}
