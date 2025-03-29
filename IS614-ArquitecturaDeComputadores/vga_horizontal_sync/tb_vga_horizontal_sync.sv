module tb_vga_horizontal_sync;

    logic clk = 0;
    logic clk_25Mhz;
    logic display_sync;
    logic horizontal_sync;

    always #1 clk = ~clk; 

    vga_clk_25Mhz UUT1 (
        .clk(clk),
        .clk_25Mhz(clk_25Mhz)
    );

    vga_horizontal_sync #(
        .BACK_PROCH(48),
        .DISPLAY_TIME(640),
        .FRONT_PROCH(16),
        .SYNC_TIME(96),
        .TOTAL(800)
    ) UUT (
        .clk(clk),
        .clk_25Mhz(clk_25Mhz),
        .display_sync(display_sync),
        .horizontal_sync(horizontal_sync)
    );

    initial begin
       #840000 $finish;
    end

    initial begin
        $dumpfile("waveform.vcd");
        $dumpvars(0, tb_vga_horizontal_sync);
    end

endmodule