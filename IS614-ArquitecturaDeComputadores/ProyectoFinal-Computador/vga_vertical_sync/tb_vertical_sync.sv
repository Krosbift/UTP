module tb_vga_vertical_sync;

    logic clk = 0;
    logic clk_25Mhz;
    logic horizontal_sync;
    logic horizontal_display_sync;
    logic new_line;
    logic vertical_sync;
    logic vertical_display_sync;

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
    ) UUT2 (
        .clk(clk),
        .clk_25Mhz(clk_25Mhz),
        .horizontal_sync(horizontal_sync),
        .horizontal_display_sync(horizontal_display_sync),
        .new_line(new_line)
    );

    vga_vertical_sync #(
        .BACK_PROCH(33),
        .DISPLAY_TIME(480),
        .FRONT_PROCH(10),
        .SYNC_TIME(2),
        .TOTAL(525)
    ) UUT (
        .clk(clk),
        .clk_25Mhz(clk_25Mhz),
        .new_line(new_line),
        .vertical_sync(vertical_sync),
        .vertical_display_sync(vertical_display_sync)
    );

    initial begin
       #8400000 $finish;
    end

    initial begin
        $dumpfile("waveform2.vcd");
        $dumpvars(0, tb_vga_vertical_sync);
    end

endmodule