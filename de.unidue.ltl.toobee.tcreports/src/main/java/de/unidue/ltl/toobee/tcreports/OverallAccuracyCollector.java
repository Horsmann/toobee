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
import org.dkpro.tc.core.util.ReportConstants;
import org.dkpro.tc.crfsuite.task.CRFSuiteTestTask;

public class OverallAccuracyCollector
    extends BatchReportBase
    implements Constants
{

    public static List<File> writtenFiles = new ArrayList<>();

    public void execute()
        throws Exception
    {

        for (TaskContextMetadata subcontext : getSubtasks()) {
            if (subcontext.getType().contains(CRFSuiteTestTask.class.getName())) {
                StorageService storageService = getContext().getStorageService();

                File id2outcome = storageService.locateKey(subcontext.getId(), Constants.ID_CONTEXT_KEY);
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
            String property = p.getProperty(ReportConstants.PCT_CORRECT);
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
        
        FileUtils.writeStringToFile(new File(output), sb.toString());
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
