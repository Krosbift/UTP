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
║ │ Enunciado: programa que se encarga de transformar un verbo del ingles en singular a plurar                                                                     │ ║
║ │                                                                                                                                                                │ ║
║ └────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘ ║
╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝*/

#include <stdio.h>
#include <string.h>

void plural(char cadena[15]){
  int longitud = strlen(cadena);
  if (cadena[longitud - 1] == 'y' || cadena[longitud - 1] == 'Y'){
    if (cadena[longitud - 1] == 'Y'){
      cadena[longitud - 1] = 'I';
      strcat(cadena, "ES");
    }else{
      cadena[longitud - 1] = 'i';
      strcat(cadena, "es");
    }
  }else{
    strcat(cadena, "s");
  }
  printf("%s", cadena);
}

int main(){
  char verbo[15];
  printf("Por favor ingrese un verbo en ingles: ");
  scanf("%s", & verbo);
  plural(verbo);
  return 0;
}