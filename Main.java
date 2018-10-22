import java.io.IOException;

public class Main{

    /**
    * main() Checks which mode we're running and creates a Game, Unit Test, or Functional Test
    */
    public static void main(String[] args) {

        // Check commandline parameters
        if (args.length == 1){
            if (args[0].equals("1"))
            {
                GuessingGame game = new GuessingGame(1);
                game.playGame();
            }
            else if (args[0].equals("2")){
                UnitTests tests = new UnitTests();
                tests.runUnitTests();
            }
            else if (args[0].equals("3")){
                FunctionalTest functionalTest = new FunctionalTest();
                try{
                    functionalTest.runFunctionalTest();
                }
                catch(IOException e)
                {
                    System.out.println("Functional Test could not be run");
                }
            }
            else{
                System.out.println("incorrect commandline parameters. Enter 1 to play, 2 for unit tests, & 3 for functional test");
            }
        }
        else {
            System.out.println("incorrect commandline parameters. Enter 1 to play, 2 for unit tests, & 3 for functional test");
        }
    }
}
