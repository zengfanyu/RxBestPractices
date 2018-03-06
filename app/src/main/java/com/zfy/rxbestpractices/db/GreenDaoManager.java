package com.zfy.rxbestpractices.db;

import android.content.Context;

import com.zfy.rxbestpractices.config.Constants;
import com.zfy.rxbestpractices.db.bean.LikeBean;

import java.util.List;

import javax.inject.Inject;

/**
 * 数据库管理类
 * @author: fanyuzeng on 2018/3/6 10:27
 */
public class GreenDaoManager implements DAO<LikeBean> {

    private DaoMaster mDaoMaster;

    private DaoSession mDaoSession;

    @Inject
    public GreenDaoManager(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "like.db", null);
        mDaoMaster = new DaoMaster(devOpenHelper.getEncryptedWritableDb(Constants.DB_PASSWIRD));
        mDaoSession = mDaoMaster.newSession();

    }

    private LikeBeanDao getLikeBeanDao() {
        return mDaoSession.getLikeBeanDao();
    }

    @Override
    public List queryAll() {
        return getLikeBeanDao()
                .queryBuilder()
                .orderDesc(LikeBeanDao.Properties.Time)
                .build().list();

    }

    @Override
    public void insert(LikeBean likeBean) {
        getLikeBeanDao().insert(likeBean);
    }


    @Override
    public boolean queryByGid(String gid) {
        LikeBean likeBean = getLikeBeanDao().queryBuilder().where(LikeBeanDao.Properties.Guid.eq(gid))
                .build().unique();
        return likeBean != null;
    }

    @Override
    public void deleteByGid(String gid) {
        LikeBean likeBean = getLikeBeanDao().queryBuilder().where(LikeBeanDao.Properties.Guid.eq(gid))
                .build().unique();
        if (likeBean != null) {
            getLikeBeanDao().delete(likeBean);
        }
    }

    @Override
    public void delete(LikeBean likeBean) {
        getLikeBeanDao().delete(likeBean);
    }

}
