package en.controller;

import edu.cmu.sphinx.api.SpeechResult;
import en.controller.synthesizer.TextToSpeech;

public class Actionmaker {
	String result;

	public void takeAction(SpeechResult speechResult) {
		// TODO Auto-generated method stub
		TextToSpeech textToSpeech = new TextToSpeech();
		this.result = speechResult.getHypothesis();
		if (result.contains("table")) {
			// create integer table par defaut à gérer
			new Table().splitVerb(speechResult);
			textToSpeech.speak("Done", 1.5f, false, true);
		} else if (result.contains("keyboard entry")) {
			WorkingFile.writeFile("KeyboardEntry", -1);
			textToSpeech.speak("Done", 1.5f, false, true);
		} else if (result.contains("introduce yourself")) {
			String text = "I am a developer assistant. "
					+ "You can tell me what you want to code and I will code for you. "
					+ "With me, you can code, even when you are eating with your two hands. "
					+ "Also, I can upload your code using a hosting service. "
					+ "The current version supports only Java language and Git. ";
			textToSpeech.speak(text, 1.5f, false, true);
		} else if (result.contains("how are you")) {
			textToSpeech.speak("Fine Thank you", 1.5f, false, true);
		} else if (result.contains("hello")) {
			textToSpeech.speak("Hi", 1.5f, false, true);
		}

	}

}
