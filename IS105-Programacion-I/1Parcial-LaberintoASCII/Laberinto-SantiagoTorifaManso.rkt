#lang racket
;================================================================================================================================================================;
; - Fecha de publicacion:                                                    09/16/2022 |                                                                        ;
; - Hora:                                                                         07:00 |                                                                        ;
; - Version del codigo:                                                           1.0.0 |                                                                        ;
; - Autor:                                                 Ing(c) Santiago Torifa Manso |                                                                        ;
; - Nombre del lenguaje utilizado:                                               Racket |                           CREDITOS DEL CODIGO                          ;
; - Version del lenguaje utilizado:                                                 8.5 |                                                                        ;
; - Presentado a:                                         Doctor Ricardo Moreno Laverde |                                                                        ;
;========================================================================================                                                                        ;
; - Universidad Tecnológica de Pereira                                                  |                                                                        ;
; - Programa de Ingenieria de Sistemas y Computacion                                    |                                                                        ;
;================================================================================================================================================================;
;                                                                                                                                                                ;
; * LABERINTO: se pide contruir un laberinto con un total de 30 filas y columnas donde la salida sea generada aleatoriamente en la ultima columna y entre las    ;         
;   las filas 2 y 29, que en su centro haya solo un 25% de probabildiad de muro, que la periferia este cubierta por muros excepto la entra en las coordenadas    ;
;   (0,0 a 0,1) y (0,0 a 1,0).                                                                                                                                   ;
;                                                                                                                                                                ;
;================================================================================================================================================================;
#| DESCRIPCION DE LOS IDENTIFICADORES GLOBALES |#

#| DESCRIPCION:
laberinto: cadena que almacena los caracteres que corresponden con el laberinto.
|#

;________________________________________________________________________________________________________________________________________________________________
#| DEFINICION PARA LA CADENA DEL LABERINTO |#

; definicion cadena laberinto:
(define laberinto (make-string 900 #\space))

;________________________________________________________________________________________________________________________________________________________________
#| FUNCION PARA LA GENERACION DE MUROS INTERIORES CON UN 25% (1 entre 4) DE PROBABILIDAD |#

#| IDENTIFICADORES LOCALES A LA FUNCION MUROS-INTERIORES
inicio: identifica el caracter de inicio
final: identifica el caracter final |#
(define (muros-interiores inicio final)
  (if (< inicio final)
      (if (= 0 (random 0 4))
          (begin
            (string-set! laberinto inicio #\█)
            (muros-interiores (+ inicio 1) final)
          ); fin begin true
      ; DE LO CONTRARIO
          (muros-interiores (+ inicio 1) final)
      ); fin if, 0 = random de 0 a 4
  ; DE LO CONTRARIO
      (void)
  ); fin if, inicio < final
); fin definicion muros-interiores

(muros-interiores 0 900)#| llamado a la funcion para generar los muros interiroes |#

;________________________________________________________________________________________________________________________________________________________________
#| FUNCION QUE IMPRIME LOS MUROS ESTERIORES DEL LABERINTO |#

#| IDENTIFICADORES LOCALES A LA FUNCION MUROS-EXTERIORES:
inicio: identifica el caracter de inicio.
final: identifica el caracter final.
incremento: identifica el incremento del inicio. |#
(define (muros-exteriores inicio final incremento)
  (if (< inicio final)
      (begin
        (string-set! laberinto inicio #\█)
        (muros-exteriores (+ inicio incremento) final incremento)
      ); fin begin true
  ; DE LO CONTRAIO
      (void)
  ); fin if, inicio < final
); fin definicion muros-exteriores

(muros-exteriores 0   30  1 )#| llamado para construir el muro superior  |#
(muros-exteriores 870 900 1 )#| llamado para construir el muro inferior  |#
(muros-exteriores 30  870 30)#| llamado para construir el muro izquierdo |#
(muros-exteriores 59  870 30)#| llamado para construir el muro derecho   |#
  
;________________________________________________________________________________________________________________________________________________________________
#| RUPTURA DEL MURO DE LA ENTRADA Y LA SALIDA DEL LABERINTO |#

(string-set! laberinto 0  #\space)#| apertura entrada en  0 |# (string-set! laberinto 1 #\space) #| apertura entrada en  1 |#
(string-set! laberinto 30 #\space)#| apertura entrada en 30 |#

#| IDENTIFICADORES LOCALES A LA FUNCION PUERTA:
puerta: identifica un numero aleatoria entre 1 y 28 |#
(define (puerta salida)
  (cond
    [(= salida 1)   59]#| de lo contrario |#[(= salida 2)   89]#| de lo contrario |#[(= salida 3)  119]#| de lo contrario |#[(= salida 4)  149]#| de lo contrario |#[(= salida 5 ) 179]#| de lo contrario |#
    [(= salida 6)  209]#| de lo contrario |#[(= salida 7)  239]#| de lo contrario |#[(= salida 8)  269]#| de lo contrario |#[(= salida 9)  299]#| de lo contrario |#[(= salida 10) 329]#| de lo contrario |#
    [(= salida 11) 359]#| de lo contrario |#[(= salida 12) 389]#| de lo contrario |#[(= salida 13) 419]#| de lo contrario |#[(= salida 14) 449]#| de lo contrario |#[(= salida 15) 479]#| de lo contrario |#
    [(= salida 16) 509]#| de lo contrario |#[(= salida 17) 539]#| de lo contrario |#[(= salida 18) 569]#| de lo contrario |#[(= salida 19) 599]#| de lo contrario |#[(= salida 20) 629]#| de lo contrario |#
    [(= salida 21) 659]#| de lo contrario |#[(= salida 22) 689]#| de lo contrario |#[(= salida 23) 719]#| de lo contrario |#[(= salida 24) 749]#| de lo contrario |#[(= salida 25) 779]#| de lo contrario |#
    [(= salida 26) 809]#| de lo contrario |#[(= salida 27) 839]#| de lo contrario |#[(= salida 28) 869]#| de lo contrario (void) |#
  ); fin cond
); fin definicion salida puerta

(string-set! laberinto (puerta (random 1 29)) #\space)#| apertura de la salida |#

;________________________________________________________________________________________________________________________________________________________________
#| FUNCION PARA IMPRIMIR EL LABERINTO DE MANERA ORDENADA |#

(printf "                        0         1         2\n")#| super indice de la columnas |#
(printf "               012345678901234567890123456789\n")#| indice de las columnas |#

#| IDENTIFICADORES LOCALES A LA FUNCION IMPRESION:
imp-caracteres: identifica desde que caracter se va a empezar a imprimir y es un valor creciente.
inicio: identifica el inicio de la funcion.
final: identifica el final de la funcion. |#
(define (impresion imp-caracteres inicio final)
  (if (< inicio final)
      (begin
        (if (= inicio 0)
            (printf "Entrada ---> ")
        ; DE LO CONTRARIO
            (printf "             ")
        ); fin if, inicio = 0
        (if (< inicio 10)
            (printf "~a " inicio)
        ; DE LO CONTRARIO
            (printf "~a" inicio)
        ); fin if, inicio < 10
        (printf "~a\n" (substring laberinto imp-caracteres (+ imp-caracteres 30)))
        (impresion (+ imp-caracteres 30) (+ inicio 1) final)
      ); fin begin true
  ; DE LO CONTRARIO
      (void)
  ); fin if, inicio < final
); fin definicion impresion
  
(impresion 0 0 30)

;________________________________________________________________________________________________________________________________________________________________