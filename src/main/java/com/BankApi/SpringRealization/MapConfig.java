package com.BankApi.SpringRealization;

import com.BankApi.Dao.Api.BillDao;
import com.BankApi.Dao.Implementation.BillDaoImpl;

import java.util.HashMap;
import java.util.Map;

public class MapConfig {
    //Default fictive realization
    Map<Class,Class>getMapOfImpl(){
        return new HashMap<Class,Class>(Map.of(BillDao.class, BillDaoImpl.class));
    }
}
