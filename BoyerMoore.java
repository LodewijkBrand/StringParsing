import java.util.HashMap;

public class BoyerMoore{
    private String myText;
    private int[] goodSuffixes;
    private HashMap<Character, Integer> badCharacters;

    public BoyerMoore(String text){
	myText = text;
    }

    public int findPattern(String pattern){
	int textLength = myText.length();
	int patternLength = pattern.length();

	generateGoodSuffixes(pattern);
	generateBadCharacters(pattern);

	int j = 0; 

	while (j <= textLength - patternLength){
	    int i = patternLength - 1;
	    System.out.println("checking index: " + (i+j));
	    while (i >= 0 && pattern.charAt(i) == myText.charAt(i+j)){
		System.out.println("checking index: " + (i + j));
		i--;
	    }
	    if (i < 0){
		System.out.println("FOUND: " + j);
		j += goodSuffixes[0];
	    } else {
		int goodSuff = goodSuffixes[i];

		if (badCharacters.containsKey(myText.charAt(i+j))){
		    int badChar = badCharacters.get(myText.charAt(i+j)) - patternLength + i + 1;
		    if (goodSuff > badChar){
			j+=goodSuff;
		    } else {
			j+=badChar;
		    }
		} else{
		    j += goodSuff;
		}
	    }
	}
	
	return -1;
    }

    public void generateBadCharacters(String pattern){
	badCharacters = new HashMap<Character, Integer>();
	int len = pattern.length();

	for (int i = 0; i < len - 1; i++){
	    char letter = pattern.charAt(i);
	    badCharacters.put(letter, len - i - 1);
	}

	for (int i = 0; i < len; i++){
	    char letter = pattern.charAt(i);
	    System.out.println(letter + " " + badCharacters.get(letter));
	}
    }

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

	for (int i = 0; i < goodSuffixes.length; i++){
	    System.out.println ("SUFFIX: " + goodSuffixes[i]);
	}
    }

    public int[] suffixes(String pattern){
	int len = pattern.length();
	int[] suff = new int[len];
	suff[len-1] = len;
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

    public static void main(String[] args){
	BoyerMoore myBM = new BoyerMoore("this is a simple example");
	System.out.println("FOUND @: " + myBM.findPattern("example"));
    }
}
