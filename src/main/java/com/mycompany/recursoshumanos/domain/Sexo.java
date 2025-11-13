
package com.mycompany.recursoshumanos.domain;

public class Sexo {
    
    private int idSexo;
    private String nombreSexo;

    public Sexo() {
    }

    public Sexo(int idSexo, String nombreSexo) {
        this.idSexo = idSexo;
        this.nombreSexo = nombreSexo;
    }

    public int getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(int idSexo) {
        this.idSexo = idSexo;
    }

    public String getNombreSexo() {
        return nombreSexo;
    }

    public void setNombreSexo(String nombreSexo) {
        this.nombreSexo = nombreSexo;
    }

    @Override
    public String toString() {
        return "Sexo{" + "idSexo=" + idSexo + ", nombreSexo=" + nombreSexo + '}';
    }
    
    
}
