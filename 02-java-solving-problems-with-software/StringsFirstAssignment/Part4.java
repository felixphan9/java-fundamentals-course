package StringsFirstAssignment;

import edu.duke.URLResource;

public class Part4 {
    public void testing(){
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");      
        for (String word : ur.words()) {
            String lowerWord = word.toLowerCase();
            int youtubeIndex = 0;
            if (lowerWord.contains("youtube.com")) {
                youtubeIndex = lowerWord.indexOf("youtube.com");
                int firstDoubleQuote = lowerWord.lastIndexOf("\"",youtubeIndex);
                int secondDoubleQuote = lowerWord.indexOf("\"",youtubeIndex);
                // System.out.println("firstDoubleQuote: " + firstDoubleQuote);
                // System.out.println("secondDoubleQuote: " + secondDoubleQuote);
                System.out.println("lowerWord: " + lowerWord);
            }


        }
    }
    public static void main (String[] args) {
        Part4 part4 = new Part4();
        part4.testing();
    }
}
