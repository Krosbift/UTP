/*
 * ---------------------------------------------------------------------
 * Módulo: cambioHora
 * Propósito: Este módulo permite la configuración dinámica de la hora y los minutos de un reloj digital.
 *            Se basa en las entradas de los switches y botones para permitir la modificación de la hora y los minutos,
 *            y cambiar entre la configuración de horas y minutos mediante el modo de cambio.
 *            El módulo también permite incrementar o decrementar la hora y los minutos, y la selección de la unidad a cambiar (hora o minuto).
 * 
 * Entradas:
 *  - clk2hz       : Señal de reloj de 2 Hz utilizada para hacer cambios de configuración en intervalos regulares.
 *  - switch1      : Switch que activa el modo de configuración del reloj.
 *  - switch2      : Switch que, junto con switch1, determina el modo de configuración.
 *  - incrementar  : Señal para incrementar la unidad seleccionada (hora o minuto).
 *  - decrementar  : Señal para decrementar la unidad seleccionada (hora o minuto).
 *  - cambiar      : Señal para cambiar entre horas y minutos, seleccionando la unidad que se ajustará.
 *  - establecer   : Señal que activa la configuración manual de la hora.
 *  - minutos      : Minutos actuales del reloj, para ser utilizados en el ajuste de la hora.
 *  - horas        : Horas actuales del reloj, para ser utilizadas en el ajuste de la hora.
 * 
 * Salidas:
 *  - setMinutos   : Salida de los minutos ajustados en el modo de configuración.
 *  - setHoras     : Salida de las horas ajustadas en el modo de configuración.
 *  - newMinutos   : Nuevos valores de minutos ajustados por el usuario.
 *  - newHoras     : Nuevos valores de horas ajustados por el usuario.
 * 
 * Funcionamiento:
 *  - Cuando `switch1` está activado y `switch2` desactivado, el módulo entra en modo de configuración.
 *  - El valor de `cambiar` alterna entre la configuración de las horas o los minutos. Si `valorCambiar` es 0, se ajustan los minutos; si es 1, se ajustan las horas.
 *  - Los botones de `incrementar` y `decrementar` permiten cambiar el valor de la unidad seleccionada.
 *  - En cada ciclo de reloj de 2 Hz (`clk2hz`), si el modo de configuración está activo, se alternan entre mostrar el valor configurado (minutos/horas) y un valor de ejemplo (15:15).
 *  - La hora y los minutos se mantienen en valores válidos, con las horas entre 0-23 y los minutos entre 0-59.
 *  - Cuando se activa la señal `establecer`, el módulo configura las nuevas horas y minutos, usando los valores de entrada.
 * ---------------------------------------------------------------------
 */
module relojMMSS(
  input wire clk1hz,
  input wire reset,
  input wire switch1,
  input wire switch2,
  input wire [5:0] newSegundosT,
  input wire [6:0] newMinutosT,
  input wire establecer,
  output reg configuration,
  output reg control,
  output reg cierreAlarma = 1'b0,
  output reg [5:0] segundosT,
  output reg [6:0] minutosT
);
  reg setValues;
  
  always @(posedge clk1hz) begin
	if (switch1 && switch2 && ~establecer) begin
	  setValues = 1;
	end else begin
	  setValues = 0;
	end
  end
  
  always @(posedge clk1hz) begin
  	 if (switch1 == 0 || switch2 == 0) begin
		control <= 0;
		configuration <= 0;
	 end
    if (switch1 == 1 && switch2 == 1) begin
		 if (reset == 0) begin
			segundosT <= 0;
			minutosT <= 0;
		 end else if (setValues) begin
			segundosT <= newSegundosT;
			minutosT <= newMinutosT;
		 end 
	   if (establecer == 0) begin
        control <= ~control;
        configuration <= ~configuration;
      end
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