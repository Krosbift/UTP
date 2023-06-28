/*
╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
║ ┌───────────────────────────────────────────────────────────────────────────────────────┬────────────────────────────────────────────────────────────────────────┐ ║
║ │ - Fecha de publicacion:                                                    17/04/2023 │                                                                        │ ║
║ │ - Hora:                                                                         09:00 │                                                                        │ ║
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
║ │ descripcion: Este programa se encarga de ayudar a encontrar los parametros ingresados de un archivo encriptado, ademas de servir como herramienta de           │ ║
║ │              desemcriptamiento y decifrador.                                                                                                                   │ ║
║ │                                                                                                                                                                │ ║
║ └────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘ ║
╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝*/

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

// Prototipos de funcion
void viewFileChars();
void viewFileInts();
void viewAllFileChars();
void viewWithKeyword();
void decrypForDisplacement();
void decryptForKeyword();
int menuOptions();

/* Esta funcion se encargara de mostrar el archivo que se desea por caracteres */
void viewFileChars() {
  // variable que almacenara el caracter que se leera cada vez en el archivo seleccionado
  unsigned char caracter = 0;

  // variable que servira para guardar el nombre del archivo a visualizar y mensaje de estado
  printf("Digite el nombre del archivo que desea leer: ");
  char string[25] = "";
  scanf("%s", &string);

  // accediendo al archivo desde este programa
  FILE *archivo = NULL;
  archivo = fopen(string, "r");

  // if que sirve como filtro para saber si se pudo acceder al archivo o no
  if (archivo == NULL){
    // error de apertura del archivo
    printf("No se pudo acceder al archivo...\n Presione cualquier tecla para continuar\n");
    getchar();
  } else {
    // variable que se usara para saber cuando termina el archivo ya que no nos podemos fiar en el eof y bucle de lectura de caracteres
    printf("Ingrese el numero de bytes que pesa el archivo a visualizar: ");
    int bytes = 0;
    scanf("%i", &bytes);

    // muestra el archivo seleccionado
    printf("\n");
    printf("\t\tContenido visual por caracteres del archivo seleccionado\n");
    for (int i = 0; i < 170; i++) printf("%c", 196);
    printf("\n");
    
    while (bytes >= 0){
      // obtenemos el caracter actual y lo mostramos por la consola
      caracter = fgetc(archivo);
      printf("%c", caracter);
      bytes --;
    }
    
    // fin de lectura del archivo
    printf("\n");
    for (int i = 0; i < 170; i++) printf("%c", 196);
    printf("\n");
    printf("\t\tPresione cualquier tecla para continuar\n");
    getchar();
  }

  // fin de ejecucion de la funcion
  fclose(archivo);
  getchar();
}

/* Esta funcion se encargara de mostrar el archivo que se desea por el numero entero que le corresponde a cada char */
void viewFileInts() {
  // variable que almacenara el caracter que se leera cada vez en el archivo seleccionado
  unsigned char caracter = 0;

  // variable que servira para guardar el nombre del archivo a visualizar y mensaje de estado
  printf("Digite el nombre del archivo que desea leer: ");
  char string[25] = "";
  scanf("%s", &string);

  // accediendo al archivo desde este programa
  FILE *archivo = NULL;
  archivo = fopen(string, "r");

  // if que sirve como filtro para saber si se pudo acceder al archivo o no
  if (archivo == NULL){
    // error de apertura del archivo
    printf("No se pudo acceder al archivo...\n Presione cualquier tecla para continuar\n");
    getchar();
  } else {
    // variable que se usara para saber cuando termina el archivo ya que no nos podemos fiar en el eof y bucle de lectura de caracteres
    printf("Ingrese el numero de bytes que pesa el archivo a visualizar: ");
    int bytes = 0;
    scanf("%i", &bytes);

    // muestra el archivo seleccionado
    printf("\n");
    printf("\t\tContenido visual por caracteres del archivo seleccionado\n");
    for (int i = 0; i < 170; i++) printf("%c", 196);
    printf("\n");
    
    while (bytes > 0){
      // obtenemos el caracter actual y lo mostramos por la consola como un entero y dejamos un espacio para poder diferenciar entre cada par de enteros
      caracter = fgetc(archivo);
      printf("%i ", caracter);
      bytes --;
    }
    
    // fin de lectura del archivo
    printf("\n");
    for (int i = 0; i < 170; i++) printf("%c", 196);
    printf("\n");
    printf("\t\tPresione cualquier tecla para continuar\n");
    getchar();
  }

  // fin de ejecucion de la funcion
  fclose(archivo);
  getchar();
}

