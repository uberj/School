import java.io.*;

public class XMLFile {

    public String name;
    public long size;
    public String content;

    public XMLFile (File f) {
        name = f.getPath();
	size = f.length();
	content = FileData.getFileData(f);
    }

    public XMLFile (String name, long size, String content) {
        name = name;
        size = size;
        content = content;
    }

    public String toString() {
           return  "<XMLFile: " + this.name + "/>\n " + "< XMLFileSize: " + this.size + "/>\n" + 
			"<XMLContent: " + this.content + "/>\n";
    }
}
