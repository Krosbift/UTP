/**
 * Instruction Memory (IM) - Memoria de Instrucciones
 * Componente para almacenar y obtener las instrucciones de un programa previamente cargado
 *
 * @input  Address      => Dirección de la instrucción a leer (32 bits)
 *
 * @output Instruction  => Instrucción leída de la memoria (32 bits)
 *
 * Características:
 * - Memoria de 512 bytes (128 instrucciones de 32 bits)
 * - Inicialización desde archivo hexadecimal externo
 * - Lectura asíncrona de instrucciones
 * - Instrucciones almacenadas en formato little-endian
 * - Valor por defecto de instrucción: 0
 * - Acceso byte a byte para formar instrucciones de 32 bits
 */
module IM (
  input  logic [31:0] Address,
  output logic [31:0] Instruction = 32'b0
);
  logic [7:0] HDD [511:0];

  initial begin
    $readmemh("fibonacci", HDD, 0, 511);
  end

  assign Instruction = {
    HDD[Address], HDD[Address + 1], HDD[Address + 2], HDD[Address + 3]
  };
endmodule