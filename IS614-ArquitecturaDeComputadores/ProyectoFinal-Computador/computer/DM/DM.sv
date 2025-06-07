/**
 * Data Memory (DM) - Memoria de Datos
 * Implementa una memoria RAM de 1024x8 bits con operaciones de lectura/escritura
 *
 * @input  clk     => Señal de reloj
 * @input  DMWr_me    => Habilitación de escritura en memoria
 * @input  DMCtrl_me  => Control de operaciones de memoria
 * @input  Address => Dirección de memoria
 * @input  DataWr  => Datos a escribir
 * @output DataRd  => Datos leídos
 *
 * Características:
 * - Memoria RAM de 1024 bytes (1024x8 bits)
 * - Escritura síncrona en flanco positivo
 * - Lectura asíncrona
 * - Soporta operaciones byte, halfword y word
 * 
 * Codificación de DMCtrl_me:
 * - 000: byte signed (sb/lb)
 * - 001: halfword signed (sh/lh)
 * - 010: word (sw/lw)
 * - 100: byte unsigned (lbu)
 * - 101: halfword unsigned (lhu)
 **/
module DM (
  input  logic        clk,
  input  logic        DMWr_me,
  input  logic [ 2:0] DMCtrl_me,
  input  logic [31:0] Address,
  input  logic [31:0] DataWr,
  output logic [31:0] DataRd = 31'b0
);
  
  logic [7:0] RAM [1023:0];

  always_ff @(posedge clk) begin
    if (DMWr_me) begin
      case (DMCtrl_me)
        3'b000: begin // sb - Store Byte
          RAM[Address]     <= {DataWr[31], DataWr[6:0]};
        end
        3'b001: begin // sh - Store Halfword
          RAM[Address]     <= {DataWr[31], DataWr[14:8]};
          RAM[Address + 1] <= DataWr[7:0];
        end
        3'b010: begin // sw - Store Word
          RAM[Address]     <= DataWr[31:24];
          RAM[Address + 1] <= DataWr[23:16];
          RAM[Address + 2] <= DataWr[15:8];
          RAM[Address + 3] <= DataWr[7:0];
        end
      endcase
    end
  end

  always_comb begin
    case (DMCtrl_me)
      3'b000: begin // lb - Load Byte signed
        DataRd = {{25{RAM[Address][7]}}, RAM[Address][6:0]};
      end
      3'b001: begin // lh - Load Halfword signed
        DataRd = {{17{RAM[Address][7]}}, RAM[Address][6:0], RAM[Address + 1]};
      end
      3'b010: begin // lw - Load Word
        DataRd = {RAM[Address], RAM[Address + 1], RAM[Address + 2], RAM[Address + 3]};
      end
      3'b100: begin // lbu - Load Byte unsigned
        DataRd = {24'b0, RAM[Address]};
      end
      3'b101: begin // lhu - Load Halfword unsigned
        DataRd = {16'b0, RAM[Address], RAM[Address + 1]};
      end
      default: begin
        DataRd = 32'b0;
      end
    endcase
  end

endmodule