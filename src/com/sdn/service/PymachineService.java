package com.sdn.service;

import com.sdn.dao.PymachineDao;
import com.sdn.model.Pymachine;
import java.sql.SQLException;
import java.util.List;

public class PymachineService {
    private PymachineDao pymachineDao;

    public PymachineService() {
        this.pymachineDao = new PymachineDao();
    }

    public Pymachine getPymachineById(int id) throws SQLException {
        return this.pymachineDao.getPymachineById(id);
    }

    public void update(Pymachine pymachine) throws SQLException {
        this.pymachineDao.update(pymachine);
    }

    public int addPymachine(Pymachine pymachine) throws SQLException {
        PymachineDao pymachineDao = new PymachineDao();
        return pymachineDao.addPymachine(pymachine);
    }

    public List<Pymachine> getPymachines() throws SQLException {
        PymachineDao pymachineDao = new PymachineDao();
        return pymachineDao.getPymachines();
    }
}