package StringsSecondAssignments;

public class Part1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex);
        if (stopIndex == -1)
        {
            return dna.length();
        }
        if ((stopIndex - startIndex) % 3 == 0){
            return stopIndex;
        }
        else {
            return dna.length();
        }
    }

    public String findGene (String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = findStopCodon(dna, startIndex, "TAA");
        int temp = Math.min(stopIndex, findStopCodon(dna, startIndex, "TAG"));
        stopIndex = Math.min(temp, findStopCodon(dna, startIndex, "TGA"));
        
        if (stopIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, stopIndex + 3);

    }

    public String printAllGene (String dna) {

        while (true) {
            String gene = findGene(dna);
            if (gene == "") {
                break;
            }
            System.out.println("Gene founded:" + gene);
            int geneIndex = dna.indexOf(gene);
            dna = dna.substring(geneIndex + gene.length());
        }
        return "";
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
        // Test 1: Gen hợp lệ (có ATG và TAA in-frame, độ dài chia hết cho 3)
        // ATG (0) -> XXXYYY (6 ký tự) -> TAA (12) => Đoạn gen: ATGXXXYYYTAA
        String dna1 = "AAATGXXXYYYTAACCC";
        String gene1 = findGene(dna1);
        System.out.println("Test 1 (Gen hợp lệ):");
        System.out.println("   DNA      : " + dna1);
        System.out.println("   Kết quả  : " + gene1 + " | Kỳ vọng: ATGXXXYYYTAA\n");

        // Test 2: Không có Start Codon (ATG)
        String dna2 = "CCXXXYYYTAACCC";
        String gene2 = findGene(dna2);
        System.out.println("Test 2 (Không có ATG):");
        System.out.println("   DNA      : " + dna2);
        System.out.println("   Kết quả  : '" + gene2 + "' | Kỳ vọng: ''\n");

        // Test 3: Có ATG nhưng không có Stop Codon nào
        String dna3 = "CCATGXXXYYYGGGCCC";
        String gene3 = findGene(dna3);
        System.out.println("Test 3 (Không có Stop Codon):");
        System.out.println("   DNA      : " + dna3);
        System.out.println("   Kết quả  : '" + gene3 + "' | Kỳ vọng: ''\n");

        // Test 4: Stop Codon bị out-of-frame (đoạn giữa không chia hết cho 3)
        // ATG -> XXXY (4 ký tự) -> TAA
        String dna4 = "CCATGXXXYTAACCC";
        String gene4 = findGene(dna4);
        System.out.println("Test 4 (Stop Codon out-of-frame):");
        System.out.println("   DNA      : " + dna4);
        System.out.println("   Kết quả  : '" + gene4 + "' | Kỳ vọng: ''\n");

        // Test 5: Tìm Stop Codon hợp lệ nhỏ nhất/đầu tiên trong 3 loại (TAA, TAG, TGA)
        // ATG -> XXXYYY -> TAG xuất hiện trước TAA
        String dna5 = "CCATGXXXYYYTAGXXTAACCC";
        String gene5 = findGene(dna5);
        System.out.println("Test 5 (Nhiều Stop Codon):");
        System.out.println("   DNA      : " + dna5);
        System.out.println("   Kết quả  : " + gene5 + " | Kỳ vọng: ATGXXXYYYTAG\n");
    }
    public void testPrintAllGene() {
        // -------------------------------------------------------------------------
        // Case 1: Chuỗi có 3 Gen hợp lệ nối tiếp nhau (Dùng cả 3 Stop Codons: TAA, TAG, TGA)
        // Gen 1: ATGXXXYYYTAA
        // Gen 2: ATGAAAGGGTAG
        // Gen 3: ATGCCCTTTTGA
        // -------------------------------------------------------------------------
        System.out.println("=== TEST 1: DNA có 3 Gen nối tiếp ===");
        String dna1 = "CCATGXXXYYYTAAATGGGGATGAAAGGGTAGTTTATGCCCTTTTGACCC";
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
        // part1.testFindStopCodon();
        // part1.testFindGene();
        part1.testPrintAllGene();
    }
}
