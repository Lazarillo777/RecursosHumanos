package com.mycompany.recursoshumanos.View;

import com.mycompany.recursoshumanos.controller.FamiliarController;
import com.mycompany.recursoshumanos.controller.FormacionController;
import com.mycompany.recursoshumanos.controller.FuncionarioController;
import com.mycompany.recursoshumanos.domain.Formacion;
import com.mycompany.recursoshumanos.domain.Funcionario;
import com.mycompany.recursoshumanos.domain.GrupoFamiliar;
import java.sql.SQLException;
import java.util.List;

public class RecursosHumanos {
    
    public static void getFuncionarios(FuncionarioController funcionariosController){
        try {
            List<Funcionario> funcionarios = funcionariosController.getFuncionarios();
            if (funcionarios.isEmpty()){
                System.out.println("No hay datos");
            }else{
                funcionarios.forEach(funcionario -> {
                    System.out.println("id: "+ funcionario.getIdFuncionario());
                    System.out.println("Número identificacion: "+ funcionario.getNumeroIdentificacion());
                    System.out.println("Tipo de identificación: "+ funcionario.getTipoIdentificacion());
                    System.out.println("Nombres: "+ funcionario.getNombresFunc());
                    System.out.println("Apellidos: "+ funcionario.getApellidosFunc());
                    System.out.println("Sexo: "+ funcionario.getSexo());
                    System.out.println("Estado Civil: "+ funcionario.getEstadoCivil());
                    System.out.println("Dirección: "+ funcionario.getDireccionFunc());
                    System.out.println("Teléfono: " + funcionario.getTelefonoFunc());
                    System.out.println("Fecha de Nacimiento: " + funcionario.getFechaNacimientoFunc());
                                      
                    System.out.println("**********************************************");
                    System.out.println("**********************************************");
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
       
    public static void getFamiliares(FamiliarController fc){
        try {
            List<GrupoFamiliar> familiares = fc.getFamiliares();
            if (familiares.isEmpty()){
                System.out.println("No hay datos");
            }else{
                familiares.forEach(grupoFamiliar -> {
                    System.out.println("id: "+ grupoFamiliar.getIdFamiliar());
                    System.out.println("idFunc: "+ grupoFamiliar.getIdFuncionario());
                    System.out.println("Cédula: "+ grupoFamiliar.getCedFamiliar());
                    System.out.println("Tipo de identificación: "+ grupoFamiliar.getTipoIdentificacion());
                    System.out.println("Nombres: "+ grupoFamiliar.getNombresFam());
                    System.out.println("Parentesco: " + grupoFamiliar.getParentesco());
                    System.out.println("Apellidos: "+ grupoFamiliar.getApellidosFam());
                    System.out.println("Sexo: "+ grupoFamiliar.getSexo());
                    System.out.println("Estado Civil: "+ grupoFamiliar.getEstadoCivil());
                    System.out.println("Dirección: "+ grupoFamiliar.getDireccionFam());
                    System.out.println("Teléfono: " + grupoFamiliar.getTelefonoFam());
                    System.out.println("Fecha de Nacimiento: " + grupoFamiliar.getFechaNacimientoFam());
                                      
                    System.out.println("**********************************************");
                    System.out.println("**********************************************");
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
      
    public static void main(String[] args) {
        
        // Inicializar ambos controladores y llamar a ambos métodos desde el único main
        
        System.out.println("--- LISTADO DE FUNCIONARIOS ---");
        FuncionarioController funcionariosController = new FuncionarioController();
        getFuncionarios(funcionariosController);

        System.out.println("\n--- LISTADO DE GRUPO FAMILIAR ---");
        FamiliarController familiarController = new FamiliarController();
        getFamiliares(familiarController);
    }

}
