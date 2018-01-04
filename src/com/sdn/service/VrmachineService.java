package com.sdn.service;

import com.sdn.dao.VrmachineDao;
import com.sdn.model.Vrmachine;

import java.sql.SQLException;
import java.util.List;

public class VrmachineService {
    private VrmachineDao vrmachineDao;

    public VrmachineService(){
        this.vrmachineDao = new VrmachineDao();
    }


    //  通过物理机ID获取虚拟机
    public List<Vrmachine> getVrmachinesByPymachineId(int id) throws SQLException{
        return this.vrmachineDao.getVrmachineByPymachineId(id);
    }

    public int delete(Vrmachine vrmachine) throws SQLException {
        return vrmachineDao.delete(vrmachine) ;
    }

    public void create(Vrmachine vrmachine)throws SQLException{
        this.vrmachineDao.create(vrmachine);
    }

    public Vrmachine getSumVermachine(int pr_id, int excludeId) throws SQLException{
        return this.vrmachineDao.getSumVermachine(pr_id, excludeId);
    }


    public void update(Vrmachine vrmachine) throws SQLException{
        this.vrmachineDao.update(vrmachine);
    }

    public  void changeStatus(Vrmachine v) throws SQLException {
        this.vrmachineDao.changeStatus(v);
    }
}
