package de.unidue.ltl.toobee.twitterUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.log4j.Logger;

import de.tudarmstadt.ukp.dkpro.core.api.io.ResourceCollectionReaderBase.Resource;

public class ResourceStreamWrapper
{
    public static BufferedReader openStream(Resource r, String encoding)
        throws Exception
    {
        InputStreamReader isr = null;
        logInfo("Initialize input stream for: " + "[" + getLocation(r) + "]");
        if (isHDFSResource(r)) {
            // hdfs does auto-unzipping if files is in any common compression
            // format
            isr = initNormalStream(r, encoding);
        }
        else {
            isr = decompressIfNecassary(r, encoding);
        }
        return new BufferedReader(isr);

    }

    private static String getLocation(Resource r) throws IOException
    {
        org.springframework.core.io.Resource resource = r.getResource();
        if(resource==null){
            return r.getLocation();
        }
        File file = resource.getFile();
        if(file == null){
            return r.getLocation();
        }
        String absolutePath = file.getAbsolutePath();
        if(absolutePath==null){
            return r.getLocation();
        }
        return absolutePath;
    }

    private static void logInfo(String aString)
    {
        Logger.getLogger(ResourceStreamWrapper.class.getName()).info(aString);
    }

    private static InputStreamReader decompressIfNecassary(Resource r, String encoding)
        throws Exception
    {
        if (hasFileEnding(r, ".gz")) {
            return initGZipStream(r, encoding);
        }
        else if (hasFileEnding(r, ".bz2")) {
            return initBZip2(r, encoding);
        }
        return initNormalStream(r, encoding);
    }

    private static boolean isHDFSResource(Resource r)
    {
        return r.getLocation().startsWith("hdfs:");
    }

    private static InputStreamReader initBZip2(Resource r, String encoding)
        throws Exception
    {
        return new InputStreamReader(new BZip2CompressorInputStream(r.getInputStream()), encoding);
    }

    private static boolean hasFileEnding(Resource r, String ending)
        throws Exception
    {
        return r.getLocation().endsWith(ending);
    }

    private static InputStreamReader initNormalStream(Resource r, String encoding)
        throws Exception
    {
        return new InputStreamReader(r.getInputStream(), encoding);
    }

    private static InputStream getClosedDummyReader()
        throws IOException
    {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                new String("Dummy").getBytes());
        byteArrayInputStream.close();

        return byteArrayInputStream;
    }

    private static InputStreamReader initGZipStream(Resource r, String encoding)
        throws Exception
    {
        return new InputStreamReader(getGzipInputStream(r), encoding);
    }

    private static InputStream getGzipInputStream(Resource r)
        throws Exception
    {
        return new GZIPInputStream(r.getInputStream());
    }

}
