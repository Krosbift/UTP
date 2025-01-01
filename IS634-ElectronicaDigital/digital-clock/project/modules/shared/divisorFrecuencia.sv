/*
 * ---------------------------------------------------------------------
 * Módulo: divisorFrecuencia
 * Propósito: Este módulo genera una señal de habilitación (`clk1hz`) que
 *            se activa durante un único ciclo del reloj base (50 MHz) 
 *            cada segundo. Está diseñado para aplicaciones que requieren 
 *            un pulso periódico cada segundo en lugar de una señal de 
 *            reloj continua.
 * 
 * Entradas:
 *  - clk   : Señal de reloj base de alta frecuencia (50 MHz), utilizada
 *            para el conteo de ciclos.
 *  - reset : Señal de reinicio asíncrono (activa en bajo), que reinicia 
 *            el contador y la salida `clk1hz`.
 * 
 * Salidas:
 *  - clk1hz : Señal de habilitación que se activa (valor 1) durante un 
 *             único ciclo de `clk` cada segundo.
 * 
 * Parámetros:
 *  - divisor : Especifica el valor que determina el intervalo entre 
 *              activaciones de `clk1hz`. En este caso, corresponde al 
 *              número de ciclos del reloj base que transcurren en un 
 *              segundo:
 *                  divisor = Frecuencia del reloj base / Frecuencia deseada
 *                         = 50,000,000 Hz / 1 Hz
 *                         = 50,000,000.
 * 
 * Funcionamiento:
 *  - Un contador de 32 bits se incrementa con cada flanco positivo de 
 *    `clk`. Cuando el contador alcanza el valor `divisor - 1`, se reinicia 
 *    y activa la señal `clk1hz` durante un ciclo del reloj base.
 *  - Durante los demás ciclos, `clk1hz` permanece en 0.
 *  - Si la señal `reset` se activa (valor bajo), el contador y la salida 
 *    se reinician inmediatamente.
 * 
 * Nota:
 *  - Cambio sugerido por Ramiro: La salida `clk1hz` actúa como una señal 
 *    de habilitación en lugar de un reloj alternado.
 * ---------------------------------------------------------------------
 */
module divisorFrecuencia(
  input wire clk,
  input wire reset,
  output reg clk1hz
);
  reg[31:0] counter;
  parameter divisor = 25000000;
  
  always @(posedge clk or negedge reset) begin
    if (reset == 0) begin
      counter = 0;
      clk1hz = 0;
    end else if (counter == divisor - 1) begin
      counter = 0;
      clk1hz = ~clk1hz;
    end else begin
      counter = counter + 1;
    end
  end
  
endmodule
