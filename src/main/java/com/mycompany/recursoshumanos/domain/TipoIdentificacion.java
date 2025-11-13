
package com.mycompany.recursoshumanos.domain;

public class TipoIdentificacion {
    
    private int idTipoIdentificacion;
    private String nombreTipoId;

    public TipoIdentificacion() {
    }

    public TipoIdentificacion(int idTipoIdentificacion, String nombreTipoId) {
        this.idTipoIdentificacion = idTipoIdentificacion;
        this.nombreTipoId = nombreTipoId;
    }

    public int getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(int idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getNombreTipoId() {
        return nombreTipoId;
    }

    public void setNombreTipoId(String nombreTipoId) {
        this.nombreTipoId = nombreTipoId;
    }

    @Override
    public String toString() {
        return "TipoIdentificacion{" + "idTipoIdentificacion=" + idTipoIdentificacion + ", nombreTipoId=" + nombreTipoId + '}';
    }
    
    
    
}
