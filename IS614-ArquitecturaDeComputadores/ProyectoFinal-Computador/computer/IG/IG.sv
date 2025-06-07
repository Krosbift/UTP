/**
 * Componente para generar el valor inmediato extendido
 * a partir del vector de bits de inmediato y el tipo
 * de instrucción:
 *
 * @input  VecImm    => vector de bits del inmediato
 * @input  ImmSrc_de => tipo de instrucción
 *
 * @output ImmExt_de    => inmediato extendido a 32 bits
 *
 * Características:
 * - Soporta 5 formatos de instrucción RISC-V: I, S, B, U, J
 * - Extensión de signo para formatos I, S, B y J
 * - Relleno con ceros para formato U
 * - Operación combinacional (sin reloj)
 * - Alineación automática para saltos (B y J) con bit 0 = 0
 * - Codificación de ImmSrc_de:
 *   - 000: Tipo I (loads, operaciones inmediatas)
 *   - 001: Tipo S (stores)
 *   - 101: Tipo B (branches)
 *   - 010: Tipo U (upper immediate)
 *   - 110: Tipo J (jumps)
 *
 * ┌───────────────────────────────────────────┐
 * │         Tabla de tipos de inmediato       │
 * ├──────────┬───────────┬────────────────────┤
 * │ Tipo     │ ImmSrc_de │ Descripción        │
 * ├──────────┼───────────┼────────────────────┤
 * │ Tipo I   │ 000       │ Inmediato I        │
 * │ Tipo S   │ 001       │ Store              │
 * │ Tipo B   │ 101       │ Branch             │
 * │ Tipo U   │ 010       │ Upper Immediate    │
 * │ Tipo J   │ 110       │ Jump               │
 * └──────────┴───────────┴────────────────────┘
 * 
 * 31                 25 24           20 19           15 14     12 11           7 6                    0
 * ┌────────────────────┬───────────────┬───────────────┬─────────┬──────────────┬──────────────────────┐
 * │  Funct7            │  rs2          │  rs1          │ Funct3  │  rd          │  OpCode              │ R
 * ├────────────────────┴───────────────┼───────────────┼─────────┼──────────────┼──────────────────────┤
 * │  Imm[11:0]                         │  rs1          │ Funct3  │  rd          │  OpCode              │ I
 * ├────────────────────┬───────────────┼───────────────┼─────────┼──────────────┼──────────────────────┤
 * │  Imm[11:5]         │  rs2          │  rs1          │ Funct3  │  Imm[4:0     │  OpCode              │ S
 * ├────────────────────┼───────────────┼───────────────┼─────────┼──────────────┼──────────────────────┤
 * │  Imm[12,10:5]      │  rs2          │  rs1          │ Funct3  │  Imm[4:1,11] │  OpCode              │ B
 * ├────────────────────┴───────────────┴───────────────┴─────────┼──────────────┼──────────────────────┤
 * │  Imm[31:12]                                                  │  rd          │  OpCode              │ U
 * ├──────────────────────────────────────────────────────────────┼──────────────┼──────────────────────┤
 * │  Imm[20,10:1,11,19:12]                                       │  rd          │  OpCode              │ J
 * └──────────────────────────────────────────────────────────────┴──────────────┴──────────────────────┘
 *
 **/
module IG (
  input  logic [24:0] VecImm,
  input  logic [2:0]  ImmSrc_de,
  output logic [31:0] ImmExt_de = 32'b0
);
  always_comb begin
    case (ImmSrc_de)
      3'b000: ImmExt_de = {{21{VecImm[24]}}, VecImm[23:13]};
      3'b001: ImmExt_de = {{21{VecImm[24]}}, VecImm[23:18], VecImm[4:0]};
      3'b101: ImmExt_de = {{20{VecImm[24]}}, VecImm[0], VecImm[23:18], VecImm[4:1], 1'b0};
      3'b010: ImmExt_de = {VecImm[24:5], 12'b0};
      3'b110: ImmExt_de = {{12{VecImm[24]}}, VecImm[12:5], VecImm[13], VecImm[23:14], 1'b0};
    endcase
  end
endmodule