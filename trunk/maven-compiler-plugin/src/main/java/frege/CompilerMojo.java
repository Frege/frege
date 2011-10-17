package frege;

import java.io.File;

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


    public void execute()
            throws MojoExecutionException, MojoFailureException {

        Log log = getLog();
        log.error(fregeSources.getAbsolutePath());

    }

}