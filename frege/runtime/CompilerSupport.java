package frege.runtime;


import frege.runtime.Meta;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;


public class CompilerSupport {
	
  public static Meta.FregePackage getFrege(ClassLoader loader, String pack) 
		  throws ClassNotFoundException {
		Class<?> cl = null;
	    cl = loader.loadClass(pack);
		return cl.getAnnotation(Meta.FregePackage.class);
	}

	/**
	 *  Reads the named (text) file in the given encoding.
	 *
	 *  @return a String representing the contents of the file
	 *  @throws UnsupportedEncodingException, IOException 
	 */
	 static public String slurp(String filename, String encoding) 
	 		throws UnsupportedEncodingException, IOException {
	     return new String(
	         Files.readAllBytes(
	             java.nio.file.FileSystems.getDefault().getPath(filename)),
	         encoding);
	 }

}
