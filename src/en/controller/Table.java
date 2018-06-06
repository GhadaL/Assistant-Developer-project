package en.controller;



import edu.cmu.sphinx.api.SpeechResult;

public class Table {

	public void splitVerb(SpeechResult speechResult) {
		// TODO Auto-generated method stub

		if (speechResult.getHypothesis().contains("create")) {
			int line = -1;
			// write file create table in path readen by keyboard
			WorkingFile.writeFile("TableCreation", line);

		} else if (speechResult.getHypothesis().contains("initialize")) {

			
			
			if (speechResult.getHypothesis().contains("zero"))		
				WorkingFile.writeFile("TableInitializationByZero", -1);
			else if (speechResult.getHypothesis().contains("two"))
				WorkingFile.writeFile("TableInitializationByTwo", -1);

		}
	}



}
