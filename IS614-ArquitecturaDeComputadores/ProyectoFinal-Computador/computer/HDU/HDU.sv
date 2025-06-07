/**
 * Unidad de Detección de Riesgos (HDU) para un procesador segmentado
 *
 * @input  DMRd_ex  => Señal de lectura de memoria en etapa Execute
 * @input  rd_ex    => Registro destino en etapa Execute
 * @input  rs1_de   => Registro fuente 1 en etapa Decode
 * @input  rs2_de   => Registro fuente 2 en etapa Decode
 *
 * @output HDUStall => Señal de parada para controlar el pipeline
 *
 * Características:
 * - Detecta riesgos de tipo load-use en el pipeline
 * - Genera señal de parada cuando es necesario
 * - Operación combinacional (sin reloj)
 * - Monitorea dependencias entre etapas Execute y Decode
 *
 * Casos de riesgo:
 * - Instrucción load en Execute seguida de instrucción que usa el dato
 * - El registro destino del load coincide con algún registro fuente
 * - Se requiere parada de un ciclo para resolver el riesgo
 *
 * ┌────────────────────────────────────────┐
 * │         Tabla de condiciones           │
 * ├────────────┬───────────────────────────┤
 * │ Condición  │ Acción                    │
 * ├────────────┼───────────────────────────┤
 * │ DMRd_ex=1  │ Verificar dependencias    │
 * │ rd_ex=rs1  │ Genera stall             │
 * │ rd_ex=rs2  │ Genera stall             │
 * └────────────┴───────────────────────────┘
 */
module HDU (
  input  logic       DMRd_ex,
  input  logic [4:0] rd_ex,
  input  logic [4:0] rs1_de,
  input  logic [4:0] rs2_de,
  output logic       HDUStall
);

  assign HDUStall = (DMRd_ex && ((rd_ex == rs1_de) || (rd_ex == rs2_de)));

endmodule