package com.cloudteam6;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Extractor {
	private static String destDir = System.getProperty("catalina.base") + "/webapps";
	
	public void extractFile(String filePath, String fileName) throws IOException {
		String jarpath = filePath;
		JarFile jarfile = new JarFile(jarpath);
		for (Enumeration<JarEntry> iter = jarfile.entries();
				iter.hasMoreElements();) {
			JarEntry entry = iter.nextElement();
			System.out.println("Processing: " + entry.getName());
			File outfile = new File(destDir + "/" + fileName.substring(0, fileName.length() - 4), entry.getName());
			if (!outfile.exists()) outfile.getParentFile().mkdirs();
			if (!entry.isDirectory()) {
				InputStream instream = jarfile.getInputStream(entry);
				FileOutputStream outstream = new FileOutputStream(outfile);
				while (instream.available() > 0) {
					outstream.write(instream.read());
				}
				outstream.close();
				instream.close();
			}
		}
		jarfile.close();
		System.out.println("extracted");
	}	
}
