package de.unidue.ltl.toobee.splitutil;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import de.unidue.ltl.toobee.splitutil.SplitUtil;

public class TestSplitUtil
{
    
    @Test
    public void testSplittingSets3()
        throws Exception
    {
        List<String> splits = SplitUtil.splitIntoNFilesIntoTemporaryFolder(
                "src/test/resources/1", ".txt", false, 3);

        assertEquals(3, splits.size());

        assertEquals(16, FileUtils.readLines(new File(splits.get(0))).size());
        assertEquals(12, FileUtils.readLines(new File(splits.get(1))).size());
        assertEquals(5, FileUtils.readLines(new File(splits.get(2))).size());
    }
    
    @Test(expected=IllegalStateException.class)
    public void testSplittingSets5()
        throws Exception
    {
        List<String> splits = SplitUtil.splitIntoNFilesIntoTemporaryFolder(
                "src/test/resources/1", ".txt", false, 5);

        assertEquals(5, splits.size());

        assertEquals(10, FileUtils.readLines(new File(splits.get(0))).size());
        assertEquals(10, FileUtils.readLines(new File(splits.get(1))).size());
        assertEquals(7, FileUtils.readLines(new File(splits.get(2))).size());
        assertEquals(5, FileUtils.readLines(new File(splits.get(3))).size());
        assertEquals(0, FileUtils.readLines(new File(splits.get(4))).size());
    }
    
    @Test
    public void testLearningCurveWith3Split() throws Exception{
    	  List<String> splits = SplitUtil.splitIntoNFilesIntoTemporaryFolder(
    	          "src/test/resources/1", ".txt", false, 3);
    	  
    	  List<List<String>> createLearningCurvesplits = SplitUtil.createLearningCurvesplits(splits);
    	  assertEquals(3, createLearningCurvesplits.size());
    	  
    	  
    	  List<String> list = createLearningCurvesplits.get(0);
    	  assertEquals(1, new File(list.get(0)).listFiles().length);
    	  assertEquals(16, FileUtils.readLines(new File(list.get(0)+"/0.data")).size());
    	  
    	  assertEquals(1, new File(list.get(1)).listFiles().length);
    	  assertEquals(12, FileUtils.readLines(new File(list.get(1)+"/1.data")).size());
    	  
    	  assertEquals(2, new File(list.get(2)).listFiles().length);
    	  assertEquals(12, FileUtils.readLines(new File(list.get(2)+"/1.data")).size());
    	  assertEquals(5, FileUtils.readLines(new File(list.get(2)+"/2.data")).size());
    }

    @Test
    public void testLearningCurveWithDoubledData3Split() throws Exception{
    	  List<String> splits = SplitUtil.splitIntoNFilesIntoTemporaryFolder(
    	          "src/test/resources/2", ".txt", false, 3);
    	  
    	  List<List<String>> createLearningCurvesplits = SplitUtil.createLearningCurvesplits(splits);
    	  assertEquals(3, createLearningCurvesplits.size());
    	  
    	  
    	  List<String> list = createLearningCurvesplits.get(0);
    	  assertEquals(1, new File(list.get(0)).listFiles().length);
    	  assertEquals(21, FileUtils.readLines(new File(list.get(0)+"/0.data")).size());
    	  
    	  assertEquals(1, new File(list.get(1)).listFiles().length);
    	  assertEquals(23, FileUtils.readLines(new File(list.get(1)+"/1.data")).size());
    	  
    	  assertEquals(2, new File(list.get(2)).listFiles().length);
    	  assertEquals(23, FileUtils.readLines(new File(list.get(2)+"/1.data")).size());
    	  assertEquals(13, FileUtils.readLines(new File(list.get(2)+"/2.data")).size());
    }
    
    @Test
    public void testCrossValidationSetCreation() throws IOException{
        List<String> files = new ArrayList<>();
        files.add("src/test/resources/cv/a.txt");
        files.add("src/test/resources/cv/b.txt");
        files.add("src/test/resources/cv/c.txt");
        
        List<String[]> cvs = SplitUtil.createCrossValidationSplits("testRoot", files, 3);
        assertEquals(3,cvs.size());
        
        Set<String> names = getFileNames(new File(cvs.get(0)[0]).listFiles());
        assertEquals(2, names.size());
        assertTrue(names.contains("b.txt"));
        assertTrue(names.contains("c.txt"));
        names = getFileNames(new File(cvs.get(0)[1]).listFiles());
        assertTrue(names.contains("a.txt"));
        
        names = getFileNames(new File(cvs.get(1)[0]).listFiles());
        assertEquals(2, names.size());
        assertTrue(names.contains("a.txt"));
        assertTrue(names.contains("c.txt"));
        names = getFileNames(new File(cvs.get(1)[1]).listFiles());
        assertTrue(names.contains("b.txt"));
        
        names = getFileNames(new File(cvs.get(2)[0]).listFiles());
        assertEquals(2, names.size());
        assertTrue(names.contains("a.txt"));
        assertTrue(names.contains("b.txt"));
        names = getFileNames(new File(cvs.get(2)[1]).listFiles());
        assertTrue(names.contains("c.txt"));
        
    }

    private Set<String> getFileNames(File[] listFiles)
    {
        List<String> s = new ArrayList<>();
        for(File f : listFiles){
            s.add(f.getName());
        }
        
        return new HashSet<String>(s);
    }
}
