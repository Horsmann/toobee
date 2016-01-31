package de.unidue.ltl.toobee.twitterUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.uima.collection.CollectionException;

public class RawJsonTweetReaderFIFO
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
            reader = getReader(currentReaderIdx);

            while (dataRemains(reader) && !isMatch(nextLine))
                ;

        }
        catch (IOException e) {
            logWarn("Skipped a broken file");
            return takeNextReader();
        }
        catch (Exception e) {
            throw new CollectionException(e);
        }

        if (nextLine == null) {
            return takeNextReader();
        }

        return true;
    }

    private BufferedReader getReader(int idx)
        throws Exception
    {
        if (reader == null) {
            reader = ResourceStreamWrapper.openStream(fileResources.get(idx), encoding);
        }

        return reader;
    }

    @Override
    protected boolean dataRemains(BufferedReader reader)
        throws IOException
    {
        boolean stillDataToRead = false;

        stillDataToRead = (nextLine = reader.readLine()) != null;

        return stillDataToRead;
    }

    @Override
    protected boolean takeNextReader()
        throws IOException, CollectionException
    {
        if (reader != null) {
            reader.close();
            reader = null;
        }

        currentReaderIdx++;

        if (currentReaderIdx == fileResources.size()) {
            return false;
        }

        return hasNext();
    }

}
