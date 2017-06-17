package com.myrescue.model.db;

/**
 * Created by HASEE on 2017/2/20.
 */
//OrmLiteSqliteOpenHelper
//public class DBHelper extends OrmLiteSqliteOpenHelper{
//    private HashMap<String,Dao> hashMap = new HashMap<>();
//
//    private DBHelper(Context context) {
//        super(context, "takeout98.db", null, 1);
//    }
//    private static DBHelper dbHelper = null;
//    //DBHelper单例模式
//    public synchronized static DBHelper getInstance(Context ctx) {
//        if (dbHelper == null){
//            dbHelper = new DBHelper(ctx);
//        }
//        return dbHelper;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
//        //表工具类
//        try {
//            TableUtils.createTable(connectionSource, UserInfo.class);
//            TableUtils.createTable(connectionSource, UserDetailBean.class);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
//                          int oldVersion, int newVersion) {
//    }
//
//    public Dao getDao(Class clazz){
//        Dao dao = null;
//        dao = hashMap.get(clazz.getSimpleName());
//        try {
//            if (dao == null){
//                dao = super.getDao(clazz);
//                hashMap.put(clazz.getSimpleName(),dao);
//            }
//            return dao;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    };
//
//    @Override
//    public void close() {
//        //将dao对象全部清空
//        for (String key : hashMap.keySet()) {
//            Dao dao = hashMap.get(key);
//            dao = null;
//        }
//        super.close();
//    }
//}
