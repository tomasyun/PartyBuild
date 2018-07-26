package www.dico.cn.partybuild.database;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;
import java.util.List;

import www.dico.cn.partybuild.bean.HistoryTagBean;

public class SqliteOperate {
    private Context context;
    private Dao<HistoryTagBean, Integer> dao;

    public SqliteOperate(Context context) {
        this.context = context;
    }

    //插入数据
    public void insert(HistoryTagBean obj) {
        SqliteProxy helper = SqliteProxy.getHelper(context);
        try {
            dao = helper.getHistoryDao();
            dao.create(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询所有操作
    public List<HistoryTagBean> queryAll() {
        SqliteProxy helper = SqliteProxy.getHelper(context);
        List<HistoryTagBean> data = new ArrayList<>();
        try {
            data = helper.getHistoryDao().queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    //删除操作
    public void Delete(int id) {
        SqliteProxy helper = SqliteProxy.getHelper(context);
        try {
            helper.getHistoryDao().deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //更新操作
    public void Update(HistoryTagBean obj, int id) {
        SqliteProxy helper = SqliteProxy.getHelper(context);
        try {
            obj.setId(id);
            helper.getHistoryDao().update(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //清除所有
    public void clearAll(List<Integer> integers) {
        SqliteProxy helper = SqliteProxy.getHelper(context);
        try {
            helper.getHistoryDao().deleteIds(integers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
