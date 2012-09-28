package se.softhouse.informed;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		String topDirectory = (args.length >= 1) ? args[0] : System.getProperty("user.dir");

		String charset = (args.length >= 2) ? args[1] : "UTF-8";

		System.out.println("Top directory: " + topDirectory);
		System.out.println("Charset: " + charset);
		Collection<File> listFiles = FileUtils.listFiles(new File(topDirectory), new SuffixFileFilter(".jar"), TrueFileFilter.INSTANCE);
		for (File file : listFiles)
		{
			System.out.println("*******");
			System.out.println(file.getName());
			try (ZipInputStream zis = new ZipInputStream(new FileInputStream(file), Charset.forName(charset)))
			{
				ZipEntry nextEntry;
				while (true)
				{
					nextEntry = zis.getNextEntry();
					if (nextEntry == null) break;
					System.out.println(nextEntry.getName());
				}
			}
		}
	}
}
