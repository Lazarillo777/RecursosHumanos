package com.mycompany.recursoshumanos.controller;

import com.mycompany.recursoshumanos.Dao.FuncionariosDao;
import com.mycompany.recursoshumanos.Dao.GrupoFamiliarDao;
import com.mycompany.recursoshumanos.domain.Funcionario;
import com.mycompany.recursoshumanos.domain.GrupoFamiliar;
import java.sql.SQLException;
import java.util.List;


public class FamiliarController {
    private GrupoFamiliarDao grupoFamiliarDao;
    private String cedFamiliar;
    
    public FamiliarController() {
        this.grupoFamiliarDao = new GrupoFamiliarDao();
    }
    
    public List<GrupoFamiliar> getFamiliares() throws SQLException {
        return grupoFamiliarDao.getFamiliares();
    }

    // Crear nuevo funcionario
    public void create(GrupoFamiliar gf) throws SQLException {
        
        grupoFamiliarDao.create(gf);
    }
    
    //Obtener funcionario por su ID
    public GrupoFamiliar getFamilares(int idFamiliar) throws SQLException {
        return grupoFamiliarDao.getGrupoFamiliar(idFamiliar);
    }
    
    public void updateFuncionario(GrupoFamiliar gf) throws SQLException {
        gf.getCedFamiliar();
        grupoFamiliarDao.update(gf);
    }
    
    public void delete(int idFamiliar) throws SQLException {
        grupoFamiliarDao.delete(idFamiliar);
    }
}
