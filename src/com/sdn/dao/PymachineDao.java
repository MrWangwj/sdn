package com.sdn.dao;

import com.sdn.model.Pymachine;
import com.sdn.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

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
}
