package frege;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import java.util.concurrent.Callable;
import java.lang.invoke.MethodHandle;

/**
 * @goal compileFrege
 */
public class CompilerMojo extends AbstractMojo{
    
    /* use only combinations of the following flag values */ 
    final static int hints = 1;
    final static int verbose = 2;
    final static int warnings = 4;
    final static int withcp = 8;
    final static int runjavac = 16;
    final static int comments = 32;
    final static int make = 64;
    /** <p> use this when "prefix" (and "fregePath") are empty. "make" may be added </p> */
    final static int stdopts = hints + warnings + withcp + runjavac;
    /** <p> use this to compile the compiler with itself, "fregePath" must not be empty. "make" may be added. </p> */
    final static int bootstrapopts = hints + warnings + runjavac;


    /** @parameter default-value="${project}" */
    private MavenProject mavenProject;

    /**
     * @parameter
     */
    private boolean aufLinks;

    /**
     * @parameter default-value="${project.build.outputDirectory}"
     * 
     */
    private File buildDirectory; // <- parameter wird den Wert der in der POM für project.build.outputDirectory festgelegt ist enthalten. Normalerweise ist das target/classes. Für einen FR-> Java compiler würden wir hier Source dir wollen ...


    /**
     * @parameter default-value="src/main/frege"
     * 
     */
    private File fregeSources;

    /**
     * @parameter
     * 
     */
    private String mainModule;


    /**
     * @parameter default-value="frege.DummyCompiler"
     */
    private String compiler;

    /**
     * @parameter default-value=""
     */
    private String prefix;

    /**
     * @parameter default-value="${project.build.outputDirectory}"
     */
    private File fregePath;


    public void execute()
            throws MojoExecutionException, MojoFailureException {
        
        MethoHandle compMethod; 
        boolean success;
        try {
            @SuppressWarnings("unchecked")
             final Callable<MethodHandle> fcx = (Callable<MethodHandle>) Class.forName("frege.rt.FregeCompiler").newInstance();
             compMethod = fc.call();   //  throws NoSuchMethodException, IllegalAccessException
        } catch (Throwable e1) {
            throw new MojoFailureException("could not instanciate compiler", e1);
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter( sw);
        try {
            success = (boolean) compMethod.invoke(
                    compiler,
                    new String[] { modules to compile },                // u.U mehrere
                    new String[] { fregeSources.toURI().toString() },   // potentiell mehrere
                    bootstrapopts (+ make),
                    buildDirectory,
                    new String[] { fregePath.toURI().toString() },      // potentiell mehrere
                    prefix,
                    pw);
        } catch (Exception e) {
            throw new MojoFailureException("frege compiler FAILED", e);
        }
        Log log = getLog();
        log.info(sw.toString());
    }

}