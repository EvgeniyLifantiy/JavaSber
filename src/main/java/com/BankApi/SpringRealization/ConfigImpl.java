package com.BankApi.SpringRealization;

import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class ConfigImpl implements Config {

    private Reflections beanScanner;
    private Map<Class,Class> hashImlp;

    public ConfigImpl(Map<Class,Class>hashImlp,String toScan) {
        this.beanScanner = new Reflections(toScan);
        this.hashImlp=hashImlp;
    }

    @Override
    public <T> Class<? extends T> getImplOfClass(Class<T> type) {
        return hashImlp.computeIfAbsent(type,aClass -> {
            Set<Class<?extends T>> classSet=beanScanner.getSubTypesOf(type);
            if(classSet.size()==0){
                throw new RuntimeException("error while scanning packages: Not found any implementations");
            } else if(classSet.size()>1){
                throw new RuntimeException("error while scanning packages: Found more than 1 implementations");
            }
            return classSet.iterator().next();
        });

    }
}
