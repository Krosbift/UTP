/**
 * Control Unit (CU) - Unidad de Control
 * Genera las señales de control para el procesador RISC-V
 *
 * @input  OpCode         => Código de operación de la instrucción
 * @input  Funct7         => Campo funct7 de la instrucción
 * @input  Funct3         => Campo funct3 de la instrucción
 * 
 * @output ALUASrc_de     => Selector para la entrada A de la ALU
 * @output ALUBSrc_de     => Selector para la entrada B de la ALU 
 * @output DMWr_de        => Habilitación de escritura en memoria de datos
 * @output RUWr_de        => Habilitación de escritura en banco de registros
 * @output RuDataWrSrc_de => Selector de fuente para datos a escribir en registros
 * @output ImmSrc_de      => Tipo de inmediato a generar
 * @output DMCtrl_de      => Control de operaciones de memoria
 * @output ALUOp_de       => Control de operación de la ALU
 * @output BrOp_de        => Control de saltos
 *
 * Características:
 * - Soporta 9 tipos de instrucciones RISC-V: R, I, I-Load, I-JALR, B, S, J-JAL, U-LUI, U-AUIPC
 * - Genera señales de control para la ruta de datos del procesador
 * - Operación combinacional (sin reloj)
 * - Control de fuentes para la ALU (ALUASrc_de, ALUBSrc_de)
 * - Control de escritura en memoria y registros (DMWr_de, RUWr_de)
 * - Codificación de RuDataWrSrc_de:
 *   - 00: Resultado ALU
 *   - 01: Datos de memoria
 *   - 10: PC + 4 (para JAL/JALR)
 * - Codificación de ImmSrc_de:
 *   - 000: Tipo I (loads, operaciones inmediatas)
 *   - 001: Tipo S (stores)
 *   - 101: Tipo B (branches)
 *   - 010: Tipo U (upper immediate)
 *   - 110: Tipo J (jumps)
 * - Control de operaciones de memoria (DMCtrl_de)
 * - Control de operaciones ALU (ALUOp_de)
 * - Control de saltos (BrOp_de):
 *   - 00000: No salto
 *   - 01xxx: Salto condicional (xxx = Funct3)
 *   - 10000: Salto incondicional (JAL/JALR)
 *
 * ┌────────────────────────────────────────────────────────┐
 * │                Tabla de Instrucciones                  │
 * ├─────────────┬────────┬─────────────────────────────────┤
 * │ Tipo        │ OpCode │ Descripción                     │
 * ├─────────────┼────────┼─────────────────────────────────┤
 * │ R-Type      │ 0110011│ Operaciones registro-registro   │
 * │ I-Type      │ 0010011│ Operaciones inmediato           │
 * │ I-Load      │ 0000011│ Carga desde memoria             │
 * │ I-JALR      │ 1100111│ Salto y enlace con registro     │
 * │ B-Type      │ 1100011│ Saltos condicionales            │
 * │ S-Type      │ 0100011│ Almacenamiento en memoria       │
 * │ J-JAL       │ 1101111│ Salto y enlace                  │
 * │ U-LUI       │ 0110111│ Load Upper Immediate            │
 * │ U-AUIPC     │ 0010111│ Add Upper Imm to PC             │
 * └─────────────┴────────┴─────────────────────────────────┘
 *
 * Las señales de control se generan de forma combinacional
 * según el tipo de instrucción determinado por el OpCode
 **/
