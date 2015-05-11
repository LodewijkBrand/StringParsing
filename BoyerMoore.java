/**
 * A class for the Boyer Moore string matching algorithm
 * Code adapted from: http://www.stoimen.com/blog/2012/04/17/computer-algorithms-boyer-moore-string-search-and-matching/ (especially the good suffixes part)
 * @author Lou Brand and Ben Steele
 */

import java.util.HashMap;

public class BoyerMoore{
    private String myText; // The text
    private int[] goodSuffixes; // Pre-processed good suffix table
    private HashMap<Character, Integer> badCharacters; // Pre-processed bad character table

    /**
     * Creates a BoyerMoore object to find a pattern in a given text
     * @param text The text to be searched through
     */
    public BoyerMoore(String text){
	myText = text;
    }

    /**
     * Finds a pattern in myText using a BoyerMoore algorithm
     * @param pattern The pattern we are looking for
     * @return The index of the target pattern
     */
    public int findPattern(String pattern){
	int textLength = myText.length();
	int patternLength = pattern.length();

	generateGoodSuffixes(pattern);
	generateBadCharacters(pattern);

	int j = 0; 

	while (j <= textLength - patternLength){
	    int i = patternLength - 1;
	    while (i >= 0 && pattern.charAt(i) == myText.charAt(i+j)){
		i--; // Keep checking characters
	    }
	    if (i < 0){
		return j; // We have checked the whole pattern!
	    } else {
		int goodSuff = goodSuffixes[i]; // Get appropriate shift from our suffix table

		if (badCharacters.containsKey(myText.charAt(i+j))){
		    int badChar = badCharacters.get(myText.charAt(i+j)) - patternLength + i + 1;
		    if (goodSuff > badChar){ // If good suffix shifts more that bad char
			j += goodSuff;
		    } else {
			j += badChar;
		    }
		} else{
		    j += goodSuff; // Shift according to suffix table
		}
	    }
	}
	
	return -1;
    }

    /**
     * Populates the bad characters HashMap
     * @param pattern The pattern we are looking for
     */
    public void generateBadCharacters(String pattern){
	badCharacters = new HashMap<Character, Integer>();
	int len = pattern.length();

	for (int i = 0; i < len - 1; i++){
	    char letter = pattern.charAt(i);
	    badCharacters.put(letter, len - i - 1); // Determine appropriate shift if we see a mismatched letter that is earlier in the pattern (replace mappings as appropriate)
	}
    }

    /**
     * Populates the goodSuffixes array for appropriate shifting
     * @param pattern The pattern we are looking for
     */
    public void generateGoodSuffixes(String pattern){
	int len = pattern.length();
	goodSuffixes = new int[len];
	int[] suff = suffixes(pattern);

	for (int i = 0; i < len; i++){
	    goodSuffixes[i] = len;
	}

	for (int i = len - 1; i >= 0; i--){
	    if (suff[i] == i + 1){
		for (int j = 0; j < len - i - 1; j++){
		    if (goodSuffixes[j] == len){
			goodSuffixes[j] = len - i - 1;
		    }
		}
	    }
	}

	for (int i = 0; i < len - 2; i++){
	    goodSuffixes[len - 1 - suff[i]] = len - i - 1;
	}
    }

    /**
     * Parse our pattern for subpatterns (suffixes)
     * @param pattern The pattern we are looking for
     * @return suff Good suffixes table
     */ 
    public int[] suffixes(String pattern){
	int len = pattern.length();
	int[] suff = new int[len];
	suff[len-1] = len; // If the last character doesn't match shift all the way over
	int g = len-1;
	int f = len-2;

	for (int i = len - 2; i >= 0; i--){
	    if (i > g && suff[i + len - 1 - f] < i - g){
		suff[i] = suff[i + len - 1 - f];
	    } else{
		if (i < g){
		    g = i;
		}
		f = i;

		while (g >= 0 && pattern.charAt(g) == pattern.charAt(g + len - 1 - f)){
		    g--;
		}
		suff[i] = f - g;
	    }
	}

	return suff;
    }

    /**
     * Main method for testing BoyerMoore
     */
    public static void main(String[] args){
	BoyerMoore myBM = new BoyerMoore("this is a simple example");
	System.out.println("FOUND @: " + myBM.findPattern("example"));
    }
}
