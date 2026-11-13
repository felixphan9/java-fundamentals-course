package BabyNames;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;

import org.apache.commons.csv.CSVRecord;

public class Part1 {
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int girlNames = 0;
        int boyNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("F")) {
                girlNames ++;
                totalGirls += numBorn;
            }
            else {
                totalBoys += numBorn;
                boyNames ++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total births = " + totalBoys);
        System.out.println("total girl's names " + girlNames);
        System.out.println("total boy's names " + boyNames);
    }

    public int getRank(int year, String name, String gender, FileResource f) {
        int rank = 0;
        for (CSVRecord rec : f.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank ++;
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }

    public String getName(int year, int rank, String gender, FileResource f) {
        int ref = 0;
        for (CSVRecord rec : f.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                ref ++;
                if (ref == rank) {
                    return rec.get(0);
                }
            }
        }
        return "No Name";
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        DirectoryResource dr = new DirectoryResource();
        File f1 = null;
        File f2 = null;
        int rank = 0;
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            System.out.println("Year : " + Integer.parseInt(fileName.substring(3, 7)));
            if ( year == Integer.parseInt(fileName.substring(3, 7)) ) {
                f1 = f;
            }
            else {
                f2 = f;
            }
        }
        FileResource fr = new FileResource(f1);
        rank = getRank(year, name, gender, fr);
        fr = new FileResource(f2);
        String newName = getName(newYear, rank, gender,fr);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }

    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int maxRank = 9999999;
        int yearRank = 0;
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));
            FileResource fr = new FileResource(f);
            int rank = getRank(year, name, gender, fr);
            if (rank < maxRank) {
                maxRank = rank;
                yearRank = year;
            }
        }
        return yearRank;
    }

    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double sum = 0.0;
        int count = 0;
        for (File f : dr.selectedFiles()) {
            count++;
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));
            FileResource fr = new FileResource(f);
            int rank = getRank(year, name, gender, fr);
            if (rank != -1) {
                sum += (double) rank;
            }
        }
        if (sum == 0.0) return -1.0;
        return (double) sum/count;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource f = new FileResource();
        int totalBirths = 0;
        int ref = 0;
        int rank = getRank(year, name, gender, f);
        for (CSVRecord rec : f.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                ref ++;
                if (ref < rank) {
                    totalBirths += Integer.parseInt(rec.get(2));
                }
            }
        }
        return totalBirths;
    }

    public static void main(String[] args) {
        Part1 p = new Part1();
        //------------ test cases -----------------------
        // FileResource f = new FileResource();

        // p.totalBirths(f);

        // int rank = p.getRank(1971, "Frank", "M", f);
        // System.out.println("Rank = " + rank);

        // String name = p.getName(1982, 450, "M");
        // System.out.println("Name = " + name);

        // p.whatIsNameInYear("Owen", 1974, 2014, "M");

        // int year = p.yearOfHighestRank("Mich", "M");
        // System.out.println("Highest ranking was in " + year);

        // double averageRank = p.getAverageRank("Robert", "M");
        // System.out.println("Average rank " + averageRank);
        
        int totalBirthsHigherRanks = p.getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println("Total births higher rank:" + totalBirthsHigherRanks);
    }
}
