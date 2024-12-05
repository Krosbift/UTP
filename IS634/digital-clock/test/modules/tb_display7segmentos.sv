`timescale 1s/1ms

module tb_display7segmentos;
  reg [3:0] digito;
  wire [6:0] segmento;

  display7segmentos uut_display7segmentos (
    .digito(digito),
    .segmento(segmento)
  );

  initial begin
    $dumpfile("tb_display7segmentos.vcd");
    $dumpvars(0, tb_display7segmentos);
  end
  
  initial begin
    digito = 0;
    #50 digito = 1;
    #50 digito = 2;
    #50 digito = 3;
    #50 digito = 4;
    #50 digito = 5;
    #50 digito = 6;
    #50 digito = 7;
    #50 digito = 8;
    #50 digito = 9;
    #50 $finish;
  end
  
endmodule