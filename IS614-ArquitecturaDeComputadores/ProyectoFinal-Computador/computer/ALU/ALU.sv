/**
 * Arithmetic Logic Unit (ALU) - Unidad Aritmético Lógica
 *
 * Componente para realizar operaciones aritmético
 * lógicas a los registros del procesador.
 *
 * @input  A        => primer operando (32 bits con signo)
 * @input  B        => segundo operando (32 bits con signo)
 * @input  ALUOp_ex => operación a realizar
 * @output ALURes_ex   => resultado obtenido (32 bits con signo)
 *
 * Características:
 * - Operaciones con datos de 32 bits con signo
 * - Opera de forma combinacional (sin reloj)
 * - Soporta 10 operaciones diferentes
 *
 * Codificación de ALUOp_ex:
 * ┌──────────────┬──────────┬─────────────────────────┐
 * │ Operación    │ ALUOp_ex │ Descripción             │
 * ├──────────────┼──────────┼─────────────────────────┤
 * │ A + B        │ 0000     │ Suma                    │
 * │ A - B        │ 1000     │ Resta                   │
 * │ A & B        │ 0111     │ AND                     │
 * │ A | B        │ 0110     │ OR                      │
 * │ A ^ B        │ 0100     │ XOR                     │
 * │ A >> B       │ 0101     │ Desplaz. lógico der.    │
 * │ A << B       │ 0001     │ Desplaz. lógico izq.    │
 * │ A >>> B      │ 1101     │ Desplaz. aritmético der.│
 * │ A < B        │ 0010     │ Menor que (signed)      │
 * │ A < B        │ 0011     │ Menor que (unsigned)    │
 * └──────────────┴──────────┴─────────────────────────┘
 **/

module ALU (
  input  logic signed [31:0] A,
  input  logic signed [31:0] B,
  input  logic        [ 3:0] ALUOp_ex,
  output logic signed [31:0] ALURes_ex    = 32'b0
);

  always_comb begin
    case (ALUOp_ex)
      4'b0000: ALURes_ex = A                    +   B;
      4'b1000: ALURes_ex = A                    -   B;
      4'b0111: ALURes_ex = A                    &   B;
      4'b0110: ALURes_ex = A                    |   B;
      4'b0100: ALURes_ex = A                    ^   B;
      4'b0101: ALURes_ex = A                    >>  B[4:0];
      4'b0001: ALURes_ex = A                    <<  B[4:0];
      4'b1101: ALURes_ex = A                    >>> B[4:0];
      4'b0010: ALURes_ex = {31'b0, $signed(A)   <   $signed(B)};
      4'b0011: ALURes_ex = {31'b0, $unsigned(A) <   $unsigned(B)};
    endcase
  end

endmodule
