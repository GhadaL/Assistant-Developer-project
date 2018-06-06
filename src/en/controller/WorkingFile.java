package en.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class WorkingFile {
	static private String SrcPath = "/home/ghada/eclipse-workspace/Test/src/Main.java";
	private String workingDirectory = null;
	private static int endLine = 3;
	private static int numberTab = 0;
	private static boolean oneTimeKeyboardEntry = true;
	private static int lastTableindex;

	// constructors
	public WorkingFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WorkingFile(String srcPath, int endLine) {
		super();
		SrcPath = srcPath;
		if (endLine != -1)
			this.endLine = endLine;
	}

	// getters & setters
	public String getSrcPath() {
		return SrcPath;
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public int getEndLine() {
		return endLine;
	}

	public void setWorkingDirectory(String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	public void setSrcPath(String string) {
		// TODO Auto-generated method stub
		this.SrcPath = string;
	}

	// methods
	public static void writeFile(String content, int line) {
		// TODO Auto-generated method stub
		String snippetOfCode = null;

		Path path = Paths.get(SrcPath);
		List<String> lines;
		try {
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);

			if (content.equals("TableCreation")) {
				snippetOfCode = "int[] tab" + numberTab++ + ";";
				lines.add(endLine, snippetOfCode);
				Files.write(path, lines, StandardCharsets.UTF_8);
				lastTableindex = endLine;
				endLine += 1;
			} else if (content.contains("TableInitialization")) {

				// pardefaut taille 3 à gerer
				if (content.endsWith("Zero"))
					snippetOfCode = "tab" + (numberTab - 1) + "= new int[] {0,0,0};";
				else if (content.endsWith("Two"))
					snippetOfCode = "tab" + (numberTab - 1) + "= new int[] {2,2,2};";

				lines.add(lastTableindex + 1, snippetOfCode);
				Files.write(path, lines, StandardCharsets.UTF_8);
				endLine += 1;

			} else if (content.equals("KeyboardEntry") && oneTimeKeyboardEntry) {
				// exist one time in a file
				oneTimeKeyboardEntry = false;
				// add import java.util.Scanner;
				snippetOfCode = "import java.util.Scanner;";
				lines.add(0, snippetOfCode);
				Files.write(path, lines, StandardCharsets.UTF_8);
				endLine += 1;
				lastTableindex += 1;

				snippetOfCode = "Scanner sc = new Scanner(System.in);\n String input = sc.nextLine();";
				lines.add(endLine, snippetOfCode);
				Files.write(path, lines, StandardCharsets.UTF_8);
				endLine += 2;
			}
			System.out.println("Line " + endLine);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
