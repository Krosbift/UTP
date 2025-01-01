/*
2. En una fiesta de disfraces, cinco amigos se disfrazaron de diferentes animales: tigre,
elefante, león, cebra y jirafa. A continuación, algunas pistas sobre quién se disfrazó de
qué:
• Carlos no se disfrazó de tigre ni de jirafa.
• El león se disfrazó de María.
• Juan no se disfrazó de jirafa.
• Laura se disfrazó de elefante.
• El tigre no fue disfrazado por Pedro ni por María. ¿Quién se disfrazó de qué
animal?
*/
set of int: animales = 1..5;
array[animales] of var animales: disfraces;

constraint forall(i in animales) (disfraces[i] != i);
constraint disfraces[3] == disfraces[2];
constraint disfraces[1] != 5;
constraint disfraces[2] == 1;
constraint disfraces[1] != 3 /\ disfraces[2] != 3;

solve satisfy;
