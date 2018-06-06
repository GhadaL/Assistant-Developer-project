package en.controller;

import edu.cmu.sphinx.api.SpeechResult;
import en.controller.synthesizer.TextToSpeech;

public class Actionmaker {
	String result;
	
	

	public void takeAction(SpeechResult speechResult) {
		// TODO Auto-generated method stub
		TextToSpeech textToSpeech = new TextToSpeech();
		System.out.println("action");
		this.result = speechResult.getHypothesis();
		if(result.contains("table"))
		{	
			//create integer table par defaut à gérer
			new Table().splitVerb(speechResult);
			textToSpeech.speak("Done", 1.5f, false, true);
		}
		else if(result.contains("keyboard entry"))
		{
			WorkingFile.writeFile("KeyboardEntry", -1);
			textToSpeech.speak("Done", 1.5f, false, true);
		}
		else if( result.contains("introduce yourself"))
		{
			String text= "I am a developer assistant. "
					+ "You can tell me what you want to code and I will code for you. "
					+ "With me, you can code, even when you are eating with your two hands. "
					+ "Also, I can upload your code using a hosting service. "
					+ "The current version supports only Java language and Git. ";
			textToSpeech.speak(text, 1.5f, false, true);
		}
		else if (result.contains("how are you"))
		{
			textToSpeech.speak("Fine Thank you", 1.5f, false, true);
		}
		else if(result.contains("hello"))
		{
			textToSpeech.speak("Hi", 1.5f, false, true);
		}
		
		
		
	}

}
//public void makeDecision(String speech , List<WordResult> speechWords) {
//
//// Split the sentence
//// System.out.println("SpeechWords: " +
//// Arrays.toString(speechWords.toArray()))
//// if (!speech.contains("hey"))
//// return;
//// else
//// speech = speech.replace("hey", "");
//
//if (speech.contains("how are you")) {
//textToSpeech.speak("Fine Thanks", 1.5f, false, true);
//return;
//} else if (speech.contains("who is your daddy")) {
//textToSpeech.speak("You boss", 1.5f, false, true);
//} else if (speech.contains("hey boss")) {
//textToSpeech.speak("can i have the pizza pliz", 1.5f, false, true);
//
//} else if (speech.contains("obey to me beach")) {
//textToSpeech.speak("never never never!", 1.5f, false, true);
//return;
//} else if (speech.contains("say hello")) {
//textToSpeech.speak("Hello Friends", 1.5f, false, true);
//return;
//} else if (speech.contains("say amazing")) {
//textToSpeech.speak("WoW it's amazing!", 1.5f, false, true);
//return;
//} else if (speech.contains("what day is today")) {
//textToSpeech.speak("A good day", 1.5f, false, true);
//return;
//} else if (speech.contains("change to voice one")) {
//textToSpeech.setVoice("cmu-slt-hsmm");
//textToSpeech.speak("Done", 1.5f, false, true);
//return;
//} else if (speech.contains("change to voice two")) {
//textToSpeech.setVoice("dfki-poppy-hsmm");
//textToSpeech.speak("Done", 1.5f, false, true);
//} else if (speech.contains("change to voice three")) {
//textToSpeech.setVoice("cmu-rms-hsmm");
//textToSpeech.speak("Done", 1.5f, false, true);
//}
//
//String[] array = speech.split("(plus|minus|multiply|division){1}");
//System.out.println(Arrays.toString(array) + array.length);
//// return if user said only one number
//if (array.length < 2)
//return;
//
//// Find the two numbers
//System.out.println("Number one is:" + stringToNumber.convert(array[0]) + "
//Number two is: "
//+ stringToNumber.convert(array[1]));
//int number1 = stringToNumber.convert(array[0]);// .convert(array[0])
//int number2 = stringToNumber.convert(array[1]);// .convert(array[1])
//
//// Calculation result in int representation
//int calculationResult = 0;
//String symbol = "?";
//
//// Find the mathematical symbol
//if (speech.contains("plus")) {
//calculationResult = number1 + number2;
//symbol = "+";
//} else if (speech.contains("minus")) {
//calculationResult = number1 - number2;
//symbol = "-";
//} else if (speech.contains("multiply")) {
//calculationResult = number1 * number2;
//symbol = "*";
//} else if (speech.contains("division")) {
//if (number2 == 0)
//return;
//calculationResult = number1 / number2;
//symbol = "/";
//}
//
//String res = numberToString.convert(Math.abs(calculationResult));
//
//// With words
//System.out.println("Said:[ " + speech + " ]\n\t\t which after calculation
//is:[ "
//+ (calculationResult >= 0 ? "" : "minus ") + res + " ] \n");
//
//// With numbers and math
//System.out.println("Mathematical expression:[ " + number1 + " " + symbol + "
//" + number2
//+ "]\n\t\t which after calculation is:[ " + calculationResult + " ]");
//
//// Speak Mary Speak
//textToSpeech.speak((calculationResult >= 0 ? "" : "minus ") + res, 1.5f,
//false, true);
//
//}
//