/*
╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
║ ┌───────────────────────────────────────────────────────────────────────────────────────┬────────────────────────────────────────────────────────────────────────┐ ║
║ │ - Fecha de publicacion:                                                    31/03/2023 │                                                                        │ ║
║ │ - Hora:                                                                         07:00 │                                                                        │ ║
║ │ - Version del codigo:                                                           1.0.0 │                                                                        │ ║
║ │ - Autor:                                                 Ing(c) Santiago Torifa Manso │                                                                        │ ║
║ │ - Nombre del lenguaje utilizado:                                                    C │                                                                        │ ║
║ │ - Version del lenguaje utilizado:                                                 C17 │                           CREDITOS DEL CODIGO                          │ ║
║ │ - Versión del compilador utilizado:                                        gcc 12.2.0 │                                                                        │ ║
║ │ - Sistema Operativo sobre el que corre el programa    Windows 11 Home Single Language │                                                                        │ ║
║ │ - Presentado a:                                         Doctor Ricardo Moreno Laverde │                                                                        │ ║
║ ├───────────────────────────────────────────────────────────────────────────────────────┤                                                                        │ ║
║ │ - Universidad Tecnológica de Pereira                                                  │                                                                        │ ║
║ │ - Programa de Ingenieria de Sistemas y Computacion                                    │                                                                        │ ║
║ ├───────────────────────────────────────────────────────────────────────────────────────┴────────────────────────────────────────────────────────────────────────┤ ║
║ │                                                                                                                                                                │ ║
║ │ Enunciado: Se quiere escribir un programa que permita detectar aspectos en la imagen que pueden ser indicadores de una enfermedad.                             │ ║
║ │            Como no tenemos los datos que generaría la máquina de “Resonancia magnética”,                                                                       │ ║
║ │            genere usted el arreglo con datos aleatorios entre [0 y 255] para probar sus funciones.                                                             │ ║
║ │                                                                                                                                                                │ ║
║ └────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘ ║
╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝*/

// libreria necesarias para el funcionamiento del programa
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/* Funcion para imprimir la linea del inicio y final de la tabla
int contador => se usara para saber cuantas veces se va a imprimir cierto char */
void tablaInicioyFinal(int contador, int iteracion, int control){
  // condicion para saber si se imprime la primear parte de la tabla o la ultima
  if (control == 0){
    // condicion para terminar el ciclo
    while (contador >= 0){
      printf("%c", 196);
      contador--; 
    }

    // impresion de caracteres especiales mas ciclo recursivo
    if (iteracion == -1){
      printf("%c", 218);
      tablaInicioyFinal(23, 0, 0);
    }else if(iteracion == 0){
      printf("%c", 194);
      tablaInicioyFinal(33, 1, 0);
    }else if(iteracion == 1){
      printf("%c", 194);
      tablaInicioyFinal(35, 2, 0);
    }else if(iteracion == 2){
      printf("%c\n", 191);
    }
  }else{
    // condicion para terminar el ciclo
    while (contador >= 0){
      printf("%c", 196);
      contador--; 
    }

    // impresion de caracteres especiales mas ciclo recursivo
    if (iteracion == -1){
      printf("%c", 192);
      tablaInicioyFinal(23, 0, 1);
    }else if(iteracion == 0){
      printf("%c", 193);
      tablaInicioyFinal(33, 1, 1);
    }else if(iteracion == 1){
      printf("%c", 193);
      tablaInicioyFinal(35, 2, 1);
    }else if(iteracion == 2){
      printf("%c\n", 217);
    }
  }
}

// Funcion para imprimir las lineas centrales de la tabla
void lineasCentrales(int contador, int iteracion){
  // condicion para terminar el ciclo
  while (contador >= 0){
    printf("%c", 196);
    contador--; 
  }

  // impresion de caracteres especiales mas ciclo recursivo
  if (iteracion == -1){
    printf("%c", 195);
    lineasCentrales(23, 0);
  }else if(iteracion == 0){
    printf("%c", 197);
    lineasCentrales(33, 1);
  }else if(iteracion == 1){
    printf("%c", 197);
    lineasCentrales(35, 2);
  }else if(iteracion == 2){
    printf("%c\n", 180);
  }

}

