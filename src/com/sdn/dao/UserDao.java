package com.sdn.dao;

import com.sdn.model.User;
import com.sdn.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;


public class UserDao {
    private String table = "vrmachines";

    private QueryRunner query;


    public UserDao() {
        this.query = new QueryRunner(JDBCUtils.getDataSource());
    }

    public User login(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());//和数据库建立链接
        String sql = " select * from users where username=? and password=? ";
        Object[] params = {user.getAccount(), user.getPassword()};

        User u = queryRunner.query(sql, new BeanHandler<User>(User.class), params);
        return u;
    }
}
