package com.sdn.dao;

import com.sdn.model.Vrmachine;
import com.sdn.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
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

        String sql = "select "+this.table
                +".*, powers.power as power from " + this.table + " inner join powers on "
                +this.table+".status=powers.id where "+this.table+".pymachine_id=?";
        return this.query.query(sql, new BeanListHandler<Vrmachine>(Vrmachine.class), id);
    }

    public int delete(Vrmachine vrmachine) throws SQLException {

        String sql = "delete from "+this.table+" where id =?";
        System.out.println(sql+""+vrmachine.getId());
        return this.query.update(sql,vrmachine.getId());
    }

    public void create(Vrmachine vrmachine) throws SQLException{
        String sql = "insert into "+this.table+" (name, cpu, ram, pymachine_id, status, created_at) values (?,?,?,?,?,?)";

        Object[] params = {
                vrmachine.getName(),
                vrmachine.getCpu(),
                vrmachine.getRam(),
                vrmachine.getPymachine_id(),
                vrmachine.getStatus(),
                vrmachine.getCreated_at()
        };
        this.query.update(sql, params);
    }

    public Vrmachine getSumVermachine(int py_id) throws SQLException{
        String sql = "select sum(powers.power) as power, sum("+this.table+".cpu) as cpu, sum("+this.table+".ram) from "+this.table+" inner join powers on "+this.table+".status=powers.id where "+this.table+".id=?";
        return this.query.query(sql, new BeanHandler<Vrmachine>(Vrmachine.class),py_id);
    }
}
