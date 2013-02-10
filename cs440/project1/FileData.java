import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList; 
import java.util.Scanner;
public class FileData {

	public static String getFileData(File f) {
		try {
			String contents = new Scanner(f, "UTF-8").useDelimiter("\\A").next();
			return contents;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "";
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

	public static void main(String[] args) {
		File f = new File("/scratch/cs440/imdb");
		ArrayList<File> paths = new ArrayList<File>();
		paths = walkPath(f);
		for(File path:paths) {
			System.out.println(path);
		}
	}
}
