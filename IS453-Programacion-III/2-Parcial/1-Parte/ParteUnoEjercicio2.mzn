% Frutas: 
var int: manzanas;
var int: naranjas;

% Restricciones
constraint manzanas + naranjas = 100;
constraint 2*manzanas + 3*naranjas = 250;

% Ejecutar el modelo
solve satisfy;