/* Funcion para la iteracion de cada plano en busca de puntos sospechosos
int datos => trae los datos de la resonancia
int x => coordenadsa en x
int y => coordenadsa en y
int z => coordenadsa en z                                                        */
void puntosTabla(int datos[100][100][100], int x, int y, int z){
  // variable que servira como contador de cada plano en busca de lineas sospechosas
  int puntosTotales = 0;
  // Bucle para recorrer el arreglo por filas y columnas en busca de contar puntos sospechosos
  // para coordenadas en y
  for (int i = 1; i < 99; i++){
    // para coordenadas en x
    for (int j = 1; j < 99; j++){
      // condicion para saber si en el momento del ciclo existe algun punto sospechoso
      if (puntoSospechoso(datos, j, i, z)){
        // incremento al total de puntos sospechosos
        puntosTotales++;
      }
    }
  }

  // condicional para saber cuantos espacios imprimir despues del numero de lineas
  if (puntosTotales < 10){
    // imprecion con tres espacios extra
    printf("                %i                   ", puntosTotales);
  }else if(puntosTotales < 100){
    // impresion con dos espacio extra
    printf("                %i                  ", puntosTotales);
  }else if(puntosTotales < 1000){
    // impresion con un extra
    printf("                %i                 ", puntosTotales);
  }else if(puntosTotales < 10000){
    // impresion sin espacios extra
    printf("                %i                ", puntosTotales);
  }
}

/* Funcion para la iteracion de cada plano en busca de lineas sospechosas
int datos => trae los datos de la resonancia
int x => coordenadsa en x
int y => coordenadsa en y
int z => coordenadsa en z                                                         */
void lineasTabla(int datos[100][100][100], int x, int y, int z){
  // variable que servira como contador para el total de lineas sospechosas
  int lineasTotales = 0;
  // Bucle para recorrer el arreglo por filas en busca de contar lineas sospechosas
  // para coordenadas en y
  for (int i = 1; i < 97; i++){
    // para coordenadas en x
    for (int j = 1; j < 97; j++){
      // condicion para saber si en el momento del ciclo existe alguna linea sospechosa
      if (lineaSospechosa(datos, j, i, z)){
        // variable que se usara para saber que tan larga es la linea sospechosa
        int longitudLinea = lineaSospechosa(datos, j, i, z);
        // incremento al total de lineas sospechosas
        lineasTotales++;
        // incremento para el eje x por el tamaño de la linea sospechosa
        j += longitudLinea;
      }
    }
  }
  
  // condicional para saber cuantos espacios imprimir despues del numero de lineas
  if (lineasTotales < 10){
    // imprecion con dos espacios extra
    printf("                %i                 %c", lineasTotales, 179);
  }else if(lineasTotales < 100){
    // impresion con un espacio extra
    printf("                %i                %c", lineasTotales, 179);
  }else if(lineasTotales < 1000){
    // impresion sin espacios extra
    printf("                %i               %c", lineasTotales, 179);
  }
}

/* Funcion encargada de inicializar la creacion de la tabla que mostrara todos los datos de la resonancia
int datos => trae los datos de la resonancia
int x => coordenadsa en x
int y => coordenadsa en y
int z => coordenadsa en z                                                         */
void tablaResonancia(int datos[100][100][100], int x, int y, int z){
  // Impresion de la parte superior de la tabla de datos de la resonancia
  tablaInicioyFinal(-1, -1, 0);
  printf("%c Plano en Z(Fotogradia) %c Lineas sospechosas x plano(foto) %c Puntos sospechosos por plano(foto) %c\n", 179, 179, 179, 179);
  lineasCentrales(-1, -1);
  while (z < 99){
    // condicion para imprimir la primera columna de manera correcta
    if (z < 10){
      // impresion del numero de plano con espacio extra
      printf("%c           %i            %c", 179, z, 179);
      // impresion de las lineas sospechosas
      lineasTabla(datos, x, y, z);
      // impresion de los puntos sospechosos
      puntosTabla(datos, x, y, z);
      // salto de linea de la fila
      printf("%c\n", 179);
    }else{
      // impresion del numero de plano sin espacio extra
      printf("%c           %i           %c", 179, z, 179);
      // impresion de las lineas sospechosas
      lineasTabla(datos, x, y, z);
      // impresion de los puntos sospechosos
      puntosTabla(datos, x, y, z);
      // salto de linea de la fila
      printf("%c\n", 179);
    }

    // filas centrales entre los datos y fila final
    if (z != 98){
      lineasCentrales(-1, -1);
    }else{
      tablaInicioyFinal(-1, -1, 1);
    }

    // incremento de z
    z++;
  }

}

