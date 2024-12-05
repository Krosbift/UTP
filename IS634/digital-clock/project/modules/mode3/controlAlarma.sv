/*
 * ---------------------------------------------------------------------
 * Módulo: controlAlarma
 * Propósito: Este módulo permite configurar la hora de una alarma en un reloj digital y activarla cuando la hora configurada coincida con la hora del sistema.
 *            Los usuarios pueden ajustar la hora y los minutos de la alarma utilizando los botones de incrementar, decrementar y cambiar. 
 *            La alarma se activa cuando la hora del sistema coincide con la hora configurada y el switch de activación de la alarma está encendido.
 * 
 * Entradas:
 *  - clk1hz           : Reloj de 1 Hz para la lógica de la alarma (comparación con el reloj del sistema).
 *  - clk2hz           : Reloj de 2 Hz para cambiar entre los valores configurados de la alarma.
 *  - switch1          : Switch que permite activar el modo de configuración de la alarma.
 *  - switch2          : Switch que, junto con switch1, determina el modo de configuración de la alarma.
 *  - switch3          : Switch que activa la alarma cuando la hora del sistema coincide con la hora configurada.
 *  - incrementar      : Botón para incrementar la unidad seleccionada (hora o minuto) de la alarma.
 *  - decrementar      : Botón para decrementar la unidad seleccionada (hora o minuto) de la alarma.
 *  - cambiar          : Señal para cambiar entre horas y minutos para la configuración de la alarma.
 *  - establecer       : Señal para establecer la hora configurada de la alarma.
 *  - minutos          : Minutos actuales del reloj del sistema.
 *  - horas            : Horas actuales del reloj del sistema.
 * 
 * Salidas:
 *  - mostrarLed       : Controla el parpadeo de un LED para indicar que la alarma ha sonado.
 *  - setAlarmaMinutos : Minutos ajustados de la alarma.
 *  - setAlarmaHoras   : Horas ajustadas de la alarma.
 *  - newAlarmaMinutos : Nuevos valores de minutos configurados para la alarma.
 *  - newAlarmaHoras   : Nuevos valores de horas configurados para la alarma.
 * 
 * Funcionamiento:
 *  - En el modo de configuración (cuando `switch1` está activado y `switch2` está desactivado), el usuario puede ajustar la hora y los minutos de la alarma.
 *  - Se alterna entre la configuración de horas o minutos con la señal `cambiar`, y los botones `incrementar` y `decrementar` permiten cambiar el valor de la unidad seleccionada.
 *  - La hora de la alarma se ajusta dentro de los rangos válidos (0-23 para las horas y 0-59 para los minutos).
 *  - En cada ciclo de reloj de 1 Hz (`clk1hz`), se compara la hora y los minutos configurados con el reloj del sistema. Si coinciden y `switch3` está activado, la alarma se activa (se activa el `cierreAlarma`).
 *  - Cuando la alarma se activa, el LED (controlado por `mostrarLed`) parpadea. Este parpadeo continúa hasta que se desactiva la alarma.
 *  - El ciclo de reloj de 2 Hz (`clk2hz`) alterna entre mostrar los valores configurados de la alarma y un valor por defecto (15:15).
 * ---------------------------------------------------------------------
 */
module controlAlarma(
  input wire clk1hz,
  input wire clk2hz,
  input wire switch1,
  input wire switch2,
  input wire switch3,
  input wire incrementar,
  input wire decrementar,
  input wire cambiar,
  input wire establecer,
  input wire [5:0] minutos,
  input wire [4:0] horas,
  output reg mostrarLed = 1'b0,
  output reg [5:0] setAlarmaMinutos = 6'd0,
  output reg [4:0] setAlarmaHoras = 5'd8,
  output reg [5:0] newAlarmaMinutos = 6'd0,
  output reg [4:0] newAlarmaHoras = 5'd8
  
);
  reg valorCambiar = 1'b0;
  reg mostrarNumero = 1'b0;
  reg cierreAlarma = 1'b0;
  
  always @(posedge clk2hz) begin
    if (switch1 == 0 && switch2 == 1) begin
      if (cambiar == 0) begin
        valorCambiar = ~valorCambiar;
      end
      if (valorCambiar == 0) begin
        if (incrementar == 0 && decrementar == 1) begin
          if (newAlarmaMinutos == 59) begin
            newAlarmaMinutos = 0;
          end else begin
            newAlarmaMinutos = newAlarmaMinutos + 1;
          end
        end else if (decrementar == 0 && incrementar == 1) begin
          if (newAlarmaMinutos == 0) begin
            newAlarmaMinutos = 59;
          end else begin
            newAlarmaMinutos = newAlarmaMinutos - 1;
          end
        end
      end else begin
        if (incrementar == 0 && decrementar == 1) begin
          if (newAlarmaHoras == 23) begin
            newAlarmaHoras = 0;
          end else begin
            newAlarmaHoras = newAlarmaHoras + 1;
          end
        end else if (decrementar == 0 && incrementar == 1) begin
          if (newAlarmaHoras == 0) begin
            newAlarmaHoras = 23;
          end else begin
            newAlarmaHoras = newAlarmaHoras - 1;
          end
        end
      end
    end
  end
  
  always @(posedge clk1hz) begin
    if (minutos == newAlarmaMinutos && horas == newAlarmaHoras && switch3 == 1) begin
      cierreAlarma = 1;
    end
    if (incrementar == 0 || decrementar == 0 || cambiar == 0 || establecer == 0) begin
      cierreAlarma = 0;
    end
  end
  
  always @(posedge clk2hz) begin
    if (switch1 == 0 && switch2 == 1) begin 
      if (mostrarNumero == 0) begin
        if (valorCambiar == 0) begin
          setAlarmaMinutos = 15;
        end else begin
          setAlarmaHoras = 15;
        end
      end else begin
        setAlarmaMinutos = newAlarmaMinutos;
        setAlarmaHoras = newAlarmaHoras;
      end
      mostrarNumero = ~mostrarNumero;
    end
    if (cierreAlarma == 1) begin
      mostrarLed = ~mostrarLed;
    end
  end
  
endmodule

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