//Lou Brand and Ben Steele

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
        ts = patterns;
        targets = new HashMap<String, Boolean>();
        int size = 1000000;
        int checkable = 0;
        ArrayList<Integer> sizes = new ArrayList<Integer>();
        //put the pattern string in the hash table so it can be compared
        for(String s : patterns) {
            //put the target string in the hash table
            targets.put(s,false);
            //find the shortest string length
            if(!sizes.contains(s.length())) {
                sizes.add(s.length());
            }
            if(s.length() < size) { 
                size = s.length();
                checkable = text.length() - size;
            }
        }
        //iterate through every index in the text, leaving out indices leaving less than the size of the target at the end
        for(int i=0;i<checkable;i++) {
            //get strings for the size of each input at the current index
            //see if those strings are in the hash table
            for(int s : sizes) {
                if(i+s < text.length()) {    //dont check strings that will go past the end of the text indices
                    if (targets.containsKey(text.substring(i,i+s))) {
                        targets.put(text.substring(i,i+s),true);    //if the current substring is in the hash table, mark the that hash as true 
                    }
                }
            }
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
        String[] p = new String[3];
        p[0] = "is";
        p[1] = "his";
        p[2] = "tst";
        m.findPatterns(p);
        m.printResults();
    }
}
