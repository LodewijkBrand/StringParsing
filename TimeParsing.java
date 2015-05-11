import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TimeParsing{
    private KMP kmpSearch;
    private BoyerMoore bmSearch;
    private RabinKarp rkSearch;
    private final int PATTERN_LENGTH = 50;

    public TimeParsing(){
	long startTime, endTime;
	String content = readFile("moby.txt");
        kmpSearch = new KMP(content);
	bmSearch = new BoyerMoore(content);
	rkSearch = new RabinKarp(content);
	//String pattern = "thousands";

	for (int j = 1; j <= 20; j++){
	    for (int i = 0; i <= 100; i++){
		int depth = (int)Math.pow(2, j);

		String pattern = content.substring(depth, depth + PATTERN_LENGTH);

		//System.out.println("PATTERN: " + pattern);

		System.out.print(depth + "\t");

		//System.out.println("testing RabinKarp");
		startTime = System.nanoTime();
		//System.out.println("Found @: " + rkSearch.findPattern(pattern));
		rkSearch.findPattern(pattern);
		endTime = System.nanoTime();
		System.out.print((endTime - startTime) + "\t");
		//System.out.println("That took " + (endTime - startTime) + " milliseconds");
	
		//System.out.println("testing BoyerMoore");
		startTime = System.nanoTime();
		//System.out.println("Found @: " + bmSearch.findPattern(pattern));
		bmSearch.findPattern(pattern);
		endTime = System.nanoTime();
		System.out.print((endTime - startTime) + "\t");
		//System.out.println("That took " + (endTime - startTime) + " milliseconds");

		//System.out.println("testing KMP");
		startTime = System.nanoTime();
		//System.out.println("Found @: " + kmpSearch.findPattern(pattern));
		kmpSearch.findPattern(pattern);
		endTime = System.nanoTime();
		System.out.println((endTime - startTime));
		//System.out.println("That took " + (endTime - startTime) + " milliseconds");
	    }
	}
    }

    public String readFile(String filename){
	String content = null;
	File file = new File(filename);
	try {
	    FileReader reader = new FileReader(file);
	    char[] chars = new char[(int) file.length()];
	    reader.read(chars);
	    content = new String(chars);
	    reader.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return content;
    }

    public static void main(String[] args){
	TimeParsing timer = new TimeParsing();
    }
}
