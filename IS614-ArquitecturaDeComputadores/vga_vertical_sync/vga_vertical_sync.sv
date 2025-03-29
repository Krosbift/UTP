module vga_vertical_sync #(
    parameter BACK_PROCH = 33,
    parameter DISPLAY_TIME = 480,
    parameter FRONT_PROCH = 10,
    parameter SYNC_TIME = 2,
    parameter TOTAL = BACK_PROCH + DISPLAY_TIME + FRONT_PROCH + SYNC_TIME
) (
    input logic clk,
    input logic clk_25Mhz,
    input logic new_line,
    output logic vertical_sync = 1,
    output logic vertical_display_sync = 0
);
    
    logic [3:0] dead_vertical_sync_count = FRONT_PROCH; // CLOCK_DEAD_TIME
    logic [2:0] vertical_sync_neg_count = 0; // [0-SYNC_TIME]
    logic [9:0] vertical_sync_pos_count = 0; // [0-(TOTAL-SYNC_TIME)]

    always_ff @(posedge clk) begin
        if (clk_25Mhz && new_line) begin
            if (dead_vertical_sync_count > 0) begin
                dead_vertical_sync_count <= dead_vertical_sync_count - 1;
            end

            if (dead_vertical_sync_count == 0) begin
                if (vertical_sync_neg_count < SYNC_TIME) begin
                    vertical_sync_neg_count <= vertical_sync_neg_count + 1;
                    vertical_sync <= 0;
                end

                if (vertical_sync_neg_count == SYNC_TIME && vertical_sync_pos_count < TOTAL - SYNC_TIME) begin
                    vertical_sync_pos_count <= vertical_sync_pos_count + 1;
                    vertical_sync <= 1;
                end

                if (vertical_sync_neg_count == SYNC_TIME && vertical_sync_pos_count == TOTAL - SYNC_TIME) begin
                    vertical_sync_neg_count <= 0;
                    vertical_sync_pos_count <= 0;
                end
            end
        end
    end

    logic [5:0] vertical_display_sync_neg_count = 0;
    logic [8:0] vertical_display_sync_pos_count = 0;

    always_ff @(posedge clk) begin
        if (clk_25Mhz && new_line) begin
            if (vertical_display_sync_neg_count < BACK_PROCH + FRONT_PROCH + SYNC_TIME) begin
                vertical_display_sync_neg_count <= vertical_display_sync_neg_count + 1;
                vertical_display_sync <= 0;
            end

            if (vertical_display_sync_neg_count == BACK_PROCH + FRONT_PROCH + SYNC_TIME && vertical_display_sync_pos_count < DISPLAY_TIME) begin
                vertical_display_sync_pos_count <= vertical_display_sync_pos_count + 1;
                vertical_display_sync <= 1;
            end

            if (vertical_display_sync_neg_count == BACK_PROCH + FRONT_PROCH + SYNC_TIME && vertical_display_sync_pos_count == DISPLAY_TIME) begin
                vertical_display_sync_neg_count <= 0;
                vertical_display_sync_pos_count <= 0;
            end
        end
    end

endmodule