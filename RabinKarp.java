import java.util.*;
import java.io.File;

public class RabinKarp {

    HashMap<String, Boolean> targets;
    String text;
    String[] ts;
    
    public RabinKarp(String _text) {
        targets = new HashMap<String, Boolean>();
        text = _text;
    }

    public void findPatterns(String[] patterns) {
        for (String p : patterns) {
            targets.put(p,false);
        }
        for(int i=0;i<2;i++) {

        }
    }
    
    public void findPattern(String pattern) {
        ts = new String[1];
        ts[0] = pattern;
        targets.put(pattern, false);
        int size = pattern.length();
        int checkable = text.length() - size;
        for(int i=0;i<checkable;i++) {
            if (targets.containsKey(text.substring(i,i+size))) {
                targets.put(text.substring(i,i+size),true);
            }
        }
    }

    public void printResults() {
        for(String s : ts) {
            System.out.println("Pattern: " + s + " found: " + targets.get(s));
        }
    }
    
    public static void main(String[] args) {
        RabinKarp m = new RabinKarp("This is my test string");
        m.findPattern("iss");
        m.printResults();
    }
}
