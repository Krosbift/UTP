/**
 * Unidad de acceso para obtener las instrucciones
 * de un programa previamente cargado:
 *
 * @input Address: Dirección de la instrucción a obtener.
 * @output Instruction: Instrucción que se va a ejecutar.
 */
module InstructionMemory (
  input logic [31:0] address,
  output logic [31:0] instruction = 0
);

  logic [7:0] HDD [511:0];

  initial begin
    $readmemh("address-direction-file", HDD, 0, 511);
  end

  assign address = {
    HDD[address], HDD[address + 1], HDD[address + 2], HDD[address + 3]
  }

  // initial begin // USAR SOLO EN CASO DE SIMULACIÓN
  //   for (int i = 0 ; i < 1024 ; i++) begin HDD[i] = 8'b000; end
  // end

endmodule