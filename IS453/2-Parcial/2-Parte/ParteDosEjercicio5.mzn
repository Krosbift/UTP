include "globals.mzn";

% Número de cursos y espacios
int: num_cursos = 7;
int: num_espacios = 4;

% Variables de decisión
array[1..num_cursos] of var 1..num_espacios: curso_a_espacio;
array[1..num_cursos] of var 1..num_cursos: orden_cursos;

% Restricciones de orden
constraint orden_cursos[4] < orden_cursos[6];
constraint orden_cursos[5] < orden_cursos[7];
constraint orden_cursos[6] < orden_cursos[2];

% Restricciones de paralelismo
constraint forall(i in 2..num_cursos) (curso_a_espacio[1] != curso_a_espacio[i]);
constraint forall(i in [3,6]) (curso_a_espacio[2] != curso_a_espacio[i]);
constraint forall(i in [4,5,6]) (curso_a_espacio[3] != curso_a_espacio[i]);
constraint forall(i in [5,6]) (curso_a_espacio[4] != curso_a_espacio[i]);
constraint curso_a_espacio[5] != curso_a_espacio[7];

% Resolviendo el modelo
solve satisfy;


