/**
 * Módulo: controlTemporizador
 * Descripción:
 *   Este módulo controla un temporizador que permite configurar y mostrar el tiempo restante en formato MM:SS.
 *   Utiliza dos interruptores (`switch1` y `switch2`) para activar el control del temporizador y ajustar el tiempo de configuración.
 *   El temporizador se puede incrementar o decrementar en segundos y minutos, y los valores se mantienen mientras se configurado. 
 *   El temporizador también se activa cuando se ajustan sus valores.
 *   Además, una alarma se activa cuando el temporizador llega a 00:00.
 *
 * Entradas:
 *   - clk1hz: Reloj de 1 Hz utilizado para el conteo de cambios en la configuración del temporizador.
 *   - clk2hz: Reloj de 2 Hz utilizado para actualizar la salida del temporizador.
 *   - switch1: Interruptor de control para la activación de la configuración del temporizador.
 *   - switch2: Interruptor de control para la activación de la configuración del temporizador.
 *   - incrementar: Señal para incrementar el tiempo del temporizador.
 *   - decrementar: Señal para decrementar el tiempo del temporizador.
 *   - cambiar: Señal para alternar entre los valores de minutos y segundos.
 *   - establecer: Señal para confirmar la configuración y comenzar el conteo.
 *   - configuration: Indica si el temporizador está en configuración (1) o en ejecución (0).
 *   - control: Indica si el temporizador está en modo de conteo descendente.
 *   - cierreAlarma: Activador de la alarma cuando el temporizador llega a 00:00.
 *   - segundosT: Valor actual de los segundos en el temporizador.
 *   - minutosT: Valor actual de los minutos en el temporizador.
 *
 * Salidas:
 *   - mostrarLed2: Señal para activar o desactivar un LED (en caso de alarma).
 *   - setTimerSegundos: Valor de los segundos del temporizador configurado.
 *   - setTimerMinutos: Valor de los minutos del temporizador configurado.
 *   - newSegundosT: Nuevo valor de los segundos configurados.
 *   - newMinutosT: Nuevo valor de los minutos configurados.
 */
module controlTemporizador(
  input wire clk1hz, 
  input wire clk2hz,		
  input wire switch1,		
  input wire switch2,           
  input wire incrementar,       
  input wire decrementar,  
  input wire cambiar,    
  input wire establecer,
  input wire configuration,
  input wire control,
  input wire cierreAlarma, 			
  input wire [5:0] segundosT,
  input wire [6:0] minutosT,
  output reg mostrarLed2 = 1'b0,
  output reg [5:0] setTimerSegundos, 		
  output reg [6:0] setTimerMinutos, 			
  output reg [5:0] newSegundosT = 6'd0,
  output reg [6:0] newMinutosT = 7'd0
);
  reg valorCambiar = 1'b0;
  reg mostrarNumero = 1'b0;
  
  always @(posedge clk1hz) begin
    if (switch1 == 1 && switch2 == 1) begin
      if (configuration == 0 && control == 0) begin
        if (cambiar == 0) begin
          valorCambiar = ~valorCambiar;
        end
        if (valorCambiar == 0) begin
          if (incrementar == 0 && decrementar == 1) begin
            if (newSegundosT == 59) begin
              newSegundosT = 0;
            end else begin
              newSegundosT = newSegundosT + 1;
            end
          end else if (decrementar == 0 && incrementar == 1) begin
            if (newSegundosT == 0) begin
              newSegundosT <= 59;
            end else begin
              newSegundosT <= newSegundosT - 1;
            end
          end
        end else begin
          if (incrementar == 0 && decrementar == 1) begin
            if (newMinutosT == 99) begin
              newMinutosT = 0;
            end else begin
              newMinutosT = newMinutosT + 1;
            end
          end else if (decrementar == 0 && incrementar == 1) begin
            if (newMinutosT == 0) begin
              newMinutosT = 99;
            end else begin
              newMinutosT = newMinutosT - 1;
            end
          end
        end
      end else begin
      	newSegundosT <= segundosT;
        newMinutosT <= newMinutosT;
      end
    end
  end
  
  always @(posedge clk2hz) begin
    if (configuration == 0) begin
      if (switch1 == 1 && switch2 == 1) begin 
        if (mostrarNumero == 0) begin
          if (valorCambiar == 0) begin
          	setTimerSegundos = 15;
          end else begin
          	setTimerMinutos = 15;
          end
        end else begin
          setTimerSegundos = newSegundosT;
          setTimerMinutos = newMinutosT;
        end
        mostrarNumero = ~mostrarNumero;
        if (cierreAlarma == 1) begin
          mostrarLed2 = ~mostrarLed2;
      	end
      end
    end
  end
  
endmodule