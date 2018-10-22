# Guessing Game w/ Tests
A guessing game where the user has to guess a secret number. After every guess the program tells the user whether their number was too large or too small. At the end the number of tries needed should be printed. It counts only as one try if they input the same number multiple times consecutively.

## Compiling, building, & running. 
1. Open Terminal
2. To compile, input 
```
javac GuessingGame.java Main.java UnitTests.java FunctionalTest.java
```
3. To create executible jar file, input
```
jar cvfm Guess.jar manifest.txt *.class *.txt" to create executible jar file
```
4. There are three different ways to run the program: 
4a. To play the game, input "" to play the game
```
java -jar Guess.jar 1
```
4b. to run Unit Tests, input 
```
java -jar Guess.jar 2
```
4c. To run the Functional Test, input
```
java -jar Guess.jar 3
```

## Thoughts & lessons learned
There are several things that I think could be done better, to name a few: 
1. Variables. There are a few magic numbers floating around and there's a lack of consistency whether class variables or local variables are used.
2. No Unit Test for the GuessingGame::playGame() function.
3. Functional Test and Unit Test code is inside the GuessingGame class, I think ideally all the test code would be outside that class. 
4. When I wrote the game program I didn't write it with tests in mind, I just wrote code as ideas came to me. I may have gone in a different direction if I had automated tests in mind. 
5. There's an inherit difficulty in writing unit tests for private methods. Ideas online seem to differ on the approach on how to deal with private methods. Some say private methods should be simple enough that they shouldn't need unit tests, others say that they are "tested" by writing tests that test the public methods that call them. Still others say they should be copied into a public project where the unit tests can test the "copied" versions directly. 
6. Designing a software project from scratch in a way that scales nicely is a lot harder than editing code in an existing project that was well written to begin with. 