// Funcion que se encarga de imprimir la barra superior e inferior que delimita la hoja de la resonancia
void barrasSuperiorInferior(){
  // condicion para imprimir todas las barras ascii
  for (int l = 0; l < 100; l++){
    printf("%c", 196);
  }
}

/* Funcion encargada de imprimir el numero de cada fila del plano de la resonancia escogido por el usuario
int x => corresponde al numero de la fila                                                                           */
void imprimirNumeroFila(int x){
  // condicion para imprimir el numero de la fila
  if (x <= 9){
    // imprimir un numero de fila menos a 10
    printf("%i %c", x, 179);
  }else{
    // imprimir un numero de fila mayor a 9
    printf("%i%c", x, 179);
  }
}

/* Funcion encargada de mostrar de forma clara un plano de la resonancia
int datos => arreglo de la resonancia que recibira las coordenadas (x, y, z)
int plano => arreglo que se modificara para ser mostrado por pantalla
int x => coordenadas en x
int y => coordenadas en y
int z => coordenadas en z                                                                                        */
void vistaPlanoZ(int datos[100][100][100], int plano[100][100], int z){
  // funcion encargada de rellenar el plano nuevo con las lineas sospechosas
  modificarPlano(datos, plano, z);

  // Se muestra el numero de plano que se eligio mostrar por pantalla
  printf("Numero de plano elegido: %i\n\n    ", z);

  // Ciclo for para imprimir el valor de las decenas para las columnas del plano
  for (int i = 0; i < 90; i){
    // Imprimimos el valor de las decenas
    printf("         %i", i / 10 + 1);
    // Incremento de la iteracion for
    i += 10;
  }

  // salto de linea para ajustar la impresion del plano
  printf("\n   ");
  // Imprimimos el valor de las columnas
  for (int i = 0; i < 10; i++){
    printf("0123456789");
  }

  // Impresion de la linea superior que separa los datos de la resonancia con el numero de filas y columnas
  printf("\n  %c", 218);
  barrasSuperiorInferior();
  printf("%c\n", 191);

  // Ciclo for encargado de imprimir cada fila del plano escogido de la resonancia
  for (int y = 0; y < 100; y++){
    // funcion para imprimir el numero de la fila
    imprimirNumeroFila(y);
    // ciclo encargado de imprimir una hoja de la resonancia con los puntos sospechosos
    for (int x = 0; x < 100; x++){
      // Condicion para saber que debe imprimir
      if (plano[x][y] == 1){
        printf("%c", 219);
      }else{
        printf("%c", 32);
      }
    }
    printf("%c\n", 179);
  }

  // Impresion de la linea inferior que separa los datos de la resonancia con el numero de filas y columnas
  printf("  %c", 192);
  barrasSuperiorInferior();
  printf("%c\n", 217);
}

/* Funcion encargada de modificar un arreglo correspondiente al plano que el usuario desea ver en la consola
int datos => arreglo de la resonancia que recibira las coordenadas (x, y, z)
int plano => arreglo que se modificara para ser mostrado por pantalla
int x => coordenadas en x
int y => coordenadas en y
int z => coordenadas en z                                                                                        */
int modificarPlano(int datos[100][100][100], int plano[100][100], int z){
  // Bucle para recorre el arreglo por filas en busca de mapear lineas sospechosas
  // para coordenadas en y
  for (int i = 1; i < 97; i++){
    // para coordenadas en x
    for (int j = 1; j < 97; j++){
      // condicion para saber si en el momento del bucle existe alguna liena sospechosa
      if (lineaSospechosa(datos, j, i, z)){
        // variable que se usara para saber que tan larga es la linea sospechosa
        int longitudLinea = lineaSospechosa(datos, j, i, z);
        // Bucle para crear la linea sospechosa
        for (int h = longitudLinea; h >= 0; h--){
          // creando la linea sospechosa
          plano[j + h][i] = 1;
        }
        // incremento para el eje x por el tamaño de la linea sospechosa
        j += longitudLinea;
      }
    }
  }
}

