package de.unidue.ltl.toobee.twitterUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.uima.collection.CollectionException;

public class RawJsonTweetReaderRevolver
    extends RawJsonReaderAbstract
{

    @Override
    protected void collectResources()
    {
        fileResources = new ArrayList<Resource>();
        try {
            for (Resource r : getResources()) {
                fileResources.add(r);
            }
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }
        initializedReaders = new ArrayList<BufferedReader>();
    }

    @Override
    public boolean hasNext()
        throws IOException, CollectionException
    {
        if (forceStopHasNoNext != null && forceStopHasNoNext) {
            return false;
        }

        BufferedReader reader = null;
        try {
            if (currentReaderIdx == fileResources.size()) {
                currentReaderIdx = 0;
                while (emptyFiles.contains(currentReaderIdx)) {
                    currentReaderIdx++;
                }
            }
            reader = getReader(currentReaderIdx);
            currentReaderIdx++;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        while (dataRemains(reader) && !isMatch(nextLine))
            ;

        if (nextLine == null) {
            return takeNextReader();
        }

        return true;
    }

    private BufferedReader getReader(int idx)
        throws Exception
    {
        if (initializedReaders.size() <= idx) {
            reader = ResourceStreamWrapper.openStream(fileResources.get(idx), encoding);
            initializedReaders.add(reader);
        }
        return initializedReaders.get(idx);
    }

    @Override
    protected boolean takeNextReader()
        throws IOException, CollectionException
    {
        emptyFiles.add(currentReaderIdx-1);

        if (emptyFiles.size() == fileResources.size()) {
            return false;
        }

        if (currentReaderIdx == fileResources.size()) {
            currentReaderIdx = 0;
            return true;
        }

        return hasNext();
    }
 

}
