package com.myrescue.model.dao;

/**
 * Created by HASEE on 2017/2/23.
 */

/**
public class UserDetailDao {

    private Dao<UserDetailBean, Integer>dao;
public UserDetailDao(Context ctx){
        DBHelper dbHelper = DBHelper.getInstance(ctx);
        dao = dbHelper.getDao(UserDetailBean.class);
        }


//插入地址方法
public void insert(UserDetailBean userDetailBean){
        try{
        dao.create(userDetailBean);
        }catch(SQLException e){
        e.printStackTrace();
        }
        }
public void delete(UserDetailBean userDetailBean){
        try{
        dao.delete(userDetailBean);
        }catch(SQLException e){
        e.printStackTrace();
        }
        }
public void update(UserDetailBean userDetailBean){
        try{
        dao.update(userDetailBean);
        }catch(SQLException e){
        e.printStackTrace();
        }
        }

/**
 * @param userId    查询依赖条件用户的id
 * @return 查询到的结果

public List<UserDetailBean>queryUserAllAddress(int userId){
        //select * from t_receiptaddress where uid = userId;
        try{
        Where<UserDetailBean, Integer>where=dao.queryBuilder().where();
        List<UserDetailBean>userDetailBean=where.eq("uid",userId).query();
        return userDetailBean;
        }catch(SQLException e){
        e.printStackTrace();
        }
        return null;
        }

/**
 * @param userId    查询地址所在用户的id
 * @param sex  查询默认选中地址 传  1,默认没选中地址   传   0
 * @return


//查询登录用户的选中送货地址
from t_receiptaddress where uid = userId and isSelect = 1;
public UserDetailBean queryUserSelectAddress(int userId,int sex){
        try{
        Where<UserDetailBean, Integer>where=dao.queryBuilder().where();
        List<UserDetailBean>queryList =where.eq("sex",1).query();
        if(queryList!=null&&queryList.size()>0){
            UserDetailBean userDetailBean=queryList.get(0);
        return userDetailBean;
        }
        }catch(SQLException e){
        e.printStackTrace();
        }
        return null;
        }
}
 */