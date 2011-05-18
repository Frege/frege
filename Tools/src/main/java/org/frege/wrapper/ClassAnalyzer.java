package org.frege.wrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import org.frege.utils.FregeImport;

public interface ClassAnalyzer {
    
    public Collection<FregeImport> getImports();
    public Collection<Field> getPublicConstants();
    public Collection<Method> getPublicMethods();
    

}
