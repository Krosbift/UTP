% Definición de las variables
var 0..100: X; % Cantidad del compuesto X
var 0..100: Y; % Cantidad del compuesto Y

% Restricciones de la dieta
constraint 1*X + 5*Y >= 15; % Restricción para la sustancia A
constraint 5*X + 1*Y >= 15; % Restricción para la sustancia B

% Resolviendo el modelo
solve minimize 10*X + 25*Y;
