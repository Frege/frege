package frege;

import java.io.File;
import java.io.PrintWriter;

public interface FregeCompiler {

    boolean compile(String[] modules,
            File target,
            String prefix,
            String[] fregePath,
            String[] sourcePath,
            PrintWriter compilerMessages) throws Exception;
}
