module tb_vga_clk_25Mhz;

    // Señales del testbench
    logic clk;
    logic clk_25Mhz;

    // Instancia del módulo bajo prueba (DUT)
    moduleName dut (
        .clk(clk),
        .clk_25Mhz(clk_25Mhz)
    );

    // Generador de reloj
    initial begin
        clk = 0;
        forever #10 clk = ~clk; // Reloj de 50MHz (20ns periodo)
    end

    // EPWave
    initial begin
        $dumpfile("tb_vga_clk_25Mhz.vcd");
        $dumpvars(0, tb_vga_clk_25Mhz);
        #1000 $finish; // Simulación de 1000ns
    end

endmodule