
package com.mycompany.recursoshumanos.domain;

public class Parentesco {
    
    private int idParentesco;
    private String nombreParentesco;

    public Parentesco() {
    }

    public Parentesco(int idParentesco, String nombreParentesco) {
        this.idParentesco = idParentesco;
        this.nombreParentesco = nombreParentesco;
    }

    public int getIdParentesco() {
        return idParentesco;
    }

    public void setIdParentesco(int idParentesco) {
        this.idParentesco = idParentesco;
    }

    public String getNombreParentesco() {
        return nombreParentesco;
    }

    public void setNombreParentesco(String nombreParentesco) {
        this.nombreParentesco = nombreParentesco;
    }

    @Override
    public String toString() {
        return "Parentesco{" + "idParentesco=" + idParentesco + ", nombreParentesco=" + nombreParentesco + '}';
    }
    
    
}
