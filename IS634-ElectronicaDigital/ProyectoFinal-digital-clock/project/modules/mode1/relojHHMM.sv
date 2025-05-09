/*
 * ---------------------------------------------------------------------
 * Módulo: relojHHMM
 * Propósito: Este módulo implementa un reloj digital en formato HH:MM que 
 *            cuenta segundos, minutos y horas, y permite configurar manualmente 
 *            la hora mediante entradas específicas. Utiliza un reloj base (`clk1hz`) 
 *            que opera a 1 Hz para el conteo de tiempo.
 * 
 * Entradas:
 *  - clk        : Señal de reloj principal, utilizada para la sincronización.
 *  - clk1hz     : Señal de reloj de 1 Hz, utilizada como base para el conteo de tiempo.
 *  - reset      : Señal de reinicio asíncrono (activa en bajo), que reinicia 
 *                 los valores del reloj a cero.
 *  - switch1    : Señal de control para habilitar la configuración manual de la hora.
 *  - switch2    : Señal adicional para control de configuración (reserva futura).
 *  - newMinutos : Entrada de 6 bits que indica los minutos a configurar manualmente.
 *  - newHoras   : Entrada de 5 bits que indica las horas a configurar manualmente.
 *  - establecer : Señal que indica cuándo aplicar la configuración manual.
 * 
 * Salidas:
 *  - minutos : Salida de 6 bits que indica los minutos actuales del reloj.
 *  - horas   : Salida de 5 bits que indica las horas actuales del reloj.
 * 
 * Funcionamiento:
 *  1. **Reinicio:**
 *     - Cuando `reset` está activo (bajo), los valores de horas, minutos y segundos se 
 *       reinician a cero.
 * 
 *  2. **Configuración Manual:**
 *     - Si `switch1` está activo y `establecer` se activa, los valores de 
 *       `newHoras` y `newMinutos` se asignan directamente a las salidas.
 * 
 *  3. **Conteo Automático:**
 *     - Cuando `clk1hz` genera un pulso, el reloj incrementa los segundos.
 *     - Al alcanzar 59 segundos, los segundos se reinician a cero y los minutos 
 *       se incrementan.
 *     - Al alcanzar 59 minutos, los minutos se reinician a cero y las horas 
 *       se incrementan.
 *     - Al alcanzar 23 horas, las horas se reinician a cero.
 * 
 * Aplicaciones Típicas:
 *  - Implementación de relojes digitales simples.
 *  - Cronómetros o sistemas que requieren conteo de tiempo en formato HH:MM.
 * 
 * Notas:
 *  - La señal `clk1hz` debe ser generada externamente mediante un divisor de frecuencia.
 *  - `switch2` está reservado para funcionalidades adicionales que podrían incluirse en 
 *    versiones futuras.
 * ---------------------------------------------------------------------
 */
module relojHHMM(
  input wire clk,
  input wire clk1hz,
  input wire reset,
  input wire switch1,
  input wire switch2,
  input wire [5:0] newMinutos,
  input wire [4:0] newHoras,
  input wire establecer,
  output reg [5:0] minutos,
  output reg [4:0] horas
);
  reg [5:0] segundos;
  reg setValues;
  
  always @(*) begin
	if (switch1 && ~switch2 && ~establecer) begin
	  setValues = 1;
	end else begin
	  setValues = 0;
	end
  end
  
  always @(posedge clk) begin
    if (reset == 0) begin
      segundos = 0;
      minutos = 0;
      horas = 0;
    end else 
    if (clk1hz) begin
	  if (setValues) begin
        minutos = newMinutos;
        horas = newHoras;
      end else begin
        if (segundos == 59) begin
          segundos = 0;
          if (minutos == 59) begin
            minutos <= 0;
            if (horas == 23) begin
              horas = 0;
            end else begin
              horas = horas + 1;
            end
          end else begin
            minutos = minutos + 1;
          end
        end else begin
          segundos = segundos + 1;
        end
      end
    end
  end
endmodule