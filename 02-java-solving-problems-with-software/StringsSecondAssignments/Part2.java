package StringsSecondAssignments;

public class Part2 {
    public int howMany(String stringa, String stringb) {
        int count = 0;
        int index;
        if (stringb == "") {
            return 0;
        }
        while (true) {
            index = stringb.indexOf(stringa);
            if (index == -1) {
                return count;
            }
            count++;
            stringb = stringb.substring(index + stringa.length());
        }
    }

    public void testHowMany(){
        // Test case 1: Ví dụ trong đề bài (kết quả mong đợi: 3)
        String a1 = "GAA";
        String b1 = "ATGAACGAATTGAATC";
        int result1 = howMany(a1, b1);
        System.out.println("stringa: " + a1 + " | stringb: " + b1);
        System.out.println("Số lần xuất hiện: " + result1 + " (Kỳ vọng: 3)\n");

        // Test case 2: Ví dụ không trùng lặp (Non-overlapping) trong đề bài (kết quả mong đợi: 2)
        String a2 = "AA";
        String b2 = "ATAAAA";
        int result2 = howMany(a2, b2);
        System.out.println("stringa: " + a2 + " | stringb: " + b2);
        System.out.println("Số lần xuất hiện: " + result2 + " (Kỳ vọng: 2)\n");

        // Test case 3: Chuỗi không tồn tại trong stringb (kết quả mong đợi: 0)
        String a3 = "CCC";
        String b3 = "ATGAATGAATC";
        int result3 = howMany(a3, b3);
        System.out.println("stringa: " + a3 + " | stringb: " + b3);
        System.out.println("Số lần xuất hiện: " + result3 + " (Kỳ vọng: 0)\n");

        // Test case 4: Chuỗi xuất hiện liên tiếp (kết quả mong đợi: 3)
        String a4 = "ab";
        String b4 = "ababab";
        int result4 = howMany(a4, b4);
        System.out.println("stringa: " + a4 + " | stringb: " + b4);
        System.out.println("Số lần xuất hiện: " + result4 + " (Kỳ vọng: 3)\n");
    }
    public static void main (String[] args) {
        Part2 part2 = new Part2();
        part2.testHowMany();
    }
}
