Company: Tap Here!

Subject: Java coding assignment

Author: Evan Fraser

Date: 12/9/2016


INTRODUCTION

Code provided is in response to textual description of the functional use of a virtual deck of cards.

The requirements required the two base functions:
1. shuffle(): which took a deck on 52 (or less cards) and randomly shuffle them.
2. deal_one_card(): which provided a card from the current deck.

DEVELOPER NOTES

The files presented are those created with Eclipse Neon.  The project type is maven.  The pom file lists a restriction for the use of Java 1.8 only.  mvn runs should executed the BaseTests category of JUnit tests.

Source for the app is presented in the directory location in src//main/java/com/taphere/cards/
* CardDeck.java: hold the storage of the deck and API of the two method mentioned above.
* DeckCharacteristics.java: holds the charac teristics of a "standard" deck of 52 cards.  
* DeckOfCardsExampleApp.java: This is the Java application that runs the code as expressed in the paper handout.

TESTER NOTES

Unit test files are located in: 
src/test/java/com/taphere/cards
Ignore the TestCategories directory, as they define the test types that are available in the JUnit test code.
