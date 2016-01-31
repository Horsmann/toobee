package de.unidue.ltl.toobee.poseval;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.unidue.ltl.toobee.poseval.type.GoldPOS;

public class AccuracyReport
    extends JCasAnnotator_ImplBase
{

    public static final String PARAM_OUTPUT_FOLDER = "outputFolder";
    @ConfigurationParameter(name = PARAM_OUTPUT_FOLDER, mandatory = true)
    private String outputFolder;

    public static final String PARAM_FILE_GROUP_KEY = "groupKey";
    @ConfigurationParameter(name = PARAM_FILE_GROUP_KEY, mandatory = true)
    private String groupKey;

    public static final String PARAM_FILE_USER_KEY = "userKey";
    @ConfigurationParameter(name = PARAM_FILE_USER_KEY, mandatory = false)
    private String userKey;

    public static final String PARAM_AUTO_CORRECT_TAGS = "autoCorrectTags";
    @ConfigurationParameter(name = PARAM_AUTO_CORRECT_TAGS, mandatory = false)
    private String[] autoCorrect;
    private Set<String> autoCorrectionSet = new HashSet<String>();

    public static final String TOKEN_ACC_FINE = "tokenAccFine";
    public static final String TOKEN_ACC_COARSE = "tokenAccCoarse";
    public static final String SENT_ACC_FINE = "sentAccFine";
    public static final String SENT_ACC_COARSE = "sentAccCoarse";

    double tokenFineCorrect = 0;
    double tokenFineWrong = 0;

    double tokenCoarseCorrect = 0;
    double tokenCoarseWrong = 0;

    double sentFineCorrect = 0;
    double sentFineWrong = 0;

    double sentCoarseCorrect = 0;
    double sentCoarseWrong = 0;

    @Override
    public void initialize(final UimaContext context)
        throws ResourceInitializationException
    {
        super.initialize(context);
        buildAutoCorrectionSet();

    }

    private void buildAutoCorrectionSet()
    {
        if (autoCorrect == null) {
            return;
        }
        for (String s : autoCorrect) {
            autoCorrectionSet.add(s);
        }

    }

    @Override
    public void process(JCas arg0)
        throws AnalysisEngineProcessException
    {

        for (Sentence s : JCasUtil.select(arg0, Sentence.class)) {

            List<GoldPOS> gold = JCasUtil.selectCovered(arg0, GoldPOS.class, s.getBegin(),
                    s.getEnd());
            List<POS> pred = JCasUtil.selectCovered(arg0, POS.class, s.getBegin(), s.getEnd());

            boolean sentFinePerfect = true;
            boolean sentCoarsePerfect = true;
            for (int i = 0; i < pred.size(); i++) {
                String predictionFine = pred.get(i).getPosValue();
                String goldFine = gold.get(i).getPosTag().getPosValue();
                boolean match = updateFine(goldFine, predictionFine);
                sentFinePerfect = sentFinePerfect && match ? true : false;

                String predictionCoarse = pred.get(i).getClass().getSimpleName();
                String goldCoarse = gold.get(i).getCPosTag();
                match = updateCoarse(goldCoarse, predictionCoarse);
                sentCoarsePerfect = sentCoarsePerfect && match ? true : false;
            }

            if (sentFinePerfect) {
                sentFineCorrect++;
            }
            else {
                sentFineWrong++;
            }

            if (sentCoarsePerfect) {
                sentCoarseCorrect++;
            }
            else {
                sentCoarseWrong++;
            }
        }

    }

    private boolean updateCoarse(String goldCoarse, String predictionCoarse)
    {
        if (isEqual(goldCoarse, predictionCoarse)) {
            tokenCoarseCorrect++;
            return true;
        }
        tokenCoarseWrong++;
        return false;
    }

    private boolean updateFine(String goldFine, String predictionFine)
    {
        if (isEqual(goldFine, performAutoCorrection(goldFine,predictionFine))) {
            tokenFineCorrect++;
            return true;
        }
        tokenFineWrong++;
        return false;
    }

    private String performAutoCorrection(String goldFine, String predictionFine)
    {
        if (autoCorrectionSet.contains(goldFine)){
            return goldFine;
        }
        
        return predictionFine;
    }

    private boolean isEqual(String gold, String prediction)
    {
        return gold.equals(prediction);
    }

    @Override
    public void collectionProcessComplete()
        throws AnalysisEngineProcessException
    {
        String file = outputFolder + "/" + groupKey + "_" + userKey + "_acc.txt";

        StringBuilder sb = new StringBuilder();
        sb.append(TOKEN_ACC_FINE + "=" + calculateFineAccToken());
        sb.append("\n");
        sb.append(TOKEN_ACC_COARSE + "=" + calculateCoarseAccToken());
        sb.append("\n");
        sb.append(SENT_ACC_FINE + "=" + calculateFineAccSent());
        sb.append("\n");
        sb.append(SENT_ACC_COARSE + "=" + calculateCoarseAccSent());

        File f = new File(file);
        try {
            FileUtils.writeStringToFile(f, sb.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(userKey + "\n" + sb.toString() + "\n");

    }

    private String calculateCoarseAccSent()
    {
        return String.format("%.4f",
                (sentCoarseCorrect / (sentCoarseCorrect + sentCoarseWrong) * 100));
    }

    private String calculateFineAccSent()
    {
        return String.format("%.4f", (sentFineCorrect / (sentFineCorrect + sentFineWrong) * 100));
    }

    private String calculateFineAccToken()
    {
        return String
                .format("%.4f", (tokenFineCorrect / (tokenFineCorrect + tokenFineWrong) * 100));
    }

    private String calculateCoarseAccToken()
    {
        return String.format("%.4f",
                (tokenCoarseCorrect / (tokenCoarseCorrect + tokenCoarseWrong) * 100));
    }

}
