#lang racket
;================================================================================================================================================================;
; - Fecha de publicacion:                                                    10/04/2022 |                                                                        ;
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
; * MEMORAMA: Se pide construir un programa, tal que permita jugar a dos(2) jugadores, el juego de memoria llamado concéntrese o memórama.                       ;
;   El juego consiste en “recordar” sobre la pantalla, donde están las parejas de figuras, ocultas bajo un recuadro sobre el que este se encontrara una de las   ;
;   imagenes a encontrar su pareja y asi ir completando el juego, cuando se completen todos los recuadros el juego terminara y se podra iniciar uno nuevo con    ;
;   los mismo jugadores anteriores.                                                                                                                              ;         
;                                                                                                                                                                ;
;                                                                                                                                                                ;
;================================================================================================================================================================;
#| IDENTIFICADORES GLOVALES DE TODO EL PROGRAMA

juegor-1 : guarda el nombre del jugador1.
jugadpor1 : crea una cadena de 10 caracteres que luego cambiara por el nombre del jugador-1 para limitar el rango de caracteres.
juego: como la definicion de la ventana de juego en la que se va a jugar.
juegor-1 : guarda el nombre del jugador1.
jugadpor1 : crea una cadena de 10 caracteres que luego cambiara por el nombre del jugador-1 para limitar el rango de caracteres. |#

;________________________________________________________________________________________________________________________________________________________________;
#| LIBRERIAS REQURIDAS PARA EL FUNCIONAMIENTO DEL PROGRAMA Y SU APERTURA O LLAMADO |#

(require graphics/graphics)
(open-graphics)

;________________________________________________________________________________________________________________________________________________________________;
#| ENTRADA DE DATOS Y SU ORGANIZACION O LIMITACION PARA NO EXEDER EL ESPACIO QUE TENEMOS |#

