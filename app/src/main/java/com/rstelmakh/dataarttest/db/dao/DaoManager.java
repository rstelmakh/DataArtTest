package com.rstelmakh.dataarttest.db.dao;

/**
 * Created by roman on 3/15/2015.
 */
public class DaoManager {
    public static <T> Dao<T> createDao(Class<T> clazz){
        if(StoreDAO.class.getName().equals(clazz.getName())){
            return (Dao<T>) new StoreDAO();
        }else if(InstrumentDAO.class.getName().equals(clazz.getName())){
            return (Dao<T>) new InstrumentDAO();
        }else{
            return null;
        }
    }
}
