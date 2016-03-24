package de.unidue.ltl.toobee.tcreports;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import de.tudarmstadt.ukp.dkpro.lab.reporting.BatchReportBase;
import de.tudarmstadt.ukp.dkpro.lab.storage.StorageService;
import de.tudarmstadt.ukp.dkpro.lab.task.TaskContextMetadata;
import de.tudarmstadt.ukp.dkpro.tc.core.Constants;
import de.tudarmstadt.ukp.dkpro.tc.core.ml.TCMachineLearningAdapter.AdapterNameEntries;
import de.tudarmstadt.ukp.dkpro.tc.core.util.ReportConstants;
import de.tudarmstadt.ukp.dkpro.tc.crfsuite.CRFSuiteAdapter;
import de.tudarmstadt.ukp.dkpro.tc.crfsuite.task.CRFSuiteTestTask;

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

                File storageFolder = storageService.locateKey(subcontext.getId(), "");
                File evaluation = new File(storageFolder,
                                new CRFSuiteAdapter()
                                        .getFrameworkFilename(AdapterNameEntries.evaluationFile));
                writtenFiles.add(evaluation);
                break;
            }
        }
    }

    public static void summarize(String output)
        throws IOException
    {

        List<Double> accuracies = new ArrayList<>();

        for (File f : writtenFiles) {
            List<String> lines = FileUtils.readLines(f);
            for (String l : lines) {
                if (!l.startsWith(ReportConstants.PCT_CORRECT+"=")) {
                    continue;
                }
                String value = l.replaceAll(ReportConstants.PCT_CORRECT+"=", "");
                value.replaceAll(",", ".");
                Double accuracy = Double.valueOf(value);
                accuracies.add(accuracy*100);
            }
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
