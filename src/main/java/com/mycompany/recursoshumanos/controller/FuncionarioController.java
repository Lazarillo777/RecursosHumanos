
package com.mycompany.recursoshumanos.controller;

import com.mycompany.recursoshumanos.Dao.FuncionariosDao;
import com.mycompany.recursoshumanos.domain.Funcionario;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {
    
    private FuncionariosDao funcionariosDao;
        
    public FuncionarioController() {
        this.funcionariosDao = new FuncionariosDao();
    }
    
    //Obetener todos los funcionarios
    
    public List<Funcionario> getFuncionarios() throws SQLException {
        return funcionariosDao.getFuncionarios();
    }

    // Crear nuevo funcionario
    public void create(Funcionario funcionarios) throws SQLException {
        
        funcionariosDao.create(funcionarios);
    }
    
    //Obtener funcionario por su ID
    public Funcionario getFuncionarios(int idFuncionario) throws SQLException {
        return funcionariosDao.getFuncionarios(idFuncionario);
    }
    
    public void updateFuncionario(Funcionario funcionarios) throws SQLException {
        funcionarios.getNumeroIdentificacion();
        funcionariosDao.updateFuncionario(funcionarios);
    }
    
    public void delete(int idFuncionario) throws SQLException {
        funcionariosDao.delete(idFuncionario);
    }
}

