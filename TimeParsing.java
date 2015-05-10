import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TimeParsing{
    private KMP kmpSearch;
    private BoyerMoore bmSearch;

    public TimeParsing(){
	String content = readFile("moby.txt");
        kmpSearch = new KMP(content);
	bmSearch = new BoyerMoore(content);
	String pattern = "its cunning spring, and, owing to its great buoyancy, rising with great";
	System.out.println("testing KMP");
	System.out.println("Found @: " + kmpSearch.findPattern(pattern));
	System.out.println("testing BoyerMoore");
	System.out.println("Found @: " + bmSearch.findPattern(pattern));
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
