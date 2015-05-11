import java.util.*;
import java.io.File;

public class RabinKarp {
    //store hashed targets marked false if they are not found in the text and true if they are
    HashMap<String, Boolean> targets;
    //full text in one string
    String text;
    //array of targets 
    String[] ts;
    
    public RabinKarp(String _text) {
        text = _text;
    }
    //find multiple patterns at the same time
    public void findPatterns(String[] patterns) {
        for (String p : patterns) {
            targets.put(p,false);
        }
        for(int i=0;i<2;i++) {

        }
    }
    //find a single pattern
    public int findPattern(String pattern) {
        //initiate ts
        ts = new String[1];
        //store pattern in ts
        ts[0] = pattern;
        targets = new HashMap<String, Boolean>();
        //put the pattern string in the hash table so if can be compared
        targets.put(pattern, false);
        int size = pattern.length();
        int checkable = text.length() - size;
        int i = 0;
        boolean done = false;
        while(done == false && i < checkable) {
            if (targets.containsKey(text.substring(i,i+size))) {
                targets.put(text.substring(i,i+size),true);
                done = true;
            }
            else {
                i++;
            }
        }
        return i;
    }

    public void printResults() {
        for(String s : ts) {
            System.out.println("Pattern: " + s + " found: " + targets.get(s));
        }
    }
    
    public static void main(String[] args) {
        RabinKarp m = new RabinKarp("This is my test string");
        m.findPattern("is");
        m.printResults();
    }
}
