/*
  1. Seis amigos desean pasar sus vacaciones juntos y deciden, cada dos, utilizar diferentes
  medios de transporte; sabemos que Alejandro no utiliza el coche ya que éste acompaña a
  Benito que no va en avión. Andrés viaja en avión.
  Si Carlos no va acompañado de Darío ni hace uso del avión.
  ¿Podrías decirnos en qué medio de transporte llega a su destino Tomás?
*/

% Definición de variables, se añade un nuevo medio de transporte para que todos puedan ir al viaje.

var bool: AlejandroCarro;
var bool: AlejandroAvion;
var bool: AlejandroBarco;

var bool: BenitoCarro;
var bool: BenitoAvion;
var bool: BenitoBarco;

var bool: AndresCarro;
var bool: AndresAvion;
var bool: AndresBarco;

var bool: CarlosCarro;
var bool: CarlosAvion;
var bool: CarlosBarco;

var bool: DarioCarro;
var bool: DarioAvion;
var bool: DarioBarco;

var bool: TomasCarro;
var bool: TomasAvion;
var bool: TomasBarco;

% Restricciones del ejercicio:

% Nos aseguramos de que cada uno solo tome un medio de transporte.
constraint AlejandroCarro + AlejandroAvion + AlejandroBarco = 1;
constraint BenitoCarro + BenitoAvion + BenitoBarco = 1;
constraint AndresCarro + AndresAvion + AndresBarco = 1;
constraint CarlosCarro + CarlosAvion + CarlosBarco = 1;
constraint DarioCarro + DarioAvion + DarioBarco = 1;
constraint TomasCarro + TomasAvion + TomasBarco = 1;

% Nos aseguramos de agregar u omitir los medios de trasnporte en los que van o en los que no van.
constraint AlejandroCarro = 0;
constraint BenitoAvion = 0;
constraint AndresAvion = 1;
constraint CarlosAvion = 0;

% Nos aseguramos de las demas restricciones de las personas que van acompañadas.
constraint AlejandroCarro + BenitoAvion + BenitoCarro + AlejandroAvion = 0;
constraint CarlosAvion + DarioCarro + DarioBarco = 0;

% Nos aseguramos que solo vatan de a dos por medio de transporte.
constraint AlejandroCarro + BenitoCarro + AndresCarro + CarlosCarro + DarioCarro + TomasCarro = 2;
constraint AlejandroAvion + BenitoAvion + AndresAvion + CarlosAvion + DarioAvion + TomasAvion = 2;
constraint AlejandroBarco + BenitoBarco + AndresBarco + CarlosBarco + DarioBarco + TomasBarco = 2;

% Ejecutar el modelo
solve satisfy;