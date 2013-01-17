package frege.runtime;


import frege.runtime.Meta;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class CompilerSupport {
	
  public static Meta.FregePackage getFrege(ClassLoader loader, String pack) throws ClassNotFoundException {
		Class<?> cl = null;
	    cl = loader.loadClass(pack);
		return cl.getAnnotation(Meta.FregePackage.class);
	}

	/**
	 *  Reads the named (text) file in the given encoding.
	 *
	 *  @return a String representing the contents of the file
	 */
	 static public String slurp(String filename, String encoding) throws Exception {
	     return new String(
	         Files.readAllBytes(
	             java.nio.file.FileSystems.getDefault().getPath(filename)),
	         encoding);
	 }

}
