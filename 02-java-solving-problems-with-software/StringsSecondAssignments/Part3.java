package StringsSecondAssignments;

public class Part3 {
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

    public int countGenes (String dna) {
        int count = 0;
            while (true) {
                String gene = findGene(dna);
                if (gene == "") {
                    return count;
                }
                System.out.println("Gene founded:" + gene);
                int geneIndex = dna.indexOf(gene);
                dna = dna.substring(geneIndex + gene.length());
                count++;
            }
    }
    public void testCountGenes(){
        // Test case 1: Chuỗi DNA chứa nhiều gene
        String dna1 = "ATGTAAGATGCCCTAG";
        System.out.println("Testing countGenes on DNA: " + dna1);
        int count1 = countGenes(dna1);
        System.out.println("Total genes count: " + count1 + "\n");

        // Test case 2: Chuỗi DNA chỉ chứa 1 gene
        String dna2 = "CCATGCGCTAAG";
        System.out.println("Testing countGenes on DNA: " + dna2);
        int count2 = countGenes(dna2);
        System.out.println("Total genes count: " + count2 + "\n");

        // Test case 3: Chuỗi DNA không có mã khởi đầu ATG (không chứa gene nào)
        String dna3 = "CGTACCCTAAG";
        System.out.println("Testing countGenes on DNA: " + dna3);
        int count3 = countGenes(dna3);
        System.out.println("Total genes count: " + count3 + "\n");

        // Test case 4: Chuỗi DNA không có mã kết thúc hợp lệ
        String dna4 = "ATGCCCCGGTTT";
        System.out.println("Testing countGenes on DNA: " + dna4);
        int count4 = countGenes(dna4);
        System.out.println("Total genes count: " + count4 + "\n");

        // Test case 5: Chuỗi DNA rỗng
        String dna5 = "";
        System.out.println("Testing countGenes on DNA: (Empty string)");
        int count5 = countGenes(dna5);
        System.out.println("Total genes count: " + count5 + "\n");
    }
    public static void main (String[] args) {
        Part3 part3 = new Part3();
        part3.testCountGenes();
    }
}
