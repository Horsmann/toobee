package de.unidue.ltl.toobee.tcreports;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import de.tudarmstadt.ukp.dkpro.lab.reporting.BatchReportBase;
import de.tudarmstadt.ukp.dkpro.lab.storage.StorageService;
import de.tudarmstadt.ukp.dkpro.lab.task.TaskContextMetadata;
import de.tudarmstadt.ukp.dkpro.tc.core.Constants;
import de.tudarmstadt.ukp.dkpro.tc.crfsuite.task.CRFSuiteTestTask;

public class AccuracyPerWordClass
    extends BatchReportBase
    implements Constants
{

    public static List<File> writtenFiles = new ArrayList<>();

    static String OUTPUT_FILE = "wordClassPerformance.txt";

    public void execute()
        throws Exception
    {

        for (TaskContextMetadata subcontext : getSubtasks()) {
            if (subcontext.getType().contains(CRFSuiteTestTask.class.getName())) {
                StorageService storageService = getContext().getStorageService();
                File locateKey = storageService.locateKey(subcontext.getId(),
                        Constants.ID_OUTCOME_KEY);

                String report = generateWordClassReport(locateKey);

                File targetFile = storageService.locateKey(subcontext.getId(), OUTPUT_FILE);
                FileUtils.writeStringToFile(targetFile, report);

                writtenFiles.add(targetFile);
            }
        }
    }

    private String generateWordClassReport(File locateKey)
        throws IOException
    {
        Map<String, WordClass> wcp = getWcPerformances(locateKey);

        StringBuilder sb = new StringBuilder();
        List<String> keySet = new ArrayList<String>(wcp.keySet());
        Collections.sort(keySet);
        
        sb.append(String.format("#%10s\t%5s\t%5s\n", "Class", "Occr", "Accr"));
        for (String k : keySet) {
            WordClass wc = wcp.get(k);
            double accuracy = wc.getCorrect() / wc.getN() * 100;

            sb.append(String.format("%10s", k) + "\t" + String.format("%5d",wc.getN().intValue())+"\t" + String.format("%5.1f", accuracy) );
            sb.append("\n");
        }

        return sb.toString();

    }

    private Map<String, WordClass> getWcPerformances(File locateKey)
        throws IOException
    {
        Map<String, WordClass> wcp = new HashMap<>();

        List<String> lines = FileUtils.readLines(locateKey);
        Map<String, String> labels = getLabels(lines);

        for (String l : lines) {
            if (l.startsWith("#")) {
                continue;
            }
            String[] entry = splitAtFirstEqualSignRightHandSide(l);

            String pg = entry[1];
            String[] split = pg.split(";");

            if (split.length < 2) {
                System.out.println("ERROR\t" + l);
                continue;
            }

            String prediction = labels.get(split[0]);
            String gold = labels.get(split[1]);

            WordClass wordClass = wcp.get(gold);
            if (wordClass == null) {
                wordClass = new WordClass();
            }
            if (gold.equals(prediction)) {
                wordClass.correct();
            }
            else {
                wordClass.incorrect();
            }
            wcp.put(gold, wordClass);
        }
        return wcp;
    }

    private String[] splitAtFirstEqualSignRightHandSide(String l)
    {
        int equal = l.lastIndexOf("=");
        String lhs = l.substring(0, equal);
        String rhs = l.substring(equal+1);
        
        return new String [] {lhs, rhs};
    }

    private Map<String, String> getLabels(List<String> lines)
    {
        for (String s : lines) {
            if (s.startsWith("#labels")) {
                return extractIdLabelMap(s);
            }
        }

        return null;
    }

    private Map<String, String> extractIdLabelMap(String s)
    {
        String wc = s.replaceAll("#labels ", "");

        String[] units = wc.split(" ");

        Map<String, String> id2label = new HashMap<>();

        for (String u : units) {
            String[] split = u.split("=");
            id2label.put(split[0], split[1]);
        }

        return id2label;
    }

    class WordClass
    {
        double correct = 0;
        double incorrect = 0;

        public Double getN()
        {
            return correct + incorrect;
        }

        public Double getCorrect()
        {
            return correct;
        }

        public void correct()
        {
            correct++;
        }

        public void incorrect()
        {
            incorrect++;
        }
    }
    
    public static void generateSummaryReport(String output)
            throws IOException
        {
            Map<String, Double> accPerformance = new HashMap<>();
            Map<String, Double> freqOccur = new HashMap<>();

            for (File f : AccuracyPerWordClass.writtenFiles) {
                List<String> lines = FileUtils.readLines(f);
                for (String l : lines) {
                    if(l.startsWith("#")){
                        continue;
                    }
                    
                    String[] split = l.split("\t");
                    String label = split[0].trim();
                    Double freq = Double.valueOf(split[1]);
                    String val = split[2].replaceAll(",", ".");
                    Double acc = Double.valueOf(val);

                    accPerformance= add(label,acc, accPerformance);
                    freqOccur = add(label,freq, freqOccur);
                }
            }

            List<String> list = new ArrayList<String>(accPerformance.keySet());
            Collections.sort(list);

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("#%10s\t%5s\t%5s\n", "Class", "Occr", "Acc"));
            for (String k : list) {
                Double val = accPerformance.get(k);
                val = val / writtenFiles.size();
                
                Double freq = freqOccur.get(k);
                freq = freq / writtenFiles.size();
                
                sb.append(String.format("%10s", k) + "\t" + String.format("%5d", freq.intValue())+ "\t" + String.format("%5.1f", val) );
                sb.append("\n");
            }
            FileUtils.writeStringToFile(new File(output),
                    sb.toString());
        }

    private static Map<String, Double> add(String label, Double val, Map<String, Double> map)
    {
        Double history = map.get(label);
        if (history == null) {
            history = val;
        }
        else {
            history += val;
        }
        map.put(label, history);
        return map;
    }
}
