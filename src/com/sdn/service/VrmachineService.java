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
}
