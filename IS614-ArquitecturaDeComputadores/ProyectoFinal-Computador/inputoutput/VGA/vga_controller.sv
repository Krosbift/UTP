module VGA_Controller (
  input  logic        fpga_clk,
  input  logic [ 7:0] vram_char_in,
  output logic [11:0] vram_addr,
  output logic [ 7:0] r,
  output logic [ 7:0] g,
  output logic [ 7:0] b,
  output logic        clk,
  output logic        sync_n,
  output logic        blank_n,
  output logic        hs,
  output logic        vs
);

  initial begin
    r = 0; g = 0; b = 0; clk = 0; hs = 1; vs = 1;
  end

  always_ff @(posedge fpga_clk) begin
    clk <= !clk;
  end

  // ... (TODA TU LÓGICA DE SINCRONIZACIÓN PERMANECE IGUAL) ...
  localparam H_DISPLAY_TIME = 640;
  localparam H_FRONT_PORCH  = 16;
  localparam H_SYNC_PULSE   = 96;
  localparam H_BACK_PORCH   = 48;
  localparam H_TOTAL_PIXELS = H_DISPLAY_TIME + H_FRONT_PORCH + H_SYNC_PULSE + H_BACK_PORCH;
  localparam V_DISPLAY_LINES = 480;
  localparam V_FRONT_PORCH   = 10;
  localparam V_SYNC_PULSE    = 2;
  localparam V_BACK_PORCH    = 33;
  localparam V_TOTAL_LINES   = V_DISPLAY_LINES + V_FRONT_PORCH + V_SYNC_PULSE + V_BACK_PORCH;

  logic new_line = 0;
  logic hds = 0;
  logic vds = 0;
  logic [4:0] dead_horizontal_sync_count = H_FRONT_PORCH;
  logic [6:0] horizontal_sync_neg_count = 0;
  logic [9:0] horizontal_sync_pos_count = 0;
  logic [7:0] horizontal_display_sync_neg_count = 0;
  logic [9:0] horizontal_display_sync_pos_count = 0;
  logic [3:0] dead_vertical_sync_count = V_FRONT_PORCH;
  logic [2:0] vertical_sync_neg_count = 0;
  logic [9:0] vertical_sync_pos_count = 0;
  logic [5:0] vertical_display_sync_neg_count = 0;
  logic [8:0] vertical_display_sync_pos_count = 0;

  always_ff @(posedge fpga_clk) begin
    if (clk) begin
      if (dead_horizontal_sync_count > 0) begin
        dead_horizontal_sync_count <= dead_horizontal_sync_count - 1;
      end
      if (dead_horizontal_sync_count == 0) begin
        if (horizontal_sync_neg_count < H_SYNC_PULSE) begin
          hs <= 0;
          new_line <= 0;
          horizontal_sync_neg_count <= horizontal_sync_neg_count + 1;
        end
        if (horizontal_sync_neg_count == H_SYNC_PULSE && horizontal_sync_pos_count < H_TOTAL_PIXELS - H_SYNC_PULSE) begin
          hs <= 1;
          horizontal_sync_pos_count <= horizontal_sync_pos_count + 1;
        end
        if (horizontal_sync_neg_count == H_SYNC_PULSE && horizontal_sync_pos_count == H_TOTAL_PIXELS - H_SYNC_PULSE) begin
          horizontal_sync_neg_count <= 0;
          horizontal_sync_pos_count <= 0;
          new_line <= 1;
        end
      end
      if (horizontal_display_sync_neg_count < H_BACK_PORCH + H_FRONT_PORCH + H_SYNC_PULSE) begin
        hds <= 0;
        horizontal_display_sync_neg_count <= horizontal_display_sync_neg_count + 1;
      end
      if (horizontal_display_sync_neg_count == H_BACK_PORCH + H_FRONT_PORCH + H_SYNC_PULSE && horizontal_display_sync_pos_count < H_DISPLAY_TIME) begin
        hds <= 1;
        horizontal_display_sync_pos_count <= horizontal_display_sync_pos_count + 1;
      end
      if (horizontal_display_sync_neg_count == H_BACK_PORCH + H_FRONT_PORCH + H_SYNC_PULSE && horizontal_display_sync_pos_count == H_DISPLAY_TIME) begin
        horizontal_display_sync_neg_count <= 0;
        horizontal_display_sync_pos_count <= 0;
      end
    end
  end

  always_ff @(posedge fpga_clk) begin
    if (clk && new_line) begin
      if (dead_vertical_sync_count > 0) begin
        dead_vertical_sync_count <= dead_vertical_sync_count - 1;
      end
      if (dead_vertical_sync_count == 0) begin
        if (vertical_sync_neg_count < V_SYNC_PULSE) begin
          vs <= 0;
          vertical_sync_neg_count <= vertical_sync_neg_count + 1;
        end
        if (vertical_sync_neg_count == V_SYNC_PULSE && vertical_sync_pos_count < V_TOTAL_LINES - V_SYNC_PULSE) begin
          vs <= 1;
          vertical_sync_pos_count <= vertical_sync_pos_count + 1;
        end
        if (vertical_sync_neg_count == V_SYNC_PULSE && vertical_sync_pos_count == V_TOTAL_LINES - V_SYNC_PULSE) begin
          vertical_sync_neg_count <= 0;
          vertical_sync_pos_count <= 0;
        end
      end
      if (vertical_display_sync_neg_count < V_BACK_PORCH + V_FRONT_PORCH + V_SYNC_PULSE) begin
        vds <= 0;
        vertical_display_sync_neg_count <= vertical_display_sync_neg_count + 1;
      end
      if (vertical_display_sync_neg_count == V_BACK_PORCH + V_FRONT_PORCH + V_SYNC_PULSE && vertical_display_sync_pos_count < V_DISPLAY_LINES) begin
        vds <= 1;
        vertical_display_sync_pos_count <= vertical_display_sync_pos_count + 1;
      end
      if (vertical_display_sync_neg_count == V_BACK_PORCH + V_FRONT_PORCH + V_SYNC_PULSE && vertical_display_sync_pos_count == V_DISPLAY_LINES) begin
        vertical_display_sync_neg_count <= 0;
        vertical_display_sync_pos_count <= 0;
      end
    end
  end

  // --- LÓGICA DE CÁLCULO Y PINTADO ---
  
  // Etapa 0: Cálculo de direcciones (Combinacional)
  logic [6:0] char_col;
  logic [5:0] char_row;
  assign char_col = horizontal_display_sync_pos_count / 8;
  assign char_row = vertical_display_sync_pos_count / 16;
  assign vram_addr = (char_row * 80) + char_col; // Dirección para la VRAM

  logic [11:0] rom_address;
  logic [7:0]  rom_data_out;
  // vram_char_in es el dato de la VRAM, que llega 1 ciclo después de vram_addr
  assign rom_address = {vram_char_in, vertical_display_sync_pos_count[3:0]}; // Dirección para la ROM

  charrom CHARROM (
    .address(rom_address), .clock(fpga_clk), .q(rom_data_out), .data(8'b0), .wren(1'b0)
  );
  
  // rom_data_out es el dato de la ROM, que llega 2 ciclos después del cálculo de vram_addr
  logic pixel_bit;
  assign pixel_bit = rom_data_out[7 - horizontal_display_sync_pos_count[2:0]];
  
  // Asignaciones directas de blanking/sync
  assign sync_n  = ~(hds && vds);
  assign blank_n = (hds && vds);
  
  // <<< INICIO DE LA CORRECCIÓN: PIPELINE DE 2 ETAPAS >>>
  // Etapa 1 del Pipeline
  logic pixel_bit_pipelined1;
  logic hds_pipelined1;
  logic vds_pipelined1;
  always_ff @(posedge fpga_clk) begin
    pixel_bit_pipelined1 <= pixel_bit;
    hds_pipelined1       <= hds;
    vds_pipelined1       <= vds;
  end
  
  // Etapa 2 del Pipeline
  logic pixel_bit_pipelined2;
  logic hds_pipelined2;
  logic vds_pipelined2;
  always_ff @(posedge fpga_clk) begin
    pixel_bit_pipelined2 <= pixel_bit_pipelined1;
    hds_pipelined2       <= hds_pipelined1;
    vds_pipelined2       <= vds_pipelined1;
  end
  // <<< FIN DE LA CORRECCIÓN >>>

  // Lógica de pintado final
  always_ff @(posedge fpga_clk) begin
    if (clk) begin
        // <<< CORRECCIÓN: Usar las señales de la segunda etapa del pipeline >>>
        if (hds_pipelined2 && vds_pipelined2) begin
          r <= pixel_bit_pipelined2 ? 8'h48 : 8'h00;
          g <= pixel_bit_pipelined2 ? 8'hDE : 8'h00;
          b <= pixel_bit_pipelined2 ? 8'h3B : 8'h00;
        end else begin
          r <= 8'h00; g <= 8'h00; b <= 8'h00; 
        end
    end
  end

endmodule