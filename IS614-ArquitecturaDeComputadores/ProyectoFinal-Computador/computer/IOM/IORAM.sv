/**
 * Módulo de Memoria RAM de Entrada/Salida (I/O RAM)
 * Implementa una memoria RAM síncrona para el manejo de dispositivos de E/S.
 *
 * @input  clk        => Señal de reloj del sistema
 * @input  Address    => Bus de direcciones de 11 bits (0-2047)
 * @input  DataWrite  => Bus de datos de entrada de 8 bits
 * @input  WrEnable   => Señal de habilitación de escritura
 * @output DataRead   => Bus de datos de salida de 8 bits
 *
 * Características:
 * - Memoria RAM síncrona de 8 bits de ancho
 * - Capacidad de 10240 posiciones (10K bytes)
 * - Operación síncrona en flanco positivo de reloj
 * - Inicialización desde archivo MIF (Memory Initialization File)
 * - Escritura y lectura mutuamente exclusivas
 *
 * ┌─────────────────────────────────────────────────────────────┐
 * │                    Diagrama de Operación                    │
 * │                                                             │
 * │           ┌─────────┐                                       │
 * │ Address ─>│         │                                       │
 * │           │  IORAM  │                                       │
 * │ DataWrite>│         │─> DataRead                            │
 * │           │         │                                       │
 * │ WrEnable ─│         │                                       │
 * │           └─────────┘                                       │
 * │                                                             │
 * └─────────────────────────────────────────────────────────────┘
 **/
module IORAM (
  input  logic        clk,
  input  logic [13:0] Address,
  input  logic [ 7:0] DataWrite,    
  input  logic        WrEnable,
  output logic [ 7:0] DataRead = 8'b0
);

  (* ram_init_file = "MemoryIO.mif" *) logic [7:0] Memory [0:10239]; 
  logic [7:0] regDataRead;

  always @(posedge clk) begin
    if(WrEnable) begin
        Memory[Address] <= DataWrite;
    end else begin
      regDataRead <= Memory[Address];
    end
  end
  
  assign DataRead = regDataRead;

endmodule