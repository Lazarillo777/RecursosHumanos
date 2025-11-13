
package com.mycompany.recursoshumanos.Dao;

import com.mycompany.recursoshumanos.config.ConnectionConfig;
import com.mycompany.recursoshumanos.domain.Formacion;
import com.mycompany.recursoshumanos.domain.Funcionario;
import com.mycompany.recursoshumanos.domain.GrupoFamiliar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FormacionDao {
    //Consultas SQL
    
    private static final String GET_FORMACION = 
            "SELECT \n" +
            "    formacion_academica.idFormacionAcadem,\n" +
            "    formacion_academica.idFuncionario,\n" +
            "    funcionarios.cedFuncionario,\n" +
            "    funcionarios.nombresFunc,\n" +
            "    funcionarios.apellidosFunc,\n" +
            "    formacion_academica.CedFuncionario AS cedFamiliar,\n" +
            "    formacion_academica.universidad,\n" +
            "    formacion_academica.idNivelEstudio,\n" +
            "    formacion_academica.tituloObtenido\n" +
            "FROM formacion_academica \n" +
            "LEFT JOIN funcionarios \n" +
            "    ON formacion_academica.idFuncionario = funcionarios.idFuncionario;";
    
    private static final String CREATE_FORMACION = 
            "INSERT INTO formacion_academica (idFuncionario, CedFuncionario, universidad, nivelEstudio, tituloObtenido) "
            +"VALUES (?, ?, ?, ?, ?)";
    
    private static final String GET_FORMACION_BY_ID = 
            "SELECT funcionarios.*, formacion_academica.nombreNivelEstudio AS nombreNivelEstudio " +
            "FROM funcionarios " +
            "LEFT JOIN formacion_academica ON funcionarios.idFuncionario = formacion_academica.idFuncionario " +
            "WHERE funcionario.idFuncionario = ?";
         
    private static final String UPDATE_FORMACION = 
            "UPDATE funcionarios SET cedFuncionario = ?, "
            + "tipoIdentificacion = ?, nombresFunc = ?, apellidosFunc = ?, estadoCivil = ?, sexo = ?, "
            + "direccionFunc = ?, telefonoFunc = ?, fechaNacimientoFunc = ? WHERE idFuncionario = ?";   
    
    private static final String DELETE_FORMACION =
        "DELETE FROM funcionarios WHERE idFuncionario = ?";
    
    // MÉTODO: OBTENER TODOS LOS FUNCIONARIOS
    
    public List<Formacion> getFormacionAcademica() throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;
        List<Formacion> formacion_academica = new ArrayList<>();
        
        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(GET_FORMACION);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Formacion fn = new Formacion();
                fn.setIdFormacionAcadem(resultSet.getInt("idForamcionAcademica"));
                fn.setIdFormacionAcadem(resultSet.getInt("idFuncionario"));
                fn.setCedFuncionario("cedFuncionario");
                fn.setNombresFunc(resultSet.getString("nombresFunc"));
                fn.setApellidosFunc(resultSet.getString("apellidosFuncionario"));
                fn.setIdFormacionAcadem(resultSet.getInt("idNivelEstudio"));
                fn.setNivelEstudio(resultSet.getString("nivelEstudio"));
                fn.setNombreNivelEstudio(resultSet.getString("nombreNivelEstudio"));
                fn.setUniverirsidad(resultSet.getString("universidad"));
                fn.setTituloObtenido(resultSet.getString("tituloObtenido"));
                
                formacion_academica.add(fn); // agrega a la lista correctamente
            }
            return formacion_academica;
        }finally {
            if(connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }
    
       
    public void create(Formacion fn) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_FORMACION);
            preparedStatement.setString(1, fn.getCedFuncionario());
            preparedStatement.setString(2, fn.getNombresFunc());
            preparedStatement.setString(3, fn.getApellidosFunc());
            preparedStatement.setString(4, fn.getNivelEstudio());
            preparedStatement.setString(5, fn.getTituloObtenido());
            preparedStatement.setString(6, fn.getUniverirsidad());
            preparedStatement.executeUpdate();
            
            
        } finally {
            
            if (connection != null) {
                connection.close();
            }
                       
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
    }
    public Formacion getFormacionAcademica(int idFormacionAcademica) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Formacion fn = null;
        
        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(GET_FORMACION_BY_ID);
            preparedStatement.setInt(1, idFormacionAcademica);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                fn = new Formacion();
                fn.setIdFormacionAcadem(resultSet.getInt("idForamcionAcademica"));
                fn.setIdFormacionAcadem(resultSet.getInt("idFuncionario"));
                fn.setCedFuncionario("cedFuncionario");
                fn.setNombresFunc(resultSet.getString("nombresFunc"));
                fn.setApellidosFunc(resultSet.getString("apellidosFuncionario"));
                fn.setIdFormacionAcadem(resultSet.getInt("idNivelEstudio"));
                fn.setNivelEstudio(resultSet.getString("nivelEstudio"));
                fn.setNombreNivelEstudio(resultSet.getString("nombreNivelEstudio"));
                fn.setUniverirsidad(resultSet.getString("universidad"));
                fn.setTituloObtenido(resultSet.getString("tituloObtenido"));
            }
            
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return fn;
    }
    public void updateFuncionario(Formacion fn) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_FORMACION);
            preparedStatement.setString(1, fn.getCedFuncionario());
            preparedStatement.setString(2, fn.getNombresFunc());
            preparedStatement.setString(3, fn.getApellidosFunc());
            preparedStatement.setString(4, fn.getNivelEstudio());
            preparedStatement.setString(5, fn.getTituloObtenido());
            preparedStatement.setString(6, fn.getUniverirsidad());
            preparedStatement.executeUpdate();
            
            
        } finally {
            
            if (connection != null) {
                connection.close();
            }
                       
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
    }
    
    public void delete(int idFormacion) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_FORMACION);
            preparedStatement.setInt(1, idFormacion);
            preparedStatement.executeUpdate();
                       
        } finally {
            
            if (connection != null) {
                connection.close();
            }
                       
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
    }
               
}


