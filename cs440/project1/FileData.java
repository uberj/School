import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList; 
import java.io.FileReader;
import java.io.BufferedReader;

public class FileData {

	public static String getFileData(File f) {
		String line = null;
		String content = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			while ((line = br.readLine()) != null) {
				content += line;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return content;
		}
	}

	public static ArrayList<File> walkPath(File f) {
		File[] paths;
		ArrayList<File> foundPaths = new ArrayList<File>();
		try {
			paths = f.listFiles();
			for(File path:paths) {
				if(path.isFile())
					foundPaths.add(path);
				else
					foundPaths.addAll(walkPath(path));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return foundPaths;
	}
	/*
	public static void main(String[] args) {
		File f = new File("/scratch/cs440/imdb");
		ArrayList<File> paths = new ArrayList<File>();
		paths = walkPath(f);
		for(File path:paths) {
			System.out.println(path);
		}
	}
	*/
}
