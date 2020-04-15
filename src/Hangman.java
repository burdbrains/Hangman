import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class Hangman{
    // Attributes
    String[] wordBank;
    String word;
    int incorrect;
    boolean wGuess;
    //int dashCount;





    // Constructor.
    public Hangman() throws IOException{
        wordBank = getWordBank();
        word = selectWord(wordBank);
        incorrect = 0;
        wGuess = false;
        //dashCount = word.length();

        // Write the code to run the entire game.
        String dashes = createDashes();
        //StringBuilder sbDash =

        while (incorrect <= 6 && !wGuess)
        {
            if (incorrect == 6)
            {
                drawHangman(incorrect);
                System.out.println("The word was: " + word);
                incorrect++;
            }
            else
            {
                drawHangman(incorrect);
                System.out.println("You have " + incorrect + " incorrect guesses.");
                System.out.println(dashes);
                String guess = getGuess();
                dashes = replaceDash(guess, dashes);
                if (wGuess)
                {
                    System.out.println("You guessed " + word + " correctly, good job you win! ");
                }
                else
                {
                    System.out.println();
                }
            }
        }
    }

    // Returns an array filled with words from the wordbank.txt file. The array will be the wordbank from which the secret word is selected.
    public String[] getWordBank() throws IOException{
        Scanner sc = new Scanner(new File("C:\\Users\\erter\\IdeaProjects\\Hangman\\src\\wordbank.txt"));
        ArrayList<String> lines = new ArrayList<String>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        return lines.toArray(new String[0]);
    }

    // Randomly selects word from the word bank to be used for the Hangman game. Returns the word.
    public String selectWord(String[] arr){
        int aLen = arr.length;
        int random = (int)(Math.random() * aLen + 1);
        return arr[random];
    }


    // Prompts the player to guess either a letter or a word. Returns the player's guess.
    public String getGuess(){
        Scanner guess = new Scanner(System.in);  // Create a Scanner object
        System.out.print("Guess a letter or the word.  ");

        return guess.nextLine();  // Read user input

    }

    // Returns a string of dashes. One dash for each letter in the selected word.
    public String createDashes(){
        int wLen = this.word.length();

        String dashes = "";

        for (int i = 0; i < wLen; i++)
        {
            dashes += "_ ";
        }

        return dashes;
    }


    // Takes in the player's guess as an argument, and replaces any dashes that correspond with any incidences of the guessed letter within the secret word. Ex. If "l" is guessed, the dashes will be "_ _ l l _" for the word "hello".
    public String replaceDash(String guess, String dashes){
        String rStr = dashes;

        if (word.equals(guess))
        {
            rStr = "You guessed " + word + " correctly, good job you win! ";
        }
        else if (guess.length() == 1 && word.indexOf(guess) != -1)
        {
            int dLen = dashes.length();

            StringBuilder sbDash = new StringBuilder(dashes);

            int j = 0;
            char d = '_';

            char gChar = guess.charAt(0);
            //char[] cArray = dashes.toCharArray();

            if (word.indexOf(guess) != -1)
            {
                int dashCount = word.length();
                for (int i = 0; i < dLen; i+=2)
                {
                    if (dashes.charAt(i) == d && word.indexOf(guess, j) != -1)
                    {
                        int gIndex = word.indexOf(guess, j);
                        sbDash.setCharAt(gIndex*2, gChar);
                    }
                    if (sbDash.charAt(i) != d)
                    {
                        dashCount--;
                    }
                    j++;
                }
                if (dashCount == 0)
                {
                    wGuess = true;
                }
            }
            else
            {   incorrect++;   }

            rStr = sbDash.toString();
        }
        else
        {   incorrect++;   }

        return rStr;
    }


    // Draws the hangman as the player makes incorrect guesses.
    public static void drawHangman(int incorrect){
        if (incorrect == 0) {
            System.out.println("____");
            System.out.println("| ");
            System.out.println("| ");
            System.out.println("| ");
            System.out.println("|____");
        }
        if (incorrect == 1) {
            System.out.println("____");
            System.out.println("|  0");
            System.out.println("| ");
            System.out.println("| ");
            System.out.println("|____");
        }
        if (incorrect == 2) {
            System.out.println("____");
            System.out.println("|  0");
            System.out.println("|  |");
            System.out.println("|");
            System.out.println("|____");
        }
        if (incorrect == 3) {
            System.out.println("____");
            System.out.println("|  0");
            System.out.println("| /|");
            System.out.println("|");
            System.out.println("|____");
        }
        if (incorrect == 4) {
            System.out.println("____");
            System.out.println("|  0");
            System.out.println("| /|\\");
            System.out.println("| ");
            System.out.println("|____");
        }

        if (incorrect == 5) {
            System.out.println("____");
            System.out.println("|  0");
            System.out.println("| /|\\");
            System.out.println("| / ");
            System.out.println("|____");
        }
        if (incorrect == 6) {
            System.out.println("GAME OVER!");
            System.out.println("____");
            System.out.println("|  0");
            System.out.println("| /|\\");
            System.out.println("| / \\");
            System.out.println("|_____");
        }
    }
}