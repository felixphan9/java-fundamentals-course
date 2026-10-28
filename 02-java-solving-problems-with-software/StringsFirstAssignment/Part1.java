package StringsFirstAssignment;

public class Part1 {

    public String findSimpleGene (String dna) {
        int start = dna.indexOf("ATG");
		if (start == -1) {
			return "";
		}
        int stop = dna.indexOf("TAA", start+3);
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

        // Print results and verify constraints
        System.out.println("1. DNA with no ATG: " + dna1);
        System.out.println("Gene " + findSimpleGene(dna1));
        System.out.println();

        System.out.println("2. DNA with no TAA: " + dna2);
        System.out.println("Gene " + findSimpleGene(dna2));
        System.out.println();

        System.out.println("3. DNA with no ATG or TAA: " + dna3);
        System.out.println("Gene " + findSimpleGene(dna3));
        System.out.println();

        System.out.println("4. DNA with ATG and TAA (not a multiple of 3 between): " + dna4);
        System.out.println("Gene " + findSimpleGene(dna4));
        System.out.println();

        System.out.println("5. Normal DNA (multiple of 3 between): " + dna5);
        System.out.println("Gene " + findSimpleGene(dna5));

    }

    public static void main (String[] args) {
        Part1 part1 = new Part1();
        part1.testSimpleGene();
    }
}

