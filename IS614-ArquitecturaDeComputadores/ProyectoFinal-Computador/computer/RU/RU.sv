/**
 * Register Unit (RU) - Banco de Registros
 * Implementa el banco de 32 registros de propósito general del procesador RISC-V
 *
 * @input  clk     => Señal de reloj del sistema
 * @input  RUWr_wb => Habilitación de escritura en el banco de registros
 * @input  rs1_de  => Dirección del primer registro fuente (5 bits)
 * @input  rs2_de  => Dirección del segundo registro fuente (5 bits) 
 * @input  Rd      => Dirección del registro destino (5 bits)
 * @input  DataWr  => Datos a escribir en el registro destino (32 bits)
 * @output RURs1   => Datos leídos del primer registro fuente (32 bits)
 * @output RURs2   => Datos leídos del segundo registro fuente (32 bits)
 *
 * Características:
 * - 32 registros de 32 bits cada uno
 * - Registro x0 siempre contiene el valor 0
 * - Inicialización de todos los registros a 0
 * - Registro x2 (sp) inicializado con 0x200 
 * - Escritura síncrona en flanco positivo de reloj
 * - Lectura asíncrona de dos registros simultáneamente
 * - La escritura se realiza solo si RUWr_wb=1 y Rd≠0
 */
module RU (
  input  logic        clk,
  input  logic        RUWr_wb,
  input  logic [4:0]  rs1_de,
  input  logic [4:0]  rs2_de,
  input  logic [4:0]  Rd,
  input  logic [31:0] DataWr,
  output logic [31:0] RUrs1,
  output logic [31:0] RUrs2
);
  logic  [31:0] REGISTERS [31:0];

  initial begin
    for (int i = 0; i < 32; i++) begin
      REGISTERS[i] = 32'b0;
    end
    REGISTERS[2] = 32'b1000000000;
  end

  assign RUrs1 = REGISTERS[rs1_de];
  assign RUrs2 = REGISTERS[rs2_de];

  always_ff @(negedge clk) begin
    if (RUWr_wb && (Rd != 0)) begin
      REGISTERS[Rd] <= DataWr;
    end
  end
endmodule