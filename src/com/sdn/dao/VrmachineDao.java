package com.sdn.dao;

import com.sdn.model.Vrmachine;
import com.sdn.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class VrmachineDao {
    private String table = "vrmachines";

    private QueryRunner query;


    public VrmachineDao(){
        this.query = new QueryRunner(JDBCUtils.getDataSource());
    }

    //  通过物理机ID获取虚拟机
    public List<Vrmachine> getVrmachineByPymachineId(int id) throws SQLException{
        String sql = "select * from " + this.table + " where pymachine_id=?";
        return this.query.query(sql, new BeanListHandler<Vrmachine>(Vrmachine.class), id);
    }

}
