/**
 * Character ROM - Memoria ROM de caracteres
 * Implementa una ROM que almacena los patrones de bits para generar caracteres ASCII en una pantalla
 *
 * @input  char_code         => Código ASCII del carácter a mostrar (8 bits)
 * @input  char_line_number  => Número de línea del carácter a leer (0-15) (4 bits)
 * @output char_line_out     => Patrón de bits para la línea del carácter (8 bits)
 *
 * Características:
 * - Almacena caracteres de 16x8 pixels (16 líneas de 8 bits cada una)
 * - Soporta letras mayúsculas (A-Z), números (0-9) y algunos símbolos especiales
 * - Cada carácter está definido por 16 bytes que representan las líneas horizontales
 * - Las primeras y últimas líneas suelen estar vacías para espaciado
 * - Utiliza codificación ASCII para seleccionar caracteres
 * - Devuelve 8'b0 para códigos de carácter no definidos
 */
module CharROM(
  input  logic [7:0] char_code,
  input  logic [3:0] char_line_number, 
  output logic [7:0] char_line_out = 8'b0
);

  logic [7:0] character[15:0];

  always_comb begin
    case(char_code)
      8'h41: begin // 'A'
        character[0]  = 8'b00000000; 
        character[1]  = 8'b00000000; 
        character[2]  = 8'b00000000; 
        character[3]  = 8'b00011000; //    XX
        character[4]  = 8'b00100100; //   X  X
        character[5]  = 8'b00100100; //   X  X
        character[6]  = 8'b01000010; //  X    X
        character[7]  = 8'b01000010; //  X    X
        character[8]  = 8'b01111110; //  XXXXXX
        character[9]  = 8'b01000010; //  X    X
        character[10] = 8'b01000010; //  X    X
        character[11] = 8'b01000010; //  X    X
        character[12] = 8'b01000010; //  X    X
        character[13] = 8'b00000000; 
        character[14] = 8'b00000000; 
        character[15] = 8'b00000000; 
      end
      8'h42: begin // 'B'
        character[0]  = 8'b00000000; 
        character[1]  = 8'b00000000; 
        character[2]  = 8'b00000000; 
        character[3]  = 8'b01111000; //  XXXX
        character[4]  = 8'b01000100; //  X   X
        character[5]  = 8'b01000100; //  X   X
        character[6]  = 8'b01000100; //  X   X
        character[7]  = 8'b01111000; //  XXXX
        character[8]  = 8'b01000100; //  X   X
        character[9]  = 8'b01000100; //  X   X
        character[10] = 8'b01000100; //  X   X
        character[11] = 8'b01111000; //  XXXX
        character[12] = 8'b00000000; 
        character[13] = 8'b00000000; 
        character[14] = 8'b00000000; 
        character[15] = 8'b00000000; 
      end
      8'h43: begin // 'C'
        character[0]  = 8'b00000000; 
        character[1]  = 8'b00000000; 
        character[2]  = 8'b00000000; 
        character[3]  = 8'b00011100; //    XXX
        character[4]  = 8'b00100010; //   X   X
        character[5]  = 8'b01000000; //  X
        character[6]  = 8'b01000000; //  X
        character[7]  = 8'b01000000; //  X
        character[8]  = 8'b01000000; //  X
        character[9]  = 8'b01000000; //  X
        character[10] = 8'b00100010; //   X   X
        character[11] = 8'b00011100; //    XXX
        character[12] = 8'b00000000; 
        character[13] = 8'b00000000; 
        character[14] = 8'b00000000; 
        character[15] = 8'b00000000; 
      end
      8'h44: begin // 'D'
        character[0]  = 8'b00000000; 
        character[1]  = 8'b00000000; 
        character[2]  = 8'b00000000; 
        character[3]  = 8'b01111000; //  XXXX
        character[4]  = 8'b01000100; //  X   X
        character[5]  = 8'b01000010; //  X    X
        character[6]  = 8'b01000010; //  X    X
        character[7]  = 8'b01000010; //  X    X
        character[8]  = 8'b01000010; //  X    X
        character[9]  = 8'b01000010; //  X    X
        character[10] = 8'b01000100; //  X   X
        character[11] = 8'b01111000; //  XXXX
        character[12] = 8'b00000000; 
        character[13] = 8'b00000000; 
        character[14] = 8'b00000000; 
        character[15] = 8'b00000000; 
      end
      8'h45: begin // 'E'
        character[0]  = 8'b00000000; 
        character[1]  = 8'b00000000; 
        character[2]  = 8'b00000000; 
        character[3]  = 8'b01111110; //  XXXXXX
        character[4]  = 8'b01000000; //  X
        character[5]  = 8'b01000000; //  X
        character[6]  = 8'b01000000; //  X
        character[7]  = 8'b01111110; //  XXXXXX
        character[8]  = 8'b01000000; //  X
        character[9]  = 8'b01000000; //  X
        character[10] = 8'b01000000; //  X
        character[11] = 8'b01111110; //  XXXXXX
        character[12] = 8'b00000000; 
        character[13] = 8'b00000000; 
        character[14] = 8'b00000000; 
        character[15] = 8'b00000000; 
      end
        8'h46: begin // 'F'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01111110; //  XXXXXX
        character[4]  = 8'b01000000; //  X
        character[5]  = 8'b01000000; //  X
        character[6]  = 8'b01000000; //  X
        character[7]  = 8'b01111110; //  XXXXXX
        character[8]  = 8'b01000000; //  X
        character[9]  = 8'b01000000; //  X
        character[10] = 8'b01000000; //  X
        character[11] = 8'b01000000; //  X
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h47: begin // 'G'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00011100; //    XXX
        character[4]  = 8'b00100010; //   X   X
        character[5]  = 8'b01000000; //  X
        character[6]  = 8'b01000000; //  X
        character[7]  = 8'b01001110; //  X  XXX
        character[8]  = 8'b01000010; //  X    X
        character[9]  = 8'b01000010; //  X    X
        character[10] = 8'b00100010; //   X   X
        character[11] = 8'b00011100; //    XXX
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h48: begin // 'H'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01000010; //  X    X
        character[4]  = 8'b01000010; //  X    X
        character[5]  = 8'b01000010; //  X    X
        character[6]  = 8'b01000010; //  X    X
        character[7]  = 8'b01111110; //  XXXXXX
        character[8]  = 8'b01000010; //  X    X
        character[9]  = 8'b01000010; //  X    X
        character[10] = 8'b01000010; //  X    X
        character[11] = 8'b01000010; //  X    X
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h49: begin // 'I'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00111100; //   XXXX
        character[4]  = 8'b00001000; //     X
        character[5]  = 8'b00001000; //     X
        character[6]  = 8'b00001000; //     X
        character[7]  = 8'b00001000; //     X
        character[8]  = 8'b00001000; //     X
        character[9]  = 8'b00001000; //     X
        character[10] = 8'b00001000; //     X
        character[11] = 8'b00111100; //   XXXX
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h4A: begin // 'J'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00011110; //    XXXX
        character[4]  = 8'b00000100; //       X
        character[5]  = 8'b00000100; //       X
        character[6]  = 8'b00000100; //       X
        character[7]  = 8'b00000100; //       X
        character[8]  = 8'b01000100; //  X    X
        character[9]  = 8'b01000100; //  X    X
        character[10] = 8'b00100100; //   X   X
        character[11] = 8'b00011000; //    XX
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h4B: begin // 'K'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01000010; //  X    X
        character[4]  = 8'b01000100; //  X   X
        character[5]  = 8'b01001000; //  X  X
        character[6]  = 8'b01010000; //  X X
        character[7]  = 8'b01100000; //  XX
        character[8]  = 8'b01010000; //  X X
        character[9]  = 8'b01001000; //  X  X
        character[10] = 8'b01000100; //  X   X
        character[11] = 8'b01000010; //  X    X
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h4C: begin // 'L'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01000000; //  X
        character[4]  = 8'b01000000; //  X
        character[5]  = 8'b01000000; //  X
        character[6]  = 8'b01000000; //  X
        character[7]  = 8'b01000000; //  X
        character[8]  = 8'b01000000; //  X
        character[9]  = 8'b01000000; //  X
        character[10] = 8'b01000000; //  X
        character[11] = 8'b01111110; //  XXXXXX
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
        8'h4D: begin // 'M'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01000010; //  X    X
        character[4]  = 8'b01100110; //  XX  XX
        character[5]  = 8'b01100110; //  XX  XX
        character[6]  = 8'b01011010; //  X XX X
        character[7]  = 8'b01011010; //  X XX X
        character[8]  = 8'b01000010; //  X    X
        character[9]  = 8'b01000010; //  X    X
        character[10] = 8'b01000010; //  X    X
        character[11] = 8'b01000010; //  X    X
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h4E: begin // 'N'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01000010; //  X    X
        character[4]  = 8'b01100010; //  XX   X
        character[5]  = 8'b01100010; //  XX   X
        character[6]  = 8'b01010010; //  X X  X
        character[7]  = 8'b01010010; //  X X  X
        character[8]  = 8'b01001010; //  X  X X
        character[9]  = 8'b01001010; //  X  X X
        character[10] = 8'b01000110; //  X   XX
        character[11] = 8'b01000010; //  X    X
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h4F: begin // 'O'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00011100; //    XXX
        character[4]  = 8'b00100010; //   X   X
        character[5]  = 8'b01000010; //  X    X
        character[6]  = 8'b01000010; //  X    X
        character[7]  = 8'b01000010; //  X    X
        character[8]  = 8'b01000010; //  X    X
        character[9]  = 8'b01000010; //  X    X
        character[10] = 8'b00100010; //   X   X
        character[11] = 8'b00011100; //    XXX
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h50: begin // 'P'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01111100; //  XXXXX
        character[4]  = 8'b01000010; //  X    X
        character[5]  = 8'b01000010; //  X    X
        character[6]  = 8'b01000010; //  X    X
        character[7]  = 8'b01111100; //  XXXXX
        character[8]  = 8'b01000000; //  X
        character[9]  = 8'b01000000; //  X
        character[10] = 8'b01000000; //  X
        character[11] = 8'b01000000; //  X
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h51: begin // 'Q'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00011100; //    XXX
        character[4]  = 8'b00100010; //   X   X
        character[5]  = 8'b01000010; //  X    X
        character[6]  = 8'b01000010; //  X    X
        character[7]  = 8'b01000010; //  X    X
        character[8]  = 8'b01001010; //  X  X X
        character[9]  = 8'b01000100; //  X   X
        character[10] = 8'b00100010; //   X   X
        character[11] = 8'b00011100; //    XXX
        character[12] = 8'b00000010; //       X
        character[13] = 8'b00000010; //       X
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h52: begin // 'R'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01111100; //  XXXXX
        character[4]  = 8'b01000010; //  X    X
        character[5]  = 8'b01000010; //  X    X
        character[6]  = 8'b01000010; //  X    X
        character[7]  = 8'b01111100; //  XXXXX
        character[8]  = 8'b01010000; //  X X
        character[9]  = 8'b01001000; //  X  X
        character[10] = 8'b01000100; //  X   X
        character[11] = 8'b01000010; //  X    X
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
          8'h53: begin // 'S'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00111100; //   XXXX
        character[4]  = 8'b01000010; //  X    X
        character[5]  = 8'b01000000; //  X
        character[6]  = 8'b01000000; //  X
        character[7]  = 8'b00111100; //   XXXX
        character[8]  = 8'b00000010; //       X
        character[9]  = 8'b00000010; //       X
        character[10] = 8'b01000010; //  X    X
        character[11] = 8'b00111100; //   XXXX
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h54: begin // 'T'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01111110; //  XXXXXX
        character[4]  = 8'b00010000; //     X
        character[5]  = 8'b00010000; //     X
        character[6]  = 8'b00010000; //     X
        character[7]  = 8'b00010000; //     X
        character[8]  = 8'b00010000; //     X
        character[9]  = 8'b00010000; //     X
        character[10] = 8'b00010000; //     X
        character[11] = 8'b00010000; //     X
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h55: begin // 'U'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01000010; //  X    X
        character[4]  = 8'b01000010; //  X    X
        character[5]  = 8'b01000010; //  X    X
        character[6]  = 8'b01000010; //  X    X
        character[7]  = 8'b01000010; //  X    X
        character[8]  = 8'b01000010; //  X    X
        character[9]  = 8'b01000010; //  X    X
        character[10] = 8'b00100100; //   X  X
        character[11] = 8'b00011000; //    XX
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h56: begin // 'V'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01000010; //  X    X
        character[4]  = 8'b01000010; //  X    X
        character[5]  = 8'b01000010; //  X    X
        character[6]  = 8'b01000010; //  X    X
        character[7]  = 8'b01000010; //  X    X
        character[8]  = 8'b00100100; //   X  X
        character[9]  = 8'b00100100; //   X  X
        character[10] = 8'b00011000; //    XX
        character[11] = 8'b00011000; //    XX
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h57: begin // 'W'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01000010; //  X    X
        character[4]  = 8'b01000010; //  X    X
        character[5]  = 8'b01000010; //  X    X
        character[6]  = 8'b01000010; //  X    X
        character[7]  = 8'b01000010; //  X    X
        character[8]  = 8'b01011010; //  X XX X
        character[9]  = 8'b01011010; //  X XX X
        character[10] = 8'b00100100; //   X  X
        character[11] = 8'b00100100; //   X  X
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h58: begin // 'X'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01000010; //  X    X
        character[4]  = 8'b01000010; //  X    X
        character[5]  = 8'b00100100; //   X  X
        character[6]  = 8'b00100100; //   X  X
        character[7]  = 8'b00011000; //    XX
        character[8]  = 8'b00100100; //   X  X
        character[9]  = 8'b00100100; //   X  X
        character[10] = 8'b01000010; //  X    X
        character[11] = 8'b01000010; //  X    X
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h59: begin // 'Y'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01000010; //  X    X
        character[4]  = 8'b01000010; //  X    X
        character[5]  = 8'b00100100; //   X  X
        character[6]  = 8'b00100100; //   X  X
        character[7]  = 8'b00011000; //    XX
        character[8]  = 8'b00011000; //    XX
        character[9]  = 8'b00010000; //     X
        character[10] = 8'b00010000; //     X
        character[11] = 8'b00010000; //     X
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h5A: begin // 'Z'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01111110; //  XXXXXX
        character[4]  = 8'b00000010; //       X
        character[5]  = 8'b00000010; //       X
        character[6]  = 8'b00000100; //      X
        character[7]  = 8'b00001000; //     X
        character[8]  = 8'b00010000; //    X
        character[9]  = 8'b00100000; //   X
        character[10] = 8'b01000000; //  X
        character[11] = 8'b01111110; //  XXXXXX
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h30: begin // '0'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00111100; //   XXXX
        character[4]  = 8'b01000010; //  X    X
        character[5]  = 8'b01000010; //  X    X
        character[6]  = 8'b01000010; //  X    X
        character[7]  = 8'b01000010; //  X    X
        character[8]  = 8'b01000010; //  X    X
        character[9]  = 8'b01000010; //  X    X
        character[10] = 8'b00111100; //   XXXX
        character[11] = 8'b00000000;
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h31: begin // '1'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00001000; //     X
        character[4]  = 8'b00011000; //    XX
        character[5]  = 8'b00101000; //   X X
        character[6]  = 8'b00001000; //     X
        character[7]  = 8'b00001000; //     X
        character[8]  = 8'b00001000; //     X
        character[9]  = 8'b00001000; //     X
        character[10] = 8'b00111110; //   XXXXXX
        character[11] = 8'b00000000;
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h32: begin // '2'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00111100; //   XXXX
        character[4]  = 8'b01000010; //  X    X
        character[5]  = 8'b00000010; //       X
        character[6]  = 8'b00000100; //      X
        character[7]  = 8'b00001000; //     X
        character[8]  = 8'b00010000; //    X
        character[9]  = 8'b00100000; //   X
        character[10] = 8'b01111110; //  XXXXXX
        character[11] = 8'b00000000;
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h33: begin // '3'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00111100; //   XXXX
        character[4]  = 8'b01000010; //  X    X
        character[5]  = 8'b00000010; //       X
        character[6]  = 8'b00011100; //    XXX
        character[7]  = 8'b00000010; //       X
        character[8]  = 8'b00000010; //       X
        character[9]  = 8'b01000010; //  X    X
        character[10] = 8'b00111100; //   XXXX
        character[11] = 8'b00000000;
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h34: begin // '4'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00000100; //      X
        character[4]  = 8'b00001100; //     XX
        character[5]  = 8'b00010100; //    X X
        character[6]  = 8'b00100100; //   X  X
        character[7]  = 8'b01111110; //  XXXXXX
        character[8]  = 8'b00000100; //      X
        character[9]  = 8'b00000100; //      X
        character[10] = 8'b00000100; //      X
        character[11] = 8'b00000000;
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h35: begin // '5'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01111110; //  XXXXXX
        character[4]  = 8'b01000000; //  X
        character[5]  = 8'b01000000; //  X
        character[6]  = 8'b01111100; //   XXXX
        character[7]  = 8'b00000010; //       X
        character[8]  = 8'b00000010; //       X
        character[9]  = 8'b01000010; //  X    X
        character[10] = 8'b00111100; //   XXXX
        character[11] = 8'b00000000;
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h36: begin // '6'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00111100; //   XXXX
        character[4]  = 8'b01000000; //  X
        character[5]  = 8'b01000000; //  X
        character[6]  = 8'b01111100; //   XXXX
        character[7]  = 8'b01000010; //  X    X
        character[8]  = 8'b01000010; //  X    X
        character[9]  = 8'b01000010; //  X    X
        character[10] = 8'b00111100; //   XXXX
        character[11] = 8'b00000000;
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h37: begin // '7'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b01111110; //  XXXXXX
        character[4]  = 8'b00000010; //       X
        character[5]  = 8'b00000100; //      X
        character[6]  = 8'b00001000; //     X
        character[7]  = 8'b00010000; //    X
        character[8]  = 8'b00100000; //   X
        character[9]  = 8'b01000000; //  X
        character[10] = 8'b01000000; //  X
        character[11] = 8'b01000000; //  X
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h38: begin // '8'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00111100; //   XXXX
        character[4]  = 8'b01000010; //  X    X
        character[5]  = 8'b01000010; //  X    X
        character[6]  = 8'b00111100; //   XXXX
        character[7]  = 8'b01000010; //  X    X
        character[8]  = 8'b01000010; //  X    X
        character[9]  = 8'b01000010; //  X    X
        character[10] = 8'b00111100; //   XXXX
        character[11] = 8'b00000000;
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h39: begin // '9'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00111100; //   XXXX
        character[4]  = 8'b01000010; //  X    X
        character[5]  = 8'b01000010; //  X    X
        character[6]  = 8'b00111110; //   XXXXXX
        character[7]  = 8'b00000010; //       X
        character[8]  = 8'b00000010; //       X
        character[9]  = 8'b01000010; //  X    X
        character[10] = 8'b00111100; //   XXXX
        character[11] = 8'b00000000;
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      // Ahora símbolos comunes
      8'h2D: begin // '-'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00000000;
        character[4]  = 8'b00000000;
        character[5]  = 8'b11111111; // XXXXXX
        character[6]  = 8'b00000000;
        character[7]  = 8'b00000000;
        character[8]  = 8'b00000000;
        character[9]  = 8'b00000000;
        character[10] = 8'b00000000;
        character[11] = 8'b00000000;
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h78: begin // '120'
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b11111111;
        character[3]  = 8'b11111111;
        character[4]  = 8'b11111111;	// xxxxxx
        character[5]  = 8'b11111111; 	// XXXXXX
        character[6]  = 8'b11111111;
        character[7]  = 8'b11111111;
        character[8]  = 8'b11111111;
        character[9]  = 8'b11111111;
        character[10] = 8'b11111111;
        character[11] = 8'b11111111;
        character[12] = 8'b11111111;
        character[13] = 8'b11111111;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h5b: begin // ' ' (91)
        character[0]  = 8'b11111111;
        character[1]  = 8'b10000001;
        character[2]  = 8'b10000001;
        character[3]  = 8'b10000001;
        character[4]  = 8'b10101001;
        character[5]  = 8'b10101001;
        character[6]  = 8'b10101001;
        character[7]  = 8'b10000001;
        character[8]  = 8'b10000001;
        character[9]  = 8'b10000001;
        character[10] = 8'b10111001;
        character[11] = 8'b10000001;
        character[12] = 8'b10000001;
        character[13] = 8'b10000001;
        character[14] = 8'b10000001;
        character[15] = 8'b11111111;
      end
      8'h3D: begin // '='
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b11111111; // XXXXXX
        character[4]  = 8'b00000000;
        character[5]  = 8'b11111111; // XXXXXX
        character[6]  = 8'b00000000;
        character[7]  = 8'b00000000;
        character[8]  = 8'b00000000;
        character[9]  = 8'b00000000;
        character[10] = 8'b00000000;
        character[11] = 8'b00000000;
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      8'h20: begin // ' ' (espacio)
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00000000;
        character[4]  = 8'b00000000;
        character[5]  = 8'b00000000;
        character[6]  = 8'b00000000;
        character[7]  = 8'b00000000;
        character[8]  = 8'b00000000;
        character[9]  = 8'b00000000;
        character[10] = 8'b00000000;
        character[11] = 8'b00000000;
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
      default begin
        character[0]  = 8'b00000000;
        character[1]  = 8'b00000000;
        character[2]  = 8'b00000000;
        character[3]  = 8'b00000000;
        character[4]  = 8'b00000000;
        character[5]  = 8'b00000000;
        character[6]  = 8'b00000000;
        character[7]  = 8'b00000000;
        character[8]  = 8'b00000000;
        character[9]  = 8'b00000000;
        character[10] = 8'b00000000;
        character[11] = 8'b00000000;
        character[12] = 8'b00000000;
        character[13] = 8'b00000000;
        character[14] = 8'b00000000;
        character[15] = 8'b00000000;
      end
    endcase
  end

  assign char_line_out = character[char_line_number];
endmodule