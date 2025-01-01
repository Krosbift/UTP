% Datos del problema
int: capacidad_peso = 600;
int: peso_pastilla_grande = 40;
int: peso_pastilla_pequena = 30;
int: beneficio_grande = 2;
int: beneficio_pequena = 1;

% Variables de decisión
var int: x; % Cantidad de pastillas grandes
var int: y; % Cantidad de pastillas pequenas

% Restricciones
constraint peso_pastilla_grande * x + peso_pastilla_pequena * y <= capacidad_peso;
constraint x >= 3;
constraint y >= 2 * x;

% Función objetivo
var int: beneficio_total = beneficio_grande * x + beneficio_pequena * y;

% Maximizar el beneficio total
solve maximize beneficio_total;

% Imprimir la solución
output ["Cantidad de pastillas grandes: ", show(x), "\n"] ++
       ["Cantidad de pastillas pequenas: ", show(y), "\n"] ++
       ["Beneficio total: $", show(beneficio_total)];
