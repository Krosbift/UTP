/**
 * Módulo: relojMMSS
 * Descripción:
 *   Este módulo implementa un reloj descendente en formato MM:SS. 
 *   Permite configurar el tiempo inicial a través de las señales de entrada `newSegundosT` y `newMinutosT`. 
 *   El reloj comienza a contar cuando ambos interruptores (`switch1` y `switch2`) están activos, y el conteo puede ser detenido y reiniciado con la señal de restablecimiento (`reset`).
 *   El reloj cuenta de manera descendente, mostrando el tiempo restante en las salidas `segundosT` y `minutosT`. 
 *   Además, se activa una alarma (`cierreAlarma`) cuando el tiempo llega a `00:00`.
 * 
 * Entradas:
 *   - clk1hz: Reloj de 1 Hz utilizado para el conteo descendente.
 *   - reset: Señal de restablecimiento. Cuando es 0, el reloj se reinicia.
 *   - switch1: Control para iniciar el conteo.
 *   - switch2: Control para iniciar el conteo.
 *   - newSegundosT: Segundos iniciales del reloj (0-59).
 *   - newMinutosT: Minutos iniciales del reloj (0-99).
 *   - establecer: Señal para confirmar la configuración y comenzar el conteo.
 * 
 * Salidas:
 *   - configuration: Indicador de que el reloj está en modo de configuración (1) o no (0).
 *   - control: Control del conteo descendente (1 para contar, 0 para detenido).
 *   - cierreAlarma: Se activa cuando el reloj llega a `00:00`.
 *   - segundosT: Valor actual de los segundos en formato MM:SS.
 *   - minutosT: Valor actual de los minutos en formato MM:SS.
 */

module relojMMSS(
  input wire clk1hz,
  input wire reset,
  input wire switch1,
  input wire switch2,
  input wire [5:0] newSegundosT,
  input wire [6:0] newMinutosT,
  input wire establecer,
  output reg configuration = 1'b0,
  output reg control = 1'b0,
  output reg cierreAlarma = 1'b0,
  output reg [5:0] segundosT,
  output reg [6:0] minutosT
);
  reg setValues;
  
  always @(*) begin
	if (switch1 && switch2 && ~establecer) begin
	  setValues = 1;
	end else begin
	  setValues = 0;
	end
  end
  
  always @(posedge clk1hz or negedge reset or posedge setValues) begin
    if (reset == 0) begin
      segundosT <= 0;
      minutosT <= 0;
    end else if (setValues) begin
      segundosT <= newSegundosT;
      minutosT <= newMinutosT;
      if (establecer == 0) begin
        control <= ~control;
        configuration <= 1;
      end
    end else if (switch1 == 1 && switch2 == 1) begin
      if (control == 1) begin
        if (segundosT == 0) begin
          if (minutosT == 0) begin
            cierreAlarma <= 1;
            configuration <= 0;
            control <= 0;
          end else begin
            minutosT <= minutosT - 1;
            segundosT <= 59;
          end
        end else begin
          segundosT <= segundosT - 1;
        end
      end
    end
  end
  
endmodule
