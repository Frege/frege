package org.frege.wrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

import org.frege.utils.FregeImport;

import wulfUtils.reflect.ReflectionUtils;

public class DefaultClassAnalyzer implements ClassAnalyzer {
    private Set<FregeImport> imports = new HashSet<FregeImport>();
    private Set<Field> publicConstants = new HashSet<Field>();
    //private Set<Method> publicMethods = new HashSet<Method>();
    
    private Class toAnalyze;
    
    public DefaultClassAnalyzer(Class toAnalyze) {
        this.toAnalyze = toAnalyze;
    }
    
    private void analyze() {
        
        for( Field f : toAnalyze.getFields()) {
            if( Modifier.isFinal(f.getModifiers())) {
                publicConstants.add(f);
            }
        }
            
      
    }
    
    @Override
    public Collection<FregeImport> getImports() {
        return imports;
    }
    @Override
    public Collection<Field> getPublicConstants() {
        return publicConstants;
    }
    @Override
    public Collection<Method> getPublicMethods() {
        return Arrays.asList( toAnalyze.getMethods());
    }
}
