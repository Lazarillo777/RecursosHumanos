
package com.mycompany.recursoshumanos.Dao;

import com.mycompany.recursoshumanos.config.ConnectionConfig;
import com.mycompany.recursoshumanos.domain.EstadoCivil;
import com.mycompany.recursoshumanos.domain.Funcionario;
import com.mycompany.recursoshumanos.domain.GrupoFamiliar;
import com.mycompany.recursoshumanos.domain.Sexo;
import com.mycompany.recursoshumanos.domain.TipoIdentificacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class FuncionariosDao {
    
    //Consultas SQL
    
    private static final String GET_FUNCIONARIO = 
            "SELECT f.*, tipoIdentificacion.nombreTipoId AS idTipoId, "
            + "estadoCivil.nombreEstadoCivil AS idEstadoCivil, "
            + "sexo.nombreSexo AS idSexo "
            + "FROM funcionarios f "
            + "LEFT JOIN tipoIdentificacion ON f.idTipoId = tipoIdentificacion.idTipoId "
            + "LEFT JOIN estadoCivil ON f.idEstadoCivil = estadoCivil.idEstadoCivil "
            + "LEFT JOIN sexo ON f.idSexo = sexo.idSexo ";
    
    private static final String CREATE_FUNCIONARIO = 
            "INSERT INTO funcionarios (cedFuncionario, tipoIdentificacion, nombresFunc, apellidosFunc, "
            + "estadoCivil, sexoFunc, direccionFunc, telefonoFunc, fechaNacimientoFunc) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    
    private static final String GET_FUNCIONARIO_BY_ID = 
            "SELECT f.*, familiares.nombresFam "
            + "FROM funcionarios f "
            + "LEFT JOIN familiares ON f.idFuncionario = familiares.idFuncionario "
            + "WHERE f.idFuncionario = ?";
         
    private static final String UPDATE_FUNCIONARIO = 
            "UPDATE funcionarios SET cedFuncionario = ?, "
            + "tipoIdentificacion = ?, nombresFunc = ?, apellidosFunc = ?, estadoCivil = ?, sexo = ?, "
            + "direccionFunc = ?, telefonoFunc = ?, fechaNacimientoFunc = ? WHERE idFuncionario = ?";   
    
    private static final String DELETE_FUNCIONARIO =
        "DELETE FROM funcionarios WHERE idFuncionario = ?";
    
    // MÉTODO: OBTENER TODOS LOS FUNCIONARIOS
    
    public List<Funcionario> getFuncionarios() throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        
        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(GET_FUNCIONARIO);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Funcionario f = new Funcionario();
                f.setIdFuncionario(resultSet.getInt("idFuncionario"));
                //f.setNumeroIdentificacion(resultSet.getString("NumeroIdentificacion"));
                f.setTipoIdentificacion(resultSet.getString("idTipoId"));
                f.setNombresFunc(resultSet.getString("nombresFunc"));
                f.setApellidosFunc(resultSet.getString("apellidosFunc"));
                f.setEstadoCivil(resultSet.getString("idEstadoCivil"));
                f.setSexo(resultSet.getString("idSexo"));
                f.setDireccionFunc(resultSet.getString("direccionFunc"));
                f.setTelefonoFunc(resultSet.getString("telefonoFunc"));
                f.setFechaNacimientoFunc(resultSet.getDate("fechaNacimientoFunc").toLocalDate());

                funcionarios.add(f); // agrega a la lista correctamente
            }
            return funcionarios;
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
    
       
    public void create(Funcionario f) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_FUNCIONARIO);
            preparedStatement.setString(1, f.getNumeroIdentificacion());
            preparedStatement.setInt(2, f.getIdTipoId()); 
            preparedStatement.setString(3, f.getNombresFunc());
            preparedStatement.setString(4, f.getApellidosFunc());
            //preparedStatement.setInt(5, f.getIdEstadoCivil()); 
            preparedStatement.setString(5, f.getEstadoCivil()); 
            preparedStatement.setString(6, f.getSexo()); 
            preparedStatement.setString(7, f.getDireccionFunc());
            preparedStatement.setString(8, f.getTelefonoFunc());
            preparedStatement.setDate(9, java.sql.Date.valueOf(f.getFechaNacimientoFunc()));
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
    public  Funcionario getFuncionarios(int idFuncionario) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Funcionario f = null;
        
        
        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(GET_FUNCIONARIO_BY_ID);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                f.setIdFuncionario(resultSet.getInt("idFuncionario"));
                f.setNumeroIdentificacion(resultSet.getString("NumeroIdentificacion"));
                f.setTipoIdentificacion(resultSet.getString("idTipoId"));
                f.setNombresFunc(resultSet.getString("nombresFunc"));
                f.setApellidosFunc(resultSet.getString("apellidosFunc"));
                f.setEstadoCivil(resultSet.getString("idEstadoCivil"));
                f.setSexo(resultSet.getString("idSexo"));
                f.setDireccionFunc(resultSet.getString("direccionFunc"));
                f.setTelefonoFunc(resultSet.getString("telefonoFunc"));
                f.setFechaNacimientoFunc(resultSet.getDate("fechaNacimientoFunc").toLocalDate());
               
            }
            return f;
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
    public void updateFuncionario(Funcionario f) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_FUNCIONARIO);
            preparedStatement.setString(1, f.getNumeroIdentificacion());
            preparedStatement.setInt(2, f.getIdTipoId()); 
            preparedStatement.setString(3, f.getNombresFunc());
            preparedStatement.setString(4, f.getApellidosFunc());
            preparedStatement.setInt(5, f.getIdEstadoCivil()); 
            preparedStatement.setString(6, f.getSexo()); 
            preparedStatement.setString(7, f.getDireccionFunc());
            preparedStatement.setString(8, f.getTelefonoFunc());
            preparedStatement.setDate(9, java.sql.Date.valueOf(f.getFechaNacimientoFunc()));
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
    
    public void delete(int idFuncionario) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_FUNCIONARIO);
            preparedStatement.setInt(1, idFuncionario);
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

