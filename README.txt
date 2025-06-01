
# Tarea 2 - Aplicación de Pruebas en Java

Este proyecto consiste en una aplicación de escritorio desarrollada en Java que permite aplicar una prueba compuesta por opción múltiple y verdadero/falso, categorizados según la taxonomía de Bloom.

##Funcionalidades

- Carga de preguntas desde archivo `.txt`
- Interfaz gráfica con navegación entre ítems
- Registro y revisión de respuestas del usuario
- Cálculo de estadísticas finales por:
  - Tipo de ítem (Opción Múltiple / Verdadero-Falso)
  - Nivel de la taxonomía de Bloom
-Modo de revisión: muestra si cada respuesta fue correcta

## Estructura del proyecto

Tarea2/
├── backend/
│   ├── Item.java
│   ├── GestorItems.java
│   └── RespuestaUsuario.java
├── frontend/
│   ├── VentanaInicio.java
│   ├── VentanaPregunta.java
│   ├── VentanaResumen.java
│   └── VentanaRevision.java
├── Main.java
└── items.txt

## Formato del archivo `items.txt`

Cada línea representa una pregunta con el siguiente formato:

ID|TIPO|NIVEL_BLOOM|TIEMPO|ENUNCIADO|RESPUESTA_CORRECTA|ALTERNATIVAS

- `TIPO`: `OPCION_MULTIPLE` o `VERDADERO_FALSO`
- `NIVEL_BLOOM`: `RECORDAR`, `ENTENDER`, `APLICAR`, `ANALIZAR`, `EVALUAR`, `CREAR`
- `ALTERNATIVAS`: separadas por `;` (solo en preguntas de opción múltiple)

Ejemplo:

1|OPCION_MULTIPLE|RECORDAR|30|¿Cuál es la salida de System.out.println("Hola")?|Hola|Hola;Adiós;Error;Nada
2|VERDADERO_FALSO|ENTENDER|45|Java es un lenguaje compilado|Verdadero|Verdadero;Falso

##Como compilar y ejecutar

Desde terminal (CMD o Git Bash):

    javac backend/*.java frontend/*.java Main.java
    java Main

##Desde VS Code:

1. Instala la extensión Java Extension Pack
2. Abre la carpeta del proyecto
3. Haz clic derecho sobre `Main.java` → Run Java

## Requisitos

- Java JDK 8 o superior
- VS Code

## Autor

Guillermo Diaz
Isidora Vargas Arellano
github.com/Gmo-1 
github.com/Isidora-Arellano 
Tarea para la asignatura de Paradigmas de Programación  
Ingeniería en Informática UNAB 
