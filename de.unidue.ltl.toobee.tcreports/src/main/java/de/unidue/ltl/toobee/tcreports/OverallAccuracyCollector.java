package de.unidue.ltl.toobee.tcreports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.dkpro.lab.reporting.BatchReportBase;
import org.dkpro.lab.storage.StorageService;
import org.dkpro.lab.task.TaskContextMetadata;
import org.dkpro.tc.core.Constants;
import org.dkpro.tc.ml.report.util.Tc2LtlabEvalConverter;

import de.unidue.ltl.evaluation.core.EvaluationData;
import de.unidue.ltl.evaluation.measures.Accuracy;

public class OverallAccuracyCollector
    extends BatchReportBase
    implements Constants
{

    public static List<File> writtenFiles = new ArrayList<>();

    public void execute()
        throws Exception
    {

        for (TaskContextMetadata subcontext : getSubtasks()) {
            if (subcontext.getType().contains("TestTask")) {
                StorageService storageService = getContext().getStorageService();

                File id2outcome = storageService.locateKey(subcontext.getId(), Constants.ID_OUTCOME_KEY);
                EvaluationData<String> data = Tc2LtlabEvalConverter.convertSingleLabelModeId2Outcome(id2outcome);
                
                Accuracy<String> acc = new Accuracy<>(data);
                
                File locateKey = storageService.locateKey(subcontext.getId(), "accuracy.txt");
                FileUtils.write(locateKey, acc.getResult() + "", "utf-8");
                
                writtenFiles.add(id2outcome);
                break;
            }
        }
    }

    public static void summarize(String output)
        throws IOException
    {

        List<Double> accuracies = new ArrayList<>();

        for (File f : writtenFiles) {
            Properties p = new Properties();
            p.load(new FileInputStream(f));
            String property = p.getProperty("Correct");
            String value = property.replaceAll(",", ".");
            Double accuracy = Double.valueOf(value);
            accuracies.add(accuracy * 100);
        }
        
        StringBuilder sb =  new StringBuilder();
        Double sum=0.0;
        for(Double d : accuracies){
            sum+=d;
            
            sb.append(String.format("%4.1f\n",d));
        }
        
        Double avg = sum/accuracies.size();
        sb.append("------\n");
        sb.append(String.format("%4.1f\n", avg));
        
        double varianz = getVarianz(avg,accuracies);
        
        sb.append("\n");
        sb.append(String.format("Std-Dev: %.2f\n", Math.sqrt(varianz)));
        
        FileUtils.writeStringToFile(new File(output), sb.toString(), "utf-8");
    }

    private static double getVarianz(Double avg, List<Double> accuracies)
    {
        double val=0.0;
        for(Double v : accuracies){
            val += Math.pow(v-avg, 2);
        }
        
        return val/accuracies.size();
    }
}
