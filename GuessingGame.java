import java.util.Random;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;

/**
* Guessing Game
*  This game is a single player game where the computer chooses a random number
*  and the player tries to guess what the number is. The program ends when the
*  player guesses the number or quits.
*/
public class GuessingGame {

    // Class Variables to help us with testing
    private static int mode; // gameMode, unitTestModeMode, funcionalTestMode
    final int  gameMode = 1;
    final int  unitTestMode = 2;
    final int  functionalTestMode = 3;

    // Used in the case of functional testing
    private String [] functionalTestGuesses;
    private int functionalTestFileIndex;

    /**
    * GuessingGame() this is the constructor where we initialize the mode we're in
    */
    public GuessingGame(int mode){
        this.mode = mode;
    }

    /**
    * print() function used to filter out print statements when running unit tests
    */
    public void print(String pGameString) {
        if (this.mode != unitTestMode){
            System.out.println(pGameString);
        }
    }

    /**
    * setFunctionalTestGuesses() initialize variables needed for a functional test
    */
    public void setFunctionalTestGuesses(String pFunctionalTestGuesses[]){
        this.functionalTestGuesses = new String[pFunctionalTestGuesses.length];
        this.functionalTestFileIndex = 0;
        System.arraycopy(pFunctionalTestGuesses, 0, this.functionalTestGuesses, 0, this.functionalTestGuesses.length);
    }

    /**
    * generateRandomNumber() generates a secret number between two numbers
    */
    public int generateRandomNumber(int pMax, int pMin){
        print("Generating a random secret number from " + pMin + " to " + pMax + "...");

        int tempSecretNumber = 0;

        Random random = new Random();

        tempSecretNumber = random.nextInt(pMax) + pMin;

        return tempSecretNumber;
    }

    /**
    * getUserInput() requests input from the user. If testing a string should be provided.
    * @return the player's guess
    */
    public String getUserInput(int pMax, int pMin, String pTestString){

        print("\nWhich number will you guess? Pick a number from " + pMin + " to " + pMax + ", or enter \"Quit\" to quit:");

        if( this.mode != gameMode) {
            System.setIn(new ByteArrayInputStream(pTestString.getBytes()));
        }

        Scanner sc = new Scanner(System.in);

        String sTempGuess = sc.nextLine();

        return sTempGuess;
    }

    /**
    * convertGuessToInt() converts a string to an int. If the String isn't compatible
    * then it will return one less than the minimum number which will be invalid
    */
    public int convertGuessToInt(String pPlayersGuess, int pMin){
        int tempGuess = 0;

        try{
            tempGuess = Integer.parseInt(pPlayersGuess);
        }
        catch(Exception e){
          tempGuess = pMin - 1; // this will ensure that the users input is invalid
         }

        return tempGuess;
    }

    /**
    * checkValidity() ensures the player's guess is a number between the min and max
    */
    public boolean checkValidity(int pPlayersGuess, int pGuessedNumbers[],
                                 int pMax, int pMin, int pNumberOfGuesses){
        boolean tempGuessIsValid = true;

        // Make sure the guess is between the min/max
        if (pPlayersGuess <= pMax && pPlayersGuess >= pMin){

            // make sure the guess hasn't been guessed before
            for (int listIx = 0; listIx < pNumberOfGuesses; listIx++){
                if (pPlayersGuess == pGuessedNumbers[listIx]){
                    print("You already guessed that number");
                    tempGuessIsValid = false;
                }
            }
        }
        else{
            tempGuessIsValid = false;
            print("invalid input");
        }

        return tempGuessIsValid;
    }

    /**
    * checkNumber() determines if the player's guess matches the secret number
    */
    public boolean isGuessCorrect(int pSecretNumber, int pPlayersGuess){

        boolean tempGuessIsCorrect = false;

        if (pSecretNumber == pPlayersGuess){
            print("You guessed correctly! You won!!");
            tempGuessIsCorrect = true;
        }
        else if (pSecretNumber > pPlayersGuess){
            print("You guessed too low!");
        }
        else{
            print("You guessed too high!");
        }

        return tempGuessIsCorrect;
    }

    /**
    * playGame() kicks off the game and contains the game loop
    */
    public void playGame() {

        print("Welcome to the Guessing Game!\n");

        int max = 100;
        int min = 1;

        int[] guessedNumbers = new int[max]; // in Java all values are initialized to 0

        int secretNumber = generateRandomNumber(max, min);

        boolean keepGuessing = true;

        boolean guessIsValid = false;

        boolean guessIsCorrect = false;

        int playersGuess = 0;

        int numberOfGuesses = 0;

        // game loop
        while (keepGuessing){

            String testString = "";

            // check for functional mode
            if (this.mode == functionalTestMode){
                if (this.functionalTestFileIndex < this.functionalTestGuesses.length){
                    testString = this.functionalTestGuesses[this.functionalTestFileIndex];
                    this.functionalTestFileIndex++;
                }
                else
                {
                    testString = "Quit"; // correct answer was not in functional test, exit to avoid problems
                }
                System.out.println("\n\nInput : " + testString);
            }

            String sPlayersGuess = getUserInput(max, min, testString);

            // check if the player wants to quit
            if (!sPlayersGuess.equals("Quit")){
                playersGuess = convertGuessToInt(sPlayersGuess, min);

                guessIsValid = checkValidity(playersGuess, guessedNumbers, max, min, numberOfGuesses);

                if (guessIsValid){
                    guessedNumbers[numberOfGuesses] = playersGuess;

                    guessIsCorrect = isGuessCorrect(secretNumber, playersGuess);

                    if (guessIsCorrect){
                        keepGuessing = false;
                    }

                    numberOfGuesses += 1;
                    print("Number of guesses " + numberOfGuesses);
                }
            }
            else{
                print("Thanks for playing!");
                keepGuessing = false;
            }

        }
    }
}
