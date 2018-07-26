package www.dico.cn.partybuild.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import www.dico.cn.partybuild.bean.HistoryTagBean;

public class SqliteProxy extends OrmLiteSqliteOpenHelper {
    private static final String NAME = "orderlang.db";
    private static final int VERSION = 1;
    /**
     * dao，每张表对应一个
     */
    private Dao<HistoryTagBean, Integer> dao;
    private static SqliteProxy instance;

    public SqliteProxy(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        //创建表
        try {
            TableUtils.createTable(connectionSource, HistoryTagBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource arg1, int i, int i1) {
        //数据库版本升级
        try {
            TableUtils.dropTable(connectionSource, HistoryTagBean.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(sqLiteDatabase, getConnectionSource());
    }

    /**
     * 单例获取该Helper
     */
    public static synchronized SqliteProxy getHelper(Context context) {
        if (instance == null) {
            synchronized (SqliteProxy.class) {
                if (instance == null) {
                    instance = new SqliteProxy(context);
                }
            }
        }
        return instance;
    }

    /**
     * 获得historyDao
     *
     * @throws SQLException
     */
    public Dao<HistoryTagBean, Integer> getHistoryDao() throws SQLException {
        if (dao == null) {
            dao = getDao(HistoryTagBean.class);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        // TODO Auto-generated method stub
        super.close();
        dao = null;
    }
}
