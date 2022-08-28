# Simple-SpellChecker

Very basic spellchecker to catch common spelling mistakes. The spellchecker goes through a text file and identifies words that are not in a bank of "all words" and then makes several small changes to the mispelled word to see if the altered version exists in a list of more common words and if it does then we add that to a list of suggested potential spelling corrections to be outputted.

## Run

```
javac *.java
java SpellChecker.java config.txt
```

Ex. Output: 

```
Line number 13
have thus fqar so nobly advanced. It is rather for us to be here dedicated to the great task remaining before 
fqar is spelled incorrectly, possible correct word spellings are:
far
fear
```


