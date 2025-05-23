# ✈️ Airport

**Proyecto elaborado por:**  
Sebastián Carbonó  
Karoll Márquez  

**NRC:** 2250  

---

## 📌 Descripción

**Airport** es una aplicación orientada a objetos que simula la gestión básica de un aeropuerto. Permite registrar, actualizar y consultar información sobre aviones, vuelos y pasajeros, siguiendo buenas prácticas de arquitectura de software.

Este proyecto **aplica el patrón de diseño MVC (Modelo-Vista-Controlador)** y los **principios SOLID**, lo que permite una estructura limpia, mantenible y escalable.

---

## 🧩 Estructura del Proyecto

El código está organizado según el patrón **MVC**:

- **Modelos (`airport.core.models`)**  
  Contienen las clases que representan la lógica de negocio, como `Plane`, `Passenger`, `Flight`, `Location`.

- **Controladores (`airport.core.controllers`)**  
  Manejan la lógica de control, validación y coordinación entre vistas y modelos.

- **Vistas (`airport.core.views`)**  
  Componentes de interacción con el usuario, responsables de la entrada/salida de datos.

- **Utilidades y almacenamiento**  
  - `airport.core.models.storage`: almacenamiento temporal en memoria.  
  - `airport.core.controllers.utils`: manejo de respuestas y estados (`Response`, `Status`).

---

## ⚙️ Funcionalidades principales

- Registro y edición de pasajeros
- Creación y gestión de aviones
- Asociación de vuelos a aviones
- Validación de formatos (como ID de avión `XXYYYYY`)
- Almacenamiento en estructuras en memoria

---

## 💻 Requisitos

- Java 17 o superior  
- NetBeans IDE 25  
- JDK correctamente configurado

---

## 🏁 Cómo ejecutar

1. Abre el proyecto en NetBeans.
2. Marca `Airport` como proyecto principal.
3. Ejecuta la clase principal o alguna vista/controlador de prueba.

---

## 📄 Licencia

Este proyecto fue desarrollado con fines académicos.  
Todos los derechos reservados a sus autores.