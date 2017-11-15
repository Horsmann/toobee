package de.unidue.ltl.toobee.readers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.junit.Before;
import org.junit.Test;

public class TestLinewiseTextReader
{
    List<String> expected ;
    @Before
    public void setUp(){
        expected= new ArrayList<String>();
        expected.add("Hi there, how are you?");
        expected.add("I am fine, how are you?");
        expected.add("fine!");
        expected.add("O, really?");
        expected.add("Something like that, yes");
        expected.add("hm kay");
    }
    
    @Test
    public void testLinewiseTokenTagReader() throws Exception{
        CollectionReader reader = getReader("*.txt");

        List<String> actual = new ArrayList<String>();
        while (reader.hasNext()) {
            JCas jcas = JCasFactory.createJCas();
            reader.getNext(jcas.getCas());
            actual.add(jcas.getDocumentText());
        }
        
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0), actual.get(0));
        assertEquals(expected.get(1), actual.get(1));
        assertEquals(expected.get(2), actual.get(2));
        assertEquals(expected.get(3), actual.get(3));
        assertEquals(expected.get(4), actual.get(4));
        assertEquals(expected.get(5), actual.get(5));
        
    }
    
    private CollectionReader getReader(String pattern) throws ResourceInitializationException {
        return CollectionReaderFactory.createReader(LinewiseTextReader.class, LinewiseTextReader.PARAM_LANGUAGE, "en",
                LinewiseTextReader.PARAM_SOURCE_LOCATION, "src/test/resources/LinewiseTextReader/",
                LinewiseTextReader.PARAM_PATTERNS, pattern);
    }

}
