/**
 * The Knuth-Morris-Pratt algorithm for finding a pattern in a given String
 * @author Lou Brand and Ben Steele
 */

public class KMP{
    private String myText; // The text
    private int[] kmpTable; // Pre-proccessed shift table

    /**
     * Creates a KMP object to find a pattern in a given text
     * @param text The text to be searched through
     */
    public KMP(String text){
	myText = text;
    }

    /**
     * Finds a pattern in myText using a KMP algorithm
     * @param pattern The pattern we are looking for
     * @return The index of the target pattern
     */
    public int findPattern(String pattern){
	int i = 0; //Index in myText
	int l = 0; //Letter in pattern
	makeTable(pattern);
	while (i + l < myText.length()){ // Check to see if at end of text
	    if (pattern.charAt(l) == myText.charAt(i + l)){ // Letter in pattern and text match
		if (l == pattern.length() - 1){
		    return i;
		}
		l++;
	    } else { // Letter in pattern and text doesn't match
		if (kmpTable[l] > -1){
		    i += l - kmpTable[l];
		    l = kmpTable[l]; // We can skip checking some part of the pattern we already know is correct
		} else{
		    i++;
		    l = 0;
		}
	    }
	}
	return -1; // The pattern does not exist in our text
    }
    
    /**
     * Makes the KMP table in order to skip rechecking idicies we know are not possible
     * based on sub-patterns in our given pattern
     * @param pattern The pattern we are looking for
     */
    public void makeTable(String pattern){
	kmpTable = new int[pattern.length()];
	kmpTable[0] = -1; // If the first index of the pattern isn't correct we just want to shift over by 1
	kmpTable[1] = 0;
	int i = 0;
	int j = 2;
	while (j < pattern.length()){
	    // Recognizing sub-patterns
	    if (pattern.charAt(i) == pattern.charAt(j-1)){
		i++;
		kmpTable[j] = i;
		j++;
	    } else if (i > 0){
		i = kmpTable[i];
	    } else{
		j++;
	    }
	}
    }

    /**
     * Main method used for testing
     */
    public static void main (String[] args){
	KMP myKMP = new KMP("ABC ABCDAB ABCDABCDABDE");
	System.out.println("INDEX: " + myKMP.findPattern("ABCDABD"));
    }
}
