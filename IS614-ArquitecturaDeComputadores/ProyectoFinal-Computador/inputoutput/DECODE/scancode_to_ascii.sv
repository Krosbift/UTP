// Módulo para convertir scancodes del teclado (Set 2) a códigos ASCII.
// Maneja el estado de la tecla Shift.
module scancode_to_ascii (
  input  logic        clk,
  input  logic [7:0]  scancode_in,
  input  logic        scancode_valid, // Pulso que indica que scancode_in es válido
  output logic [7:0]  ascii_out,
  output logic        ascii_valid     // Pulso que indica que ascii_out es válido
);

  // Registro para mantener el estado de la tecla Shift
  logic shift_pressed = 1'b0;

  // Lógica para detectar si se presiona o suelta la tecla Shift
  logic [7:0] prev_scancode = 8'h00;
  
  always_ff @(posedge clk) begin
    if (scancode_valid) begin
      prev_scancode <= scancode_in;
      
      // Presionar Shift
      if (scancode_in == 8'h12 || scancode_in == 8'h59) begin
        shift_pressed <= 1'b1;
      end
      
      // Soltar Shift (detecta el F0 anterior)
      if (prev_scancode == 8'hF0 && (scancode_in == 8'h12 || scancode_in == 8'h59)) begin
        shift_pressed <= 1'b0;
      end
    end
  end

  // La tabla de consulta principal (LUT)
  always_comb begin
    ascii_valid = 1'b0; // Por defecto, no hay salida válida
    ascii_out = 8'h00;   // Valor por defecto

    if (scancode_valid) begin
      // Solo generamos un ASCII válido si no es una tecla de control como F0 o E0
      if (scancode_in != 8'hF0 && scancode_in != 8'hE0) begin
        ascii_valid = 1'b1;
        
        case (scancode_in)
          // === Fila de números ===
          8'h0E: ascii_out = shift_pressed ? "~" : "`"; 
          8'h16: ascii_out = shift_pressed ? "!" : "1";
          8'h1E: ascii_out = shift_pressed ? "@" : "2";
          8'h26: ascii_out = shift_pressed ? "#" : "3";
          8'h25: ascii_out = shift_pressed ? "$" : "4";
          8'h2E: ascii_out = shift_pressed ? "%" : "5";
          8'h36: ascii_out = shift_pressed ? "^" : "6";
          8'h3D: ascii_out = shift_pressed ? "&" : "7";
          8'h3E: ascii_out = shift_pressed ? "*" : "8";
          8'h46: ascii_out = shift_pressed ? "(" : "9";
          8'h45: ascii_out = shift_pressed ? ")" : "0";
          8'h4E: ascii_out = shift_pressed ? "_" : "-";
          8'h55: ascii_out = shift_pressed ? "+" : "=";

          // === Letras ===
          8'h15: ascii_out = shift_pressed ? "Q" : "q";
          8'h1D: ascii_out = shift_pressed ? "W" : "w";
          8'h24: ascii_out = shift_pressed ? "E" : "e";
          8'h2D: ascii_out = shift_pressed ? "R" : "r";
          8'h2C: ascii_out = shift_pressed ? "T" : "t";
          8'h35: ascii_out = shift_pressed ? "Y" : "y";
          8'h3C: ascii_out = shift_pressed ? "U" : "u";
          8'h43: ascii_out = shift_pressed ? "I" : "i";
          8'h44: ascii_out = shift_pressed ? "O" : "o";
          8'h4D: ascii_out = shift_pressed ? "P" : "p";
          8'h1C: ascii_out = shift_pressed ? "A" : "a";
          8'h1B: ascii_out = shift_pressed ? "S" : "s";
          8'h23: ascii_out = shift_pressed ? "D" : "d";
          8'h2B: ascii_out = shift_pressed ? "F" : "f";
          8'h34: ascii_out = shift_pressed ? "G" : "g";
          8'h33: ascii_out = shift_pressed ? "H" : "h";
          8'h3B: ascii_out = shift_pressed ? "J" : "j";
          8'h42: ascii_out = shift_pressed ? "K" : "k";
          8'h4B: ascii_out = shift_pressed ? "L" : "l";
          8'h1A: ascii_out = shift_pressed ? "Z" : "z";
          8'h22: ascii_out = shift_pressed ? "X" : "x";
          8'h21: ascii_out = shift_pressed ? "C" : "c";
          8'h2A: ascii_out = shift_pressed ? "V" : "v";
          8'h32: ascii_out = shift_pressed ? "B" : "b";
          8'h31: ascii_out = shift_pressed ? "N" : "n";
          8'h3A: ascii_out = shift_pressed ? "M" : "m";
          
          // === Símbolos y teclas especiales ===
          8'h29: ascii_out = " ";       // Espacio
          8'h5A: ascii_out = 8'h0A;     // Enter -> Newline (\n)
          8'h66: ascii_out = 8'h08;     // Backspace
          8'h0D: ascii_out = 8'h09;     // Tab
          8'h41: ascii_out = shift_pressed ? "<" : ",";
          8'h49: ascii_out = shift_pressed ? ">" : ".";
          8'h4A: ascii_out = shift_pressed ? "?" : "/";
          8'h4C: ascii_out = shift_pressed ? ":" : ";";
          8'h52: ascii_out = shift_pressed ? 8'h22 : 8'h27; // " y '
          8'h54: ascii_out = shift_pressed ? "{" : "[";
          8'h5B: ascii_out = shift_pressed ? "}" : "]";
          // <<< CORRECCIÓN FINAL >>>
          8'h5D: ascii_out = shift_pressed ? 8'h7C : 8'h5C; // | (pipe) y \ (backslash)

          // === Teclado numérico (asumiendo Num Lock ON) ===
          8'h70: ascii_out = "0";
          8'h69: ascii_out = "1";
          8'h72: ascii_out = "2";
          8'h7A: ascii_out = "3";
          8'h6B: ascii_out = "4";
          8'h73: ascii_out = "5";
          8'h74: ascii_out = "6";
          8'h6C: ascii_out = "7";
          8'h75: ascii_out = "8";
          8'h7D: ascii_out = "9";
          8'h71: ascii_out = ".";
          8'h7C: ascii_out = "*";
          8'h7B: ascii_out = "-";
          8'h79: ascii_out = "+";
          
          default: ascii_valid = 1'b0; // Si no está en la lista, no es un ASCII válido
        endcase
      end
    end
  end

endmodule