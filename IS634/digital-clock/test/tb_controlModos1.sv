`timescale 1s/1ms

module TB_controlModos;
  
  // valores de entrada y salida
  reg clk;
  reg reset;
  reg switch1;
  reg switch2;
  reg switch3;
  reg incrementar;
  reg decrementar;
  reg cambiar;
  reg establecer;
  wire led;
  wire [6:0] displayH2;
  wire [6:0] displayH1;
  wire [6:0] displayM2;
  wire [6:0] displayM1;
  
  // modulo a probar
  controlModos U_controlModos(
    .clk(clk),
    .reset(reset),
    .switch1(switch1),
    .switch2(switch2),
    .switch3(switch3),
    .incrementar(incrementar),
    .decrementar(decrementar),
    .cambiar(cambiar),
    .establecer(establecer),
    .displayH2(displayH2),
    .displayH1(displayH1),
    .displayM2(displayM2),
    .displayM1(displayM1),
    .led(led)
  );
  
  // subproceso para simular el funcionamiento de solo el reloj
  initial begin
    clk = 0;
    forever #250 clk <= ~clk; // simula señal de 2hz
  end
  
  // subproceso para simular el funcionamiento
  initial begin
    reset = 0;
    switch1 = 0;
    switch2 = 0;
    switch3 = 0;
    incrementar = 1;
    decrementar = 1;
    cambiar = 1;
    establecer = 1;
    #500 reset = 1;
    //
    
    //
    #100000000 $stop;
  end
  
  // subproceso para ver en el EPWave los resultados
  initial begin
    $dumpfile("TB_controlModos.vcd");
    $dumpvars(0, TB_controlModos);
  end
  
endmodule 