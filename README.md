# 🛒 Sistema de Gestión de Tienda  
Aplicación de consola desarrollada en **Java**, diseñada para administrar productos, clientes y ventas dentro de una tienda.  
Permite persistir toda la información en archivos planos (`.txt`) durante y después de la ejecución.

---

## 📌 Características principales

### ✔ Gestión de Productos
- Agregar productos de 3 tipos:
  - Producto de Alimento
  - Producto Electrónico
  - Producto de Ropa
- Cargar productos desde archivo (`Producto.txt`)
- Actualizar stock automáticamente después de cada venta  
- Mostrar y buscar productos por ID

### ✔ Gestión de Clientes
- Registrar nuevos clientes
- Cargar clientes desde archivo (`Cliente.txt`)
- Mantener historial de compras **en memoria**
- Historial reconstruible desde `Venta.txt`

### ✔ Gestión de Ventas
- Asociar ventas a un cliente
- Cada venta contiene múltiples ítems
- Guardar ventas en archivo (`Venta.txt`)
- Añadir venta al historial del cliente
- Calcular totales automáticamente

---

## 🏗️ Arquitectura del Sistema

### 🧱 Clases principales

**App**  
Clase central. Maneja menús, carga de archivos, operaciones y flujo general.

**Producto (abstracto)**  
Clase base para diferentes tipos de productos:
- `ProductoAlimento`
- `ProductoElectrico`
- `ProductoRopa`

**Cliente**  
Almacena información básica y su historial de compras.

**Venta**  
Contiene los productos comprados, cliente asociado, fecha y total.

**Items**  
Representa un producto dentro de una venta, con cantidad y subtotal.

---

## 📄 Persistencia de Datos

Toda la información se almacena en archivos `.txt`.

### 🧾 Producto.txt  
**Formato:**
PA,ID,NOMBRE,PRECIO,STOCK,DISPONIBLE,FECHA_VENC
PE,ID,NOMBRE,PRECIO,STOCK,DISPONIBLE,GARANTIA
PR,ID,NOMBRE,PRECIO,STOCK,DISPONIBLE,TALLA,COLOR


### 🧾 Cliente.txt  
**Formato:**
ID,NOMBRE,EMAIL


### 🧾 Venta.txt  
**Formato:**
IDVENTA,IDCLIENTE,IDPROD-CANT,IDPROD2-CANT,...,TOTAL,FECHA


## ▶️ Cómo Ejecutar

1️⃣ Compilar  
```bash
javac App.java
```

2️⃣ Ejecutar  
```bash
java App
```

3️⃣ Asegúrate de tener estos archivos en /BaseDatos/  
```
Producto.txt
Cliente.txt
Venta.txt
```

---

## 👨‍💻 Autor
Carlos David Leguia Gomez  
Sistema de gestión de tienda en Java.

---

## 📜 Licencia
Este proyecto puede ser utilizado libremente con fines educativos.