module CU (
  input  logic [6:0] OpCode,
  input  logic [6:0] Funct7,
  input  logic [2:0] Funct3,
  output logic       ALUASrc_de     = 1'b0,
  output logic       ALUBSrc_de     = 1'b0,
  output logic       DMWr_de        = 1'b0,
  output logic       RUWr_de        = 1'b0,
  output logic [1:0] RuDataWrSrc_de = 2'b00,
  output logic [2:0] ImmSrc_de      = 3'b000,
  output logic [2:0] DMCtrl_de      = 3'b000,
  output logic [3:0] ALUOp_de       = 4'b0000,
  output logic [4:0] BrOp_de        = 5'b00000
);
  always_comb begin
    case (OpCode)
      7'b0110011: begin // R-Operation
        ALUASrc_de     = 1'b0;
        ALUBSrc_de     = 1'b0;
        DMWr_de        = 1'b0;
        RUWr_de        = 1'b1;
        RuDataWrSrc_de = 2'b00;
        ImmSrc_de      = 3'b000;
        DMCtrl_de      = 3'b000;
        ALUOp_de       = {Funct7[5], Funct3[2:0]};
        BrOp_de        = 5'b00000;
      end
      7'b0010011: begin // I-Operation
        ALUASrc_de     = 1'b0;
        ALUBSrc_de     = 1'b1;
        DMWr_de        = 1'b0;
        RUWr_de        = 1'b1;
        RuDataWrSrc_de = 2'b00;
        ImmSrc_de      = 3'b000;
        DMCtrl_de      = 3'b000;
        ALUOp_de       = Funct3 == 3'b101 ? {Funct7[5], Funct3[2:0]} : {1'b0, Funct3};
        BrOp_de        = 5'b00000;
      end
      7'b0000011: begin // I-Load
        ALUASrc_de     = 1'b0;
        ALUBSrc_de     = 1'b1;
        DMWr_de        = 1'b0;
        RUWr_de        = 1'b1;
        RuDataWrSrc_de = 2'b01;
        ImmSrc_de      = 3'b000;
        DMCtrl_de      = Funct3[2:0];
        ALUOp_de       = 5'b0000;
        BrOp_de        = 5'b00000;
      end
      7'b1100111: begin // I_JALR
        ALUASrc_de     = 1'b0;
        ALUBSrc_de     = 1'b1;
        DMWr_de        = 1'b0;
        RUWr_de        = 1'b1;
        RuDataWrSrc_de = 2'b10;
        ImmSrc_de      = 3'b000;
        DMCtrl_de      = 3'b000;
        ALUOp_de       = 4'b0000;
        BrOp_de        = 5'b10000;
      end
      7'b1100011: begin // B-Jump
        ALUASrc_de     = 1'b1;
        ALUBSrc_de     = 1'b1;
        DMWr_de        = 1'b0;
        RUWr_de        = 1'b0;
        RuDataWrSrc_de = 2'b00;
        ImmSrc_de      = 3'b101;
        DMCtrl_de      = 3'b000;
        ALUOp_de       = 4'b0000;
        BrOp_de        = {2'b01, Funct3[2:0]};
      end
      7'b0100011: begin // S-Storage
        ALUASrc_de     = 1'b0;
        ALUBSrc_de     = 1'b1;
        DMWr_de        = 1'b1;
        RUWr_de        = 1'b0;
        RuDataWrSrc_de = 2'b00;
        ImmSrc_de      = 3'b001;
        DMCtrl_de      = Funct3[2:0];
        ALUOp_de       = 4'b0000;
        BrOp_de        = 5'b00000;
      end
      7'b1101111: begin // J-jump
        ALUASrc_de     = 1'b1;
        ALUBSrc_de     = 1'b1;
        DMWr_de        = 1'b0;
        RUWr_de        = 1'b1;
        RuDataWrSrc_de = 2'b10;
        ImmSrc_de      = 3'b110;
        DMCtrl_de      = 3'b000;
        ALUOp_de       = 4'b0000;
        BrOp_de        = 5'b10000;
      end
      7'b0110111: begin // J-LUI
        ALUASrc_de     = 1'b0;
        ALUBSrc_de     = 1'b1;
        DMWr_de        = 1'b0;
        RUWr_de        = 1'b1;
        RuDataWrSrc_de = 2'b00;
        ImmSrc_de      = 3'b010;
        DMCtrl_de      = 3'b000;
        ALUOp_de       = 4'b1001;
        BrOp_de        = 5'b00000;
      end
      7'b0010111: begin // J-AUIPC
        ALUASrc_de     = 1'b1;
        ALUBSrc_de     = 1'b1;
        DMWr_de        = 1'b0;
        RUWr_de        = 1'b1;
        RuDataWrSrc_de = 2'b00;
        ImmSrc_de      = 3'b010;
        DMCtrl_de      = 3'b000;
        ALUOp_de       = 4'b0000;
        BrOp_de        = 5'b00000;
      end
      default: begin // RESET
        ALUASrc_de     = 1'b0;
        ALUBSrc_de     = 1'b0;
        DMWr_de        = 1'b0;
        RUWr_de        = 1'b0;
        RuDataWrSrc_de = 2'b00;
        ImmSrc_de      = 3'b000;
        DMCtrl_de      = 3'b000;
        ALUOp_de       = 4'b0000;
        BrOp_de        = 5'b00000;
      end
    endcase
  end
endmodule