/* Funcion encargada de encontrar una linea sospechosa y devolver su longitud total
int datos => arreglo de la resonancia que recibira las coordenadas (x, y, z)
int x => coordenadas en x
int y => coordenadas en y
int z => coordenadas en z                                                                                        */
int lineaSospechosa(int datos[100][100][100], int x, int y, int z){
  // variable que funcioan como contador para saber si existe linea sospechosa y obtener su longitud
  int contador = 0;

  // Bucle para recorrer la fila en busca de una linea sospechosa
  for (int i = 0; i + x < 98; i++){
    // condicion para preguntar si el punto de la posicion actual es sospechoso
    if (puntoSospechoso(datos, i + x, y, z)){
      // incremento al contador
      contador++;
    }else{
      // condicion para saber si es una linea sospechosa o no
      if (contador >= 3){
        // incremento al contador para terminar el bucle
        i = 100;
      }else{
        // incremento al contador para terminar el bucle
        i = 100;
        // reseteo el valor del contador para devolver falso
        contador = 0;
      }
    }
  }

  // devuelo la longitud de la linea sospechosa
  return contador;
}

/* Funcion encargada de devolver si el punto esccogido por las coordenadas en (x, y, z) es un punto sospechosos
int datos => arreglo de la resonancia que recibira las coordenadas (x, y, z)
int x => coordenadas en x
int y => coordenadas en y
int z => coordenadas en z                                                                                        */
int puntoSospechoso(int datos[100][100][100], int x, int y, int z){
  // variable que funciona como contador para saber si el punto analizado es sospechoso
  int contador = 0;

  // Bucle anidado para recorrer las pociones que estan alrededor del punto que se esta analizando
  // para coordenadas en z
  for (int i = -1; i <= 1; i++){
    // para coordenadas en y
    for (int j = -1; j <= 1; j++){
      // para coordenadas en x
      for (int k = -1; k <= 1; k++){
        // condicional para no tener en cuenta el punto principal
        if (!(i == 0 && j == 0 && k == 0)){
          // condicion para saber si se le debe sumar al contador
          if (datos[x + k][y + j][z + i] >= 20 && datos[x + k][y + j][z + i] <= 40){
            // se le incrementa uno al contador
            contador++;
          }
        }
      }
    }
  }

  // condicional para retornar verdadero o falso dependiendo de si el punto es sospechoso o no
  if (contador == 26){
    // retornamos verdadero
    return 1;
  }else{
    // retornamos falso
    return 0;
  }
}

// Funcion de origen
int main(){

  // estableciendo la semilla para generar numeros seudo-aleatorios.
  srand(time(NULL));

  // ! Declaracion del arreglo de la resonancia magnetica
  static int resonancia[100][100][100];

  // rellenando el arreglo de la resonancia con numeros aleatorios entre 20 y 42
  // recorriendo plano en el eje z
  for (int i = 0; i < 100; i++){
    // recorriendo plano en el eje y
    for (int j = 0; j < 100; j++){
      // recorriendo plano en el eje x
      for (int k = 0; k < 100; k++){
        // rellenando con los numeros correspondientes
        resonancia[k][j][i] = (rand() % 21) + 23; // TODO => por favor poner lo siguiente si desea que existan valores en la tabla y en la imagen [ (rand() % 21) + 22 ]
      }
    }
  }

  // declarando un arreglo de dos dimensiones para hacer la impresion del plano que el usuario solicite
  int planoZ[100][100];
  // rellenando el arreglo del plano en Z con ceros
  // recorriendo plano en el eje y
  for (int i = 0; i < 100; i++){
    // recorriendo plano en el eje x
    for (int j = 0; j < 100; j++){
      // rellenando con ceros
      planoZ[j][i] = 0;
    }
  }
  
  // primera parte del programa
  printf("Por favor ingrese el plano que desea ver: ");
  int numeroZ; // variable para asignar el plano que se desea buscar
  scanf("%i", &numeroZ); // asignamiento de la variable
  vistaPlanoZ(resonancia, planoZ, numeroZ); // llamado de la funcion para generar el plano elegido

  printf("\n\n\n"); // espaciado para cada parte del programa

  // segundoa parte
  tablaResonancia(resonancia, 1, 1, 1);

  int cierre;
  scanf("%i", &cierre);

  return 0;
}