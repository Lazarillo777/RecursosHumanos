/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.recursoshumanos.View;

import com.mycompany.recursoshumanos.controller.FamiliarController;
import com.mycompany.recursoshumanos.controller.FormacionController;
import com.mycompany.recursoshumanos.controller.FuncionarioController;
import com.mycompany.recursoshumanos.domain.EstadoCivil;
import com.mycompany.recursoshumanos.domain.Formacion;
import com.mycompany.recursoshumanos.domain.Funcionario;
import com.mycompany.recursoshumanos.domain.GrupoFamiliar;
import com.mycompany.recursoshumanos.domain.NivelEstudio;
import com.mycompany.recursoshumanos.domain.Parentesco;
import com.mycompany.recursoshumanos.domain.Sexo;
import com.mycompany.recursoshumanos.domain.TipoIdentificacion;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author [Tu Nombre]
 */
public class RecursosMain extends javax.swing.JFrame {

    private final FuncionarioController funcionarioController;
    private final FamiliarController familiarController;
    private final FormacionController formacionController;
    private static final String[] COLUMNS = {
        "Nombres", "Apellidos", "Tipo de Identificación", "Número de Identificación",
        "Estado Civil", "Fecha Nacimiento", "Dirección", "Teléfono", "Sexo"
    };
    private static final String SELECCIONE = "-- SELECCIONE --";

    public RecursosMain() {
        initComponents();
        funcionarioController = new FuncionarioController();
        formacionController = new FormacionController();
        familiarController = new FamiliarController();
        listFuncionarios();
        //listFormacion(); // Asumiendo que estos métodos existen
        //listFamiliar();  // Asumiendo que estos métodos existen
        addListener();
    }

