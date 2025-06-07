module ps2_controller (
  input  logic       fpga_clk,
  input  logic       ps2_clk,
  input  logic       ps2_data,
  output logic [7:0] char,
  output logic       wrreq
);

  logic       validity = 1'b0;
  logic [3:0] counter  = 4'b0000;
  logic [7:0] register = 8'b00000000;

  always_ff @(posedge fpga_clk) begin
    wrreq <= 1'b0;
    
    if (~ps2_clk) begin
      if (counter == 4'b0000 && ~ps2_data) begin
        counter <= 4'b0001;
      end

      if (counter > 4'b0000 && counter < 4'b1001) begin
        register[counter - 4'b0001] <= ps2_data;
        counter <= counter + 4'b0001;
      end
      
      if (counter == 4'b1001) begin
        if (ps2_data == ~(^register)) begin
          validity <= 1'b1;
        end
        counter <= counter + 4'b0001;
      end

      if (counter == 4'b1010 && ps2_data) begin
        counter <= 4'b0000;
        if (validity) begin
          char <= register;
          wrreq <= 1'b1;
          validity <= 1'b00;
        end
      end
    end
  end

endmodule