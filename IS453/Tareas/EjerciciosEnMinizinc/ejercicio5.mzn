/*
5. Cinco personas, A, B, C, D y E, se paran en una fila. Cada una de ellas lleva un sombrero
de un color: rojo, azul, verde, amarillo o blanco. No pueden ver el color de su propio
sombrero, pero pueden ver los sombreros de las personas delante de ellas. Se sabe
que:
• A ve a B y C, y sus sombreros son de diferentes colores.
• B ve a C, y sus sombreros son del mismo color.
• C no ve ningún sombrero.
• D ve a C y A, y sus sombreros son de diferentes colores.
• E ve a D y B, y sus sombreros son de diferentes colores. ¿Qué color de
sombrero lleva cada persona?
*/
set of int: colores = 1..5;
array[colores] of var colores: sombreros_a;
array[colores] of var colores: sombreros_b;
array[colores] of var colores: sombreros_c;
array[colores] of var colores: sombreros_d;
array[colores] of var colores: sombreros_e;

constraint forall(c in colores) (sombreros_a[c] != sombreros_b[c] /\ sombreros_a[c] != sombreros_c[c]);
constraint sombreros_b == sombreros_c;
constraint forall(c in colores) (sombreros_d[c] != sombreros_a[c] /\ sombreros_d[c] != sombreros_c[c]);
constraint forall(c in colores) (sombreros_e[c] != sombreros_d[c] /\ sombreros_e[c] != sombreros_b[c]);

solve satisfy;