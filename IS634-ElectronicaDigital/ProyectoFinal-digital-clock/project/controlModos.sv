/**
 * Módulo controlModos
 * 
 * Este módulo controla los diferentes modos de un sistema que involucra un reloj, temporizador y alarma.
 * Utiliza switches y pulsadores para cambiar entre los modos, ajustar la hora, los minutos y segundos,
 * así como activar y desactivar la alarma. Además, gestiona la visualización en displays de 7 segmentos
 * y el encendido de un LED indicador de la alarma.
 * 
 * Entradas:
 * - clk: Reloj de la FPGA (50 MHz).
 * - reset: Señal de reset.
 * - switch1, switch2, switch3: Interruptores para cambiar entre los modos y habilitar la alarma.
 * - incrementar, decrementar, cambiar, establecer: Pulsadores para ajustar la hora y los minutos.
 * 
 * Salidas:
 * - displayH2, displayH1, displayM2, displayM1: Señales para controlar los displays de 7 segmentos
 *   que muestran la hora y los minutos.
 * - led: Indicador de estado de la alarma.
 */

module controlModos(
  input wire clk,       
  input wire reset,      
  input wire switch1,  
  input wire switch2,   
  input wire switch3,    
  input wire incrementar,
  input wire decrementar,   
  input wire cambiar,  
  input wire establecer,      
  output wire [6:0] displayH2,  
  output wire [6:0] displayH1, 
  output wire [6:0] displayM2,  
  output wire [6:0] displayM1,  
  output wire led	
);
  wire clk1hz;
  wire clk2hz;
  wire [5:0] setMinutos; 
  wire [4:0] setHoras; 
  wire [5:0] newMinutos; 
  wire [4:0] newHoras;
  wire [5:0] minutos;
  wire [4:0] horas;   
  wire [3:0] digito4;
  wire [3:0] digito3; 
  wire [3:0] digito2;
  wire [3:0] digito1;
  wire [5:0] setAlarmaMinutos; 
  wire [4:0] setAlarmaHoras; 
  wire [5:0] newAlarmaMinutos; 
  wire [4:0] newAlarmaHoras;
  wire mostrarLed;
  
  wire [5:0] setTimerSegundos;
  wire [6:0] setTimerMinutos; 
  wire [5:0] newSegundosT; 
  wire [6:0] newMinutosT;
  wire [5:0] segundosT;
  wire [6:0] minutosT;
  wire mostrarLed2; 
  wire control;
  wire configuration;
  wire cierreAlarma;
  
  divisorFrecuencia U_divisorFrecuencia(
    .clk(clk),
    .reset(reset),
    .clk1hz(clk1hz)
  );
  
  divisorConteo U_divisorConteo(
    .clk(clk),
    .reset(reset),
    .clk2hz(clk2hz)
  );
  
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
  
  display7segmentos U_display7segmentosH2(
    .digito(digito4),
    .segmento(displayH2)
  );

  display7segmentos U_display7segmentosH1(
    .digito(digito3),
    .segmento(displayH1)
  );

  display7segmentos U_display7segmentosM2(
    .digito(digito2),
    .segmento(displayM2)
  );

  display7segmentos U_display7segmentosM1(
    .digito(digito1),
    .segmento(displayM1)
  );

  assign digito4 = 
    (switch1 == 0 && switch2 == 0) ? horas[4:0] / 10 :
    (switch1 == 1 && switch2 == 0) ? (setHoras == 15) ? setHoras : setHoras[4:0] / 10 :
    (switch1 == 0 && switch2 == 1) ? (setAlarmaHoras == 15) ? setAlarmaHoras : setAlarmaHoras[4:0] / 10 :
    (switch1 == 1 && switch2 == 1) ? (control == 1) ? minutosT[6:0] / 10 : (setTimerMinutos == 15) ? setTimerMinutos : setTimerMinutos[6:0] / 10 : 15;
  
  assign digito3 = 
    (switch1 == 0 && switch2 == 0) ? horas[4:0] % 10 :
    (switch1 == 1 && switch2 == 0) ? (setHoras == 15) ? setHoras : setHoras[4:0] % 10 :
    (switch1 == 0 && switch2 == 1) ? (setAlarmaHoras == 15) ? setAlarmaHoras : setAlarmaHoras[4:0] % 10 :
    (switch1 == 1 && switch2 == 1) ? (control == 1) ? minutosT[6:0] % 10 : (setTimerMinutos == 15) ? setTimerMinutos : setTimerMinutos[6:0] % 10 : 15;
  
  assign digito2 = 
    (switch1 == 0 && switch2 == 0) ? minutos[5:0] / 10 :
    (switch1 == 1 && switch2 == 0) ? (setMinutos == 15) ? setMinutos : setMinutos[5:0] / 10 :
    (switch1 == 0 && switch2 == 1) ? (setAlarmaMinutos == 15) ? setAlarmaMinutos : setAlarmaMinutos[5:0] / 10 :
    (switch1 == 1 && switch2 == 1) ? (control == 1) ? segundosT[5:0] / 10 : (setTimerSegundos == 15) ? setTimerSegundos : setTimerSegundos[5:0] / 10 : 15;
  
  assign digito1 = 
    (switch1 == 0 && switch2 == 0) ? minutos[5:0] % 10 :
    (switch1 == 1 && switch2 == 0) ? (setMinutos == 15) ? setMinutos : setMinutos[5:0] % 10 :
    (switch1 == 0 && switch2 == 1) ? (setAlarmaMinutos == 15) ? setAlarmaMinutos : setAlarmaMinutos[5:0] % 10 :
    (switch1 == 1 && switch2 == 1) ? (control == 1) ? segundosT[5:0] % 10 : (setTimerSegundos == 15) ? setTimerSegundos : setTimerSegundos[5:0] % 10 : 15;
  
  assign led = (mostrarLed == 1 || mostrarLed2 == 1) ? 1 : 0;

endmodule
