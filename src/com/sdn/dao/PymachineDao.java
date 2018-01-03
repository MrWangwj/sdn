package com.sdn.dao;

import com.sdn.model.Pymachine;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.sdn.utils.JDBCUtils;
import java.util.List;
import java.sql.SQLException;

public class PymachineDao {
    public int addPymachine(Pymachine pymachine) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());//和数据库建立链接
        String sql = " insert into pymachines(name,cpu,ram,power,created_at) values(?,?,?,?,?) ";
        System.out.println(pymachine.getName());
        Object[] params = {pymachine.getName(),pymachine.getCpu(),pymachine.getRam(),pymachine.getPower(),System.currentTimeMillis()};
        return queryRunner.update(sql, params);
    }

    public List<Pymachine> getPymachines() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());//和数据库建立链接
        String sql = " select * from pymachines  ";
        Object[] params = {};
        List<Pymachine> list = (List<Pymachine>)queryRunner.query(sql, new BeanListHandler<Pymachine>(Pymachine.class),params);
        return list;
    }
}
