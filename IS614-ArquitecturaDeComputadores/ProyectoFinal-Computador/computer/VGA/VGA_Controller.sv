/**
 * Módulo Controlador VGA (VGA_Controller)
 * Genera señales de sincronización y control para una pantalla VGA 640x480@60Hz
 *
 * Parámetros Horizontales:
 * @param H_DISPLAY_TIME  => Tiempo de visualización horizontal (640 píxeles)
 * @param H_FRONT_PORCH   => Porch delantero horizontal (16 píxeles)
 * @param H_SYNC_PULSE    => Pulso de sincronización horizontal (96 píxeles)
 * @param H_BACK_PORCH    => Porch trasero horizontal (48 píxeles)
 * @param H_TOTAL_PIXELS  => Total de píxeles por línea (800)
 *
 * Parámetros Verticales:
 * @param V_DISPLAY_LINES => Líneas visibles (480 líneas)
 * @param V_FRONT_PORCH   => Porch delantero vertical (10 líneas)
 * @param V_SYNC_PULSE    => Pulso de sincronización vertical (2 líneas)
 * @param V_BACK_PORCH    => Porch trasero vertical (33 líneas)
 * @param V_TOTAL_LINES   => Total de líneas por frame (525)
 *
 * Entradas:
 * @input clk_50MHz       => Reloj principal de 50MHz
 * @input screen_char     => Carácter a mostrar en pantalla [7:0]
 *
 * Salidas:
 * @output r,g,b          => Canales de color RGB [7:0]
 * @output clk_25MHz      => Reloj dividido a 25MHz
 * @output sync_n         => Señal de sincronización (activa en bajo)
 * @output blank_n        => Señal de blanking (activa en bajo)
 * @output hs             => Sincronización horizontal
 * @output vs             => Sincronización vertical
 * @output AddressRd      => Dirección de lectura [31:0]
 *
 * Señales Internas:
 * - hds: Señal de visualización horizontal activa
 * - vds: Señal de visualización vertical activa
 * - new_line: Indica inicio de nueva línea
 * - dead_horizontal_sync_count: Contador para el front porch horizontal
 * - horizontal_sync_neg_count: Contador para el pulso de sincronización horizontal negativo
 * - horizontal_sync_pos_count: Contador para el pulso de sincronización horizontal positivo
 * - horizontal_display_sync_neg_count: Contador para el periodo no visible horizontal
 * - horizontal_display_sync_pos_count: Contador para el periodo visible horizontal
 * - dead_vertical_sync_count: Contador para el front porch vertical
 * - vertical_sync_neg_count: Contador para el pulso de sincronización vertical negativo
 * - vertical_sync_pos_count: Contador para el pulso de sincronización vertical positivo 
 * - vertical_display_sync_neg_count: Contador para el periodo no visible vertical
 * - vertical_display_sync_pos_count: Contador para el periodo visible vertical
 *
 * ┌────────────────────────────────────────────────────────────────┐
 * │                  Diagrama de tiempos VGA                       │
 * │                                                                │
 * │ Horizontal: [BP][Display][FP][Sync] = 48+640+16+96 = 800       │
 * │ Vertical:   [BP][Display][FP][Sync] = 33+480+10+2  = 525       │
 * │                                                                │
 * │ Frecuencia de refresco: 60Hz                                   │
 * │ Reloj de píxel: 25MHz                                          │
 * └────────────────────────────────────────────────────────────────┘
 *
 * ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────┐
 * │                  Diagrama de tiempos VGA Horizontal                                                        │
 * │                                                                                                            │
 * │     [BackPorch]->[DisplayTime]->[FrontPorch]->[SyncPulse]                                                  │
 * │                                                                                                            │
 * │     ┌─────────────────────────────────────────────────────────────────────────────────────┐                │
 * │ hs                                                                                        └──────────────  │
 * │           ┌───────────────────────────────────────────────────────────────────────────────┐                │
 * │ hds ──────┘                                                                               └──────────────  │
 * │     0     0                                                                               6 7           8  │
 * │     0     4                                                                               8 0           0  │
 * │     0     8                                                                               8 4           0  │
 * └────────────────────────────────────────────────────────────────────────────────────────────────────────────┘
 *
 * ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────┐
 * │                  Diagrama de tiempos VGA Vertical                                                          │
 * │                                                                                                            │
 * │     [BackPorch]->[DisplayLines]->[FrontPorch]->[SyncPulse]                                                 │
 * │                                                                                                            │
 * │     ┌───────────────────────────────────────────────────────────────────────┐                              │
 * │ vs                                                                          └─                             │
 * │           ┌───────────────────────────────────────────────────────────────┐                                │
 * │ vds ──────┘                                                               └───                             │
 * │     0     0                                                               5 5 5                            │
 * │     0     3                                                               1 2 2                            │
 * │     0     3                                                               3 3 5                            │
 * └────────────────────────────────────────────────────────────────────────────────────────────────────────────┘
 **/
