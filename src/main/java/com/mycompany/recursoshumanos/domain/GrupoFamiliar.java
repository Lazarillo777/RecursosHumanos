
package com.mycompany.recursoshumanos.domain;

import java.time.LocalDate;


public class GrupoFamiliar {
    
    
    private int idFamiliar;
    private int idFuncionario;
    private String cedFamiliar;
    private String tipoIdentificacion;
    private String nombresFam;
    private String apellidosFam;
    private String parentesco;
    private String estadoCivil; 
    private String sexo; 
    private String direccionFam;
    private String telefonoFam;
    private int idParentesco;
    private int idTipoId;
    private int idEstadoCivil;
    private int idSexo;
    private LocalDate fechaNacimientoFam;
         
        public GrupoFamiliar() {
    }

    public GrupoFamiliar(int idFamiliar, int idFuncionario, String cedFamiliar, String tipoIdentificacion, String nombresFam, String apellidosFam, String parentesco, String estadoCivil, String sexo, String direccionFam, String telefonoFam, int idParentesco, int idTipoId, int idEstadoCivil, int idSexo, LocalDate fechaNacimientoFam) {
        this.idFamiliar = idFamiliar;
        this.idFuncionario = idFuncionario;
        this.cedFamiliar = cedFamiliar;
        this.tipoIdentificacion = tipoIdentificacion;
        this.nombresFam = nombresFam;
        this.apellidosFam = apellidosFam;
        this.parentesco = parentesco;
        this.estadoCivil = estadoCivil;
        this.sexo = sexo;
        this.direccionFam = direccionFam;
        this.telefonoFam = telefonoFam;
        this.idParentesco = idParentesco;
        this.idTipoId = idTipoId;
        this.idEstadoCivil = idEstadoCivil;
        this.idSexo = idSexo;
        this.fechaNacimientoFam = fechaNacimientoFam;
    }

    public int getIdFamiliar() {
        return idFamiliar;
    }

    public void setIdFamiliar(int idFamiliar) {
        this.idFamiliar = idFamiliar;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getCedFamiliar() {
        return cedFamiliar;
    }

    public void setCedFamiliar(String cedFamiliar) {
        this.cedFamiliar = cedFamiliar;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNombresFam() {
        return nombresFam;
    }

    public void setNombresFam(String nombresFam) {
        this.nombresFam = nombresFam;
    }

    public String getApellidosFam() {
        return apellidosFam;
    }

    public void setApellidosFam(String apellidosFam) {
        this.apellidosFam = apellidosFam;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
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

    public String getDireccionFam() {
        return direccionFam;
    }

    public void setDireccionFam(String direccionFam) {
        this.direccionFam = direccionFam;
    }

    public String getTelefonoFam() {
        return telefonoFam;
    }

    public void setTelefonoFam(String telefonoFam) {
        this.telefonoFam = telefonoFam;
    }

    public int getIdParentesco() {
        return idParentesco;
    }

    public void setIdParentesco(int idParentesco) {
        this.idParentesco = idParentesco;
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

    public LocalDate getFechaNacimientoFam() {
        return fechaNacimientoFam;
    }

    public void setFechaNacimientoFam(LocalDate fechaNacimientoFam) {
        this.fechaNacimientoFam = fechaNacimientoFam;
    }

    @Override
    public String toString() {
        return "GrupoFamiliar{" + "idFamiliar=" + idFamiliar + ", idFuncionario=" + idFuncionario + ", cedFamiliar=" + cedFamiliar + ", tipoIdentificacion=" + tipoIdentificacion + ", nombresFam=" + nombresFam + ", apellidosFam=" + apellidosFam + ", parentesco=" + parentesco + ", estadoCivil=" + estadoCivil + ", sexo=" + sexo + ", direccionFam=" + direccionFam + ", telefonoFam=" + telefonoFam + ", idParentesco=" + idParentesco + ", idTipoId=" + idTipoId + ", idEstadoCivil=" + idEstadoCivil + ", idSexo=" + idSexo + ", fechaNacimientoFam=" + fechaNacimientoFam + '}';
    }

    
}
    