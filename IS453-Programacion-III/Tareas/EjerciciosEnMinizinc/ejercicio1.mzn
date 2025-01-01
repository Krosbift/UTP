/*
1. Tres hermanos, Ana, Benito, y Carla, tienen edades diferentes. Carla es más joven que 
Benito. Ana no es la más joven. Carla no es la más vieja. Benito no es el más joven. 
¿Cuál es la edad de cada uno?
*/
include "globals.mzn";

int: Ana;
int: Benito;
int: Carla;

set of int: Edades = 1..100;
array[1..3] of Edades: edades = [Ana, Benito, Carla];


constraint Carla < Benito;
constraint Ana < Benito;
constraint Carla < Ana /\ Carla < Benito;
constraint Benito > Ana /\ Benito > Carla;
constraint all_different(edades);

solve satisfy;

output ["Ana: ", show(Ana), "\n", "Benito: ", show(Benito), "\n", "Carla: ", show(Carla), "\n"];