package en.view;

import java.io.Console;
import java.util.Scanner;

import en.controller.WorkingFile;
import en.controller.speechRecognizer.SpeechRecognizer;

public class Main {
	public static void main(String[] args) {

		// lunch speech recognizer
		SpeechRecognizer speechRecognizer = new SpeechRecognizer();
		// speechRecognizer.checkResources();
		speechRecognizer.listenAndReact();

		// get working directory
		String workingDir = System.getProperty("user.dir");
		System.out.println("Current working directory : " + workingDir);
		// // file test
		// WorkingFile file = new WorkingFile();
		// file.setWorkingDirectory(workingDir);

	}
}
