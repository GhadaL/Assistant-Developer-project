package en.view;

import java.io.Console;
import java.util.Scanner;

import en.controller.WorkingFile;
import en.controller.speechRecognizer.SpeechRecognizer;

public class Main {
	public static void main(String[] args) {

		// lunch speech recognizer
		SpeechRecognizer speechRecognizer = new SpeechRecognizer();
		//speechRecognizer.checkResources();
		speechRecognizer.listenAndReact();

		// get working directory
		String workingDir = System.getProperty("user.dir");
		System.out.println("Current working directory : " + workingDir);
//		// file test
//		WorkingFile file = new WorkingFile();
//		file.setWorkingDirectory(workingDir);

		// input
		// Scanner scanner=new Scanner(System.in);
		// System.out.println("enter the abs file path");
		// String input = scanner.nextLine();
		// file.setSrcPath(input);
		// file.setEndLine(3);
		// System.out.println("You entered : " + file.getSrcPath());
		// WorkingFile.writeFile("TableCreation", -1);
		// WorkingFile.writeFile("TableCreation", -1);
	}
}