/* Esta funcion se encargara de mostrar el archivo que se desea por caracteres en todas las posibilidades de desplazamiento que tenga */
void viewAllFileChars() {
  // variable que almacenara el caracter que se leera cada vez en el archivo seleccionado
  unsigned char caracter = 0;

  // variable para cerra el ciclo for en caso de ya haber encontrado una coincidencia
  int endSearch = 0;

  // variable que servira para guardar el nombre del archivo a visualizar y mensaje de estado
  printf("Digite el nombre del archivo que desea leer: ");
  char string[25] = "";
  scanf("%s", &string);

  printf("Ingrese el numero de bytes que pesa el archivo a visualizar: ");
  int bytes = 0;
  scanf("%i", &bytes);

  // guardo los bytes del archivo para hacer un reset
  int saveBytes = bytes;

  // bucle para recorrer todas las posibilidades de desplazamiento existentes
  for (int i = 255; i > 0 && endSearch != 1 ; i--){
    // accediendo al archivo desde este programa
    FILE *archivo = NULL;
    archivo = fopen(string, "r");

    // muestra el archivo seleccionado
    printf("\n");
    printf("\t\tContenido visual por caracteres del archivo seleccionado en el ciclo %i\n", i);
    for (int i = 0; i < 170; i++) printf("%c", 196);
    printf("\n");
      
    while (bytes > 0){
      // obtenemos el caracter actual y lo mostramos por la consola
      caracter = fgetc(archivo);
      printf("%c", caracter + i);
      bytes --;
    }
      
    // fin de lectura del archivo
    printf("\n");
    for (int i = 0; i < 170; i++) printf("%c", 196);
    printf("\n");

    // renicio el total de bytes
    bytes = saveBytes;

    // fin de ejecucion de la funcion
    fclose(archivo);

    printf("digite el numero 1 si desea terminar de buscar: ");
    scanf("%i", &endSearch);
    printf("\n");
  }
}

/* Esta funcion se encargara de mostrarnos por consola como se veria el archivo con una palabra clave que ingresemos nosotros */
void viewWithKeyword() {

  char keyword[256] = ""; // para almacenar la palabra clave
  int flag = 0; // se usara para saber si algun indice coincide
  unsigned char caracter = 0; // para guardar el caracter leido del archivo
  int index = 0; // para almacenar el indice en el que se repite la palabra

  // variables para el movimiento de los archivos
  printf("Ingrese el nombre del archivo que hay que decencriptar por palabra clave: ");
  char string[25] = ""; // almacena el nombre del archivo a abrir
  scanf("%s", string);
  FILE *archivo = NULL; // direccion al archivo a leer

  // accediendo a los archivos externos
  archivo = fopen(string, "r");

  // condicion para saber si existio algun error a la hora de acceder a los archivos externos
  if (archivo == NULL){
    // error
    printf("Error al abrir o crear los archivos necesarios\n");
    getchar();
  } else {
    // inicio del decencriptado

    // ingreso de la palabra clave que nos permitira decifrar el codigo
    printf("Ingrese la palabra clave para iniciar el decifrado: ");
    scanf("%s", &keyword);

    int lenght = strlen(keyword); // obtenemos la longitud de la palbra clave
    char keywordChar = 0; // variable para guardar un caracter correspondiente a la palbra clave

    printf("Ingrese el peso del archivo en bytes: ");
    int bytes = 0; // almacena el peso del archivo en bytes
    scanf("%i", &bytes);

    // inicio de lectura del archivo
    printf("\n");
    printf("\t\tContenido visual por caracteres del archivo con decrypt por keyword\n");
    for (int i = 0; i < 170; i++) printf("%c", 196);
    printf("\n");

    // lectura y escritura de los archivos externos
    while (bytes > 0){
      // reset de las variables y lectura de cada caracter
      caracter = fgetc(archivo);
      flag = 0; 
      index = 0;
      keywordChar = 0;

      // ciclo para saber si se repite o no el indice de la palabra con algun char del archivo cifrado
      for (int i = 0; i < lenght && flag == 0 ; i++) {
        // miramos si el indice coincide con el caracter
        if (i == caracter){
          flag = 1;
          index = keyword[i];
        }
      }
      
      // condicional para hacer el cambio pertinente
      if (flag == 1){
        // exoste un caracter que coincide
        printf("%c", index);
      } else {
        // no existe un caracter que coincide
        printf("%c", caracter);
      }

      bytes--;
    }
    
    // fin de lectura del archivo
    printf("\n");
    for (int i = 0; i < 170; i++) printf("%c", 196);
    printf("\n");
    printf("\t\tPresione cualquier tecla para continuar\n");
    getchar();

  }
}

