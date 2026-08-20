# 📦 Sistema de Inventarios

Aplicación web full-stack para la gestión de inventarios, desarrollada con **Spring Boot** (backend REST) y **Angular** (frontend SPA). Permite listar, agregar, editar y eliminar productos, cada uno con descripción, precio y existencias.

---

## 🚀 Funcionalidades

- **Listar** todos los productos del inventario
- **Agregar** un nuevo producto mediante un formulario reactivo con validaciones
- **Editar** un producto existente, precargando sus datos actuales
- **Eliminar** un producto del inventario
- Validación de reglas de negocio tanto en frontend como en backend (existencias no negativas)
- Manejo centralizado y estructurado de errores HTTP

---

## 🏗️ Arquitectura

Este proyecto separa completamente el backend del frontend, comunicándose exclusivamente mediante una **API REST** en formato JSON.

```
┌─────────────────────────┐        HTTP / JSON        ┌──────────────────────────┐
│   Frontend (Angular)     │  ────────────────────▶   │   Backend (Spring Boot)  │
│   http://localhost:4200  │  ◀────────────────────   │   http://localhost:8080  │
└─────────────────────────┘                            └──────────────────────────┘
```

Ambos proyectos viven en el mismo repositorio (monorepo):

```
inventario/
├── backend/     👈 proyecto Spring Boot (abrir en IntelliJ)
└── frontend/    👈 proyecto Angular (abrir en VS Code)
```

### Capas del backend

```
Controlador REST  →  Servicio  →  Repositorio  →  Base de Datos (MySQL)
   (@RestController)  (@Service)   (JpaRepository)
```

### Estructura del frontend

```
Componentes (vistas)  →  Servicios (HttpClient)  →  API REST del backend
```

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión | Rol |
|---|---|---|
| Java | 21 (LTS) | Lenguaje del backend |
| Spring Boot | 4.1.0 | Framework backend / API REST |
| Spring Data JPA | 4.1.0 | Acceso a datos |
| MySQL | 8.x | Base de datos |
| Lombok | — | Reducción de código boilerplate |
| Angular | 21 | Framework frontend (SPA) |
| Angular Router | — | Navegación entre vistas sin recargar |
| Reactive Forms | — | Formularios con validación |
| Bootstrap | 5.3.8 | Estilos y componentes UI |
| Node.js | 24.x | Entorno de ejecución de Angular CLI |

---

## 📦 Modelo de datos

Un **Producto** se compone de:

| Campo | Tipo (Java) | Tipo (TypeScript) | Descripción |
|---|---|---|---|
| `idProducto` | Integer | `number` (opcional) | Identificador único (autogenerado) |
| `descripcion` | String | `string` | Descripción del producto |
| `precio` | BigDecimal | `number` | Precio unitario (sin decimales negativos) |
| `existencias` | Integer | `number` | Cantidad disponible en stock (≥ 0) |

> `precio` usa `BigDecimal` en el backend para evitar errores de precisión decimal propios de `double`/`float` en operaciones financieras.

---

## 📁 Estructura del proyecto

```
backend/src/main/java/gm/inventario/
├── controlador/
│   └── ProductoControlador.java
├── modelo/
│   └── Producto.java
├── repositorio/
│   └── ProductoRepositorio.java
├── servicio/
│   ├── IProductoServicio.java
│   └── ProductoServicio.java
└── excepcion/
    ├── ExistenciasInvalidasExcepcion.java
    ├── ProductoNoEncontradoExcepcion.java
    ├── ErrorRespuesta.java
    └── ManejadorExcepciones.java

frontend/src/app/
├── producto.ts                    (interface Producto)
├── producto.service.ts            (HttpClient)
├── producto-lista/                (listado + editar/eliminar)
├── agregar-producto/              (formulario de alta)
├── editar-producto/                (formulario de edición)
├── app.routes.ts                  (rutas)
└── app.ts / app.html              (shell + navbar)
```

---

## ⚙️ Requisitos previos

- [Java JDK 21+](https://www.oracle.com/java/technologies/downloads/) (LTS)
- [Maven 3+](https://maven.apache.org/download.cgi)
- [MySQL 8+](https://dev.mysql.com/downloads/)
- [Node.js 20+](https://nodejs.org/) y [Angular CLI](https://angular.dev/tools/cli)
- IntelliJ IDEA (backend) y VS Code (frontend), o los IDEs de tu preferencia

---

## 🔧 Configuración y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/inventario.git
cd inventario
```

### 2. Backend — Configurar variables de entorno

| Variable | Descripción | Ejemplo |
|---|---|---|
| `URL` | URL de conexión a MySQL | `jdbc:mysql://localhost:3306/inventario_db?createDatabaseIfNotExist=true` |
| `NAME` | Usuario de MySQL | `root` |
| `PASSWORD` | Contraseña de MySQL | `tu_password` |

**En IntelliJ:** `Run > Edit Configurations > Environment Variables`

### 3. Backend — Ejecutar

```bash
cd backend
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080/api/productos`.

### 4. Frontend — Instalar dependencias y ejecutar

```bash
cd frontend
npm install
ng serve -o
```

La aplicación se abre automáticamente en `http://localhost:4200`.

> El backend tiene configurado `@CrossOrigin(value = "http://localhost:4200")` para permitir peticiones desde Angular en desarrollo.

---

## 📌 Endpoints de la API

| Método | Ruta | Descripción | Código éxito | Código error |
|---|---|---|---|---|
| GET | `/api/productos/` | Lista todos los productos | 200 | — |
| GET | `/api/productos/{id}` | Obtiene un producto por id | 200 | 404 si no existe |
| POST | `/api/productos/` | Crea un nuevo producto | 200 | 400 si existencias < 0 |
| PUT | `/api/productos/{id}` | Actualiza un producto existente | 200 | 404 si no existe |
| DELETE | `/api/productos/{id}` | Elimina un producto | 200 | 404 si no existe |

### Formato de error

```json
{
  "mensaje": "Las Existencias no pueden ser negativas",
  "fecha": "2026-08-04T10:15:30"
}
```

---

## 🧭 Rutas del frontend (Angular Router)

| Ruta | Componente | Descripción |
|---|---|---|
| `/productos` | `ProductoLista` | Listado principal con acciones |
| `/agregar-producto` | `AgregarProducto` | Formulario de alta |
| `/editar-producto/:id` | `EditarProducto` | Formulario de edición precargado |
| `/` | — | Redirige a `/productos` |
