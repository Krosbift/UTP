module inputoutput (
  input  logic [7:0] ps2_scancode_out,
  input  logic       ps2_scancode_valid,
  
  input  logic       fpga_clk,
  input  logic       ps2_clk,
  input  logic       ps2_data,

  output logic [7:0] vga_r,
  output logic [7:0] vga_g,
  output logic [7:0] vga_b,
  output logic       vga_clk,
  output logic       vga_sync_n,
  output logic       vga_blank_n,
  output logic       vga_hs,
  output logic       vga_vs,
  output logic       led_view
);

  logic prev_button_state = 1'b0; // Asume que el botón empieza sin presionar
  logic write_pulse;

  always_ff @(posedge fpga_clk) begin
    prev_button_state <= ps2_scancode_valid;
  end

  assign write_pulse = ~prev_button_state && ps2_scancode_valid;
  assign led_view = write_pulse; 

  logic [ 7:0] ascii_char_out;
  logic        ascii_char_valid;
  logic [11:0] vga_vram_addr;
  logic [ 7:0] vga_vram_char;
  logic [11:0] kbd_vram_addr;

  scancode_to_ascii converter (
    .clk(fpga_clk),
    .scancode_in(ps2_scancode_out),  // Dato viene de los switches
    .scancode_valid(write_pulse), // Se activa solo por un ciclo
    .ascii_out(ascii_char_out),
    .ascii_valid(ascii_char_valid)
  );
  
  logic [11:0] cursor_pos = 10;
  
  // La lógica del cursor ahora se activa con el pulso limpio 'ascii_char_valid'
  always_ff @(posedge fpga_clk) begin
    if (ascii_char_valid) begin
      case (ascii_char_out)
        8'h08: cursor_pos <= (cursor_pos > 0) ? cursor_pos - 1 : 0;
        8'h0A: cursor_pos <= ((cursor_pos / 80) + 1) * 80;
        default: cursor_pos <= cursor_pos + 1;
      endcase
      
      if (cursor_pos >= 4096) begin
        cursor_pos <= 0;
      end
    end
  end
  
  assign kbd_vram_addr = cursor_pos;

  VideoRam vram_inst (
    // Puerto A (Escritura)
    .address_a (kbd_vram_addr),
    .data_a    (ascii_char_out),
    .wren_a    (ascii_char_valid), // Se activa con el pulso limpio
    .q_a       (),

    // Puerto B (Lectura)
    .address_b (vga_vram_addr),
    .data_b    (8'b0),
    .wren_b    (1'b0),
    .q_b       (vga_vram_char),

    // Reloj
    .clock     (fpga_clk)
  );
  
  VGA_Controller display (
    .fpga_clk(fpga_clk),
    .vram_addr(vga_vram_addr),
    .vram_char_in(vga_vram_char),
    .r(vga_r), .g(vga_g), .b(vga_b),
    .clk(vga_clk), .sync_n(vga_sync_n), .blank_n(vga_blank_n),
    .hs(vga_hs), .vs(vga_vs)
  );

endmodule