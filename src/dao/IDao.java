/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author X1 YOGA
 */
public interface IDao <T> {
    boolean create (T obj);
    boolean update (T obj);
    boolean delete (T obj);
    T findById (int id);
    List<T> findAll ();
}
