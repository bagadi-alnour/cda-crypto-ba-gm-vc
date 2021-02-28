package com.cda.jee.crypto.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cda.jee.crypto.dao.exception.DaoException;

public interface IDao<T> {
    
    Optional<T> get(int id) throws DaoException;
    
    List<T> getAll() throws DaoException;
    
    void save(T t) throws DaoException;
    
    void update(T t, int params) throws DaoException;
    
    boolean delete(int id) throws DaoException;

    ArrayList<Integer> delta();
}
