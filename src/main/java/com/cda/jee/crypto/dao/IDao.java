package com.cda.jee.crypto.dao;

import java.util.List;
import java.util.Optional;

import com.cda.jee.crypto.dao.exception.DaoException;

public interface IDao<T> {
    
    Optional<T> get(int id) throws DaoException;
    
    List<T> getAll() throws DaoException;
    
    void save(T t) throws DaoException;
    
    void update(T t, String[] params) throws DaoException;
    
    void delete(T t) throws DaoException;
}