/**
 * Módulo FIFO (First In First Out)
 * Implementa una cola FIFO síncrona con una profundidad configurable.
 *
 * @input  clock   => Señal de reloj del sistema
 * @input  data    => Datos de 8 bits para escribir en la FIFO
 * @input  rdreq   => Señal de solicitud de lectura
 * @input  wrreq   => Señal de solicitud de escritura 
 *
 * @output empty   => Indica cuando la FIFO está vacía (1: vacía, 0: con datos)
 * @output q       => Datos de 8 bits leídos de la FIFO
 *
 * Características:
 * - Profundidad configurable mediante parámetro DEPTH (por defecto 16)
 * - Ancho de bus de direcciones configurable con ADDR_WIDTH
 * - Operación síncrona con el flanco positivo del reloj
 * - Control independiente de lectura/escritura
 * - Indicador de FIFO vacía
 *
 * Estructura interna:
 * - Memoria interna de DEPTH x 8 bits
 * - Punteros circulares de lectura y escritura
 * - Contador de elementos almacenados
 *
 * ┌──────────────────────────────────────────────────┐
 * │                  Memoria FIFO                    │
 * │                                                  │
 * │    wr_ptr →   [dato n]                           │
 * │              [dato n-1]                          │
 * │              [dato n-2]                          │
 * │              [   ...  ]                          │
 * │    rd_ptr →   [dato 1]                           │
 * │                                                  │
 * └──────────────────────────────────────────────────┘
 **/
module FIFO (
  input  logic       clk,
  input  logic [7:0] data,
  input  logic       rdreq,
  input  logic       wrreq, 
  output logic       empty,
  output logic [7:0] q
);

  parameter DEPTH = 16;
  parameter ADDR_WIDTH = 4;

  logic [7:0] CACHE [DEPTH-1:0];
  logic [ADDR_WIDTH - 1:0] wr_ptr, rd_ptr;
  logic [ADDR_WIDTH:0] fifo_count;

  initial begin
    wr_ptr = 0;
    rd_ptr = 0;
    fifo_count = 0;
  end

  always_ff @(posedge clk) begin
    if (wrreq && fifo_count < DEPTH) begin
      CACHE[wr_ptr] <= data;
      wr_ptr <= wr_ptr + 1;
      fifo_count <= fifo_count + 1;
    end
  end

  always_ff @(posedge clk) begin
    if (rdreq && fifo_count > 0) begin
      q <= CACHE[rd_ptr];
      rd_ptr <= rd_ptr + 1;
      fifo_count <= fifo_count - 1;
    end
  end

  assign empty = (fifo_count == 0);

endmodule
