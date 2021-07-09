package com.BankApi.SpringRealization;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class ApplicationContext {

    private static volatile ApplicationContext applicationContext;

    private Config config=new ConfigImpl("com.BankApi");

//    private ConfigScanner configScanner;

    public static ApplicationContext getInstance(){
        ApplicationContext localInstance = applicationContext;
        if (localInstance == null) {
            synchronized (ApplicationContext.class) {
                localInstance = applicationContext;
                if (localInstance == null) {
                    applicationContext = localInstance = new ApplicationContext();
                }
            }
        }
        return localInstance;
    }

    public <T> T getBean(Class<T> type)  {
        Class<? extends T> implement=type;
        if(type.isInterface()){
            implement=config.getImplOfClass(type);
        }

        try {
            return implement.getDeclaredConstructor().newInstance();
        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }

    }
}
