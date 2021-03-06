package de.unidue.ltl.toobee.tcreports;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.dkpro.lab.reporting.BatchReportBase;
import org.dkpro.lab.storage.StorageService;
import org.dkpro.lab.task.TaskContextMetadata;
import org.dkpro.tc.core.Constants;
import org.dkpro.tc.core.task.TcTaskTypeUtil;

public class ConfusionMatrixReport
    extends BatchReportBase
    implements Constants
{


    static String OUTPUT_FILE = "confussionMatrix.txt";

    ConfusionMatrix matrix = new ConfusionMatrix();
    
    public void execute()
        throws Exception
    {
        StorageService store = getContext().getStorageService();
        for (TaskContextMetadata subcontext : getSubtasks()) {
            if (TcTaskTypeUtil.isMachineLearningAdapterTask(store, subcontext.getId())) {
                StorageService storageService = getContext().getStorageService();
                File prediction = storageService.locateKey(subcontext.getId(),
                        "predictions.txt");
                
                List<String> lines = FileUtils.readLines(prediction, "utf-8");
                
                for(String l : lines){
                    if(l.isEmpty()){
                        continue;
                    }
                    String[] split = l.split("\t");
                    matrix.registerOutcome(split[0], split[1]);
                }
                
                File locateKey = storageService.locateKey(subcontext.getId(), OUTPUT_FILE);
                String formattedMatrix = matrix.getFormattedMatrix();
                FileUtils.writeStringToFile(locateKey, formattedMatrix, "utf-8");
            }
        }
    }

    
}
