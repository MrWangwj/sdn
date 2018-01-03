package com.sdn.service;

import com.sdn.dao.PymachineDao;
import com.sdn.model.Pymachine;

import java.sql.SQLException;

public class PymachineService {


    private PymachineDao pymachineDao;

    public PymachineService(){
        this.pymachineDao = new PymachineDao();
    }

    public Pymachine getPymachineById(int id) throws SQLException{
        return this.pymachineDao.getPymachineById(id);
    }
}
