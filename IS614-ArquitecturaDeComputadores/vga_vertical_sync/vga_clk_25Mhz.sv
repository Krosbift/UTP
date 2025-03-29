module vga_clk_25Mhz (
    input logic clk,
    output logic clk_25Mhz = 0
);
    
    always_ff @(posedge clk) begin
        clk_25Mhz <= !clk_25Mhz;
    end

endmodule
