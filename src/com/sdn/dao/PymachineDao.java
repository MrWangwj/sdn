package com.sdn.dao;

import com.sdn.model.Pymachine;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.sdn.utils.JDBCUtils;
import java.util.List;
import java.sql.SQLException;

public class PymachineDao {
    private  String table = "pymachines";
    private QueryRunner query;

    public PymachineDao(){
        this.query = new QueryRunner(JDBCUtils.getDataSource());
    }

    public Pymachine getPymachineById(int id) throws SQLException{
        String sql = "select * from "+this.table+" where id=?";
        return this.query.query(sql, new BeanHandler<Pymachine>(Pymachine.class), id);
    }

    public void update(Pymachine pymachine) throws SQLException{
        String sql = "update "+ this.table+" set name=?,cpu=?,ram=?,power=? where id=?;";
        Object[] params = {
                pymachine.getName(),
                pymachine.getCpu(),
                pymachine.getRam(),
                pymachine.getPower(),
                pymachine.getId()
        };
        this.query.update(sql, params);
    }

    public int addPymachine(Pymachine pymachine) throws SQLException {
        String sql = " insert into pymachines(name,cpu,ram,power,created_at) values(?,?,?,?,?) ";
        Object[] params = {pymachine.getName(),pymachine.getCpu(),pymachine.getRam(),pymachine.getPower(),System.currentTimeMillis()};
        return query.update(sql, params);
    }

    public List<Pymachine> getPymachines() throws SQLException {
        String sql = " select * from pymachines  ";
        Object[] params = {};
        List<Pymachine> list = (List<Pymachine>) query.query(sql, new BeanListHandler<Pymachine>(Pymachine.class), params);
        return list;
    }
}