/* Esta funcion se encargara de decencriptar la capa de desplazamiento del archivo cifrado */
void decrypForDisplacement() {
  // variable que almacenara el caracter que se leera cada vez en el archivo seleccionado y variable que contendra el valor del desplazamiento que se corregira
  unsigned char caracter = 0;
  int desplazamiento = 0;

  // variable que servira para guardar el nombre del archivo encriptado
  printf("Digite el nombre del archivo al que le desea corregir el desplazamiento: ");
  char string[25] = "";
  scanf("%s", &string);

  // variable que servira para guardar el nombre que se le desea poner al decencriptado
  printf("Digite el nombre que desea que obtenga el archivo que se va aquedar decencriptado: ");
  char decrypt[25] = "";
  scanf("%s", &decrypt);

  // accediendo a los archivos y creando uno nuevo para mostrar el decrypt
  FILE *archivo = fopen(string, "r");
  FILE *displacement = fopen(decrypt,"w");

  // condicion para saber si existio algun problema con los archivos
  if (archivo == NULL || displacement == NULL){
    // problemas con la lectura o escritura de los archivos externos
    printf("Al abrir el archivo encriptado o al crear el archivo para crear la copia del decencriptado se genero un error");
  } else {
    // inicio del decencriptamiento por desplazamiento

    // ingreso del peso del archivo en bytes
    printf("Ingrese el total de bytes que contiene el archivo cifrado: ");
    int bytes = 0;
    scanf("%i", &bytes);

    // ingreso del desplazamiento
    printf("Ingrese el valor del desplazamiento decifrado: ");
    scanf("%i", &desplazamiento);
    // bucle para crear el archivo sin la capa de encriptamiento por desplazamiento
    while (bytes > 0){
      // creacion del nuevo archivo con una capa menos de encriptacion
      caracter = fgetc(archivo);
      caracter += desplazamiento;
      fputc(caracter, displacement);
      bytes --;
    }
  }
  fclose(archivo);
  fclose(displacement);
}

