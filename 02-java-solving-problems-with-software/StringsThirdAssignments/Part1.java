package StringsThirdAssignments;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class Part1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(currIndex != -1) {
            int diff = currIndex - startIndex;
            if( diff % 3 == 0 ) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }

    public String findGene (String dna, int where){
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int stopIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        
        if (stopIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, stopIndex + 3);

    }

    public String printAllGene (String dna) {
        int startIndex = 0;
        while (true) {
            int currIndex = dna.indexOf("ATG", startIndex);
            if (currIndex == -1) {
                break;
            }
            String gene = findGene(dna, currIndex);
            
            System.out.println("Gene founded:" + gene);
        }
        return "";
    }
    
    public StorageResource getAllGenes (String dna) {
        StorageResource store = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            store.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex + 1) + currentGene.length();
            // System.out.println("Gene founded:" + gene);
        }
        return store;
    }

    public float cgRatio (String dna) {
        int countC = 0;
        int countG = 0;
        int length = dna.length();
        while (true) {
            int cIndex = dna.indexOf("C");
            if (cIndex == -1) {
                break;
            }
            countC++;
            dna = dna.substring(0, cIndex) + dna.substring(cIndex + 1);
        }
        
        while (true) {
            int gIndex = dna.indexOf("G");
            if (gIndex == -1) {
                break;
            }
            countG++;
            dna = dna.substring(0,gIndex) + dna.substring(gIndex + 1);
        }
        int sum = countC + countG;
        return (float)sum/length;
    }
    public int countCTG (String dna) {
        int countCTG = 0;
        while (true) {
            int ctgIndex = dna.indexOf("CTG");
            if (ctgIndex == -1) {
                return countCTG;
            }
            countCTG++;
            dna = dna.substring(0,ctgIndex) + dna.substring(ctgIndex + 3);
        }
    }

    public void processGenes(StorageResource sr) {
        // int totalItems = sr.size();
        int count = 0;
        int maxLength = 0;
        int countCGRatio = 0;
        // if (totalItems == 0) {
        
        // }
        for (String item : sr.data()) {
            int temp = item.length();
            if (item.length() > 60) {
                count++;
                // System.out.println("Items that are longer than 60 characters: " + item);   
            }
            float cgRatio = cgRatio(item);
            // System.out.println("cgRatio: " + cgRatio);
            
            if (cgRatio > 0.35) {
                // System.out.println("Items that have C-G ratio higher than 0.35: " + item);
                countCGRatio++;
            }
            
            maxLength = Math.max(temp, maxLength);
            
        }
        System.out.println("Number of genes in sr that are longer than 60 characters: " + count);
        System.out.println("Number of items in sr that have C-G ratio higher than 0.35: " + countCGRatio);
        System.out.println("Length of the longest gene in sr: " + maxLength);
    }
    public void testProcessGenes(String dna) {
        // String dna1 = "AAATGXXXYYYTAACCC";
        // String dna2 = "ATGCCGTAA"; // higher ratio than 0.35
        // String dna3 = "ATGCCXXTTAAXTAA"; // lower ratio than 0.35
        // String dna4 = "CCATGXXXYTAACCC";
        // String dna5 = "CCATGXXXYYYTAGXXTAACCC";
        // StorageResource sr = getAllGenes(dna1);
        // processGenes(sr);
        // sr = getAllGenes(dna2);
        // processGenes(sr);
        // sr = getAllGenes(dna3);
        // processGenes(sr);
        // sr = getAllGenes(dna4);
        // processGenes(sr);
        // sr = getAllGenes(dna5);
        StorageResource sr = getAllGenes(dna);
        int countCTG = countCTG(dna);
        System.out.println("Number of condons CTG: " + countCTG);
        processGenes(sr);
        int totalItems = sr.size();
        System.out.println("Number of genes: " + totalItems);
    }
    public void testFindStopCodon() {
        // Setup test DNA string
        String dna = "ATGXXXYYYTAAXXTAG";

        // 1. Valid in-frame stop codon ("TAA" at index 9: (9 - 0) % 3 == 0)
        int index1 = findStopCodon(dna, 0, "TAA");
        System.out.println("Test 1 (Tìm 'TAA' từ 0): Kết quả = " + index1 + " | Kỳ vọng = 9");

        // 2. Out-of-frame stop codon should be ignored ("TAG" at index 14: (14 - 0) % 3 != 0)
        int index2 = findStopCodon(dna, 0, "TAG");
        System.out.println("Test 2 (Tìm 'TAG' out-of-frame từ 0): Kết quả = " + index2 + " | Kỳ vọng = " + dna.length());

        // 3. Stop codon không tồn tại
        int index3 = findStopCodon(dna, 0, "TGA");
        System.out.println("Test 3 (Tìm 'TGA' không có): Kết quả = " + index3 + " | Kỳ vọng = " + dna.length());

        // 4. Custom start index (tìm 'TAG' bắt đầu từ index 6)
        int index4 = findStopCodon(dna, 6, "TAG");
        System.out.println("Test 4 (Tìm 'TAG' từ index 6): Kết quả = " + index4 + " | Kỳ vọng = 14");
    }
    public void testFindGene() {

    }
    public void testPrintAllGene() {
        // -------------------------------------------------------------------------
        // Case 1: Chuỗi có 3 Gen hợp lệ nối tiếp nhau (Dùng cả 3 Stop Codons: TAA, TAG, TGA)
        // Gen 1: ATGXXXYYYTAA
        // Gen 2: ATGAAAGGGTAG
        // Gen 3: ATGCCCTTTTGA
        // -------------------------------------------------------------------------
        System.out.println("=== TEST 1: DNA có 3 Gen nối tiếp ===");
        String dna1 = "CCATGXXATGYTAAATGGGGATGAAAGGGTAGTTTATGCCCTTTTGACCC";
        printAllGene(dna1);
        /* Kỳ vọng in ra:
        Gene found: ATGXXXYYYTAA
        Gene found: ATGAAAGGGTAG
        Gene found: ATGCCCTTTTGA
        */

        // -------------------------------------------------------------------------
        // Case 2: Chuỗi chứa Gen out-of-frame xen kẽ giữa các Gen hợp lệ
        // Đoạn 1: ATGXXTAA (Độ dài giữa = 2 -> out-of-frame -> Bỏ qua)
        // Đoạn 2: ATGYYYZZZTAG (Gen 1 hợp lệ)
        // Đoạn 3: ATGAAATGA (Gen 2 hợp lệ)
        // -------------------------------------------------------------------------
        System.out.println("\n=== TEST 2: Xen kẽ Gen bị Out-of-Frame ===");
        String dna2 = "ATGXXTAAXXATGYYYZZZTAGCCATGAAATGACCC";
        printAllGene(dna2);
        /* Kỳ vọng in ra:
        Gene found: ATGYYYZZZTAG
        Gene found: ATGAAATGA
        */

        // -------------------------------------------------------------------------
        // Case 3: Chuỗi có mã kết thúc bị lặp hoặc xuất hiện nhiều Stop Codons
        // Gen 1 sẽ dừng ngay ở 'TAG' hợp lệ đầu tiên, đoạn phía sau được tính tiếp cho Gen 2
        // -------------------------------------------------------------------------
        System.out.println("\n=== TEST 3: Chuỗi chứa nhiều Stop Codons gần nhau ===");
        String dna3 = "AAATGAAABBBTAGXXTAACCATGGGGHHHTGA";
        printAllGene(dna3);
        /* Kỳ vọng in ra:
        Gene found: ATGAAABBBTAG
        Gene found: ATGGGGHHHTGA
        */
    }
    public static void main (String[] args) {
        Part1 part1 = new Part1();
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();

        // part1.testFindStopCodon();
        // part1.testFindGene();
        // part1.testPrintAllGene();
        part1.testProcessGenes(dna);
    }
}
