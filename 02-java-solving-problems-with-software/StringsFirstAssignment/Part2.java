package StringsFirstAssignment;

public class Part2 {
    public String findSimpleGene (String dna, String startCodon, String stopCodon) {
        int start = dna.indexOf(startCodon);
		if (start == -1) {
			return "";
		}
        int stop = dna.indexOf(stopCodon, start+3);
		if ((stop - start) % 3 == 0) {
			return dna.substring(start, stop+3);
		}
		else {
			return "";
		}

    }

    public void testSimpleGene () {
        // 1. DNA with no ATG
        String dna1 = "GATTACACCCCGGGGAAAA";

        // 2. DNA with no TAA
        String dna2 = "ATGGGGCACACACACACAC";

        // 3. DNA with no ATG or TAA
        String dna3 = "CCCCGGGGCCCCGGGGAAA";

        // 4. DNA with ATG, TAA and substring between them NOT a multiple of 3
        String dna4 = "CCATGGGTAATT";

        // 5. Normal DNA (ATG to TAA with intervening sequence as a multiple of 3)
        String dna5 = "CCATGGACGCATAATT";

        // --- BỔ SUNG CÁC TEST CASE LOWERCASE ---
        // 6. DNA chữ thường hoàn toàn hợp lệ (multiple of 3)
        String dna6 = "ccatggacgcataatt";

        // 7. DNA kết hợp cả chữ hoa và chữ thường hợp lệ (Mixed Case)
        String dna7 = "cCAtgGacGCaTAaTt";

        // 8. DNA chữ thường không hợp lệ (đoạn giữa không chia hết cho 3)
        String dna8 = "ccatgggtaatt";

        // Print results and verify constraints
        System.out.println("1. DNA with no ATG: " + dna1);
        System.out.println("Gene " + findSimpleGene(dna1,"ATG", "TAA"));
        System.out.println();

        System.out.println("2. DNA with no TAA: " + dna2);
        System.out.println("Gene " + findSimpleGene(dna2,"ATG", "TAA"));
        System.out.println();

        System.out.println("3. DNA with no ATG or TAA: " + dna3);
        System.out.println("Gene " + findSimpleGene(dna3,"ATG", "TAA"));
        System.out.println();

        System.out.println("4. DNA with ATG and TAA (not a multiple of 3 between): " + dna4);
        System.out.println("Gene " + findSimpleGene(dna4,"ATG", "TAA"));
        System.out.println();

        System.out.println("5. Normal DNA (multiple of 3 between): " + dna5);
        System.out.println("Gene " + findSimpleGene(dna5,"ATG", "TAA"));

        // In kết quả các test case LowerCase mới
        System.out.println("6. Normal DNA (Lower Case): " + dna6);
        System.out.println("Gene: " + findSimpleGene(dna6, "atg", "taa") + "\n");

        System.out.println("7. Normal DNA (Mixed Case): " + dna7);
        System.out.println("Gene: " + findSimpleGene(dna7, "atg", "taa") + "\n");

        System.out.println("8. Invalid DNA (Lower Case - not multiple of 3): " + dna8);
        System.out.println("Gene: " + findSimpleGene(dna8, "atg", "taa") + "\n");
    }
    public static void main (String[] args) {
        Part2 part2 = new Part2();
        part2.testSimpleGene();
    }
}
