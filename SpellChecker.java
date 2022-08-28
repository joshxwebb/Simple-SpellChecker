import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class SpellChecker {
	private static HashTable allWords;
	private static HashTable commonWords;
	
	public static ArrayList<String> removeOneLetter(String word) {
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0;i < word.length();i++) {
			String shorterWord = "";
			for (int j = 0;j < word.length();j++) {
				if (word.charAt(j) != word.charAt(i)) {
					shorterWord += word.charAt(j);
				}
			}
			words.add(shorterWord);
		}
		return words;
	}
	
	public static ArrayList<String> addOneLetter(String word) {
		ArrayList<String> words = new ArrayList<String>();
		for (char letter = 'a';letter <= 'z';letter++) {
			for (int i = 0;i < word.length() + 1;i++) {
				String longerWord = "";
				for(int j = 0;j < word.length() + 1;j++) {
					if (i == j) {
						longerWord += letter;
					} else if (j < i){
						longerWord += word.charAt(j);
					} else {
						longerWord += word.charAt(j - 1);
					}
				}
			words.add(longerWord);
			}
		}
		return words;
	}
	
	public static ArrayList<String> swapAdjLetters(String word) {
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0;i < word.length() - 1;i++) {
			String swappedWord = "" + word.charAt(i + 1) + word.charAt(i);
				if (i == 0) {
					swappedWord += word.substring(i + 2);
				} else {
					swappedWord = word.substring(0, i) + swappedWord + word.substring(i + 2);
				}		
			words.add(swappedWord);
		}
		return words;
	}
	
	public static ArrayList<String> replaceAllLetters(String word) {
		ArrayList<String> words = new ArrayList<String>();
		for (char letter = 'a';letter <= 'z';letter++) {
			for (int i = 0;i < word.length();i++) {
				String replacedWord = "";
				for(int j = 0;j < word.length();j++) {
					if (i == j) {
						replacedWord += letter;
					} else {
						replacedWord += word.charAt(j);	
					}
				}
			words.add(replacedWord);
			}
		}
		return words;
	}
	
	public static ArrayList<String> spaceBetweenAllLetters(String word) {
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 1;i < word.length();i++) {
			String longerWord = "";
			for(int j = 0;j < word.length() + 1;j++) {
				if (i == j) {
					longerWord += " ";
				} else if (j < i){
					longerWord += word.charAt(j);
				} else {
					longerWord += word.charAt(j - 1);
				}
			}
		words.add(longerWord);
		}
		return words;
	}
	
	public static void fillHTFromFile(String fileName, HashTable hT) {
		try {
			BufferedReader inputFile = new BufferedReader(new FileReader(fileName));
			
			while (true) {
				
				String fileLine = inputFile.readLine();
				if (fileLine == null) {
					break; // done reading from file
				}
				hT.insert(fileLine);
			}
				inputFile.close();		 
		} catch (Throwable throwable) {
			System.out.println("Encountered an error filling the hash table.");
		}
	}
	
	public static ArrayList<String> isInHashTable(ArrayList<String> words, HashTable hT) {
		ArrayList<String> possibleCorrections = new ArrayList<String>();
		for(int i = 0;i < words.size();i++) {
			if (hT.retrieve(words.get(i).toLowerCase())) {
				possibleCorrections.add(words.get(i).toLowerCase());
			}
		}
		return possibleCorrections;
	}
	
	public static void printArrayList(ArrayList<String> words) {
		for (int i = 0;i < words.size();i++) {
			System.out.println(words.get(i));
		}
	}
	
	public static void spellCheckFromFile(String fileName, HashTable allWords, HashTable commonWords) {
		int lineNumber = 1;
		try {
			BufferedReader inputFile = new BufferedReader(new FileReader(fileName));
			
			while (true) {
				
				String fileLine = inputFile.readLine();
				if (fileLine == null) {
					break; // done reading from file
				} 
				String[] words = fileLine.split("[ ,.?]");
				for(int i = 0;i < words.length;i++) {
					
					if (words[i].length() > 0) {
						if(!allWords.retrieve(words[i])) {
							System.out.println("Line number " + lineNumber);
							System.out.println(fileLine);
							System.out.println(words[i] + " is spelled incorrectly, possible correct word spellings are:");
							ArrayList<String> changedWords = new ArrayList<String>();
							
							changedWords = isInHashTable(removeOneLetter(words[i]), commonWords);
							printArrayList(changedWords);
							changedWords = isInHashTable(addOneLetter(words[i]), commonWords);
							printArrayList(changedWords);
							changedWords = isInHashTable(swapAdjLetters(words[i]), commonWords);
							printArrayList(changedWords);
							changedWords = isInHashTable(replaceAllLetters(words[i]), commonWords);
							printArrayList(changedWords);
							changedWords = isInHashTable(spaceBetweenAllLetters(words[i]), commonWords);
							printArrayList(changedWords);
							System.out.println();
						}
					}
				}
				lineNumber++;
			}
				inputFile.close();		 
		} catch (Throwable throwable) {
			System.out.println("Encountered an error spellchecking from file.");
		}
	}

	public static void main(String[] args) {
		String fileName = args[0];
		String[] configData = new String[5];
		int counter = 0;
		
		try {
			BufferedReader inputFile = new BufferedReader(new FileReader(fileName));
			
			while (true) {
				
				String fileLine = inputFile.readLine();
				if (fileLine == null) {
					break; // done reading from file
				} else {
					configData[counter] = fileLine;
					counter++;
				}
				
			}
				inputFile.close();		 
		} catch (Throwable throwable) {
			System.out.println("Encountered an error in main.");
		}
		
		
		allWords = new HashTable(Integer.parseInt(configData[0]));
		fillHTFromFile(configData[1], allWords);
		commonWords = new HashTable(Integer.parseInt(configData[2]));
		fillHTFromFile(configData[3], commonWords);
		spellCheckFromFile(configData[4], allWords, commonWords);
	}

}
