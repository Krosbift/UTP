`timescale 1ns/1ps

module tb_keyboard_controller;
    logic clk = 0;
    logic ps2_clk = 0;
    logic ps2_data = 1;
    logic[7:0] char;

    keyboard_controller UUT (
        .clk(clk),
        .ps2_clk(ps2_clk),
        .ps2_data(ps2_data),
        .char(char)
    );

    always #20 clk = ~clk;
    always #40 ps2_clk = ~ps2_clk;

    initial begin
        #200
            ps2_data = 0; // bit de inicio
        #80
            ps2_data = 1;
        #80
            ps2_data = 1;
        #80
            ps2_data = 1;
        #80
            ps2_data = 1;
        #80
            ps2_data = 1;
        #80
            ps2_data = 1;
        #80
            ps2_data = 1;
        #80
            ps2_data = 1;
        #80
            ps2_data = 1; // paridad
        #80
            ps2_data = 1; // finalización
        #200 $finish;
    end

    initial begin
        $dumpfile("tb_keyboard_controller.vcd");
        $dumpvars(0, tb_keyboard_controller);
    end

endmodule