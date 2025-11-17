package com.example.app;

import javafx.fxml.FXML;

public class MainController {

    // Antes este controlador manejaba una lista de usuarios de ejemplo.
    // Ahora la vista principal delega en vistas específicas (EmployeeView) para
    // CRUD.
    @FXML
    public void initialize() {
        // No-op: el contenido principal se carga en FXML con controladores específicos.
    }

    // Métodos anteriores de ejemplo removidos; conserva `UserService` si lo
    // necesitas más adelante.
}
