package com.BankApi.SpringRealization;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public interface Config {
    <T> Class<? extends  T> getImplOfClass(Class<T> type);
}
