/*
 * ---------------------------------------------------------------------
 * Módulo: divisorConteo
 * Propósito: Este módulo genera una señal de habilitación (`enable7segmentos`) 
 *            que alterna su estado (parpadea) a una frecuencia de 2 Hz. 
 *            Está diseñado para controlar el parpadeo de números en 
 *            displays de 7 segmentos o aplicaciones similares.
 * 
 * Entradas:
 *  - clk   : Señal de reloj base de alta frecuencia (50 MHz), utilizada 
 *            como referencia para el conteo.
 *  - reset : Señal de reinicio síncrono (activa en bajo), que reinicia 
 *            el contador y la salida `enable7segmentos`.
 * 
 * Salidas:
 *  - enable7segmentos : Señal que alterna su estado a una frecuencia de 
 *                       2 Hz, generando un parpadeo uniforme.
 * 
 * Parámetros:
 *  - divisor : Especifica el valor que determina el intervalo entre 
 *              cambios de estado de `enable7segmentos`. En este caso:
 *                  divisor = Frecuencia del reloj base / (2 * Frecuencia deseada)
 *                         = 50,000,000 Hz / (2 * 2 Hz)
 *                         = 25,000,000.
 * 
 * Funcionamiento:
 *  - Un contador de 32 bits se incrementa con cada flanco positivo de 
 *    la señal `clk`. Cuando el contador alcanza el valor `divisor - 1`, 
 *    se reinicia y alterna el estado de la señal `enable7segmentos`.
 *  - Si la señal `reset` se activa (valor bajo), el contador y la salida 
 *    se reinician inmediatamente. Esto asegura un inicio consistente 
 *    del parpadeo.
 * 
 * Aplicaciones Típicas:
 *  - Control de parpadeo en displays de 7 segmentos.
 *  - Señales visuales o de control temporizadas a baja frecuencia.
 * ---------------------------------------------------------------------
 */
module divisorConteo(
  input wire clk,
  input wire reset,
  output reg enable7segmentos
);
  reg[31:0] counter;
  parameter divisor = 12500000; // para una señal de 50MHz
  
  always @(posedge clk) begin
    if (reset == 0) begin
      counter = 0;
      enable7segmentos = 0;
    end else if (counter == divisor - 1) begin
      counter = 0;
      enable7segmentos = ~enable7segmentos;
    end else begin
      counter = counter + 1;
    end
  end
  
endmodule
