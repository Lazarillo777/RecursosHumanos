package com.example.app.employee;

import com.example.app.model.Employee;
import com.example.app.service.EmployeeService;
import com.example.app.service.LookupService;
import com.example.app.service.LookupService.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EmployeeController {
    @FXML
    private TextField idField;
    @FXML
    private ComboBox<Item> documentTypeCombo;
    @FXML
    private TextField documentNumberField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private ComboBox<Item> genderCombo;
    @FXML
    private ComboBox<Item> maritalCombo;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField emailField;
    @FXML
    private Button newBtn, saveBtn, updateBtn, deleteBtn;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchBtn;

    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, Long> colId;
    @FXML
    private TableColumn<Employee, String> colDocument;
    @FXML
    private TableColumn<Employee, String> colName;
    @FXML
    private TableColumn<Employee, String> colEmail;

    private final EmployeeService employeeService = new EmployeeService();
    private final LookupService lookupService = new LookupService();
    private final ObservableList<Employee> data = FXCollections.observableArrayList();

    public void initialize() {
        colId.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getId()));
        colDocument.setCellValueFactory(
                c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getDocumentNumber()));
        colName.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getFirstName() + " " + c.getValue().getLastName()));
        colEmail.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getEmail()));

        loadLookups();
        loadEmployees();

        tableView.setItems(data);
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null)
                populateForm(newV);
        });

        saveBtn.setOnAction(e -> saveEmployee());
        updateBtn.setOnAction(e -> updateEmployee());
        deleteBtn.setOnAction(e -> deleteEmployee());
        newBtn.setOnAction(e -> clearForm());
        searchBtn.setOnAction(e -> searchEmployees());
    }

    private void loadLookups() {
        documentTypeCombo.setItems(FXCollections.observableArrayList(lookupService.findAll("document_type")));
        genderCombo.setItems(FXCollections.observableArrayList(lookupService.findAll("gender")));
        maritalCombo.setItems(FXCollections.observableArrayList(lookupService.findAll("marital_status")));
    }

    private void loadEmployees() {
        data.clear();
        data.addAll(employeeService.findAll());
    }

    private void populateForm(Employee e) {
        idField.setText(String.valueOf(e.getId()));
        documentNumberField.setText(e.getDocumentNumber());
        firstNameField.setText(e.getFirstName());
        lastNameField.setText(e.getLastName());
        birthDatePicker.setValue(e.getBirthDate());
        addressField.setText(e.getAddress());
        cityField.setText(e.getCity());
        emailField.setText(e.getEmail());
        // lookups selection by id
        documentTypeCombo.getItems().stream()
                .filter(it -> it.getId() == (e.getDocumentTypeId() == null ? -1 : e.getDocumentTypeId())).findFirst()
                .ifPresent(documentTypeCombo::setValue);
        genderCombo.getItems().stream().filter(it -> it.getId() == (e.getGenderId() == null ? -1 : e.getGenderId()))
                .findFirst().ifPresent(genderCombo::setValue);
        maritalCombo.getItems().stream()
                .filter(it -> it.getId() == (e.getMaritalStatusId() == null ? -1 : e.getMaritalStatusId())).findFirst()
                .ifPresent(maritalCombo::setValue);
    }

    private void clearForm() {
        idField.clear();
        documentNumberField.clear();
        firstNameField.clear();
        lastNameField.clear();
        birthDatePicker.setValue(null);
        addressField.clear();
        cityField.clear();
        emailField.clear();
        documentTypeCombo.setValue(null);
        genderCombo.setValue(null);
        maritalCombo.setValue(null);
    }

    private Employee readForm() {
        Employee e = new Employee();
        if (!idField.getText().isEmpty())
            e.setId(Long.parseLong(idField.getText()));
        e.setDocumentNumber(documentNumberField.getText());
        e.setFirstName(firstNameField.getText());
        e.setLastName(lastNameField.getText());
        e.setAddress(addressField.getText());
        e.setCity(cityField.getText());
        e.setEmail(emailField.getText());
        if (documentTypeCombo.getValue() != null)
            e.setDocumentTypeId(documentTypeCombo.getValue().getId());
        if (genderCombo.getValue() != null)
            e.setGenderId(genderCombo.getValue().getId());
        if (maritalCombo.getValue() != null)
            e.setMaritalStatusId(maritalCombo.getValue().getId());
        e.setBirthDate(birthDatePicker.getValue());
        return e;
    }

    private void saveEmployee() {
        Employee e = readForm();
        employeeService.create(e);
        loadEmployees();
        clearForm();
    }

    private void updateEmployee() {
        Employee e = readForm();
        employeeService.update(e);
        loadEmployees();
    }

    private void deleteEmployee() {
        if (idField.getText().isEmpty())
            return;
        long id = Long.parseLong(idField.getText());
        employeeService.delete(id);
        loadEmployees();
        clearForm();
    }

    private void searchEmployees() {
        // simple search: find if name or document contains text
        String q = searchField.getText();
        if (q == null || q.isEmpty()) {
            loadEmployees();
            return;
        }
        data.clear();
        for (Employee e : employeeService.findAll()) {
            if ((e.getFirstName() + " " + e.getLastName()).toLowerCase().contains(q.toLowerCase())
                    || e.getDocumentNumber().toLowerCase().contains(q.toLowerCase())) {
                data.add(e);
            }
        }
    }
}
