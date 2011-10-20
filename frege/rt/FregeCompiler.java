package frege.rt;

/*
 * $Author$
 * $Revision$
 * $Date$
 * $Id$
 */


 
import java.io.File;
import java.io.PrintWriter;
import java.util.concurrent.Callable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;


/**
 * <p> Interface to compiler from Java. This is used by the frege/maven plugin. </p> 
 */
public class FregeCompiler  implements Callable<MethodHandle> {    
    /**
     * <p>Run the compiler.</p>
     * <p>Whenever an array parameter is null, 
     *   it will be seen as empty list by the frege compiler. A null for a string parameter
     *   will be replaced with a sensible value.</p>
     * @param compiler the compiler's class, usually "frege.compiler.Main"
     * @param modules  array of file names to compile. 
     *                 If the make flag is set, also names of classes that must be made are allowed.
     * @param sourcePath array of directory names where source files can be found. Makes sense only with "make"
     * @param flags    compiler flags, use either "stdopts" or "bootstrapopts" and possibly add "make"
     * @param target   directory name of the target directory, will be the javac -d option. defaults to "."
     * @param fregePath Array of directory names, jar/zip file names and URLs. Compiler uses this to
                        construct an URLCLassLoader needed to import frege classes.
     * @param prefix    Prefix for modifying class names. Used in compiler bootstrap only. default ""
     * @param compilerMessages a PrintWriter for compiler error messages and warnings
     * @return true     if and only if there were no errors.
     * @throws Compiler bugs and reflective activities done here can lead to various exceptions. 
     */
    final public static boolean compile(
            String   compiler,
            String[] modules,
            String[] sourcePath,
            int flags,
            String target,
            String[] fregePath,
            String prefix,
            PrintWriter compilerMessages) throws Exception {
        // correct bad manners
        if (compiler == null) compiler = "frege.compiler.Main";
        if (modules == null) return true;   // short circuit evaluation :)
        if (sourcePath == null) sourcePath = new String[] {};
        if (target == null) target = ".";
        if (prefix == null) prefix = "";
        if (fregePath == null) fregePath =  new String[] {};
        try {
            Class runclass = Class.forName(compiler + "$runcompiler");
            MethodHandle runcompilerw = MethodHandles.lookup().findStatic(
                runclass, "w",
                MethodType.methodType( 
                    Fun.class,
                    String[].class,     // modules
                    String[].class,     // sourcePath
                    int.class,          // flags
                    String.class,       // target directory
                    String[].class,     // frege path
                    String.class,       // prefix
                    PrintWriter.class   )); 
            Fun<Boxed.Int, Boxed.Bool> io = (Fun<Boxed.Int, Boxed.Bool>) runcompilerw.invokeExact(
                    modules, sourcePath, flags, target, fregePath, prefix, compilerMessages
                );
            return io._e().a(Boxed.Int.mk(42))._e().j;
        } catch (Throwable e) {
            compilerMessages.println("exception: " + e);
            e.printStackTrace();
        }
        
        
        compilerMessages.println("frege compiler (not yet) running.");
        return true;
    }
    /**
        obtain a MethodHandle that calls the compile(...) method.
    
        <p>Use like this:</p>
        
        <pre>
    &#064;SuppressWarnings("unchecked")
    Callable&lt;MethodHandle&gt; fc = (Callable&lt;MethodHandle&gt;) Class.forName("frege.rt.FregeCompiler").newInstance();
    boolean result = fc.call().invoke("frege.compiler.Main", 
                                        new String[] {"foo.fr"}, 
                                        null, flags, "build",
                                        new String[] {"fregec1.jar"},
                                        "", 
                                        new PrintWriter (new StringBuffer()));
        </pre>
        @return a MethodHandle to the compile method 
        @throws Error if MH cannot be made
    */
    public MethodHandle call() 
        throws NoSuchMethodException, IllegalAccessException  
    {
        return MethodHandles.lookup().findStatic(
                frege.rt.FregeCompiler.class, "compile",
                MethodType.methodType(
                            boolean.class,      // return type
                            String.class,       // compiler
                            String[].class,     // modules
                            String[].class,     // sourcePath
                            int.class,          // flags
                            String.class,       // target directory
                            String[].class,     // frege path
                            String.class,       // prefix
                            PrintWriter.class   // error message sink
                        )
                    );
    }
    
    // test ruzn, specify 0..n files to compile
    public static void main(String[] argv) throws Exception {
        PrintWriter pw = new PrintWriter(System.err);
        boolean succ = compile(null, argv, null, 31, "build", null, null, pw);
        pw.println("compile returned " + succ);
        pw.close();
    }
}
