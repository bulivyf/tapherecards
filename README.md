Company: Tap Here!

Subject: Java coding assignment

Author: Evan Fraser

Origin Date: 12/9/2016


INTRODUCTION

Code provided is in response to printout description for the functional use of a deck of cards.

The requirements listed the two base functions:

1. shuffle(): which took a deck on 52 (or less cards) and randomly shuffle them.

2. deal_one_card(): which provides one card from the current deck.


DEVELOPER NOTES

The files presented are those created with Eclipse Neon.  The project type is maven.  The pom file lists a restriction for the use of Java 1.8 only.  mvn runs should executed the BaseTests category of JUnit tests.


Source for the app is presented in the directory location in src//main/java/com/taphere/cards/

1) IDeckType: provides the method signatures for any deck of cards that is to be used by the dealer.

2) StandardDeck.java: provides a standard deck implementation covering the API methods mentioned above.

3) CardDeckDealer.java: Performs the actions shuffle and deal_one_card with the deck of cards being used.  

4) PlayRoomApp.java: This is the Java application that runs the code, via main().


TESTER NOTES

Unit test files are located in: 
src/test/java/com/taphere/cards
Ignore the TestCategories directory, as they define the test types that are available in the JUnit test code.
