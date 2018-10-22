import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


public class FunctionalTest{

    public void runFunctionalTest() throws IOException{
        String guess;
        // Read guesses from file and insert them into a list and then convert list to a String array
        FileReader fileReader = new FileReader("FunctionalTestGuesses.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // not sure how long the file is so we use an ArrayList
        List<String> tempList = new ArrayList<String>();

        while ((guess = bufferedReader.readLine()) != null)
        {
            tempList.add(guess);
        }

        bufferedReader.close();

        // format of text file is important! first line is max, second is min, third is nothing

        String [] functionalTestGuesses = new String[tempList.size()];
        functionalTestGuesses = tempList.toArray(new String[tempList.size()]);

        System.out.println(tempList.get(0));
        System.out.println(functionalTestGuesses[0]);

        GuessingGame bbTestGame = new GuessingGame(3);
        bbTestGame.setFunctionalTestGuesses(functionalTestGuesses);
        bbTestGame.playGame();
    }
}
