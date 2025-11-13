
package com.mycompany.recursoshumanos.domain;

public class EstadoCivil {
    
    private int idEstadocivil;
    private String nombreEstadoCivil;

    public EstadoCivil() {
    }

    public EstadoCivil(int idEstadocivil, String nombreEstadoCivil) {
        this.idEstadocivil = idEstadocivil;
        this.nombreEstadoCivil = nombreEstadoCivil;
    }

    public int getIdEstadocivil() {
        return idEstadocivil;
    }

    public void setIdEstadocivil(int idEstadocivil) {
        this.idEstadocivil = idEstadocivil;
    }

    public String getNombreEstadoCivil() {
        return nombreEstadoCivil;
    }

    public void setNombreEstadoCivil(String nombreEstadoCivil) {
        this.nombreEstadoCivil = nombreEstadoCivil;
    }

    @Override
    public String toString() {
        return "EstadoCivil{" + "idEstadocivil=" + idEstadocivil + ", nombreEstadoCivil=" + nombreEstadoCivil + '}';
    }
    
       
}

