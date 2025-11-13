package com.mycompany.recursoshumanos.domain;

import java.time.LocalDate;

public class Funcionario {
       
    private int idFuncionario;
    private String numeroIdentificacion;
    private String tipoIdentificacion;
    private String nombresFunc;
    private String apellidosFunc;
    private String estadoCivil;
    private String sexo;
    private String direccionFunc;
    private String telefonoFunc;
    private int idTipoId;
    private int idEstadoCivil;
    private int idSexo;
    private LocalDate fechaNacimientoFunc;

    public Funcionario() {
    }

    public Funcionario(int idFuncionario, String numeroIdentificacion, String tipoIdentificacion, String nombresFunc, String apellidosFunc, String estadoCivil, String sexo, String direccionFunc, String telefonoFunc, int idTipoId, int idEstadoCivil, int idSexo, LocalDate fechaNacimientoFunc) {
        this.idFuncionario = idFuncionario;
        this.numeroIdentificacion = numeroIdentificacion;
        this.tipoIdentificacion = tipoIdentificacion;
        this.nombresFunc = nombresFunc;
        this.apellidosFunc = apellidosFunc;
        this.estadoCivil = estadoCivil;
        this.sexo = sexo;
        this.direccionFunc = direccionFunc;
        this.telefonoFunc = telefonoFunc;
        this.idTipoId = idTipoId;
        this.idEstadoCivil = idEstadoCivil;
        this.idSexo = idSexo;
        this.fechaNacimientoFunc = fechaNacimientoFunc;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNombresFunc() {
        return nombresFunc;
    }

    public void setNombresFunc(String nombresFunc) {
        this.nombresFunc = nombresFunc;
    }

    public String getApellidosFunc() {
        return apellidosFunc;
    }

    public void setApellidosFunc(String apellidosFunc) {
        this.apellidosFunc = apellidosFunc;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccionFunc() {
        return direccionFunc;
    }

    public void setDireccionFunc(String direccionFunc) {
        this.direccionFunc = direccionFunc;
    }

    public String getTelefonoFunc() {
        return telefonoFunc;
    }

    public void setTelefonoFunc(String telefonoFunc) {
        this.telefonoFunc = telefonoFunc;
    }

    public int getIdTipoId() {
        return idTipoId;
    }

    public void setIdTipoId(int idTipoId) {
        this.idTipoId = idTipoId;
    }

    public int getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(int idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public int getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(int idSexo) {
        this.idSexo = idSexo;
    }

    public LocalDate getFechaNacimientoFunc() {
        return fechaNacimientoFunc;
    }

    public void setFechaNacimientoFunc(LocalDate fechaNacimientoFunc) {
        this.fechaNacimientoFunc = fechaNacimientoFunc;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "idFuncionario=" + idFuncionario + ", numeroIdentificacion=" + numeroIdentificacion + ", tipoIdentificacion=" + tipoIdentificacion + ", nombresFunc=" + nombresFunc + ", apellidosFunc=" + apellidosFunc + ", estadoCivil=" + estadoCivil + ", sexo=" + sexo + ", direccionFunc=" + direccionFunc + ", telefonoFunc=" + telefonoFunc + ", idTipoId=" + idTipoId + ", idEstadoCivil=" + idEstadoCivil + ", idSexo=" + idSexo + ", fechaNacimientoFunc=" + fechaNacimientoFunc + '}';
    }

    
    
    }
