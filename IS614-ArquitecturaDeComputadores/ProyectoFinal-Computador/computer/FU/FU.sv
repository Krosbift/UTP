/**
 * Unidad de Reenvío (Forwarding Unit) para un procesador segmentado
 *
 * @input  RUWr_me  => Señal de escritura en registro en etapa Memory
 * @input  RUWr_wb  => Señal de escritura en registro en etapa Writeback
 * @input  rd_me    => Registro destino en etapa Memory
 * @input  rd_wb    => Registro destino en etapa Writeback
 * @input  rs1_ex   => Registro fuente 1 en etapa Execute
 * @input  rs2_ex   => Registro fuente 2 en etapa Execute

 * @output FUASrc   => Control de forwarding para operando A
 * @output FUBSrc   => Control de forwarding para operando B
 *
 * Características:
 * - Maneja reenvío de datos desde etapas Memory y Writeback
 * - Resuelve riesgos de datos por dependencias RAW
 * - Operación combinacional (sin reloj)
 * - Monitorea dependencias entre etapas ME/WB y EX
 *
 * Códigos de control:
 * - 00: No forwarding (usa valor original)
 * - 01: Forwarding desde etapa Memory
 * - 10: Forwarding desde etapa Writeback
 *
 * ┌────────────────────────────────────────┐
 * │         Tabla de Forwarding            │
 * ├────────────┬───────────────────────────┤
 * │ Condición  │ Acción                    │
 * ├────────────┼───────────────────────────┤
 * │ ME-EX      │ Forward desde Memory      │
 * │ WB-EX      │ Forward desde Writeback   │
 * │ Ninguna    │ Usar valor original       │
 * └────────────┴───────────────────────────┘
 */
module FU (
  input  logic       RUWr_me,
  input  logic       RUWr_wb,
  input  logic [4:0] rd_me,
  input  logic [4:0] rd_wb,
  input  logic [4:0] rs1_ex,
  input  logic [4:0] rs2_ex,
  output logic [1:0] FUASrc,
  output logic [1:0] FUBSrc
);

  assign FUASrc = (RUWr_me && (rd_me == rs1_ex)) ? 2'b01:
                  (RUWr_wb && (rd_wb == rs1_ex)) ? 2'b10:
                                                   2'b00;

  assign FUBSrc = (RUWr_me && (rd_me == rs2_ex)) ? 2'b01:
                  (RUWr_wb && (rd_wb == rs2_ex)) ? 2'b10:
                                                   2'b00;
endmodule