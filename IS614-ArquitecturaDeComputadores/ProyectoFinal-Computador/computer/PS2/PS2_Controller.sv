/**
 * Módulo controlador de interfaz PS/2 para teclado
 * Implementa el protocolo de comunicación PS/2 para recibir datos del teclado.
 *
 * @input  clk_50MHz  => Señal de reloj principal de 50MHz
 * @input  ps2_clk    => Señal de reloj PS/2 del teclado
 * @input  ps2_data   => Línea de datos PS/2 del teclado
 * @output char       => Byte de datos recibido del teclado
 * @output wrreq      => Señal de solicitud de escritura cuando hay datos válidos
 *
 * Características:
 * - Implementa protocolo PS/2 para recepción de datos
 * - Formato de trama: 1 bit inicio + 8 bits datos + 1 bit paridad + 1 bit stop
 * - Validación de paridad par
 * - Señales internas:
 *   - validity: Flag de validación de paridad
 *   - counter: Contador de bits recibidos
 *   - register: Registro de desplazamiento para almacenar bits
 *   - wrreq: Señal que se activa por un ciclo cuando hay datos válidos
 *
 * ┌───────────────────────────────────────────────────────────────────┐
 * │                 Formato de trama PS/2                             │
 * │                                                                   │
 * │  Start   D0   D1   D2   D3   D4   D5   D6   D7   Parity  Stop     │
 * │    0    [        8 bits de datos       ]    Par     1             │
 * │                                                                   │
 * │    └─────┴────┴────┴────┴────┴────┴────┴────┴────┴──────┴────┘    │
 * └───────────────────────────────────────────────────────────────────┘
 * 
 * Funcionamiento:
 * 1. Detecta bit de inicio (0) en ps2_data
 * 2. Captura 8 bits de datos en registro
 * 3. Verifica bit de paridad
 * 4. Si paridad correcta y bit stop válido:
 *    - Actualiza salida char con datos recibidos
 *    - Activa wrreq por un ciclo
 **/
module PS2_Controller (
  input  logic       clk_50MHz,
  input  logic       ps2_clk,
  input  logic       ps2_data,
  output logic [7:0] char = 0,
  output logic       wrreq = 0
);

  logic       validity = 0;
  logic [3:0] counter  = 0;
  logic [7:0] register = 0;

  always_ff @(posedge clk_50MHz) begin
    wrreq <= 0;
    
    if (~ps2_clk) begin
      if (counter == 0 && ~ps2_data) begin
        counter <= 1;
      end

      if (counter > 0 && counter < 9) begin
        register[counter - 1] <= ps2_data;
        counter <= counter + 1;
      end
      
      if (counter == 9) begin
        if (ps2_data == ~(^register)) begin
          validity <= 1;
        end
        counter <= counter + 1;
      end

      if (counter == 10 && ps2_data) begin
        counter <= 0;
        if (validity) begin
          char <= register;
          wrreq <= 1;
          validity <= 0;
        end
      end
    end
  end 

endmodule
