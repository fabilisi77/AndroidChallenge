# AndroidChallenge

Esta es una aplicación de Android que permite a los usuarios buscar razas de gatos y ver imágenes de alta resolución de la raza seleccionada. La aplicación utiliza varias tecnologías como Glide, Retrofit, Navigation, Safe Args, ViewBinding, MVVM, ViewModel y LiveData.

## Características

- Muestra imágenes de gatos de manera aleatoria al iniciar la aplicación.
- Permite a los usuarios buscar gatos por raza utilizando un SearchView.
- Al hacer clic en una imagen, se muestra un segundo fragmento con la imagen en alta resolución.

- ## Tecnologías Utilizadas

- Glide: Biblioteca de carga y visualización de imágenes.
- Retrofit: Biblioteca para realizar solicitudes HTTP y consumir una API REST.
- Navigation: Componente de Android Jetpack para la navegación entre fragmentos.
- Safe Args: Biblioteca de Android Jetpack que permite pasar argumentos de manera segura entre destinos de navegación.
- ViewBinding: Genera clases de enlace para vistas, lo que facilita el acceso a los elementos de la interfaz de usuario.
- MVVM (Model-View-ViewModel): Patrón de diseño arquitectónico utilizado para separar la lógica de presentación de la interfaz de usuario.
- ViewModel y LiveData: Componentes de Android Jetpack que permiten la comunicación y persistencia de datos entre fragmentos y actividades.

## Uso

1. Ejecuta la aplicación en un dispositivo o emulador Android.
2. En la pantalla principal, se mostrarán imágenes de gatos de manera aleatoria.
3. Utiliza el SearchView para buscar gatos por raza. Escribe el nombre de la raza deseada y presiona Enter.
4. Se mostrarán los resultados de la búsqueda en la lista.
5. Haz clic en una imagen de gato para verla en alta resolución en el segundo fragmento.