    public void listFuncionarios() {
        // Limpiar el combo box
        cbxFuncionarios.removeAllItems();
        // Agregar opción de selección
        Funcionario seleccionar = new Funcionario();
        seleccionar.setNombresFunc(SELECCIONE);
        cbxFuncionarios.addItem(seleccionar);

        // Configurar la tabla
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        for (String COLUMN : COLUMNS) {
            defaultTableModel.addColumn(COLUMN);
        }
        tblFuncionarios.setModel(defaultTableModel);

        try {
            List<Funcionario> funcionarios = funcionarioController.getFuncionarios();
            if (funcionarios.isEmpty()) {
                return;
            }

            // Limpiar la tabla antes de llenarla
            defaultTableModel.setRowCount(0);

            for (Funcionario funcionario : funcionarios) {
                Object[] row = {
                    funcionario.getNombresFunc(),
                    funcionario.getApellidosFunc(),
                    funcionario.getTipoIdentificacion(),
                    funcionario.getNumeroIdentificacion(),
                    funcionario.getEstadoCivil(),
                    funcionario.getFechaNacimientoFunc(),
                    funcionario.getDireccionFunc(),
                    funcionario.getTelefonoFunc(),
                    funcionario.getSexo()
                };
                defaultTableModel.addRow(row);
                cbxFuncionarios.addItem(funcionario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los funcionarios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addListener() {
        cbxFuncionarios.addItemListener(event -> {
            Funcionario selectedFuncionario = (Funcionario) event.getItem();
            if (selectedFuncionario.getNombresFunc().equals(SELECCIONE)) {
                // Limpiar campos
                txtNombres.setText("");
                txtApellidos.setText("");
                txtTipoIdentificacion.setText("");
                txtNumeroIdentificacion.setText("");
                txtEstadoCivil.setText("");
                txtFechaNacimiento.setText("");
                txtDireccion.setText("");
                txtTelefono.setText("");
                txtSexo.setText("");
            } else {
                // Llenar campos con los datos del funcionario seleccionado
                txtNombres.setText(selectedFuncionario.getNombresFunc());
                txtApellidos.setText(selectedFuncionario.getApellidosFunc());
                txtTipoIdentificacion.setText(selectedFuncionario.getTipoIdentificacion());
                txtNumeroIdentificacion.setText(selectedFuncionario.getNumeroIdentificacion());
                txtEstadoCivil.setText(selectedFuncionario.getEstadoCivil());
                txtFechaNacimiento.setText(selectedFuncionario.getFechaNacimientoFunc().toString());
                txtDireccion.setText(selectedFuncionario.getDireccionFunc());
                txtTelefono.setText(selectedFuncionario.getTelefonoFunc());
                txtSexo.setText(selectedFuncionario.getSexo());
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtIdFuncionario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTipoIdentificacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNumeroIdentificacion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtSexo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtEstadoCivil = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFuncionarios = new javax.swing.JTable();
        btnCrearFuncionario = new javax.swing.JButton();
        cbxFuncionarios = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtIdFuncionarioEdit = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtSexoEdit = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTipoIdentificacionEdit = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTelefonoEdit = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtNumeroIdentificacionEdit = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtFechaNacimientoEdit = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtNombresEdit = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtApellidosEdit = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtEstadoCivilEdit = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtDireccionEdit = new javax.swing.JTextField();
        btnActualizarFuncionario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Recursos Humanos App");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Recursos Humanos App");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(
    javax.swing.BorderFactory.createEtchedBorder(), 
    "Datos del Funcionario", 
    javax.swing.border.TitledBorder.LEFT, 
    javax.swing.border.TitledBorder.TOP));

        jLabel5.setText("ID:");

        txtIdFuncionario.setEditable(false);

        jLabel6.setText("Tipo de Identificación:");

        jLabel7.setText("Número de Identificación:");

        jLabel8.setText("Nombres:");

        jLabel9.setText("Apellidos:");

        jLabel10.setText("Sexo:");

        jLabel11.setText("Estado Civil:");

        jLabel12.setText("Dirección:");

        jLabel13.setText("Teléfono:");

        jLabel14.setText("Fecha Nacimiento (YYYY-MM-DD):");

        tblFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String[]{
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9"
            }
        ));
        jScrollPane2.setViewportView(tblFuncionarios);

        btnCrearFuncionario.setText("Crear Funcionario");
        btnCrearFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearFuncionarioActionPerformed(evt);
            }
        });

        // cbxFuncionarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        cbxFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFuncionariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNumeroIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCrearFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxFuncionarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNumeroIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCrearFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("CREAR FUNCIONARIO", jPanel2);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Actualizar Funcionario"));

        jLabel15.setText("ID:");

        txtIdFuncionarioEdit.setEditable(false);

        jLabel16.setText("Sexo:");

        jLabel17.setText("Tipo de Identificación:");

        jLabel18.setText("Teléfono:");

        jLabel19.setText("Número de Identificación:");

        jLabel20.setText("Fecha Nacimiento (YYYY-MM-DD):");

        jLabel21.setText("Nombres:");

        jLabel22.setText("Apellidos:");

        jLabel23.setText("Estado Civil:");

        jLabel24.setText("Dirección:");

        btnActualizarFuncionario.setText("Actualizar Funcionario");
        btnActualizarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarFuncionarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtIdFuncionarioEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtTipoIdentificacionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSexoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtNumeroIdentificacionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTelefonoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtDireccionEdit))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel22)
                                .addComponent(jLabel21)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(16, 16, 16)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(txtEstadoCivilEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel20)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtFechaNacimientoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtApellidosEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                        .addComponent(txtNombresEdit, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addComponent(btnActualizarFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtIdFuncionarioEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTipoIdentificacionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtSexoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtNumeroIdentificacionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txtTelefonoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtNombresEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txtFechaNacimientoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtApellidosEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtEstadoCivilEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtDireccionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizarFuncionario)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("ACTUALIZAR FUNCIONARIO", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void btnCrearFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtNombres.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite los Nombres del funcionario");
            txtNombres.requestFocus();
            return;
        }
        if (txtApellidos.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite los Apellidos del funcionario");
            txtApellidos.requestFocus();
            return;
        }
        if (txtTipoIdentificacion.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite el tipo de identificación del funcionario");
            txtTipoIdentificacion.requestFocus();
            return;
        }
        if (txtNumeroIdentificacion.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite el número de identificación del funcionario");
            txtNumeroIdentificacion.requestFocus();
            return;
        }
        if (txtFechaNacimiento.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite la fecha de nacimiento del funcionario (YYYY-MM-DD)");
            txtFechaNacimiento.requestFocus();
            return;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setNombresFunc(txtNombres.getText().trim());
        funcionario.setApellidosFunc(txtApellidos.getText().trim());
        funcionario.setTipoIdentificacion(txtTipoIdentificacion.getText().trim());
        funcionario.setNumeroIdentificacion(txtNumeroIdentificacion.getText().trim());
        funcionario.setEstadoCivil(txtEstadoCivil.getText().trim());
        funcionario.setSexo(txtSexo.getText().trim());
        funcionario.setDireccionFunc(txtDireccion.getText().trim());
        funcionario.setTelefonoFunc(txtTelefono.getText().trim());

        // Convertir la fecha de texto a LocalDate
        try {
            LocalDate fechaNac = LocalDate.parse(txtFechaNacimiento.getText().trim());
            funcionario.setFechaNacimientoFunc(fechaNac);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use YYYY-MM-DD");
            txtFechaNacimiento.requestFocus();
            return;
        }

        try {
            funcionarioController.create(funcionario);
            // Limpiar campos
            txtNombres.setText("");
            txtApellidos.setText("");
            txtTipoIdentificacion.setText("");
            txtNumeroIdentificacion.setText("");
            txtEstadoCivil.setText("");
            txtSexo.setText("");
            txtDireccion.setText("");
            txtTelefono.setText("");
            txtFechaNacimiento.setText("");

            // Actualizar lista
            listFuncionarios();
            JOptionPane.showMessageDialog(null, "Funcionario creado con éxito");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "El funcionario no pudo ser creado: " + ex.getMessage());
        }
    }

    private void btnActualizarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {
        // Verificar si hay un funcionario seleccionado en el combo box
        if (cbxFuncionarios.getSelectedItem() == null || 
            ((Funcionario) cbxFuncionarios.getSelectedItem()).getNombresFunc().equals(SELECCIONE)) {
            JOptionPane.showMessageDialog(null, "Seleccione un Funcionario de la lista");
            return;
        }

        // Obtener el funcionario seleccionado
        Funcionario selectedFuncionario = (Funcionario) cbxFuncionarios.getSelectedItem();

        // Validar campos
        if (txtNombresEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite los nombres del funcionario");
            txtNombresEdit.requestFocus();
            return;
        }
        if (txtApellidosEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite los apellidos del funcionario");
            txtApellidosEdit.requestFocus();
            return;
        }
        if (txtTipoIdentificacionEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite el tipo de identificación del funcionario");
            txtTipoIdentificacionEdit.requestFocus();
            return;
        }
        if (txtNumeroIdentificacionEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite el número de identificación del funcionario");
            txtNumeroIdentificacionEdit.requestFocus();
            return;
        }
        if (txtFechaNacimientoEdit.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Digite la fecha de nacimiento del funcionario (YYYY-MM-DD)");
            txtFechaNacimientoEdit.requestFocus();
            return;
        }

        // Confirmar actualización
        int opt = JOptionPane.showConfirmDialog(null, "¿Desea actualizar este funcionario?", "Confirmar actualización", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);                  
        
        if (opt == 0) {
            try {
                // Crear objeto funcionario con los nuevos datos
                Funcionario funcionarioActualizado = new Funcionario();
                funcionarioActualizado.setIdFuncionario(selectedFuncionario.getIdFuncionario());
                funcionarioActualizado.setNombresFunc(txtNombresEdit.getText().trim());
                funcionarioActualizado.setApellidosFunc(txtApellidosEdit.getText().trim());
                funcionarioActualizado.setTipoIdentificacion(txtTipoIdentificacionEdit.getText().trim());
                funcionarioActualizado.setNumeroIdentificacion(txtNumeroIdentificacionEdit.getText().trim());
                funcionarioActualizado.setEstadoCivil(txtEstadoCivilEdit.getText().trim());
                funcionarioActualizado.setSexo(txtSexoEdit.getText().trim());
                funcionarioActualizado.setDireccionFunc(txtDireccionEdit.getText().trim());
                funcionarioActualizado.setTelefonoFunc(txtTelefonoEdit.getText().trim());

                // Convertir la fecha de texto a LocalDate
                try {
                    LocalDate fechaNac = LocalDate.parse(txtFechaNacimientoEdit.getText().trim());
                    funcionarioActualizado.setFechaNacimientoFunc(fechaNac);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use YYYY-MM-DD");
                    txtFechaNacimientoEdit.requestFocus();
                    return;
                }

                // Actualizar en la base de datos
                funcionarioActualizado.setIdFuncionario(selectedFuncionario.getIdFuncionario());
                funcionarioController.updateFuncionario(funcionarioActualizado);
                
                // Limpiar campos de edición
                txtIdFuncionarioEdit.setText("");
                txtNombresEdit.setText("");
                txtApellidosEdit.setText("");
                txtTipoIdentificacionEdit.setText("");
                txtNumeroIdentificacionEdit.setText("");
                txtEstadoCivilEdit.setText("");
                txtSexoEdit.setText("");
                txtDireccionEdit.setText("");
                txtTelefonoEdit.setText("");
                txtFechaNacimientoEdit.setText("");

                // Actualizar lista
                listFuncionarios();
                JOptionPane.showMessageDialog(null, "Se ha actualizado el Funcionario con éxito");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "El Funcionario no pudo ser actualizado: " + ex.getMessage());
            }
        }
    }

    private void cbxFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {
        // Este método se ejecuta cuando se cambia la selección en el combo box
        // La lógica ya está manejada en el itemListener
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnActualizarFuncionario;
    private javax.swing.JButton btnCrearFuncionario;
    private javax.swing.JComboBox<Funcionario> cbxFuncionarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblFuncionarios;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtApellidosEdit;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDireccionEdit;
    private javax.swing.JTextField txtEstadoCivil;
    private javax.swing.JTextField txtEstadoCivilEdit;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtFechaNacimientoEdit;
    private javax.swing.JTextField txtIdFuncionario;
    private javax.swing.JTextField txtIdFuncionarioEdit;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNombresEdit;
    private javax.swing.JTextField txtNumeroIdentificacion;
    private javax.swing.JTextField txtNumeroIdentificacionEdit;
    private javax.swing.JTextField txtSexo;
    private javax.swing.JTextField txtSexoEdit;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoEdit;
    private javax.swing.JTextField txtTipoIdentificacion;
    private javax.swing.JTextField txtTipoIdentificacionEdit;
    // End of variables declaration
    
        public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new RecursosMain().setVisible(true);
        });
    }
}
