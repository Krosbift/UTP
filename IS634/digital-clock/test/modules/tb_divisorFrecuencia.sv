`timescale 1s/1ms

module tb_divisorFrecuencia;
  reg clk;
  reg reset;
  wire enable;
  
  divisorFrecuencia U_divisorFrecuencia(
    .clk(clk),
    .reset(reset),
    .enable(enable)
  );
  
  initial begin
    clk = 0;
    forever #250 clk <= ~clk;
  end
  
  initial begin
    $dumpfile("tb_divisorFrecuencia.vcd");
    $dumpvars(0, tb_divisorFrecuencia);
  end
  
  initial begin
    reset = 0;
    #100 reset = 1;
    #10000000 $stop;
  end

endmodule