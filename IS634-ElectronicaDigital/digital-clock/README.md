# Sistema de Reloj Digital para FPGA

## Descripción Funcional Detallada

El dispositivo tiene 4 modos de operación que serán seleccionados mediante la configuración de 2 switches.

### Modo 1: Operación Normal
En los displays de 8 segmentos de la tarjeta se observa el funcionamiento del reloj, permitiendo ver la hora actual y su avance.

### Modo 2: Cambio de Hora
En los displays de 8 segmentos de la tarjeta se observa la hora actual detenida, mostrando de forma intermitente los dos dígitos de los minutos indicando que a través de dos botones de pulsación se podrá incrementar o decrementar dicho valor. Existe otro botón de pulsación para cambiar entre modificar los minutos o la hora, es decir, pasar a modificar los dos dígitos de la hora (éstos se muestran intermitentemente) y viceversa. En este modo existe un cuarto botón que cuando es presionado, lleva la hora actual configurada a ser la hora del sistema.

### Modo 3: Alarma
En los displays de 8 segmentos de la tarjeta se observa la hora de la alarma actual (por defecto la hora es 08:00 en caso de que nunca se haya configurado alguna alarma). El funcionamiento de configuración es muy similar al Modo 2, con la diferencia adicional de que la hora configurada se guarda como la hora de la alarma actual con el mismo cuarto botón del Modo 2 y no se guarda como hora del sistema.

### Modo 4: Temporizador
En los displays de 8 segmentos de la tarjeta se observa 00:00, donde el formato actual representa MM:SS (MM son minutos hasta 99 y SS son segundos hasta 59). Su operación es similar a los Modos 2 y 3 para la configuración, donde los MM o los SS están intermitentes (con el tercer botón se cambia entre MM y SS) hasta que se presiona el cuarto botón, en cuyo caso el temporizador iniciará la cuenta regresiva a partir del último valor configurado en ellos. Si el temporizador está en cuenta regresiva y se vuelve a presionar el cuarto botón el temporizador se pausa hasta que se vuelva a presionar el mismo botón (se toma como botón de Inicio y Pausa). En caso de que el temporizador llegue a 00:00 se detiene la cuenta regresiva, se pone intermitente los dígitos de minutos (Se dispone en modo de configuración de temporizador) y se activa la alarma en el sistema. La alarma se detiene en cuanto se presiona alguno de los cuatro botones o se cambia de modo de operación.

## Habilitación de la Alarma
El sistema tiene un tercer switch que actuará como habilitador de la alarma. Si está activada, la alarma se activa (un LED inicia a ser intermitente) si la hora de la alarma almacenada coincide con la hora actual del sistema. La alarma se detiene cuando se presiona alguno de los cuatro botones utilizados en los modos 2, 3 y 4.

## Nota
La hora del sistema nunca se detiene, independiente del modo en el que se encuentre, siempre continúa su operación así no se muestre en los displays.