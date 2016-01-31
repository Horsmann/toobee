package de.unidue.ltl.toobee.twitterUtils.pipeline;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.unidue.ltl.toobee.twitterUtils.RawJsonTweetReaderFIFO;

public class SingleFilePayloadExtractor {
	public static void main(String[] args) throws Exception {
		
		String inputFolder = args[0];
		String outputFile = args[1];
		
		CollectionReader reader = CollectionReaderFactory.createReader(
				RawJsonTweetReaderFIFO.class,
				RawJsonTweetReaderFIFO.PARAM_SOURCE_LOCATION, inputFolder,
				RawJsonTweetReaderFIFO.PARAM_PATTERNS, new String [] {"*.gz", "*.bz2"},
				RawJsonTweetReaderFIFO.PARAM_ALL_IN_LOWER_CASE, false,
				RawJsonTweetReaderFIFO.PARAM_REMOVE_TWITTER_PHENOMENONS, false);
		
		AnalysisEngineDescription printer = AnalysisEngineFactory.createEngineDescription(
	                FileWriter.class, FileWriter.PARAM_TARGET_FILE, outputFile, 
	                FileWriter.PARAM_REPLACE_TWITTER_PHENOMENON_WITH_CONSTANTS, true,
	                FileWriter.PARAM_LOWER_CASE, true);
		
		SimplePipeline.runPipeline(reader, printer);
	}
}
