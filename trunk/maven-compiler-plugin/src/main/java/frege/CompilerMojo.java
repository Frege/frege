package frege;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

/**
 * @goal compileFrege
 */
public class CompilerMojo extends AbstractMojo{


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
        FregeCompiler fc;
        try {
            fc = (FregeCompiler) Class.forName(compiler).newInstance();
        } catch (Throwable e1) {
            throw new MojoFailureException("could not instanciate compiler", e1);
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter( sw);
        try {

            fc.compile(
                    new String[] {mainModule},
                    buildDirectory,
                    prefix,
                    new String[] { fregePath.toURI().toString() },
                    new String[] { fregeSources.toURI().toString() },
                    pw);
        } catch (Exception e) {
            throw new MojoFailureException("frege compiler FAILED", e);
        }
        Log log = getLog();
        log.info(sw.toString());

    }

}