# Recurso Humano - IUDIgital

Proyecto de aplicación de escritorio para el área de Recursos Humanos de la Institución Universitaria Digital de
Antioquia (IUDIgital).

Estructura importante:

- `src/main/java/com/example/app` - código fuente
- `src/main/resources/com/example/app/MainView.fxml` - vista FXML
- `pom.xml` - dependencias y plugin JavaFX

Ejecución (necesitas Maven y JDK 17+):

```powershell
cd 'c:\Luis Toro\IUDigital\ingenieria_web1\RH_JAVA\javafx-desktop-skeleton'
mvn clean javafx:run
```

Esto inicializa la base de datos `app.db` en el directorio del proyecto.

Descripción del dominio:

- Gestión de funcionarios: datos personales (tipo identificación, número, nombres, apellidos, estado civil, sexo,
  dirección, teléfono, fecha de nacimiento, correo, etc.)
- Grupo familiar de cada funcionario: datos de cada miembro y rol (conyuge, hijo, padre, etc.)
- Formación académica: universidad, nivel de estudio, título obtenido, año, campo de estudio.

El directorio `resources/sql/schema.sql` contiene el script SQL para crear las tablas normalizadas y `docs/er.puml`
contiene el diagrama MER en PlantUML. Para pruebas locales la base SQLite será inicializada desde
`src/main/resources/sql/schema_sqlite.sql`. Puedes ejecutar la app y usar la pestaña "Funcionarios" para probar CRUD
sobre la tabla `employee`.

Operaciones soportadas en la UI:

- Listado de funcionarios (Table)
- Búsqueda por documento o nombre (campo de búsqueda)
- Crear nuevo funcionario (botón Nuevo + campos + Guardar)
- Actualizar funcionario (selecciona en la tabla, modifica, Actualizar)
- Eliminar funcionario (selecciona en la tabla, Eliminar)

La aplicación usa por defecto SQLite para pruebas locales. El script para MySQL está en
`src/main/resources/sql/schema.sql`.

Si deseas utilizar MySQL en lugar de SQLite, actualiza `Database.getConnection()` con la URL de tu servidor y ejecuta el
script MySQL `schema.sql` (por ejemplo con `mysql -u root -p < schema.sql`).
