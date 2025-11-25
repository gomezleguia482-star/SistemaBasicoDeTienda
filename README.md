# 🛍️ Sistema Básico de Gestión de Tienda

Este proyecto es una aplicación Java sencilla, construida con Maven, que simula la estructura fundamental de un sistema de gestión de tienda.

## 🧱 Arquitectura

El proyecto sigue una arquitectura por capas para separar responsabilidades:

1.  **`CapaDeModelos`**: Define las entidades de negocio (Articulo, Cliente, Producto, Venta).
2.  **`CapaDePersistencia`**: Contiene las clases DAO (Data Access Object) para manejar la lectura y escritura de datos.
3.  **`CapaDeServicios`**: Implementa la lógica de negocio y las interacciones entre modelos y persistencia.

## 💻 Tecnologías Utilizadas

* **Lenguaje:** Java
* **Gestor de Dependencias:** Maven (Configurado en `pom.xml`)
* **IDE:** IntelliJ IDEA (Recomendado)

## 🚀 Cómo Ejecutar el Proyecto

Sigue estos pasos para poner en marcha el sistema:

1.  **Clonar el Repositorio:**
    ```bash
    git clone [https://github.com/gomezleguia482-star/SistemaBasicoDeTienda.git](https://github.com/gomezleguia482-star/SistemaBasicoDeTienda.git)
    ```
2.  **Abrir en el IDE:**
    * Abre IntelliJ IDEA.
    * Selecciona `File` -> `Open` y elige la carpeta `SistemaBasicoDeTienda`.
3.  **Compilar y Ejecutar:**
    * Asegúrate de que Maven resuelva todas las dependencias.
    * Ejecuta la clase principal: `org.example.Main`.

## 📜 Licencia

Este proyecto está bajo la Licencia **MIT**. Consulta el archivo [LICENSE](LICENSE) para más detalles.