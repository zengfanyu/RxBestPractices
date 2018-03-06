package com.zfy.rxbestpractices.db;

import java.util.List;

/**
 * DAO接口
 *
 * @author: fanyuzeng on 2018/3/6 10:21
 */
public interface DAO<T> {
    /**
     * 查询所有
     *
     * @return
     */
    List<T> queryAll();

    /**
     * 插入一条数据
     *
     * @param t
     */
    void insert(T t);

    /**
     * 查询数据库中是否存在 gid 这条数据
     *
     * @param gid
     * @return true 数据库中存在这一条数据
     */
    boolean queryByGid(String gid);

    /**
     * 删除数据库中 gid 这条数据
     *
     * @param gid
     */
    void deleteByGid(String gid);

    /**
     * 删除 t 这条数据
     *
     * @param t
     */
    void delete(T t);


}
