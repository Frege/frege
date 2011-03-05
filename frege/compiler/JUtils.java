package frege.compiler;

// import frege.Prelude;
import frege.CA;

import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.lang.annotation.*;

/*
 * $Revision$
 * $Header: E:/iwcvs/fc3/frege/compiler/JUtils.java,v 3.10 2009/04/30 13:41:12 iw Exp $
 * $Log: JUtils.java,v $
 * Revision 3.10  2009/04/30 13:41:12  iw
 * - added
 *
 * Revision 3.1  2009/04/22 21:43:01  iw
 * no message
 *
 * Revision 2.5  2008/04/28 16:29:28  iw
 * - documenatation comments (up to constructors)
 *
 * Revision 2.4  2008/04/18 12:52:19  iw
 * - deprecated method File.toURL(), replaced by toURI().toURL()
 *
 * Revision 2.3  2008/04/18 12:15:29  iw
 * - remove nasty comment
 *
 * Revision 2.2  2007/10/01 16:27:14  iw
 * - runJavac only takes the commandline, anything else is done by Main
 *
 * Revision 2.1  2007/09/23 12:13:01  iw
 * - initila revision for self hosting derived from frege-2-prefix
 *
 * Revision 1.10  2007/09/23 11:08:49  iw
 * - frege2 with prefix
 *
 * Revision 1.9  2006/12/26 15:41:18  iw
 * - import&export completed
 * - run javac
 *
 * Revision 1.8  2006/12/20 13:46:21  iw
 * - Annotation classes now loaded from frege.compiler.CA
 * - no dependencies on any frege code anymore
 *
 * Revision 1.7  2006/12/14 22:36:33  iw
 * - don't use any frege data structures, only natives and arrays (function getOp removed)
 *
 * Revision 1.6  2006/12/14 21:53:49  iw
 * - imports class definitions
 *
 * Revision 1.5  2006/11/02 14:08:03  iw
 * - all the import/package stuff done
 * - predeclaration of data, type and class items done in pass2
 *
 * Revision 1.4  2006/10/26 19:26:00  iw
 * - replaced getOP by getOperators, deprecated getOP
 * - added getTypedefs
 *
 * Revision 1.3  2006/10/21 16:33:29  iw
 * - array support with data A dvars = native [] type;
 * - let bound or global variables may not be quantified. Thus, there is no easy way to have a global Var a or Var [a], or similiar.
 * - Prelude.undef does not exist anymore, Prelude.die was renamed Prleude.undefined
 *
 * Revision 1.2  2006/08/12 12:20:08  iw
 * - getOP function for getting the Operators annotations from a class
 *
 * Revision 1.1  2006/05/21 18:07:03  iw
 * CVS on memorystick
 *
 * Revision 2.0  2006/05/20 14:12:25  iw
 * initial revision
 *
 */

public class JUtils {
	private static URLClassLoader loader = null;
	private static Class<JUtils> jutil;

	private static URLClassLoader getCL() throws MalformedURLException {
		// we need an extra class loader when we run from a jar
		if (loader == null) {
			String clpath = System.getenv("CLASSPATH");
			if (clpath == null) clpath = ".";
			// System.err.println("Classpath: " + clpath);
			String[] dirs = clpath.split(System.getProperty("path.separator"));
			java.net.URL[] urls = new java.net.URL[dirs.length];
			java.net.URL url = null;
			{
				String s = null;
				for (int i = 0; i < dirs.length; i++) {
					s = dirs[i];
					java.io.File f = new java.io.File(s);
					url = f.toURI().toURL();
					// System.err.println("-- " + url.toString());
					urls[i] = url;
				}
				loader = new URLClassLoader(urls);
			}
		}
		return loader;
	}
	final public static ClassLoader theCL() { return loader; }
	final public static void initCL(String clpath)
			throws MalformedURLException, ClassNotFoundException {
		// System.err.println("initCL " + clpath);
		String[] dirs = clpath.split(System.getProperty("path.separator"));
		java.net.URL[] urls = new java.net.URL[dirs.length];
		java.net.URL url = null;
		{
			String s = null;
			for (int i = 0; i < dirs.length; i++) {
				s = dirs[i];
				java.io.File f = new java.io.File(s);
				url = f.toURI().toURL();
				// System.err.println("-- " + url.toString());
				urls[i] = url;
			}
			loader = new URLClassLoader(urls);
			// jutil = (Class<JUtils>) loader.loadClass("frege.compiler.JUtils");
		}
	}
	final public static java.lang.String getIF(java.lang.String cls)
			throws ClassNotFoundException, NoSuchFieldException,
			IllegalAccessException, MalformedURLException {
		Class<?> imp = null;
		java.lang.reflect.Field fld = null;

		imp = getCL().loadClass(cls + "$_Interface");
		fld = imp.getDeclaredField("INTERFACE");
		return (String) fld.get(null);
	}

