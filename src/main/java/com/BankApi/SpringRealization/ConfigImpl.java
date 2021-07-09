package com.BankApi.SpringRealization;

import org.reflections.Reflections;

import java.util.Set;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class ConfigImpl implements Config {

    private Reflections beanScanner;

    public ConfigImpl(String toScan) {
        this.beanScanner = new Reflections(toScan);
    }

    @Override
    public <T> Class<? extends T> getImplOfClass(Class<T> type) {
        Set<Class<?extends T>> classSet=beanScanner.getSubTypesOf(type);
        if(classSet.size()==0){
            throw new RuntimeException("error while scanning packages: Not found any implementations");
        } else if(classSet.size()>1){
            throw new RuntimeException("error while scanning packages: Found more than 1 implementations");
        }
        return classSet.iterator().next();
    }
}
