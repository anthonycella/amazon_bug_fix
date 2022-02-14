package fix_quicken_loans_file;

import java.awt.FileDialog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFrame;

public class QuickenLoansFile {
	public static void write(String fileDirectoryAddress, String text) {
		if (!fileDirectoryAddress.contains(".qfx")) {
			fileDirectoryAddress += ".qfx";
		}
		File textFile = new File(fileDirectoryAddress);
		
		try {
			BufferedWriter textFileWriter = new BufferedWriter(new FileWriter(textFile));
			textFileWriter.write(text);
			textFileWriter.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: invalid file name");
		} catch (IOException e) {
			System.out.println("Error: unable to write to file");
		}
	}
	
	public static String read(String quickenLoansFileName) {
		
		if (!quickenLoansFileName.contains(".qfx")) {
			return "Error: not a QFX file";
		}
		
		else {
			
			String content = null;
			
			try {
				content = new String(Files.readAllBytes(Paths.get(quickenLoansFileName)));
			} catch (IOException e) {
				System.out.println("Error: unable to read file");
			}
			
			return content;
		}
	}
	
	public static String getFileFromFileExplorer() {

		FileDialog fileExplorer = new FileDialog(new JFrame());
		fileExplorer.setVisible(true);

		File[] files = fileExplorer.getFiles();
		String file = files[0].getAbsolutePath();

		return file;
	}
	
	
	public static String[] fix(String[] dissectedFileContents, String startDate, String endDate) {
		
		for (int currentIndex = 0; currentIndex < dissectedFileContents.length; currentIndex++) {
			String currentSegment = dissectedFileContents[currentIndex];
			if (currentSegment.contains("<DTSTART>R")) {
				String currentSegmentFrontDateRemoved = currentSegment.substring(0, 9);
				String currentSegmentBackDateRemoved = currentSegment.substring(10, currentSegment.length() - 4);
				currentSegment = currentSegmentFrontDateRemoved + startDate + currentSegmentBackDateRemoved;
				dissectedFileContents[currentIndex] = currentSegment;
			}
			else if (currentSegment.contains("<DTEND>R")) {
				String currentSegmentFrontDateRemoved = currentSegment.substring(0, 7);
				String currentSegmentBackDateRemoved = currentSegment.substring(8);
				currentSegment = currentSegmentFrontDateRemoved + endDate + currentSegmentBackDateRemoved;
				dissectedFileContents[currentIndex] = currentSegment;
				break;
			}
		}
		
		
		
		return dissectedFileContents;
	}
	

}
