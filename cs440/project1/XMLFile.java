import java.io.*;

public class XMLFile {

    public String name;
    public long size;
    public String content = "";

    public XMLFile () {
        // Constructor
    }

    public XMLFile (String name, Integer size, String content) {
        // Constructor
        this.name = name;
        this.size = size;
        this.content = content;
    }

    public void print() {
        System.out.println(
            "<XMLFile: " + name + " " + size + " " + content + ">"
        );
    }
}
