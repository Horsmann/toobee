package de.unidue.ltl.toobee.twitterUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.MiniDFSCluster;
import org.apache.hadoop.hdfs.MiniDFSCluster.Builder;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.factory.ExternalResourceFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ExternalResourceDescription;
import org.junit.Before;
import org.junit.Ignore;

import de.tudarmstadt.ukp.dkpro.bigdata.io.hadoop.HdfsResourceLoaderLocator;

public class TestRawJsonReaderHDFS {
	static MiniDFSCluster cluster = null;
	static Configuration conf = new Configuration();
	String hdfsAddress = null;
	String hdfsFolder = null;
	String sourceFolder = null;
	String sourceFileA = null;
	String sourceFileB = null;

	@Ignore
	public void testRawTweetAnnotation() throws Exception {
		ExternalResourceDescription hdfsResource = ExternalResourceFactory
				.createExternalResourceDescription(
						HdfsResourceLoaderLocator.class,
						HdfsResourceLoaderLocator.PARAM_FILESYSTEM, hdfsAddress);

		CollectionReader reader = CollectionReaderFactory.createReader(
				RawJsonTweetReaderFIFO.class,
				RawJsonTweetReaderFIFO.KEY_RESOURCE_RESOLVER, hdfsResource,
				RawJsonTweetReaderFIFO.PARAM_SOURCE_LOCATION, "hdfs:" + hdfsFolder,
				RawJsonTweetReaderFIFO.PARAM_PATTERNS, new String[] { "*.gz",
						"*.bz2" });

		List<String> documents = readDocuments(reader);

		assertEquals(4, documents.size());
		assertTrue(StringEscapeUtils.unescapeJava(documents.get(0)).startsWith(
				"「あやつが"));
		assertTrue(documents.get(1).startsWith("Iyaps saking"));
		assertTrue(documents.get(2).endsWith("alright mate"));
		assertTrue(documents.get(3).startsWith("Let's "));

	}

	private List<String> readDocuments(CollectionReader aReader)
			throws Exception {
		List<String> documentContents = new ArrayList<String>();
		while (aReader.hasNext()) {
			JCas createJCas = JCasFactory.createJCas();
			aReader.getNext(createJCas.getCas());
			String text = createJCas.getDocumentText();
			documentContents.add(text);
		}
		return documentContents;
	}

	@Before
	public void copyFilesIntoHdfs() throws Exception {

		Builder builder = new MiniDFSCluster.Builder(conf);
		cluster = builder.clusterId("testcluster").build();
		cluster.waitActive();
		DistributedFileSystem fs = cluster.getFileSystem();

		hdfsAddress = "hdfs://localhost:" + cluster.getNameNodePort() + "/";
		hdfsFolder = "/junitFolder";
		sourceFolder = "src/test/resources/";
		sourceFileA = sourceFolder + "oldStyle.json.gz";
		sourceFileB = sourceFolder + "singleTweet.txt.bz2";

		conf.set("fs.default.dir", hdfsAddress);
		fs.mkdirs(new Path(hdfsFolder));
		fs.copyFromLocalFile(new Path(sourceFileA), new Path(hdfsAddress
				+ hdfsFolder));
		fs.copyFromLocalFile(new Path(sourceFileB), new Path(hdfsAddress
				+ hdfsFolder));

		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path(
				hdfsAddress + hdfsFolder), true);
		while (listFiles.hasNext()) {
			LocatedFileStatus next = listFiles.next();
			System.out.println(next.getPath().toString());
		}
	}

}
