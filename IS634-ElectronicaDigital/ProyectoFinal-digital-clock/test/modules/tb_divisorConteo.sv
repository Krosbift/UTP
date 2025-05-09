`timescale 1s/1ms

module tb_divisorConteo;
  reg clk;
  reg reset;
  wire enable7segmentos;
  
  divisorConteo U_divisorConteo(
    .clk(clk),
    .reset(reset),
    .enable7segmentos(enable7segmentos)
  );
  
  initial begin
    clk = 0;
    forever #250 clk <= ~clk;
  end
  
  initial begin
    $dumpfile("tb_divisorConteo.vcd");
    $dumpvars(0, tb_divisorConteo);
  end
  
  initial begin
    reset = 0;
    #1000 reset = 1;
    #10000000 $stop;
  end

endmodule