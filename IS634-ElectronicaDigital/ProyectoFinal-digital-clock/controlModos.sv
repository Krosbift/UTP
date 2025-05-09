/*
  Este archivo es la versión de todo el proyecto en uno
  solo, con el mismo funcionamiento pero compactos en uno
  mismo archivo para facilitar su compilación
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

module divisorConteo(
  input wire clk,
  input wire reset,
  output reg clk2hz
);
  reg[31:0] counter;
  parameter divisor = 12500000;
  
  always @(posedge clk or negedge reset) begin
    if (reset == 0) begin
      counter = 0;
      clk2hz = 0;
    end else if (counter == divisor - 1) begin
      counter = 0;
      clk2hz = ~clk2hz;
    end else begin
      counter = counter + 1;
    end
  end
  
endmodule

module display7segmentos(
  input wire [3:0] digito,
  output reg [6:0] segmento
);
  
  always @(*) begin
    case (digito)
      4'd0: segmento = 7'b1000000;
      4'd1: segmento = 7'b1111001;
      4'd2: segmento = 7'b0100100;
      4'd3: segmento = 7'b0110000;
      4'd4: segmento = 7'b0011001;
      4'd5: segmento = 7'b0010010;
      4'd6: segmento = 7'b0000010;
      4'd7: segmento = 7'b1111000;
      4'd8: segmento = 7'b0000000;
      4'd9: segmento = 7'b0010000;
      default: segmento = 7'b1111111;
    endcase
  end
  
endmodule

module relojHHMM(
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
  
  always @(posedge clk1hz or negedge reset) begin
    if (reset == 0) begin
      segundos = 0;
      minutos = 0;
      horas = 0;
    end else if (setValues) begin
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
  
endmodule

module cambioHora(
  input wire clk2hz,
  input wire switch1,
  input wire switch2,
  input wire incrementar,
  input wire decrementar,
  input wire cambiar,
  input wire establecer,
  input wire [5:0] minutos,
  input wire [4:0] horas,
  output reg [5:0] setMinutos,
  output reg [4:0] setHoras,
  output reg [5:0] newMinutos = 6'd0,
  output reg [4:0] newHoras = 5'd0
);
  reg valorCambiar = 1'b0;
  reg inicializarTiempo = 1'b0;
  reg mostrarNumero = 1'b0;
  
  always @(posedge clk2hz) begin
    if (switch1 == 0 || switch2 == 1) begin
    	inicializarTiempo = 0;
    end
    if (switch1 == 1 && switch2 == 0) begin
      if (inicializarTiempo == 0) begin
        inicializarTiempo = 1;
        newMinutos = minutos;
        newHoras = horas;
      end else begin
        if (cambiar == 0) begin
          valorCambiar = ~valorCambiar;
        end
        if (valorCambiar == 0) begin
          if (incrementar == 0 && decrementar == 1) begin
            if (newMinutos == 59) begin
              newMinutos = 0;
            end else begin
              newMinutos = newMinutos + 1;
            end
          end else if (decrementar == 0 && incrementar == 1) begin
            if (newMinutos == 0) begin
              newMinutos = 59;
            end else begin
              newMinutos = newMinutos - 1;
            end
          end
        end else begin
          if (incrementar == 0 && decrementar == 1) begin
            if (newHoras == 23) begin
              newHoras = 0;
            end else begin
              newHoras = newHoras + 1;
            end
          end else if (decrementar == 0 && incrementar == 1) begin
            if (newHoras == 0) begin
              newHoras = 23;
            end else begin
              newHoras = newHoras - 1;
            end
          end
        end
      end
    end
  end
  
  always @(posedge clk2hz) begin
    if (switch1 == 1 && switch2 == 0) begin 
      if (mostrarNumero == 0) begin
        if (valorCambiar == 0) begin
          setMinutos = 15;
        end else begin
          setHoras = 15;
        end
      end else begin
        setMinutos = newMinutos;
        setHoras = newHoras;
      end
      mostrarNumero = ~mostrarNumero;
    end
  end
  
endmodule

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

module controlModos(
  input wire clk,                  // señal de reloj de la FPGA (señal a 50MHz)
  input wire reset,                // señal de reset de la FPGA (pulsador)
  input wire switch1,              // switch número uno para cambiar entre los modos (switch)
  input wire switch2,              // switch número dos para cambiar entre los modos (switch)
  input wire switch3,              // switch número tres para habilitar la alarma (switch)
  input wire incrementar,          // sirve para incrementar el valor del display seleccionado (pulsador)
  input wire decrementar,          // sirve para decrementar el valor del display seleccionado (pulsador)
  input wire cambiar,              // sirve para cambiar entre los digitos MM a HH o SS : MM (pulsador)
  input wire establecer,           // sirve para establecer la hora configurada (pulsador)
  output wire [6:0] displayH2,     // valor del 7 segmentos para la hora 2 (0-3)
  output wire [6:0] displayH1,     // valor del 7 segmentos para la hora 1 (0-9)
  output wire [6:0] displayM2,     // valor del 7 segmentos para los minutos 2 (0-5)
  output wire [6:0] displayM1,     // valor del 7 segmentos para los minutos 1 (0-9)
  output wire led				   // led para mostrar la alarma como activa
);
  // señales globales
  wire clk1hz;  // cable para almacenar la señal de reloj de 1hz
  wire clk2hz; // cable para almacenar la señal de reloj de 2hz
  
  // señales para el relojHHMM
  wire [5:0] setMinutos; // entrada de minutos para establecer
  wire [4:0] setHoras; // entrada de horas para establecer
  wire [5:0] newMinutos; // minutos a enviar al reloj
  wire [4:0] newHoras; // horas a enviar al reloj
  wire [5:0] minutos; // salida de minutos
  wire [4:0] horas;   // salida de horas
  
  // señales para las entradas de los 7 segmentos
  wire [3:0] digito4; // cuarto digito
  wire [3:0] digito3; // tercer digito
  wire [3:0] digito2; // segundo digito
  wire [3:0] digito1; // primer digito
  
  // señales para el control de la alarma
  wire [5:0] setAlarmaMinutos; // entrada de minutos para establecer
  wire [4:0] setAlarmaHoras; // entrada de horas para establecer
  wire [5:0] newAlarmaMinutos; // minutos a almacenar para la alarma
  wire [4:0] newAlarmaHoras; // horas a almacenar para la alarma
  wire mostrarLed; // señal para ver si se muestra o no el led
  
  // señales para el control del timmer
  wire [5:0] setTimerSegundos; // entrada de minutos para establecer
  wire [6:0] setTimerMinutos; // entrada de horas para establecer
  wire [5:0] newSegundosT; // minutos a almacenar para la alarma
  wire [6:0] newMinutosT; // horas a almacenar para la alarma
  wire [5:0] segundosT;
  wire [6:0] minutosT;
  wire mostrarLed2; //
  wire control;
  wire configuration;
  wire cierreAlarma;
  
  // Instanciación del módulo divisorFrecuencia
  divisorFrecuencia U_divisorFrecuencia(
    .clk(clk),
    .reset(reset),
    .clk1hz(clk1hz)
  );
  
  // Instanciación del módulo divisorConteo
  divisorConteo U_divisorConteo(
    .clk(clk),
    .reset(reset),
    .clk2hz(clk2hz)
  );
  
  // Instanciación del módulo relojHHMM
  relojHHMM U_relojHHMM(
    .clk1hz(clk1hz),
    .reset(reset),
    .switch1(switch1),
    .switch2(switch2),
    .newMinutos(newMinutos),
    .newHoras(newHoras),
    .establecer(establecer),
    .minutos(minutos),
    .horas(horas)
  );

  // Instaciación del módulo de cambioHora
  cambioHora U_cambioHora(
    .clk2hz(clk2hz),
    .switch1(switch1),
    .switch2(switch2),
    .incrementar(incrementar),
    .decrementar(decrementar),
    .cambiar(cambiar),
    .establecer(establecer),
    .minutos(minutos),
    .horas(horas),
    .setMinutos(setMinutos),
    .setHoras(setHoras),
    .newMinutos(newMinutos),
    .newHoras(newHoras)
  );
  
  // Instaciación del módulo de control de la alarma
  controlAlarma U_controlAlarma(
    .clk1hz(clk1hz),
    .clk2hz(clk2hz),
    .switch1(switch1),
    .switch2(switch2),
    .switch3(switch3),
    .incrementar(incrementar),
    .decrementar(decrementar),
    .cambiar(cambiar),
    .establecer(establecer),
    .minutos(minutos),
    .horas(horas),
    .mostrarLed(mostrarLed),
    .setAlarmaMinutos(setAlarmaMinutos),
    .setAlarmaHoras(setAlarmaHoras),
    .newAlarmaMinutos(newAlarmaMinutos),
    .newAlarmaHoras(newAlarmaHoras)
  );
  
  // Instanciación del módulo relojMMSS
  relojMMSS U_relojMMSS(
    .clk1hz(clk1hz),
    .reset(reset),
    .switch1(switch1),
    .switch2(switch2),
    .newSegundosT(newSegundosT),
    .newMinutosT(newMinutosT),
    .establecer(establecer),
	.configuration(configuration),
	.control(control),
    .cierreAlarma(cierreAlarma),
    .segundosT(segundosT),
    .minutosT(minutosT)
  );
  
  // Instaciación del módulo controlTemporizador para el manejo del timmer
  controlTemporizador U_controlTemporizador(
    .clk1hz(clk1hz),
    .clk2hz(clk2hz),
    .switch1(switch1),
    .switch2(switch2),
    .incrementar(incrementar),
    .decrementar(decrementar),
    .cambiar(cambiar),
    .establecer(establecer),
	.configuration(configuration),
	.control(control),
	.cierreAlarma(cierreAlarma),
    .segundosT(segundosT),
    .minutosT(minutosT),
    .mostrarLed2(mostrarLed2),
    .setTimerSegundos(setTimerSegundos),
    .setTimerMinutos(setTimerMinutos),
    .newSegundosT(newSegundosT),
    .newMinutosT(newMinutosT)
  );
  
  // Instanciación del módulo display7segmentos para el cuarto digito
  display7segmentos U_display7segmentosH2(
    .digito(digito4),
    .segmento(displayH2)
  );

  // Instanciación del módulo display7segmentos para el tercer digito
  display7segmentos U_display7segmentosH1(
    .digito(digito3),
    .segmento(displayH1)
  );

  // Instanciación del módulo display7segmentos para el segundo digito
  display7segmentos U_display7segmentosM2(
    .digito(digito2),
    .segmento(displayM2)
  );

  // Instanciación del módulo display7segmentos para el primer digito
  display7segmentos U_display7segmentosM1(
    .digito(digito1),
    .segmento(displayM1)
  );

  // asignación del valor para el digito4 en el 7 segmentos
  assign digito4 = 
    (switch1 == 0 && switch2 == 0) ? horas[4:0] / 10 :
    (switch1 == 1 && switch2 == 0) ? (setHoras == 15) ? setHoras : setHoras[4:0] / 10 :
    (switch1 == 0 && switch2 == 1) ? (setAlarmaHoras == 15) ? setAlarmaHoras : setAlarmaHoras[4:0] / 10 :
    (switch1 == 1 && switch2 == 1) ? (control == 1) ? minutosT[6:0] / 10 : (setTimerMinutos == 15) ? setTimerMinutos : setTimerMinutos[6:0] / 10 : 15;
  
  // asignación del valor para el digito4 en el 7 segmentos
  assign digito3 = 
    (switch1 == 0 && switch2 == 0) ? horas[4:0] % 10 :
    (switch1 == 1 && switch2 == 0) ? (setHoras == 15) ? setHoras : setHoras[4:0] % 10 :
    (switch1 == 0 && switch2 == 1) ? (setAlarmaHoras == 15) ? setAlarmaHoras : setAlarmaHoras[4:0] % 10 :
    (switch1 == 1 && switch2 == 1) ? (control == 1) ? minutosT[6:0] % 10 : (setTimerMinutos == 15) ? setTimerMinutos : setTimerMinutos[6:0] % 10 : 15;
  
  // asignación del valor para el digito4 en el 7 segmentos
  assign digito2 = 
    (switch1 == 0 && switch2 == 0) ? minutos[5:0] / 10 :
    (switch1 == 1 && switch2 == 0) ? (setMinutos == 15) ? setMinutos : setMinutos[5:0] / 10 :
    (switch1 == 0 && switch2 == 1) ? (setAlarmaMinutos == 15) ? setAlarmaMinutos : setAlarmaMinutos[5:0] / 10 :
    (switch1 == 1 && switch2 == 1) ? (control == 1) ? segundosT[5:0] / 10 : (setTimerSegundos == 15) ? setTimerSegundos : setTimerSegundos[5:0] / 10 : 15;
  
  // asignación del valor para el digito4 en el 7 segmentos
  assign digito1 = 
    (switch1 == 0 && switch2 == 0) ? minutos[5:0] % 10 :
    (switch1 == 1 && switch2 == 0) ? (setMinutos == 15) ? setMinutos : setMinutos[5:0] % 10 :
    (switch1 == 0 && switch2 == 1) ? (setAlarmaMinutos == 15) ? setAlarmaMinutos : setAlarmaMinutos[5:0] % 10 :
    (switch1 == 1 && switch2 == 1) ? (control == 1) ? segundosT[5:0] % 10 : (setTimerSegundos == 15) ? setTimerSegundos : setTimerSegundos[5:0] % 10 : 15;
  
  assign led = (mostrarLed == 1 || mostrarLed2 == 1) ? 1 : 0; // validar con que valor se prende el led, aqui es intermitente a 2hz de frecuencia
 
endmodule
