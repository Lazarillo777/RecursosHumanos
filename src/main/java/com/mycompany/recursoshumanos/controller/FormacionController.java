package com.mycompany.recursoshumanos.controller;

import com.mycompany.recursoshumanos.Dao.FormacionDao;
import com.mycompany.recursoshumanos.Dao.FuncionariosDao;
import com.mycompany.recursoshumanos.domain.Formacion;
import com.mycompany.recursoshumanos.domain.Funcionario;
import java.sql.SQLException;
import java.util.List;


public class FormacionController {
    private FormacionDao formacionDao;
    private String cedFuncionario;
        
    public FormacionController() {
        this.formacionDao = new FormacionDao();
    }
    
    //Obetener todos los funcionarios
    
    public List<Formacion> getFormacionAcademica() throws SQLException {
        return formacionDao.getFormacionAcademica();
    }

    // Crear nuevo funcionario
    public void create(Formacion fn) throws SQLException {
        
        formacionDao.create(fn);
    }
    
    //Obtener funcionario por su ID
    public Formacion getFormacionAcademica(int idFormacion) throws SQLException {
        return formacionDao.getFormacionAcademica(idFormacion);
    }
    
    public void updateFormacionAcademmica(Formacion fn) throws SQLException {
        fn.getCedFuncionario();
        formacionDao.updateFuncionario(fn);
    }
    
    public void delete(int idFormacion) throws SQLException {
        formacionDao.delete(idFormacion);
    }
}
