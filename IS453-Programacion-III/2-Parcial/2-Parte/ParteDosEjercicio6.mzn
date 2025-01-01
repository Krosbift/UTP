include "globals.mzn";

int: N;

array[1..N, 1..N] of var 1..N*N: cuadrado;

% Restricciones para asegurar que todos los números del cuadrado son diferentes
constraint alldifferent(cuadrado);

% Restricciones para la suma de filas y columnas
constraint forall(i in 1..N) (sum(j in 1..N) (cuadrado[i,j]) = sum(j in 1..N) (cuadrado[j,i]));
constraint sum(i in 1..N) (cuadrado[1,i]) = sum(i in 1..N) (cuadrado[2,i]);
constraint sum(i in 1..N) (cuadrado[2,i]) = sum(i in 1..N) (cuadrado[3,i]);

% Restricciones para la suma de las diagonales
constraint sum(i in 1..N) (cuadrado[i,i]) = sum(i in 1..N) (cuadrado[N-i+1,i]);

solve satisfy;