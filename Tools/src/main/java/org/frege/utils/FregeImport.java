package org.frege.utils;

import java.util.*;

public class FregeImport {
    
    private String fregeImport;
    private String javaImport;
    
    
    public FregeImport(Class cls) {
        javaImport = cls.getCanonicalName();
        fregeImport =  "???" + javaImport + "??";
    }
    
    
    public String getAsFregeImport() {
        return fregeImport;
    }
    public String getAsJavaImport() {
        return javaImport;
    }

}
