import java.util.ArrayList;

public class BoyerMoore{
    private String myText;
    private ArrayList<BMTuple> bmTable;
    private final char ANY = '*';

    public BoyerMoore(String text){
	myText = text;
	bmTable = new ArrayList<>();
    }

    public int findPattern(String pattern){
	makeTable(pattern);

	int i = 0;
	int l = pattern.length()-1;
	
	while (i < myText.length()){
	    char currentLetter = myText.charAt(i+l);
	    System.out.println("Checking: " + currentLetter);
	    if (pattern.charAt(l) == currentLetter){
		l--;
		if (l < 0){
		    return i;
		}
	    } else{
		l = pattern.length()-1;
		BMTuple shift = getTuple(currentLetter);
		if (shift != null){
		    i += shift.value;
		} else{
		    i += pattern.length();
		}
	    }
	    System.out.println("index: " + i);
	}

	return -1;
    }

    public void makeTable(String pattern){
	int len = pattern.length();
	for (int i = 0; i < len; i++){
	    char letter = pattern.charAt(i);
	    int newValue = len - 1 - i;
	    if (newValue < 1){
		newValue = 1;
	    }
	    BMTuple entry = getTuple(letter);
	    if (entry == null){
		if (i == len-1){
		    newValue = len;
		}
		BMTuple newEntry = new BMTuple(letter, newValue);
		bmTable.add(newEntry);
	    } else{
		System.out.println(i + " HELLO! " + letter + " val: " + newValue);
		entry.value = newValue;
	    }
	}

	for (BMTuple tuple : bmTable){
	    System.out.println(tuple.letter + " " + tuple.value);
	}
    }

    public boolean hasTuple(char c){
	for (BMTuple entry : bmTable){
	    if (entry.letter == c){
		return true;
	    }
	}
	return false;
    }

    public BMTuple getTuple(char c){
	for (BMTuple entry : bmTable){
	    if (entry.letter == c){
		return entry;
	    }
	}
	return null;
    }

    public static void main(String[] args){
	BoyerMoore myBM = new BoyerMoore("here is a simplexample");
	System.out.println("FOUND @: " + myBM.findPattern("example"));
    }
}

class BMTuple{
    public char letter;
    public int value;

    public BMTuple(char l, int val){
	letter = l;
	value = val;
    }
}
