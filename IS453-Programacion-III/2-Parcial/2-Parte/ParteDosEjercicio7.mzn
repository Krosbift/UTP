include "globals.mzn";

% Tamaño de la matriz
int: M;

% Variables de decisión
array[1..M, 1..M] of var 1..M: matriz;

% Restricciones
constraint forall(i in 2..M, j in 1..M) (
    if matriz[i,j] = M then matriz[i-1,j] = 1 else true endif
);

constraint forall(i in 1..M, j in 2..(M-1)) (
    if matriz[i,j] mod 2 = 0 then 
        matriz[i,j-1] = matriz[i,j] - 1 /\ matriz[i,j+1] = matriz[i,j] + 1 
    else true endif
);

constraint forall(k in 1..M) (
    sum(i in 1..M, j in 1..M) (bool2int(matriz[i,j] = k)) >= 2
);

% Resolviendo el modelo
solve satisfy;
