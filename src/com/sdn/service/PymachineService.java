package com.sdn.service;

import com.sdn.dao.PymachineDao;
import com.sdn.model.Pymachine;
import com.sdn.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

public class PymachineService {
    public int addPymachine(Pymachine pymachine) throws SQLException{
        PymachineDao pymachineDao=new PymachineDao();
        return pymachineDao.addPymachine(pymachine);
    }

    public List<Pymachine> getPymachines() throws SQLException {
        PymachineDao pymachineDao=new PymachineDao();
        return pymachineDao.getPymachines();
    }
}
