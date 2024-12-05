/*
 * ---------------------------------------------------------------------
 * Módulo: display7segmentos
 * Propósito: Este módulo toma un dígito binario de 4 bits como entrada (rango de 0 a 9)
 *            y lo convierte en una señal de 7 bits que controla los segmentos de
 *            un display de 7 segmentos, permitiendo mostrar los dígitos del 0 al 9.
 *            Es útil para visualizar números en dispositivos como displays LED de 7 segmentos.
 * 
 * Entradas:
 *  - digito : Un valor binario de 4 bits (0 a 9), que representa el dígito a mostrar.
 * 
 * Salidas:
 *  - segmento : Una señal de 7 bits que controla los segmentos del display. Cada bit corresponde
 *               a un segmento del display (A, B, C, D, E, F, G), donde un valor bajo (0) enciende
 *               el segmento y un valor alto (1) lo apaga.
 * 
 * Funcionamiento:
 *  - El módulo utiliza una sentencia `case` para mapear cada dígito de entrada a su patrón de
 *    segmentos correspondiente en el display de 7 segmentos.
 *  - Cada patrón es un valor de 7 bits que representa el estado (encendido o apagado) de los segmentos.
 *    Un '0' en un bit indica que el segmento está encendido, y un '1' indica que el segmento está apagado.
 *    El patrón de los segmentos es el siguiente:
 *    Segmentos: A, B, C, D, E, F, G
 *      - 7'b1000000: 0 (donde A está apagado, el resto encendidos).
 *      - 7'b1111001: 1 (solo los segmentos B y C encendidos).
 *      - 7'b0100100: 2 (un patrón específico que representa el número 2 en el display).
 *      - 7'b0110000: 3 (y así sucesivamente para los otros números).
 *  - Si se recibe un valor fuera del rango de 0-9, la salida `segmento` será configurada a 7'b1111111,
 *    lo cual apaga todos los segmentos (esto puede considerarse un estado de error o apagado).
 * ---------------------------------------------------------------------
 */
module display7segmentos(
  input wire [3:0] digito,
  output reg [6:0] segmento
);
  
  always @(*) begin
    case (digito)
      4'd0: segmento = 7'b1000000;
      4'd1: segmento = 7'b1111001;
      4'd2: segmento = 7'b0100100;
      4'd3: segmento = 7'b0110000;
      4'd4: segmento = 7'b0011001;
      4'd5: segmento = 7'b0010010;
      4'd6: segmento = 7'b0000010;
      4'd7: segmento = 7'b1111000;
      4'd8: segmento = 7'b0000000;
      4'd9: segmento = 7'b0010000;
      default: segmento = 7'b1111111;
    endcase
  end
  
endmodule
