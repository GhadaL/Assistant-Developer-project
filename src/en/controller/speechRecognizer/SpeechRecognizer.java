package en.controller.speechRecognizer;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;

import java.util.logging.*;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;
import en.controller.Actionmaker;

public class SpeechRecognizer {
	private LiveSpeechRecognizer recognizer;
	private Logger logger = Logger.getLogger(getClass().getName());
	// private String speechRecognitionResult;
	private boolean ignoreSpeechRecognitionResults = false;
	private boolean speechRecognizerThreadRunning = false;
	private boolean resourcesThreadRunning;
	private SpeechResult speechResult = null;

	/**
	 * This executor service is used in order the playerState events to be executed
	 * in an order
	 */
	private ExecutorService eventsExecutorService = Executors.newFixedThreadPool(2);

	public SpeechRecognizer() {

		// Loading Message
		logger.log(Level.INFO, "Loading Speech Recognizer...\n");
		// Configuration
		Configuration configuration = new Configuration();
		// Load model from the jar
		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");

		// Grammar
		configuration.setGrammarPath("resource:/grammars");
		configuration.setGrammarName("grammar");
		configuration.setUseGrammar(true);

		try {
			recognizer = new LiveSpeechRecognizer(configuration);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

	// getters & setters
	public boolean isResourcesThreadRunning() {
		return resourcesThreadRunning;
	}

	public SpeechResult getSpeechResult() {
		return speechResult;
	}

	public void setIgnoreSpeechRecognitionResults(boolean ignoreSpeechRecognitionResults) {
		this.ignoreSpeechRecognitionResults = ignoreSpeechRecognitionResults;
	}

	public void setSpeechRecognizerThreadRunning(boolean speechRecognizerThreadRunning) {
		this.speechRecognizerThreadRunning = speechRecognizerThreadRunning;
	}

	public void setResourcesThreadRunning(boolean resourcesThreadRunning) {
		this.resourcesThreadRunning = resourcesThreadRunning;
	}

	public void setSpeechResult(SpeechResult speechResult) {
		this.speechResult = speechResult;
	}

	// method
	public synchronized void listenAndReact() {
		// TODO Auto-generated method stub

		// Check lock
		if (speechRecognizerThreadRunning)
			logger.log(Level.INFO, "Speech Recognition Thread already running...\n");
		else
			// Submit to ExecutorService
			eventsExecutorService.submit(() -> {

				// locks
				speechRecognizerThreadRunning = true;
				ignoreSpeechRecognitionResults = false;

				// Start Recognition
				recognizer.startRecognition(true);

				// Information
				logger.log(Level.INFO, "You can start to speak...\n");

				try {
					while (speechRecognizerThreadRunning) {
						/*
						 * This method will return when the end of speech is reached. Note that the end
						 * pointer will determine the end of speech.
						 */
						this.speechResult = recognizer.getResult();

						// Check if we ignore the speech recognition results
						if (!ignoreSpeechRecognitionResults) {

							// Check the result
							if (this.speechResult == null)
								logger.log(Level.INFO, "I can't understand what you said.\n");
							else {

								// Get the hypothesis
								// speechRecognitionResult = speechResult.getHypothesis();

								// You said?
								System.out.println(this.speechResult.getHypothesis() + "\n");

								// Call the appropriate method
								new Actionmaker().takeAction(speechResult);

							}
						} else
							logger.log(Level.INFO, "Ingoring Speech Recognition Results...");

					}
				} catch (Exception ex) {
					logger.log(Level.WARNING, null, ex);
					speechRecognizerThreadRunning = false;
				}

				logger.log(Level.INFO, "SpeechThread has exited...");

			});

	}

	/**
	 * Stops ignoring the results of SpeechRecognition
	 */
	public synchronized void stopIgnoreSpeechRecognitionResults() {

		// Stop ignoring speech recognition results
		ignoreSpeechRecognitionResults = false;
	}

	/**
	 * Ignores the results of SpeechRecognition
	 */
	public synchronized void ignoreSpeechRecognitionResults() {

		// Instead of stopping the speech recognition we are ignoring it's results
		ignoreSpeechRecognitionResults = true;

	}

	public boolean getIgnoreSpeechRecognitionResults() {
		return ignoreSpeechRecognitionResults;
	}

	public boolean getSpeechRecognizerThreadRunning() {
		return speechRecognizerThreadRunning;
	}

	public synchronized void checkResources() {
		// Check lock
		if (resourcesThreadRunning)
			logger.log(Level.INFO, "Resources Thread already running...\n");
		else
			// Submit to ExecutorService
			eventsExecutorService.submit(() -> {
				try {

					// Lock
					resourcesThreadRunning = true;

					// Detect if the microphone is available
					while (true) {

						// Is the Microphone Available
						if (!AudioSystem.isLineSupported(Port.Info.MICROPHONE))
							logger.log(Level.INFO, "Microphone is not available.\n");

						// Sleep some period
						Thread.sleep(350);
					}

				} catch (InterruptedException ex) {
					logger.log(Level.WARNING, null, ex);
					resourcesThreadRunning = false;
				}
			});
	}
}
