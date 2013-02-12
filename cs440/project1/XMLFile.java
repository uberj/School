import java.io.*;
import java.nio.ByteBuffer;

public class XMLFile {

    public String name;
    public long size;
    public String content;

    public XMLFile () {
    }

    public XMLFile (File f) {
        name = f.getName();
        size = f.length();
        content = FileData.getFileData(f);
    }

    public XMLFile (String name, long size, String content) {
        this.name = name;
        this.size = size;
        this.content = content;
    }

    public byte[] getSizeByteArray() {
        byte b[] = new byte[8];
        ByteBuffer buf = ByteBuffer.wrap(b);
        buf.putLong(this.size);
        return b;
    }

    public String getName() {
        return this.name;
    }
	
	public long getSize() { 
		return this.size;
	}

    public String toString() {
           return  "<XMLFile: " + this.name + "/>\n " + "< XMLFileSize: " + this.size + "/>\n" + 
			"<XMLContent: " + this.content + "/>\n";
    }
}
