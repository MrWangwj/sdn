package com.sdn.dao;

import com.sdn.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

public class UserDao {
    private String table = "vrmachines";

    private QueryRunner query;


    public UserDao(){
        this.query = new QueryRunner(JDBCUtils.getDataSource());
    }

}
