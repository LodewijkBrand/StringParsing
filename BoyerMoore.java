import java.util.HashMap;

public class BoyerMoore{
    private String myText;
    private HashMap<Character, Integer> goodSuffixes;
    private HashMap<Character, Integer> badCharacters;

    public BoyerMoore(String text){
	myText = text;
    }

    public int findPattern(String pattern){
	generateGoodSuffixes(pattern);
	generateBadCharacters(pattern);
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
	
    }

    public static void main(String[] args){
	BoyerMoore myBM = new BoyerMoore("here is a simplexample");
	System.out.println("FOUND @: " + myBM.findPattern("roadrunner"));
    }
}
