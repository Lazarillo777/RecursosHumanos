package com.mycompany.recursoshumanos.domain;

public class Formacion {
        private int idFormacionAcadem;
	private int idFuncionario;
        private String nombresFunc;
        private String apellidosFunc;
	private String CedFuncionario;
	private String univerirsidad;
	private String nivelEstudio;
        private String nombreNivelEstudio;
	private String tituloObtenido;

    public Formacion() {
    }

    public Formacion(int idFormacionAcadem, int idFuncionario, String nombresFunc, String apellidosFunc, String CedFuncionario, String univerirsidad, String nivelEstudio, String nombreNivelEstudio, String tituloObtenido) {
        this.idFormacionAcadem = idFormacionAcadem;
        this.idFuncionario = idFuncionario;
        this.nombresFunc = nombresFunc;
        this.apellidosFunc = apellidosFunc;
        this.CedFuncionario = CedFuncionario;
        this.univerirsidad = univerirsidad;
        this.nivelEstudio = nivelEstudio;
        this.nombreNivelEstudio = nombreNivelEstudio;
        this.tituloObtenido = tituloObtenido;
    }

    public int getIdFormacionAcadem() {
        return idFormacionAcadem;
    }

    public void setIdFormacionAcadem(int idFormacionAcadem) {
        this.idFormacionAcadem = idFormacionAcadem;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
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

    public String getCedFuncionario() {
        return CedFuncionario;
    }

    public void setCedFuncionario(String CedFuncionario) {
        this.CedFuncionario = CedFuncionario;
    }

    public String getUniverirsidad() {
        return univerirsidad;
    }

    public void setUniverirsidad(String univerirsidad) {
        this.univerirsidad = univerirsidad;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public String getNombreNivelEstudio() {
        return nombreNivelEstudio;
    }

    public void setNombreNivelEstudio(String nombreNivelEstudio) {
        this.nombreNivelEstudio = nombreNivelEstudio;
    }

    public String getTituloObtenido() {
        return tituloObtenido;
    }

    public void setTituloObtenido(String tituloObtenido) {
        this.tituloObtenido = tituloObtenido;
    }

    
}