#| Entrada de datos de los jugadores |#
(printf "Por favor digite el nombre del primer jugador: ")
(define jugador-1 (~a(read)))
(define jugador1 (make-string 10 #\space))
(printf "Por favor digite el nombre del segundo jugador: ")
(define jugador-2 (~a(read)))
(define jugador2 (make-string 10 #\space))

#| DESCRIPCION DE LOS IDENTIFICADORRES LOCALES A LA FUNCION LIMITADOR
final: es el contador de la funcion.
final-2: es el ontador para el numero total de carateres que toma el nombre del jugador-1 o jugador-2.
jugador: identificador que espera receibir la cadena que va a ser copiada a la cadeba jugador1.
jugador-c: es la cadena en la que se copiaran los primeros 10 chars de la cadena jugador-1. |#
; esta funcion limitara el nombre de los jugadores a un total de 10 chars:
(define (limitador final final-2 jugador jugador-c)
  (if (<= final-2 (- (string-length jugador-c) 1))
      (if (<= final 9)
          (begin  
            (string-set! jugador final (string-ref jugador-c final))
            (limitador (+ 1 final)(+ 1 final-2) jugador jugador-c)
          );fin begin
      ;de lo contrario
          (void)
      );fin if
  ;de lo contrario
      (void)
  );fin if
);fin definicion cambio

(limitador 0 0 jugador1 jugador-1); llamado para limitar el nombre del jugador 1
(limitador 0 0 jugador2 jugador-2); llamado para limitar el nombre del jugador 2

;________________________________________________________________________________________________________________________________________________________________;
#| DEFINICION DEL ENTORNO EN EL QUE SE JUGARA |#

#| Definicion de la ventana principal del juego: |#
(define juego (open-viewport "MEMORAMA" 800 600)); identificador para la ventana de juego.
((draw-viewport juego) "black")

; Cadenas de puntuacion, titulo y nombres de los jugadores:
((draw-string juego) (make-posn 480  40) "JUEGO DEL CONCENTRESE" "white")
((draw-string juego) (make-posn 710  40)                 "SCORE" "white")
((draw-string juego) (make-posn 480  80)            "JUGADOR 1:" "white")
((draw-string juego) (make-posn 580  80)                jugador1 "white")
((draw-string juego) (make-posn 480 100)            "JUGADOR 2:" "white")
((draw-string juego) (make-posn 580 100)                jugador2 "white")

; Logo del juego memorama:
(((draw-pixmap-posn "IMAGENES/LOGO.png") juego) (make-posn 480 420))

#| DESCRIPCION DE LOS IDENTIFICADORRES LOCALES A LA FUNCION IMP-CUADROS
final: es el contador del total de cuadros que se imprimiran.
filas: es el contador para las filas que poseen los cuadros de juego.
pixeles-x: guarda los pixeles de las coordenadas en x desde los que se imprimira la imagen.
pixeles-y: guarda los pixeles de las coordenadas en y desde los que se imprime la imagen. |#
;Definicion de funcion que imprimira en la ventana de juego los cuadros que ocultan las imagenes:
(define (imp-cuadros final filas pixeles-x pixeles-y)
  (if (<= final 16)
      (if (<= filas 4)
          (begin
            (((draw-pixmap-posn "IMAGENES/CUADRO.png") juego) (make-posn pixeles-x pixeles-y))
            (imp-cuadros (+ 1 final) (+ 1 filas) (+ pixeles-x 110) pixeles-y)
          ); fin begin true del if, (<= filas 4)
      ; DE LO CONTRARIO
          (imp-cuadros final 1 40 (+ 143 pixeles-y))
      ); fin if, (<= filas 4)
  ; DE LO CONTRARIO
      (void)
  ); fin if, (<= final 16)
); fin definicion imp-cuadros, para imprimir los cuadros que ocultan las imagenes a revelar

(imp-cuadros 1 1 40 20)

;________________________________________________________________________________________________________________________________________________________________;
#| FUNCION QUE PERMITE EL REINICIO DEL JUEGO CON LOS MISMOS JUGADORES DIFERENTE ORDEN DE FICHAS |#

#| DESCRIPCION DE LOS IDENTIFICADORRES LOCALES A LA FUNCION SIGUIENTE
play-retrun: es el identificador de la ventana de volver a jugar. |#
; fuincion para ejecutar el reinicio del juego:
(define (reinicio play-return)
  (((draw-pixmap-posn "IMAGENES/FINAL.png")  play-return) (make-posn 0 0))
  (if (and (and (>= [posn-x {mouse-click-posn (get-mouse-click play-return)}] 0) (<= [posn-x {mouse-click-posn (get-mouse-click play-return)}] 400)) (and (>= [posn-y {mouse-click-posn (get-mouse-click play-return)}] 0) (<= [posn-y {mouse-click-posn (get-mouse-click play-return)}] 200)))
      (begin
        (close-viewport play-return)
        ((draw-string juego) (make-posn 600 173) "█████████" "black")
        ((draw-string juego) (make-posn 710 80)  "██████" "black")
        ((draw-string juego) (make-posn 710 100) "██████" "black")
        ((draw-string juego) (make-posn 540 173) "█████████" "black")
        (imp-cuadros 1 1 40 20)
        (randomizer (make-string 16 #\space) "AABBCCDDEEFFGGHH" (random 16) 0)
        );fin begin
      ;de lo contrario
      (reinicio (get-mouse-click play-return))
      );fin if
  );fin definicion

;________________________________________________________________________________________________________________________________________________________________;
#| FUNCION PARA VERIFICAR QUE AMBAS CASILLAS DESCUBIERTAS SEAN LAS MISMAS |#

#| DESCRIPCION DE LOS IDENTIFICADORRES LOCALES A LA FUNCION SIGUIENTE
turno: se identifica como 1 o 2 para dar paso al turno del jugador 1 o jugador 2
filas-cuadro-1: guarda la posicion de la primera ficha abierta.
filas-cuadro-2: guarda la posicion de la segunda ficha abierta.
pixsave-x: cuada las coordenadas en x de la primera imagen abierta.
pixsave-2: cuada las coordenadas en y de la primera imagen abierta.
pix-x: cuada las coordenadas en x de la segunda imagen abierta.
pix-y: cuada las coordenadas en y de la segunda imagen abierta.
score1: guarda el puntaje del jugador 1.
score2: guarda el puntaje del jugador 2.
cadena-aleatoria: almacena la cadena que define la aleatoridad de las imagenes. |#
; funcion para verigicar si las casillas abiertas son las mismas:
(define (verificacion turno final-cuadro-1 final-cuadro-2 pixsave-x pixsave-y pix-x pix-y score1 score2 cadena-aleatoria)
  (if [equal? (string-ref cadena-aleatoria final-cuadro-1) (string-ref cadena-aleatoria final-cuadro-2)]
      (if (= turno 1)
          (begin
            (string-set! cadena-aleatoria final-cuadro-1 #\X)
            (string-set! cadena-aleatoria final-cuadro-2 #\X)
            ((draw-string juego) (make-posn 540 173) jugador1 "black")
            ((draw-string juego) (make-posn 710 80) (~a score1) "black" )
            ((draw-string juego) (make-posn 710 80)  (~a (+ score1 1)) "snow") 
            (controlador cadena-aleatoria (+ 1 score1) score2 1)
            );fin begin
      ; DE LO CONTRARIO
          (begin
            (string-set! cadena-aleatoria final-cuadro-1 #\X)
            (string-set! cadena-aleatoria final-cuadro-2 #\X)
            ((draw-string juego) (make-posn 540 173) jugador2 "black")
            ((draw-string juego) (make-posn 710 100) (~a score2) "black" )
            ((draw-string juego) (make-posn 710 100)  (~a (+ score2 1)) "snow") 
            (controlador cadena-aleatoria score1 (+ 1 score2) 2)
            );fin begin
      );fin if
  ; DE LO CONTRARIO
      (if (= turno 1)
          (begin
            ((draw-string juego) (make-posn 710 80) (~a score1) "black" )
            ((draw-string juego) (make-posn 540 173) jugador1 "black")
            ((draw-string juego) (make-posn 540 173) jugador2 "snow")
            ((draw-string juego) (make-posn 710 80)  (~a score1) "snow")
            (sleep 0.7)
            (((draw-pixmap-posn "IMAGENES/CUADRO.png") juego)  (make-posn pixsave-x pixsave-y))
            (((draw-pixmap-posn "IMAGENES/CUADRO.png") juego)  (make-posn pix-x pix-y)) 
            (controlador cadena-aleatoria score1 score2 2)
          );fin begin
      ; DE LO CONTRARIO       
          (begin              
            ((draw-string juego) (make-posn 710 100) (~a score2) "black" )
            ((draw-string juego) (make-posn 540 173) jugador2 "black")
            ((draw-string juego) (make-posn 540 173) jugador1 "snow")
            ((draw-string juego) (make-posn 710 100) (~a score2) "snow")
            (sleep 0.7)             
            (((draw-pixmap-posn "IMAGENES/CUADRO.png") juego)  (make-posn pixsave-x pixsave-y))
            (((draw-pixmap-posn "IMAGENES/CUADRO.png") juego)  (make-posn pix-x pix-y)) 
            (controlador cadena-aleatoria score1 score2 1)
          );fin begin
        );fin if
    );fin if
);fin definicion condicion

;_____________________________________________________________________________________________________________________________;
#| FUNCIONAMIENTO DEL JUEGO |#

#| DESCRIPCION DE LOS IDENTIFICADORRES LOCALES A LA FUNCION POSICION2
click-mouse: guarda el click que se ha dado en la ventana.
limites: se usa para que las posiciones en x vuelvan a las base y las y aumentes para seguir ir a la otra fila.
turno: se identifica como 1 o 2 para dar paso al turno del jugador 1 o jugador 2
filas-cuadro-1: identificador de la posicion del cuadro 1
filas-cuadro-2: identificador de la posicion del cuadro 2
pixsave-x: cuada las coordenadas en x de la primera imagen abierta.
pixsave-2: cuada las coordenadas en y de la primera imagen abierta.
pix-x: cuada las coordenadas en x de la segunda imagen abierta.
pix-y: cuada las coordenadas en y de la segunda imagen abierta.
score1: guarda el puntaje del jugador 1.
score2: guarda el puntaje del jugador 2.
cadena-aleatoria: almacena la cadena que define la aleatoridad de las imagenes. |#
; Funcion para seleccion el segundo cuadro a descubrir:
(define (posicion2 turno final-cuadro-1 final-cuadro-2 click-mouse score1 score2 cadena-aleatoria pix-x pix-y pixsave-x pixsave-y limite)
  (if (or
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 40 ] [<= (posn-x (mouse-click-posn click-mouse)) 140]) (and [>= (posn-y (mouse-click-posn click-mouse)) 20 ] [<= (posn-y (mouse-click-posn click-mouse)) 153]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 150] [<= (posn-x (mouse-click-posn click-mouse)) 250]) (and [>= (posn-y (mouse-click-posn click-mouse)) 20 ] [<= (posn-y (mouse-click-posn click-mouse)) 153]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 260] [<= (posn-x (mouse-click-posn click-mouse)) 360]) (and [>= (posn-y (mouse-click-posn click-mouse)) 20 ] [<= (posn-y (mouse-click-posn click-mouse)) 153]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 370] [<= (posn-x (mouse-click-posn click-mouse)) 470]) (and [>= (posn-y (mouse-click-posn click-mouse)) 20 ] [<= (posn-y (mouse-click-posn click-mouse)) 153]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 40 ] [<= (posn-x (mouse-click-posn click-mouse)) 140]) (and [>= (posn-y (mouse-click-posn click-mouse)) 163] [<= (posn-y (mouse-click-posn click-mouse)) 296]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 150] [<= (posn-x (mouse-click-posn click-mouse)) 250]) (and [>= (posn-y (mouse-click-posn click-mouse)) 163] [<= (posn-y (mouse-click-posn click-mouse)) 296]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 260] [<= (posn-x (mouse-click-posn click-mouse)) 360]) (and [>= (posn-y (mouse-click-posn click-mouse)) 163] [<= (posn-y (mouse-click-posn click-mouse)) 296]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 370] [<= (posn-x (mouse-click-posn click-mouse)) 470]) (and [>= (posn-y (mouse-click-posn click-mouse)) 163] [<= (posn-y (mouse-click-posn click-mouse)) 296]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 40 ] [<= (posn-x (mouse-click-posn click-mouse)) 140]) (and [>= (posn-y (mouse-click-posn click-mouse)) 306] [<= (posn-y (mouse-click-posn click-mouse)) 439]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 150] [<= (posn-x (mouse-click-posn click-mouse)) 250]) (and [>= (posn-y (mouse-click-posn click-mouse)) 306] [<= (posn-y (mouse-click-posn click-mouse)) 439]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 260] [<= (posn-x (mouse-click-posn click-mouse)) 360]) (and [>= (posn-y (mouse-click-posn click-mouse)) 306] [<= (posn-y (mouse-click-posn click-mouse)) 439]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 370] [<= (posn-x (mouse-click-posn click-mouse)) 470]) (and [>= (posn-y (mouse-click-posn click-mouse)) 306] [<= (posn-y (mouse-click-posn click-mouse)) 439]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 40 ] [<= (posn-x (mouse-click-posn click-mouse)) 140]) (and [>= (posn-y (mouse-click-posn click-mouse)) 449] [<= (posn-y (mouse-click-posn click-mouse)) 582]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 150] [<= (posn-x (mouse-click-posn click-mouse)) 250]) (and [>= (posn-y (mouse-click-posn click-mouse)) 449] [<= (posn-y (mouse-click-posn click-mouse)) 582]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 260] [<= (posn-x (mouse-click-posn click-mouse)) 360]) (and [>= (posn-y (mouse-click-posn click-mouse)) 449] [<= (posn-y (mouse-click-posn click-mouse)) 582]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 370] [<= (posn-x (mouse-click-posn click-mouse)) 470]) (and [>= (posn-y (mouse-click-posn click-mouse)) 449] [<= (posn-y (mouse-click-posn click-mouse)) 582]))
      )#| fin condicional |#
      (if (and (and [>= (posn-x (mouse-click-posn click-mouse)) pixsave-x] [<= (posn-x (mouse-click-posn click-mouse)) (+ 100 pixsave-x)]) (and [>= (posn-y (mouse-click-posn click-mouse)) pixsave-y] [<= (posn-y (mouse-click-posn click-mouse)) (+ 133 pixsave-y)]))
          (posicion2 turno final-cuadro-1 final-cuadro-2 (get-mouse-click juego) score1 score2 cadena-aleatoria pix-x pix-y pixsave-x pixsave-y limite)
      ;  DE LO CONTRARIO
          (if (and (and [>= (posn-x (mouse-click-posn click-mouse)) pix-x] [<= (posn-x (mouse-click-posn click-mouse)) (+ pix-x 110)]) (and [>= (posn-y (mouse-click-posn click-mouse)) pix-y] [<= (posn-y (mouse-click-posn click-mouse)) (+ pix-y 133)]))
              (if (equal? (string-ref cadena-aleatoria final-cuadro-2) #\X)
                  (posicion2 turno final-cuadro-1 0 (get-mouse-click juego) score1 score2 cadena-aleatoria 40 20 pixsave-x pixsave-y 0)
              ; DE LO CONTRARIO
                  (cond
                    [(equal? (string-ref cadena-aleatoria final-cuadro-2) #\A) (((draw-pixmap-posn "IMAGENES/IMAGEN1.png") juego) (make-posn pix-x pix-y)) (verificacion turno final-cuadro-1 final-cuadro-2 pixsave-x pixsave-y pix-x pix-y score1 score2 cadena-aleatoria)]
                    [(equal? (string-ref cadena-aleatoria final-cuadro-2) #\B) (((draw-pixmap-posn "IMAGENES/IMAGEN2.png") juego) (make-posn pix-x pix-y)) (verificacion turno final-cuadro-1 final-cuadro-2 pixsave-x pixsave-y pix-x pix-y score1 score2 cadena-aleatoria)]
                    [(equal? (string-ref cadena-aleatoria final-cuadro-2) #\C) (((draw-pixmap-posn "IMAGENES/IMAGEN3.png") juego) (make-posn pix-x pix-y)) (verificacion turno final-cuadro-1 final-cuadro-2 pixsave-x pixsave-y pix-x pix-y score1 score2 cadena-aleatoria)]
                    [(equal? (string-ref cadena-aleatoria final-cuadro-2) #\D) (((draw-pixmap-posn "IMAGENES/IMAGEN4.png") juego) (make-posn pix-x pix-y)) (verificacion turno final-cuadro-1 final-cuadro-2 pixsave-x pixsave-y pix-x pix-y score1 score2 cadena-aleatoria)]
                    [(equal? (string-ref cadena-aleatoria final-cuadro-2) #\E) (((draw-pixmap-posn "IMAGENES/IMAGEN5.png") juego) (make-posn pix-x pix-y)) (verificacion turno final-cuadro-1 final-cuadro-2 pixsave-x pixsave-y pix-x pix-y score1 score2 cadena-aleatoria)]
                    [(equal? (string-ref cadena-aleatoria final-cuadro-2) #\F) (((draw-pixmap-posn "IMAGENES/IMAGEN6.png") juego) (make-posn pix-x pix-y)) (verificacion turno final-cuadro-1 final-cuadro-2 pixsave-x pixsave-y pix-x pix-y score1 score2 cadena-aleatoria)]
                    [(equal? (string-ref cadena-aleatoria final-cuadro-2) #\G) (((draw-pixmap-posn "IMAGENES/IMAGEN7.png") juego) (make-posn pix-x pix-y)) (verificacion turno final-cuadro-1 final-cuadro-2 pixsave-x pixsave-y pix-x pix-y score1 score2 cadena-aleatoria)]
                    [(equal? (string-ref cadena-aleatoria final-cuadro-2) #\H) (((draw-pixmap-posn "IMAGENES/IMAGEN8.png") juego) (make-posn pix-x pix-y)) (verificacion turno final-cuadro-1 final-cuadro-2 pixsave-x pixsave-y pix-x pix-y score1 score2 cadena-aleatoria)]
                    [else (posicion2 turno final-cuadro-1 final-cuadro-2 click-mouse score1 score2 cadena-aleatoria (if (<= limite 4) (+ pix-x 110) (- pix-x 440)) (if (<= limite 4) pix-y (+ pix-y 133)) pixsave-x pixsave-y (if (= limite 4) (- limite 4) (+ limite 1)))]
                  ); fin cond
              ); fin if
          ; DE LO CONTRARIO
              (posicion2 turno final-cuadro-1 (if (= limite 4) final-cuadro-2 (+ final-cuadro-2 1)) click-mouse score1 score2 cadena-aleatoria (if (< limite 4) (+ pix-x 110) (- pix-x 440)) (if (< limite 4) pix-y (+ pix-y 143)) pixsave-x pixsave-y (if (= limite 4) (- limite 4) (+ limite 1)))
          ); fin if
      ); fin if
  ; DE LO CONTRARIO
      (posicion2 turno final-cuadro-1 0 (get-mouse-click juego) score1 score2 cadena-aleatoria 40 20 pixsave-x pixsave-y 0)
  ); fin if
); fin definicion posicion2

#| DESCRIPCION DE LOS IDENTIFICADORRES LOCALES A LA FUNCION POSICION
turno: se identifica como 1 o 2 para dar paso al turno del jugador 1 o jugador 2
filas-cuadro-1: identificador de la posicion del cuadro 1
filas-cuadro-2: identificador de la posicion del cuadro 2
click-mouse: guarda el click dado en la pantalla
pix-x: cuada las coordenadas en x de la segunda imagen abierta.
pix-y: cuada las coordenadas en y de la segunda imagen abierta.
score1: guarda el puntaje del jugador 1.
score2: guarda el puntaje del jugador 2.
cadena-aleatoria: almacena la cadena que define la aleatoridad de las imagenes. |#
; Funcion para seleccion el primer cuadro a descubrir:
(define (posicion turno final-cuadro-1 final-cuadro-2 click-mouse score1 score2 cadena-aleatoria pix-x pix-y limite)
  (if (or
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 40 ] [<= (posn-x (mouse-click-posn click-mouse)) 140]) (and [>= (posn-y (mouse-click-posn click-mouse)) 20 ] [<= (posn-y (mouse-click-posn click-mouse)) 153]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 150] [<= (posn-x (mouse-click-posn click-mouse)) 250]) (and [>= (posn-y (mouse-click-posn click-mouse)) 20 ] [<= (posn-y (mouse-click-posn click-mouse)) 153]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 260] [<= (posn-x (mouse-click-posn click-mouse)) 360]) (and [>= (posn-y (mouse-click-posn click-mouse)) 20 ] [<= (posn-y (mouse-click-posn click-mouse)) 153]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 370] [<= (posn-x (mouse-click-posn click-mouse)) 470]) (and [>= (posn-y (mouse-click-posn click-mouse)) 20 ] [<= (posn-y (mouse-click-posn click-mouse)) 153]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 40 ] [<= (posn-x (mouse-click-posn click-mouse)) 140]) (and [>= (posn-y (mouse-click-posn click-mouse)) 163] [<= (posn-y (mouse-click-posn click-mouse)) 296]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 150] [<= (posn-x (mouse-click-posn click-mouse)) 250]) (and [>= (posn-y (mouse-click-posn click-mouse)) 163] [<= (posn-y (mouse-click-posn click-mouse)) 296]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 260] [<= (posn-x (mouse-click-posn click-mouse)) 360]) (and [>= (posn-y (mouse-click-posn click-mouse)) 163] [<= (posn-y (mouse-click-posn click-mouse)) 296]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 370] [<= (posn-x (mouse-click-posn click-mouse)) 470]) (and [>= (posn-y (mouse-click-posn click-mouse)) 163] [<= (posn-y (mouse-click-posn click-mouse)) 296]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 40 ] [<= (posn-x (mouse-click-posn click-mouse)) 140]) (and [>= (posn-y (mouse-click-posn click-mouse)) 306] [<= (posn-y (mouse-click-posn click-mouse)) 439]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 150] [<= (posn-x (mouse-click-posn click-mouse)) 250]) (and [>= (posn-y (mouse-click-posn click-mouse)) 306] [<= (posn-y (mouse-click-posn click-mouse)) 439]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 260] [<= (posn-x (mouse-click-posn click-mouse)) 360]) (and [>= (posn-y (mouse-click-posn click-mouse)) 306] [<= (posn-y (mouse-click-posn click-mouse)) 439]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 370] [<= (posn-x (mouse-click-posn click-mouse)) 470]) (and [>= (posn-y (mouse-click-posn click-mouse)) 306] [<= (posn-y (mouse-click-posn click-mouse)) 439]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 40 ] [<= (posn-x (mouse-click-posn click-mouse)) 140]) (and [>= (posn-y (mouse-click-posn click-mouse)) 449] [<= (posn-y (mouse-click-posn click-mouse)) 582]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 150] [<= (posn-x (mouse-click-posn click-mouse)) 250]) (and [>= (posn-y (mouse-click-posn click-mouse)) 449] [<= (posn-y (mouse-click-posn click-mouse)) 582]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 260] [<= (posn-x (mouse-click-posn click-mouse)) 360]) (and [>= (posn-y (mouse-click-posn click-mouse)) 449] [<= (posn-y (mouse-click-posn click-mouse)) 582]))
       (and (and [>= (posn-x (mouse-click-posn click-mouse)) 370] [<= (posn-x (mouse-click-posn click-mouse)) 470]) (and [>= (posn-y (mouse-click-posn click-mouse)) 449] [<= (posn-y (mouse-click-posn click-mouse)) 582]))
      )#| fin condicional |#
      (if (and (and [>= (posn-x (mouse-click-posn click-mouse)) pix-x] [<= (posn-x (mouse-click-posn click-mouse)) (+ pix-x 110)]) (and [>= (posn-y (mouse-click-posn click-mouse)) pix-y] [<= (posn-y (mouse-click-posn click-mouse)) (+ pix-y 133)]))
          (if (equal? (string-ref cadena-aleatoria final-cuadro-1) #\X)
              (posicion turno 0 final-cuadro-2 (get-mouse-click juego) score1 score2 cadena-aleatoria 40 20 0)
          ; DE LO CONTRARIO
              (cond
                [(equal? (string-ref cadena-aleatoria final-cuadro-1) #\A) (((draw-pixmap-posn "IMAGENES/IMAGEN1.png") juego) (make-posn pix-x pix-y)) (posicion2 turno final-cuadro-1 0 (get-mouse-click juego) score1 score2 cadena-aleatoria 40 20 pix-x pix-y 0)]
                [(equal? (string-ref cadena-aleatoria final-cuadro-1) #\B) (((draw-pixmap-posn "IMAGENES/IMAGEN2.png") juego) (make-posn pix-x pix-y)) (posicion2 turno final-cuadro-1 0 (get-mouse-click juego) score1 score2 cadena-aleatoria 40 20 pix-x pix-y 0)]
                [(equal? (string-ref cadena-aleatoria final-cuadro-1) #\C) (((draw-pixmap-posn "IMAGENES/IMAGEN3.png") juego) (make-posn pix-x pix-y)) (posicion2 turno final-cuadro-1 0 (get-mouse-click juego) score1 score2 cadena-aleatoria 40 20 pix-x pix-y 0)]
                [(equal? (string-ref cadena-aleatoria final-cuadro-1) #\D) (((draw-pixmap-posn "IMAGENES/IMAGEN4.png") juego) (make-posn pix-x pix-y)) (posicion2 turno final-cuadro-1 0 (get-mouse-click juego) score1 score2 cadena-aleatoria 40 20 pix-x pix-y 0)]
                [(equal? (string-ref cadena-aleatoria final-cuadro-1) #\E) (((draw-pixmap-posn "IMAGENES/IMAGEN5.png") juego) (make-posn pix-x pix-y)) (posicion2 turno final-cuadro-1 0 (get-mouse-click juego) score1 score2 cadena-aleatoria 40 20 pix-x pix-y 0)]
                [(equal? (string-ref cadena-aleatoria final-cuadro-1) #\F) (((draw-pixmap-posn "IMAGENES/IMAGEN6.png") juego) (make-posn pix-x pix-y)) (posicion2 turno final-cuadro-1 0 (get-mouse-click juego) score1 score2 cadena-aleatoria 40 20 pix-x pix-y 0)]
                [(equal? (string-ref cadena-aleatoria final-cuadro-1) #\G) (((draw-pixmap-posn "IMAGENES/IMAGEN7.png") juego) (make-posn pix-x pix-y)) (posicion2 turno final-cuadro-1 0 (get-mouse-click juego) score1 score2 cadena-aleatoria 40 20 pix-x pix-y 0)]
                [(equal? (string-ref cadena-aleatoria final-cuadro-1) #\H) (((draw-pixmap-posn "IMAGENES/IMAGEN8.png") juego) (make-posn pix-x pix-y)) (posicion2 turno final-cuadro-1 0 (get-mouse-click juego) score1 score2 cadena-aleatoria 40 20 pix-x pix-y 0)]
                [else (posicion turno final-cuadro-1 0 click-mouse score1 score2 cadena-aleatoria (if (< limite 4) (+ pix-x 110) (- pix-x 440)) (if (< limite 4) pix-y (+ pix-y 133)) (if (= limite 4) (- limite 4) (+ limite 1)))]
              );fin cond
          ); fin if
      ; DE LO CONTRARIO
            (posicion turno (if (= limite 4) final-cuadro-1 (+ final-cuadro-1 1)) 0 click-mouse score1 score2 cadena-aleatoria (if (< limite 4) (+ pix-x 110) (- pix-x 440)) (if (< limite 4) pix-y (+ pix-y 143)) (if (= limite 4) (- limite 4) (+ limite 1))) 
      ); fin if
      (posicion turno final-cuadro-1 0 (get-mouse-click juego) score1 score2 cadena-aleatoria pix-x pix-y limite)
  ); fin if
); fin definicion posicion

;________________________________________________________________________________________________________________________________________________________________;
#| FUNCION QUE CONTROLA EL PROGRAMA, TENIENDO EN CUENTA SI EL TURNO DEL JUGADOR, LOS PUNTAJES Y LOS MUESTRA EN LA VENTANA DE MANERA ACTUALIZADA |#

#| DESCRIPCION DE LOS IDENTIFICADORRES LOCALES A LA FUNCION CONTROLADOR
cadena-aleatoria: cadena que almacenara los caracteres de manera aleatoria para las imagenes
score1: guarda el puntaje del jugador 2
score2: guarda el puntaje del jugador 1
turno: guarda el turno del juador |#
; funcion que controla aquien le corresponde jugar y determina si es que hay un ganardor ya:
(define (controlador cadena-aleatoria score1 score2 turno)
  (if (< (+ score1 score2) 8)
      (cond
        [(= turno 1) ((draw-string juego) (make-posn 480 173) "JUEGA: "   "white")
                     ((draw-string juego) (make-posn 540 173) jugador1    "white")
                     ((draw-string juego) (make-posn 710 80)  (~a score1) "white")
                     ((draw-string juego) (make-posn 710 100) (~a score2) "white")
                     (posicion turno 0 0 (get-mouse-click juego) score1 score2 cadena-aleatoria 40 20 0)
        ]
      ; DE LO CONTRARIO
        [(= turno 2) ((draw-string juego) (make-posn 480 173) "JUEGA: "   "white")
                     ((draw-string juego) (make-posn 540 173) jugador2    "white")
                     ((draw-string juego) (make-posn 710 80)  (~a score1) "white")
                     ((draw-string juego) (make-posn 710 100) (~a score2) "white")
                     (posicion turno 0 0 (get-mouse-click juego) score1 score2 cadena-aleatoria 40 20 0)
        ]
      ; DE LO CONTRARIO
      #| no hace nada |#
      ); fin cond control de turnos, elige el jugador al que le corresponde jugar
  ; DE LO CONTRARIO
      (cond
        [(= score1 score2) ((draw-string juego) (make-posn 480 173) "JUEGA: " "yellow")
                           ((draw-string juego) (make-posn 540 173) "QUEDO"    "white")
                           ((draw-string juego) (make-posn 600 173) "EMPATE"   "white")
                           (reinicio (open-viewport "PLAY AGAIN" 400 200))
        ]
      ; DE LO CONTRARIO
        [(> score1 score2) ((draw-string juego) (make-posn 480 173) "JUEGA: " "yellow")
                           ((draw-string juego) (make-posn 540 173) "GANÓ"     "white")
                           ((draw-string juego) (make-posn 600 173) jugador1   "white")
                           (reinicio (open-viewport "PLAY AGAIN" 400 200))
        ]
      ; DE LO CONTRARIO
        [(< score1 score2) ((draw-string juego) (make-posn 480 173) "JUEGA: " "yellow")
                           ((draw-string juego) (make-posn 540 173) "GANÓ"     "white")
                           ((draw-string juego) (make-posn 600 173) jugador2   "white")
                           (reinicio (open-viewport "PLAY AGAIN" 400 200))
        ]
      ; DE LO CONTRARIO
      #| no hace nada |#
      ); fin cond que decide el ganador y espera a que se reinicie el juego
  ); fin if, (< (+ score1 score2) 8)
); fin definicion controlador, para controlar el juego

;________________________________________________________________________________________________________________________________________________________________;
#| FUNCION PARA GENERAR LA ALEATEORIDAD DE LAS IMAGENES APARTIR DE UNOS CARACTERES QUE SE FORMARA EN DESORDEN |#

#| DESCRIPCION DE LOS IDENTIFICADORRES LOCALES A LA FUNCION SIGUIENTE
cadena-aleatoria: guarda la nueva cadena a la que se le cambiaran los caracteres por los nuevos aleatoriamente para las imagenes
cadena-letras: cadena de letras en parejas para copiarlas en la cadena-aleatoria pero de manera aleatoria
selector: elije la letra que se imprimira |#
; funcion para generar la cadena que aleatorizara las posiciones de las imagenes
(define (randomizer cadena-aleatoria cadena-letras selector final)
  (if (< final 16)
      (if (equal? (string-ref cadena-aleatoria selector) #\space)
          (begin
            (string-set! cadena-aleatoria selector (string-ref cadena-letras final))
            (randomizer cadena-aleatoria cadena-letras (random 16) (+ final 1))
          ) ; fin begin true del if, (equal? (string-ref cadena-aleatoria final) #\space)    
      ; DE LO CONTRARIO
          (randomizer cadena-aleatoria cadena-letras (random 16) final)
      ); fin if, (equal? (string-ref cadena-aleatoria final) #\space)
  ; DE LO CONTRARIO
      (controlador  cadena-aleatoria 0 0 1)
  ); fin if, (< final 16)
); fin definicion ramdomizer, para dar aleatoreidad a las 16 imagenes

(randomizer (make-string 16 #\space) "AABBCCDDEEFFGGHH" (random 16) 0)#| inicio del juego |#

;________________________________________________________________________________________________________________________________________________________________;