/* Esta funcion se encargara de decencriptar la capa de la palabra clave del archivo cifrado */
void decryptForKeyword() {

  char keyword[256] = ""; // para almacenar la palabra clave
  int flag = 0; // se usara para saber si algun indice coincide
  unsigned char caracter = 0; // para guardar el caracter leido del archivo
  int index = 0; // para almacenar el indice en el que se repite la palabra

  // variables para el movimiento de los archivos
  printf("Ingrese el nombre del archivo que hay que decencriptar por palabra clave: ");
  char string[25] = ""; // almacena el nombre del archivo a abrir
  scanf("%s", string);

  // variable que servira para guardar el nombre que se le desea poner al decencriptado
  printf("Digite el nombre que desea que obtenga el archivo que se va aquedar decencriptado: ");
  char decrypt[25] = "";
  scanf("%s", &decrypt);

  FILE *archivo = NULL; // direccion al archivo a leer
  FILE *decryptKeyword = NULL; // direccion al archivo en el que se copiarla el decrypt

  // accediendo a los archivos externos
  archivo = fopen(string, "r");
  decryptKeyword = fopen(decrypt, "w");

  // condicion para saber si existio algun error a la hora de acceder a los archivos externos
  if (decryptKeyword == NULL || archivo == NULL){
    // error
    printf("Error al abrir o crear los archivos necesarios\n");
    getchar();
  } else {
    // inicio del decencriptado

    // ingreso de la palabra clave que nos permitira decifrar el codigo
    printf("Ingrese la palabra clave para iniciar el decifrado: ");
    scanf("%s", &keyword);

    int lenght = strlen(keyword); // obtenemos la longitud de la palbra clave
    char keywordChar = 0; // variable para guardar un caracter correspondiente a la palbra clave

    printf("Ingrese el peso del archivo en bytes: ");
    int bytes = 0; // almacena el peso del archivo en bytes
    scanf("%i", &bytes);

    // lectura y escritura de los archivos externos
    while (bytes > 0){
      // reset de las variables y lectura de cada caracter
      caracter = fgetc(archivo);
      flag = 0; 
      index = 0;
      keywordChar = 0;

      // ciclo para saber si se repite o no el indice de la palabra con algun char del archivo cifrado
      for (int i = 0; i < lenght && flag == 0 ; i++) {
        // miramos si el indice coincide con el caracter
        if (i == caracter){
          flag = 1;
          index = keyword[i];
        }
      }
      
      // condicional para hacer el cambio pertinente
      if (flag == 1){
        // exoste un caracter que coincide
        fputc(index, decryptKeyword);
      } else {
        // no existe un caracter que coincide
        fputc(caracter, decryptKeyword);
      }

      bytes--;
    }
  }
}

/* Esta funcion se encarga de generar el menu y ejecutar la funcion selecciona por el usuario */
int menuOptions(){
  // opcion que elegira que debe de hacer el programa
  int option = 0;

  // menu en estado decorado para ser mostrado cada vez que el programa termine la ejecucion de una funcion
  printf("%c", 218);
  for (int i = 0; i < 80; i++) printf("%c", 196);
  printf("%c\n", 191);
  printf("%c                                  MENU GENERAL                                  %c\n", 179, 179);
  printf("%c", 195);
  for (int i = 0; i < 80; i++) printf("%c", 196);
  printf("%c\n", 180);
  printf("%c 0. Salir del programa                                                          %c\n", 179, 179);
  printf("%c 1. Visualizar archivo elegido por caracteres                                   %c\n", 179, 179);
  printf("%c 2. Visualizar archivo elegido por numeros enteros                              %c\n", 179, 179);
  printf("%c 3. Visualizar archivo elegido con un decemcriptado por palabra clave           %c\n", 179, 179);
  printf("%c 4. Visualizar archivo elegido con sus posibles desplazamientos con caracteres  %c\n", 179, 179);
  printf("%c 5. Decencriptado del archivo por desplazamiento si ya se conoce el mismo       %c\n", 179, 179);
  printf("%c 6. Decencriptado del archivo por palabra clave si ya se conoce la misma        %c\n", 179, 179);
  printf("%c", 192);
  for (int i = 0; i < 80; i++) printf("%c", 196);
  printf("%c\n", 217);

  // Entrada para la opcion que se va a ejecutar y su respectiva validacion
  printf("Selecciona la opcion que se va a ejecutar: ");
  scanf("%i", &option);
  while (option < 0 || 6 < option){
    printf("\nEl numero ingresado no tiene una opcion que la componga.\n");
    printf("Por favor seleccionar una opcion existene.");
    scanf("%i", &option);
  }

  // seleccion de la funcion a ejecutar
  if (option == 1){
    // opcion para visualizar el archivo por caracteres
    viewFileChars();
  } else if (option == 2){
    // opcion 2
    viewFileInts();
  } else if (option == 3){
    // opcion 3
    viewWithKeyword();
  } else if (option == 4){
    // opcion 4
    viewAllFileChars();
  } else if (option == 5){
    // opcion 5
    decrypForDisplacement();
  } else if (option == 6){
    // opcion 6
    decryptForKeyword();
  }

  return option;
}

/* funcion main */
void main() {
  // variable para saber si el programa se debe cerrar 
  int leave = 1;
  // bucle que se encarga de dar cierre al programa cuando sea debido
  while (leave != 0){
    // if(system("cls")) system("clear");
    leave = menuOptions();
  }
}