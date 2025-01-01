/*
4. Cuatro amigos están hablando acerca de los días de la semana. Cada uno hace una
afirmación:
• Antonio dice: "Hoy no es lunes ni martes."
• Beatriz dice: "Ayer era domingo."
• Carlos dice: "Mañana será jueves."
• Daniel dice: "Pasado mañana será sábado." ¿Qué día es hoy?
*/
int: hoy;
array[1..4] of int: dias = [1, 2, 3, 4];

constraint forall(i in dias) (i != hoy);

constraint hoy != 1 /\ hoy != 2;  % Antonio dice: "Hoy no es lunes ni martes."
constraint hoy + 1 == 7;  % Beatriz dice: "Ayer era domingo."
constraint hoy - 1 == 5;  % Carlos dice: "Mañana será jueves."
constraint hoy + 2 == 7;  % Daniel dice: "Pasado mañana será sábado."

solve satisfy;