import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Wordle {
    public static void main(String[] args) throws IOException {
        // list that holds strings of a file
        List<String> dict = new ArrayList<String>();

        // load data from file
        BufferedReader bf = new BufferedReader(new FileReader("dict.txt"));

        // read entire line as string
        String line = bf.readLine();

        // create a scanner object
        Scanner scanner = new Scanner(System.in);

        // checking for end of file
        while (line != null) {
            dict.add(line);
            line = bf.readLine();
        }

        // closing bufferreader object
        bf.close();

        // generate a random number range in dict
        Random random = new Random();
        int randomNum = random.nextInt(2317 + 1);

        // select a random word from dict
        String word = dict.get(randomNum);

        // input attempt
        String userWord = "";
        for (int i = 1; i <= 7; i++) {
            if(i==7){
                System.out.println("You exceeded maximum try shot!");
                System.out.println("You failed! The key word is " + word + ".");
                break;
            }
            userWord = scanner.nextLine();
            System.out.println("TRY" + "(" + i + ")" + " " + userWord + ":");

            if (userWord.length() != 5) { // ensure input length is 5
                System.out.println("The length of word must be five!");
                continue;
            } else if (!isContains(dict, userWord)) { // checks whether the word is in dictionary or not
                System.out.println("Word does not exist in the dictionary!");
                continue;
            } else if (userWord.equals(word)) { // if the words match exit the program
                if (i == 1)
                    System.out.println("Congratulations! You guess right in " + i + "st shot!");
                else if (i == 2)
                    System.out.println("Congratulations! You guess right in " + i + "nd shot!");
                else if (i == 3)
                    System.out.println("Congratulations! You guess right in " + i + "rd shot!");
                else
                    System.out.println("Congratulations! You guess right in " + i + "th shot!");
                break;

            } else { // if the conditions are met, compare the letters
                for (int j = 1; j < 5; j++) {
                    if (userWord.charAt(j) == word.charAt(j)) {
                        System.out.println((j+1) + ". letter exists and located in right position.");
                    } else if (word.indexOf(userWord.charAt(j)) != -1) {
                        System.out.println((j+1) + ". letter exists and located in wrong position.");
                    } else {
                        System.out.println((j+1) + ". letter does not exist.");
                    }

                }
            }

        }
        scanner.close();
    }

    // checks whether the word is in dictionary or not. If contains it returns true
    public static boolean isContains(List<String> dict, String word) {
        for (String string : dict) {
            if (string.equals(word))
                return true;
        }
        return false;
    }
}