module VGA_Controller (
  input  logic        clk_50MHz,
  input  logic [7:0]  screen_char,
  output logic [7:0]  r         = 0,
  output logic [7:0]  g         = 0,
  output logic [7:0]  b         = 0,
  output logic        clk_25MHz = 0,
  output logic        sync_n,
  output logic        blank_n,
  output logic        hs        = 1,
  output logic        vs        = 1,
  output logic [31:0] AddressRd = 0
);

  // RELOJ DE 25MHZ PARA TENER EL VGA A 60Hz
  always_ff @(posedge clk_50MHz) begin
    clk_25MHz <= !clk_25MHz;
  end

  // PARÁMETRO DE DIMENSIONES DE LA PANTALLA
  localparam H_DISPLAY_TIME = 640;
  localparam H_FRONT_PORCH  = 16;
  localparam H_SYNC_PULSE   = 96;
  localparam H_BACK_PORCH   = 48;
  localparam H_TOTAL_PIXELS = H_DISPLAY_TIME + H_FRONT_PORCH + H_SYNC_PULSE + H_BACK_PORCH;
  localparam V_DISPLAY_LINES = 480;
  localparam V_FRONT_PORCH   = 10;
  localparam V_SYNC_PULSE    = 2;
  localparam V_BACK_PORCH    = 33;
  localparam V_TOTAL_LINES   = V_DISPLAY_LINES + V_FRONT_PORCH + V_SYNC_PULSE + V_BACK_PORCH;

  // VARIABLES PARA EL MANEJO DE SINCRONIZACIÓN Y TIEMPO EN PANTALLA VISIBLE
  logic new_line = 0;
  logic hds = 0;
  logic vds = 0;

  // MANEJO DE LA SINCRONIZACIÓN HORIZONTAL
  logic [4:0] dead_horizontal_sync_count = H_FRONT_PORCH;
  logic [6:0] horizontal_sync_neg_count = 0;
  logic [9:0] horizontal_sync_pos_count = 0;

  always_ff @(posedge clk_50MHz) begin
    if (clk_25MHz) begin
      if (dead_horizontal_sync_count > 0) begin
        dead_horizontal_sync_count <= dead_horizontal_sync_count - 1;
      end

      if (dead_horizontal_sync_count == 0) begin
        if (horizontal_sync_neg_count < H_SYNC_PULSE) begin
          horizontal_sync_neg_count <= horizontal_sync_neg_count + 1;
          hs <= 0;
          new_line <= 0;
        end

        if (horizontal_sync_neg_count == H_SYNC_PULSE && horizontal_sync_pos_count < H_TOTAL_PIXELS - H_SYNC_PULSE) begin
          horizontal_sync_pos_count <= horizontal_sync_pos_count + 1;
          hs <= 1;
        end

        if (horizontal_sync_neg_count == H_SYNC_PULSE && horizontal_sync_pos_count == H_TOTAL_PIXELS - H_SYNC_PULSE) begin
          horizontal_sync_neg_count <= 0;
          horizontal_sync_pos_count <= 0;
          new_line <= 1;
        end
      end
    end
  end

  logic [7:0] horizontal_display_sync_neg_count = 0;
  logic [9:0] horizontal_display_sync_pos_count = 0;

  always_ff @(posedge clk_50MHz) begin
    if (clk_25MHz) begin
      if (horizontal_display_sync_neg_count < H_BACK_PORCH + H_FRONT_PORCH + H_SYNC_PULSE) begin
        horizontal_display_sync_neg_count <= horizontal_display_sync_neg_count + 1;
        hds <= 0;
      end

      if (horizontal_display_sync_neg_count == H_BACK_PORCH + H_FRONT_PORCH + H_SYNC_PULSE && 
          horizontal_display_sync_pos_count < H_DISPLAY_TIME) begin
        horizontal_display_sync_pos_count <= horizontal_display_sync_pos_count + 1;
        hds <= 1;
      end

      if (horizontal_display_sync_neg_count == H_BACK_PORCH + H_FRONT_PORCH + H_SYNC_PULSE && 
          horizontal_display_sync_pos_count == H_DISPLAY_TIME) begin
        horizontal_display_sync_neg_count <= 0;
        horizontal_display_sync_pos_count <= 0;
      end
    end
  end

  // MANEJO DE LA SINCRONIZACIÓN VERTICAL
  logic [3:0] dead_vertical_sync_count = V_FRONT_PORCH;
  logic [2:0] vertical_sync_neg_count = 0;
  logic [9:0] vertical_sync_pos_count = 0;

  always_ff @(posedge clk_50MHz) begin
    if (clk_25MHz && new_line) begin
      if (dead_vertical_sync_count > 0) begin
        dead_vertical_sync_count <= dead_vertical_sync_count - 1;
      end

      if (dead_vertical_sync_count == 0) begin
        if (vertical_sync_neg_count < V_SYNC_PULSE) begin
          vertical_sync_neg_count <= vertical_sync_neg_count + 1;
          vs <= 0;
        end

        if (vertical_sync_neg_count == V_SYNC_PULSE && vertical_sync_pos_count < V_TOTAL_LINES - V_SYNC_PULSE) begin
          vertical_sync_pos_count <= vertical_sync_pos_count + 1;
          vs <= 1;
        end

        if (vertical_sync_neg_count == V_SYNC_PULSE && vertical_sync_pos_count == V_TOTAL_LINES - V_SYNC_PULSE) begin
          vertical_sync_neg_count <= 0;
          vertical_sync_pos_count <= 0;
        end
      end
    end
  end

  logic [5:0] vertical_display_sync_neg_count = 0;
  logic [8:0] vertical_display_sync_pos_count = 0;

  always_ff @(posedge clk_50MHz) begin
    if (clk_25MHz && new_line) begin
      if (vertical_display_sync_neg_count < V_BACK_PORCH + V_FRONT_PORCH + V_SYNC_PULSE) begin
        vertical_display_sync_neg_count <= vertical_display_sync_neg_count + 1;
        vds <= 0;
      end

      if (vertical_display_sync_neg_count == V_BACK_PORCH + V_FRONT_PORCH + V_SYNC_PULSE && 
          vertical_display_sync_pos_count < V_DISPLAY_LINES) begin
        vertical_display_sync_pos_count <= vertical_display_sync_pos_count + 1;
        vds <= 1;
      end

      if (vertical_display_sync_neg_count == V_BACK_PORCH + V_FRONT_PORCH + V_SYNC_PULSE && 
          vertical_display_sync_pos_count == V_DISPLAY_LINES) begin
        vertical_display_sync_neg_count <= 0;
        vertical_display_sync_pos_count <= 0;
      end
    end
  end

  // CONEXIÓN ADICIONAL CON EL CHARROM PARA LA TRADUCCIÓN DE CARACTERES
  logic [2:0] pixel_x;
  logic [7:0] char_line_out;
  logic pixel_on;

  CharROM CHARROM (
    .char_code(screen_char),
    .char_line_number(vertical_display_sync_pos_count[3:0]),
    .char_line_out(char_line_out)
  );

  assign sync_n = ~(hds && vds);
  assign blank_n = (hds && vds);
  assign pixel_x = horizontal_display_sync_pos_count[2:0];
  assign pixel_on = char_line_out[7 - pixel_x];

  always_ff @(posedge clk_50MHz) begin
    if (hds && vds) begin
      r <= pixel_on ? 8'hFF : 8'h00;
      g <= pixel_on ? 8'h00 : 8'h00;
      b <= pixel_on ? 8'hF0 : 8'h00;
    end else begin
      r <= 8'h00;
      g <= 8'h00;
      b <= 8'h00;
    end
  end

  assign AddressRd = (vertical_display_sync_pos_count[8:4] * 80) + horizontal_display_sync_pos_count[9:3];

endmodule
