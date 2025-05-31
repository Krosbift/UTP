module keyboard_controller (
    input logic clk,
    input logic ps2_clk,
    input logic ps2_data,
    output logic[7:0] char = 8'b00000000
);
    logic validity = 0;
    logic[3:0] counter = 0;
    logic[7:0] register = 8'b00000000;

    always_ff @(negedge ps2_clk) begin
	 
        if (counter == 0 && ~ps2_data) begin
            counter <= 1;
        end

        if (counter > 0 && counter < 9) begin
            register[counter - 1] <= ps2_data;
            counter <= counter + 1;
        end
		  
        if (counter == 9) begin
            if (ps2_data == ~(^register)) begin
                validity <= 1;
            end
            counter <= counter + 1;
        end

        if (counter == 10 && ps2_data) begin
            counter <= 0;
            if (validity) begin
                char <= register;
                validity <= 0;
            end
        end
    end

endmodule
