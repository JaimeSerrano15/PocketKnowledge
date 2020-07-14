# Empresaurios. Pocket Knowledge App
Pocket Knowledge es una aplicación donde puedes repasar contenidos vistos en tus clases de secundaria, referidos a las materias de Matemáticas, Sociales, Ciencias y Lenguaje, dónde encontrarás diversas infografías acerca de varios temas que dichas materias contemplan.
Pocket Knowledge utiliza un sistema de materias, las cuales se dividen en tres niveles generales: Básico, Intermedio y Avanzado, los cuales cada uno posee diferentes lecciones con respecto a su nivel de dificultad o comprensión de sus respectivos temas. A su vez, cada lección cuenta con una pequeña pregunta al final de la misma relacionada con los temas tratados en las infografías.

## Instalación y Prueba en Ambiente Controlado
Para poder poder agregar nuevas funciones, corregir errores o simplemente probar la aplicación en un ambiente fuera del ámbito de producción, se deben de tener las siguientes consideraciones:
- Tener instalado el Entorno de Desarrollo Integrado (IDE): Android Studio (La última versión actual).
  - Aquí se podrá podrá modificar, eliminar y/o agregar el código con mucha más facilidad, además de poder examinar dependencias y otros recursos utilizados en el desarrallo de la app.
  - Como mínimo, se deben de tener las siguientes SDK Developer Tools instaladas en el IDE:
    - Android SDK Build-Tools
    - NDK (Side by side)
    - CMake
    - Android Emulator
    - Android SDK Platform-Tools
    - Android SDK Tools
- Tener instalado Git en tu ordenador para poder clonar el repositorio del proyecto y abrir el mismo con el Entorno de Desarrollo antes mencionado.

```bash
git clone "aqui falta" 
```
"Pocket Knowledge" ha sido construido en base a un SDK Mínimo al nivel del API 19: Android 4.4 (KitKat) llegando así a un 98.1% de distribución. Además, se hizo uso de múltiples librerias y recursos, adicionales a los que Android Studio brinda por defecto, para poder trabajar de una manera más eficiente las funcionalidades implementadas:
- Dependencias, Librerias y Servicios:
  - Navigation Component
  - JustifiedTextView
  - Lifecycle: ViewModel Component
  - PhotoView
  - PDF Viewer
  - Glide
  - Firebase Tools:
    - Authentication
    - Firestore
- Usuario de Prueba: Para poder acceder (Tanto en un ambiente de prueba controlado como en produccin), es posible utilizar el siguiente Usuario (Correo) y Contraseña:
  - Correo Electrónico (Usuario) : prueba@gmail.com
  - Contraseña: prueba123

## Prueba en Producción:
Si quiere disfrutar de todo lo que ofrece "Pocket Knowledge", puede descargar la app a través de la Play Store siguiendo el siguiente enlace:
- https://play.google.com/store/apps/details?id=com.jaiser.pocketknowledgeapp