	/*
	 *
	 @Deprecated
	final public static frege.Prelude.Lst getOP(String classname)
		// throws ClassNotFoundException, MalformedURLException
	{
		frege.Prelude.Lst nil = frege.Prelude.Lst.Lst;
		Class<?> cl = null;
		// Class<? extends frege.Run.Operators> rops = null;
		// Class<? extends frege.Run.Operator> op = null;
		System.err.print("getOP " + classname);
		try {
			cl = loader.loadClass(classname);
			// rops = (Class<frege.Run.Operators>) loader.loadClass("frege.Run$Operators");
			// op =   (Class<frege.Run.Operator>)  loader.loadClass("frege.Run$Operator");
		}
		catch (ClassNotFoundException e) {
			System.err.println(" not found: " + e.getLocalizedMessage());
			return nil;
		}


		frege.Run.Operators os = cl.getAnnotation(frege.Run.Operators.class);
		if (os != null) {
			Run.Operator[] ops = os.value();
			int j;
			for (j=ops.length - 1; j>=0; j--) {
				Prelude.Tuple2 t = Prelude.Tuple2._Tuple2(Prelude.String.$fromj(ops[j].name()), Prelude.Int.$fromj(ops[j].prec()));
				nil = Prelude.Lst._$cons(t, nil);
			}
			System.err.println(" " + ops.length);
		}
		else System.err.println(" null");
		return nil;
	}
	*/

	public static CA.Operator[] getOperators(String pack) {
		Class<?> cl = null;
		// System.err.print("getOperators " + pack);
		try {
			cl = loader.loadClass(pack);
		}
		catch (ClassNotFoundException e) {
			System.err.println(" not found: " + e.getLocalizedMessage());
			return null;
		}

		CA.Operators os = cl.getAnnotation(CA.Operators.class);
		if (os == null) return null;
		return os.value();
	}

	public static CA.Typedef[] getTypedefs(Class<?> cl) {
		CA.Typedefs tds = cl.getAnnotation(CA.Typedefs.class);
		if (tds == null) return null;
		return tds.value();
	}

	public static java.lang.String[] getImports(Class<?> cl) {
		CA.Imports imp = cl.getAnnotation(CA.Imports.class);
		if (imp == null) return null;
		return imp.value();
	}

	public static java.lang.String[] getNamespaces(Class<?> cl) {
		CA.Namespaces imp = cl.getAnnotation(CA.Namespaces.class);
		if (imp == null) return null;
		return imp.value();
	}

	public static java.lang.String getPackageDoc(Class<?> cl) {
	    CA.PackageDoc imp = cl.getAnnotation(CA.PackageDoc.class);
	    if (imp == null) return null;
	    return imp.value();
	}

	public static CA.Classdef[] getClassdefs(Class<?> cl) {
		CA.Classdefs cds = cl.getAnnotation(CA.Classdefs.class);
		return cds==null? null : cds.value();
	}

	public static CA.Data[] getDatadefs(Class<?> cl) {
		CA.Datadefs cds = cl.getAnnotation(CA.Datadefs.class);
		return cds==null? null : cds.value();
	}

	public static CA.Inst[] getInstdefs(Class<?> cl) {
		CA.Instdefs cds = cl.getAnnotation(CA.Instdefs.class);
		return cds==null? null : cds.value();
	}

	public static CA.Fundef[] getFundefs(Class<?> cl) {
		CA.Fundefs cds = cl.getAnnotation(CA.Fundefs.class);
		return cds==null? null : cds.value();
	}

	public static int runJavac(final String cmd) {
		try {
			// String cmd = "javac -cp " + cp + " -d " + d + " " + src;
			int cex = 0;
			Process jp = Runtime.getRuntime().exec(cmd);
			// if (Common.verbose)
				System.err.println("running: " + cmd);
			java.io.InputStream is = jp.getErrorStream();
			while ((cex = is.read()) >= 0) {
				System.err.write(cex);
			}
			if ((cex = jp.waitFor()) != 0) {
				System.err.println("javac terminated with exit code " + cex);
			}
			return cex;
		} catch (java.io.IOException e) {
			System.err.println("Can't javac  (" + e.getMessage() + ")");
		} catch (InterruptedException e) {
			System.err.println("Can't javac  (" + e.getMessage() + ")");
		}
		return 1;
	}

	public static void main(String[] args) {
		for (String classname : args) {
			System.err.print(classname);
			Class<?> cl = null;
			try {
				cl = getCL().loadClass(classname);
				// rops = (Class<frege.Run.Operators>) loader.loadClass("frege.Run$Operators");
				// op =   (Class<frege.Run.Operator>)  loader.loadClass("frege.Run$Operator");
			}
			catch (ClassNotFoundException e) {
				System.err.println(" not found: " + e.getLocalizedMessage());
				continue;
			}
			catch (MalformedURLException e) {
				System.err.println(" bad URLs: " + e.getLocalizedMessage());
				continue;
			}
			System.err.println(" loaded.");
			// Annotation[] as = cl.getAnnotations();
			for (Annotation a : cl.getAnnotations()) {
				System.out.println(a);
			}
		}

	}

}
