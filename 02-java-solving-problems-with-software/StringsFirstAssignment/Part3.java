package StringsFirstAssignment;

public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int count = 0;
        while (true) {
            int stringaIndex = stringb.indexOf(stringa);
            if (stringaIndex == -1) {
                break;
            }
            count++;
            stringb = stringb.substring(0,stringaIndex) + stringb.substring(stringaIndex + stringa.length(), stringb.length());
        }
        if (count >= 2) {
            return true;
        }
        return false;
    }
    public void testing() {
        if (twoOccurrences("by", "A story by Abby Long")){
            System.out.println("by in 'A story by Abby Long' is True");
        }
        if (twoOccurrences("a", "banana")){
            System.out.println(" a in 'banana' is True");
        }
        if (twoOccurrences("atg", "ctgtatgta")){
            System.out.println(" atg in 'ctgtatgta' is True");
        }
    }
    public static void main (String[] args) {
        Part3 part3 = new Part3();
        part3.testing();
    }
}
