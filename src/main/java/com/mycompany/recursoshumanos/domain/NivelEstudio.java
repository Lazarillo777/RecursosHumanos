package com.mycompany.recursoshumanos.domain;


public class NivelEstudio {
    private int idNivelEstudio;
    private String nombreNivelEstudio;

    public NivelEstudio() {
    }

    public NivelEstudio(int idNivelEstudio, String nombreNivelEstudio) {
        this.idNivelEstudio = idNivelEstudio;
        this.nombreNivelEstudio = nombreNivelEstudio;
    }

    public int getIdNivelEstudio() {
        return idNivelEstudio;
    }

    public void setIdNivelEstudio(int idNivelEstudio) {
        this.idNivelEstudio = idNivelEstudio;
    }

    public String getNombreNivelEstudio() {
        return nombreNivelEstudio;
    }

    public void setNombreNivelEstudio(String nombreNivelEstudio) {
        this.nombreNivelEstudio = nombreNivelEstudio;
    }

    @Override
    public String toString() {
        return "NivelEstudio{" + "idNivelEstudio=" + idNivelEstudio + ", nombreNivelEstudio=" + nombreNivelEstudio + '}';
    }
    
    
}
