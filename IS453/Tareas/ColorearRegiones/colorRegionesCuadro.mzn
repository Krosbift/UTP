%ver anexo de dibujo del cuadro

%número de colores a utilizar:
int: nc = 3;

%Variables que representan las regiones:
var 1..nc: A; var 1..nc: B; var 1..nc: C; var 1..nc: D; var 1..nc: E;

%Restricciones (cada región no puede tener una frontera con el mismo color):
constraint A != B /\ A != C /\ A != D;
constraint B != C /\ B != E;
constraint C != D /\ C != E;
constraint D != E;

%Solución:
solve satisfy;