package de.unidue.ltl.toobee.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.parameter.ComponentParameters;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class LinewiseWriter
    extends JCasAnnotator_ImplBase
{

    public static final String PARAM_TARGET_LOCATION = ComponentParameters.PARAM_TARGET_LOCATION;
    @ConfigurationParameter(name = PARAM_TARGET_LOCATION, mandatory = true)
    private String targetLocation;

    public static final String PARAM_SOURCE_ENCODING = ComponentParameters.PARAM_SOURCE_ENCODING;
    @ConfigurationParameter(name = PARAM_SOURCE_ENCODING, mandatory = true, defaultValue = "UTF-8")
    private String encoding;

    private BufferedWriter buffWrite = null;

    private final String WHITESPACE = " ";
    private final String LINEBREAK = "\n";

    @Override
    public void initialize(final UimaContext context)
        throws ResourceInitializationException
    {
        super.initialize(context);
        try {
            buffWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(
                    targetLocation)), encoding));
        }
        catch (Exception e) {
            throw new ResourceInitializationException(e);
        }
    }

    @Override
    public void process(JCas aJCas)
        throws AnalysisEngineProcessException
    {

        for (Sentence s : JCasUtil.select(aJCas, Sentence.class)) {
            List<Token> tokensOfSequence = JCasUtil.selectCovered(aJCas, Token.class, s.getBegin(),
                    s.getEnd());
            for (int i = 0; i < tokensOfSequence.size(); i++) {
                Token token = tokensOfSequence.get(i);
                write(buffWrite, token.getCoveredText());
                if (i + 1 < tokensOfSequence.size()) {
                    write(buffWrite, WHITESPACE);
                }
            }
            write(buffWrite, LINEBREAK);
        }

    }

    private void write(BufferedWriter aBf, String text)
        throws AnalysisEngineProcessException
    {
        try {
            buffWrite.write(text);
        }
        catch (IOException e) {
            throw new AnalysisEngineProcessException(e);
        }
    }

    public void collectionProcessComplete()
        throws AnalysisEngineProcessException
    {
        if (buffWrite != null) {
            try {
                buffWrite.close();
                buffWrite = null;
            }
            catch (IOException e) {
              throw new AnalysisEngineProcessException(e);
            }
        }
    }

}
