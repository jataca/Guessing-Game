public class UnitTests {

    GuessingGame testGame;

    /**
    * UnitTests() create an an instance of GuessingGame so we can call it's methods
    */
    public UnitTests()
    {
        this.testGame = new GuessingGame(2); // passing in magic number. 2 should be passed in as a parameter to the constructor
    }

    /**
    * testGenerateRandomNumber() ensure random numbers are between min and max
    */
    public void testGenerateRandomNumber(){
        int max = 100;
        int min = 1;
        for (int testNum = 1; testNum <= 10; testNum++)
        {
            int randomNumber = this.testGame.generateRandomNumber(max, min);
            if (randomNumber >= min && randomNumber <= max)
            {
                System.out.println("generateRandomNumber() test " + testNum + " passed");
            }
            else{
                System.out.println("generateRandomNumber() test " + testNum + "failed");
            }
        }
    }

    /**
    * testGetUserInput() Ensures we return a string.
    * I believe it's debatible whether it's worth while testing this function
    */
    public void testGetUserInput(){
        String testString = "abc";
        if (this.testGame.getUserInput(100, 1, testString).getClass().equals(String.class)){
            System.out.println("getUserInput() test 1 passed");
        }
        else {
            System.out.println("getUserInput() test 1 failed");
        }
    }

    /**
    * testConverStringToInt() ensure we return the correct Integer
    */
    public void testConverStringToInt(){
        // test valid input. should return testString as an int
        String testString = "5";
        int valid = 5;
        int testMin = 1;
        int invalid = testMin - 1; // beware, this could change if the logic in convertGuessToInt() changes

        if (this.testGame.convertGuessToInt(testString, testMin) == valid) {
            System.out.println("convertGuessToInt() test 1 passed");
        }
        else {
            System.out.println("convertGuessToInt() test 1 failed");
        }

        // test input too big to be an Integer. Should return testMin - 1
        testString = "10000000000000000000000000";
        if (this.testGame.convertGuessToInt(testString, testMin) == invalid) {
            System.out.println("convertGuessToInt() test 2 passed");
        }
        else {
            System.out.println("convertGuessToInt() test 2 failed : " + this.testGame.convertGuessToInt(testString, testMin));
        }

        // test non numeric input. Should return testMin - 1
        testString = "abc";
        if (this.testGame.convertGuessToInt(testString, testMin) == invalid) {
            System.out.println("convertGuessToInt() test 3 passed");
        }
        else {
            System.out.println("convertGuessToInt() test 3 failed: " + this.testGame.convertGuessToInt(testString, testMin));
        }
    }

    /**
    * testCheckValidity() ensures checks whether the guess is a valid number, whether it's whithin bounds,
    * and if it's been guessed before work correctly
    */
    public void testCheckValidity(){
        // check valid guess
        int max = 3;
        int min = 1;
        int playersGuess = 2;
        int[] guessedNumbers = new int [] {0, 0, 0};
        int numberOfGuesses = 0;

        if (this.testGame.checkValidity(playersGuess, guessedNumbers, max, min, numberOfGuesses) == true){
            System.out.println("checkValidity() test 1 passed");
        }
        else {
            System.out.println("checkValidity() test 1 failed");
        }

        // check corner cases
        playersGuess = 3;
        if (this.testGame.checkValidity(playersGuess, guessedNumbers, max, min, numberOfGuesses) == true){
            System.out.println("checkValidity() test 2 passed");
        }
        else {
            System.out.println("checkValidity() test 2 failed");
        }

        playersGuess = 1;
        if (this.testGame.checkValidity(playersGuess, guessedNumbers, max, min, numberOfGuesses) == true){
            System.out.println("checkValidity() test 3 passed");
        }
        else {
            System.out.println("checkValidity() test 3 failed");
        }

        // check out of bounds
        playersGuess = 4;
        if (this.testGame.checkValidity(playersGuess, guessedNumbers, max, min, numberOfGuesses) == false){
            System.out.println("checkValidity() test 4 passed");
        }
        else {
            System.out.println("checkValidity() test 4 failed");
        }

        playersGuess = 0;
        if (this.testGame.checkValidity(playersGuess, guessedNumbers, max, min, numberOfGuesses) == false){
            System.out.println("checkValidity() test 5 passed");
        }
        else {
            System.out.println("checkValidity() test 5 failed");
        }

        // check with number already guessed
        playersGuess = 2;
        numberOfGuesses = 1;
        guessedNumbers[0] = 2;
        if (this.testGame.checkValidity(playersGuess, guessedNumbers, max, min, numberOfGuesses) == false){
            System.out.println("checkValidity() test 6 passed");
        }
        else {
            System.out.println("checkValidity() test 6 failed");
        }
    }

    /**
    * testIsGuessCorrect() ensures we return true if player's guess is correct, false otherwise
    */
    public void testIsGuessCorrect(){
        // number too high
        int secretNumber = 2;
        int guessedNumber = 3;
        if (this.testGame.isGuessCorrect(secretNumber, guessedNumber) == false){
            System.out.println("checkNumberTest() test 1 passed");
        }
        else{
            System.out.println("checkNumberTest() test 1 failed");
        }

        // number too low
        guessedNumber = 1;
        if (this.testGame.isGuessCorrect(secretNumber, guessedNumber) == false){
            System.out.println("checkNumberTest() test 2 passed");
        }
        else{
            System.out.println("checkNumberTest() test 2 failed");
        }

        // number just right
        guessedNumber = 2;
        if (this.testGame.isGuessCorrect(secretNumber, guessedNumber) == true){
            System.out.println("checkNumberTest() test 3 passed");
        }
        else{
            System.out.println("checkNumberTest() test 3 failed");
        }
    }
    /**
    * runUnitTests() - yep do that
    */
    public void runUnitTests(){
        testGenerateRandomNumber();
        testGetUserInput();
        testConverStringToInt();
        testCheckValidity();
        testIsGuessCorrect();
    }
}
