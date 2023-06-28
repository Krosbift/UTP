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
║ │ Enunciado: programa de rush, encargado de hacer un cuadrado con los caracteres pedidos en el enunciado                                                         │ ║
║ │                                                                                                                                                                │ ║
║ └────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘ ║
╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝*/

#include <stdio.h>

void rush(int rows, int colums, int totalColums, int totalRows){
  // condicion de para finalizar el programa
  if (rows >= 1){
    // rellenar primera fila
    if (rows == totalRows){
      // imprime parte superior izquierda
      if (colums == totalColums){
        printf("%c", 218);
      }
      // imprime parte superior derecha
      if (colums == 1){
        printf("%c", 191); 
      }
      // imprime las partes centrales
      if (colums != totalColums && colums != 1 && colums != 0){
        printf("%c", 196);
      }
      // condicion para saber si terminamos de imprimir toda a fila
      if (colums == 0){
        printf("\n");
        rush((rows - 1), totalColums, totalColums, totalRows);
      }else{
        rush(rows, (colums - 1), totalColums, totalRows);
      }
    }
    // rellenar ultima fila
    if (rows == 1){
      // imprime parte inferior izquierda
      if (colums == totalColums){
        printf("%c", 192);
      }
      // imprime parte inferior derecha
      if (colums == 1){
        printf("%c", 217);
      }
      // imprime las partes centrales
      if (colums != totalColums && colums != 1 && colums != 0){
        printf("%c", 196);
      }
      // condicion para saber si terminamos de imprimir toda a fila
      if (colums == 0){
        printf("\n");
        rush((rows - 1), totalColums, totalColums, totalRows);
      }else{
        rush(rows, (colums - 1), totalColums, totalRows);
      }
    }
    // rellenar filas centrales
    if (rows != 1 && rows != totalRows){
      // imprime las pártes laterales de la fila
      if (colums == totalColums || colums == 1){
        printf("%c", 179);
      }
      // imprime las partes centrales
      if (colums != totalColums && colums != 1 && colums != 0){
        if(colums % 2 == 0){
          printf("%c", 175);
        }else{
          printf("%C", 174);
        }
      }
      // condicion para saber si terminamos de imprimir toda a fila
      if (colums == 0){
        printf("\n");
        rush((rows - 1), totalColums, totalColums, totalRows);
      }else{
        rush(rows, (colums - 1), totalColums, totalRows);
      }
    }
  }
}

int main(){
  // variables para el total de filas y columnas
  int rows, colums;
  // entrada de datos
  printf("Ingresar el numero de columnas. ");
  scanf("%i", &rows);
  printf("Ingresar el numero de filas. ");
  scanf("%i", &colums);
  // llamado a la funcion rush
  rush(rows, colums, colums, rows);
  return 0;
}