/**
 * The Knuth-Morris-Pratt algorithm for finding a pattern in a given String
 * @author Lou Brand and Ben Steele
 */

public class KMP{
    private String myText;
    private int[] kmpTable;

    public KMP(String text){
	myText = text;
    }

    /**
     * Finds a pattern in myText using a KMP algorithm
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
		    l = kmpTable[l];
		} else{
		    i++;
		    l = 0;
		}
	    }
	}
	return -1; // The pattern does not exist in our text
    }
    
    /**
     * Makes the KMP table to search for repeated sub-patterns in pattern
     */
    public void makeTable(String pattern){
	kmpTable = new int[pattern.length()];
	kmpTable[0] = -1;
	kmpTable[1] = 0;
	int i = 0;
	int j = 2;
	while (j < pattern.length()){
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
