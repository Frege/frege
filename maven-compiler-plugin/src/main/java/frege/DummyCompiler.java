package frege;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;

public class DummyCompiler implements FregeCompiler {

    public boolean compile(String[] modules,
            File target,
            String prefix,
            String[] fregePath,
            String[] sourcePath,
            PrintWriter compilerMessages) throws Exception {

        compilerMessages.append("ich sollte eigentlich " + Arrays.toString(modules) + " compilen aber leider bin ich zu dumm!");

        return false;
    }

}
