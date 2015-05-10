import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TimeParsing{
    private KMP kmpSearch;
    private BoyerMoore bmSearch;

    public TimeParsing(){
	long startTime, endTime;
	String content = readFile("moby.txt");
        kmpSearch = new KMP(content);
	bmSearch = new BoyerMoore(content);
	String pattern = "thousands";
	
	System.out.println("testing BoyerMoore");
	startTime = System.currentTimeMillis();
	System.out.println("Found @: " + bmSearch.findPattern(pattern));
	endTime = System.currentTimeMillis();
	System.out.println("That took " + (endTime - startTime) + " milliseconds");

	System.out.println("testing KMP");
	startTime = System.currentTimeMillis();
	System.out.println("Found @: " + kmpSearch.findPattern(pattern));
	endTime = System.currentTimeMillis();
	System.out.println("That took " + (endTime - startTime) + " milliseconds");	
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
