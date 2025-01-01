% Definición de los datos
int: n = 6; % Número de artículos
set of int: ITEMS = 1..n; % Conjunto de artículos
array[ITEMS] of int: value = [15, 20, 5, 10, 2, 8]; % Valor de los artículos
array[ITEMS] of int: weight = [3, 5, 1, 4, 2, 1]; % Peso de los artículos
int: W = 15; % Capacidad de la mochila

% Variables del modelo
array[ITEMS] of var 0..1: x; % x[i] es 1 si el artículo i se incluye en la mochila, y 0 en caso contrario

% Función objetivo
var int: total_value = sum(i in ITEMS)(x[i]*value[i]);

% Restricciones
constraint sum(i in ITEMS)(x[i]*weight[i]) <= W;

% Ejecucion del modelo
solve maximize total_value;
output [ "Artículo \(i): " ++ show(x[i]) ++ "\n" | i in ITEMS];
