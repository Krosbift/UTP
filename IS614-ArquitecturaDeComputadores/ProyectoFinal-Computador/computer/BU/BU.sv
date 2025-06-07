/**
 * Unidad de Branch (BU) para evaluación de saltos condicionales
 * en el procesador RISC-V segmentado.
 *
 * @input  A         => Valor del registro fuente 1 
 * @input  B         => Valor del registro fuente 2
 * @input  BrOp_ex      => Código de operación de branch
 *
 * @output NextPcSrc => Señal que indica si se toma el salto (1) o no (0)
 *
 * Características:
 * - Evalúa 6 tipos de comparaciones para saltos condicionales RISC-V
 * - Soporta comparaciones con y sin signo
 * - Operación combinacional (sin reloj)
 * - Saltos incondicionales (JAL/JALR) cuando BrOp_ex[4] = 1
 * - No salto cuando BrOp_ex[4:3] = 00
 *
 * Codificación de BrOp_ex[4:3]:
 * - 00: No salto
 * - x1: Evaluar condición según BrOp_ex[2:0]
 * - 1x: Salto incondicional (JAL/JALR)
 *
 * ┌──────────────────────────────────────────┐
 * │         NextPCSrc según BrOp_ex          │
 * ├────────────┬──────────┬──────────────────┤
 * │ NextPCSrc  │ BrOp_ex  │ Descripción      │
 * ├────────────┼──────────┼──────────────────┤
 * │     0      │ 00XXX    │ No saltar        │
 * │     =      │ 01000    │ Igual            │
 * │     ≠      │ 01001    │ Diferente        │
 * │     <      │ 01100    │ Menor que        │
 * │     ≥      │ 01101    │ Mayor o igual    │
 * │    <(U)    │ 01110    │ Menor que (U)    │
 * │    ≥(U)    │ 01111    │ Mayor o igual (U)│
 * │     1      │ 1XXXX    │ Saltar siempre   │
 * └────────────┴──────────┴──────────────────┘
 **/
module BU (
  input  logic signed [31:0] A,
  input  logic signed [31:0] B,
  input  logic        [ 4:0] BrOp_ex,
  output logic               NextPcSrc = 1'b0
);
  always_comb begin
    if (BrOp_ex[4] == 1'b1) begin
      NextPcSrc = 1'b1;
    end if (BrOp_ex[4:3] == 2'b00) begin
      NextPcSrc = 1'b0;
    end else begin
      case (BrOp_ex[2:0])
        3'b000: NextPcSrc = A            == B;
        3'b001: NextPcSrc = A            != B;
        3'b100: NextPcSrc = A            <  B;
        3'b101: NextPcSrc = A            >= B;
        3'b110: NextPcSrc = $unsigned(A) <  $unsigned(B);
        3'b111: NextPcSrc = $unsigned(A) >= $unsigned(B);
      endcase
    end
  end
endmodule 