module vga_horizontal_sync #(
    parameter BACK_PROCH = 48,
    parameter DISPLAY_TIME = 640,
    parameter FRONT_PROCH = 16,
    parameter SYNC_TIME = 96,
    parameter TOTAL = BACK_PROCH + DISPLAY_TIME + FRONT_PROCH + SYNC_TIME
) (
    input logic clk,
    input logic clk_25Mhz,
    output logic horizontal_sync = 1,
    output logic horizontal_display_sync = 0,
    output logic new_line = 0
);
    
    logic [4:0] dead_horizontal_sync_count = FRONT_PROCH; // CLOCK_DEAD_TIME
    logic [6:0] horizontal_sync_neg_count = 0; // [0-SYNC_TIME]
    logic [9:0] horizontal_sync_pos_count = 0; // [0-(TOTAL-SYNC_TIME)]

    always_ff @(posedge clk) begin
        if (clk_25Mhz) begin
            if (dead_horizontal_sync_count > 0) begin
                dead_horizontal_sync_count <= dead_horizontal_sync_count - 1;
            end

            if (dead_horizontal_sync_count == 0) begin
                if (horizontal_sync_neg_count < SYNC_TIME) begin
                    horizontal_sync_neg_count <= horizontal_sync_neg_count + 1;
                    horizontal_sync <= 0;
                    new_line <= 0;
                end

                if (horizontal_sync_neg_count == SYNC_TIME && horizontal_sync_pos_count < TOTAL - SYNC_TIME) begin
                    horizontal_sync_pos_count <= horizontal_sync_pos_count + 1;
                    horizontal_sync <= 1;
                end

                if (horizontal_sync_neg_count == SYNC_TIME && horizontal_sync_pos_count == TOTAL - SYNC_TIME) begin
                    horizontal_sync_neg_count <= 0;
                    horizontal_sync_pos_count <= 0;
                    new_line <= 1;
                end
            end
        end
    end

    logic [7:0] horizontal_display_sync_neg_count = 0; // [0-(BACK_PROCH+FRONT_PROCH+SYNC_TIME)]
    logic [9:0] horizontal_display_sync_pos_count = 0; // [0-DISPLAY_TIME]

    always_ff @(posedge clk) begin
        if (clk_25Mhz) begin
            if (horizontal_display_sync_neg_count < BACK_PROCH + FRONT_PROCH + SYNC_TIME) begin
                horizontal_display_sync_neg_count <= horizontal_display_sync_neg_count + 1;
                horizontal_display_sync <= 0;
            end

            if (horizontal_display_sync_neg_count == BACK_PROCH + FRONT_PROCH + SYNC_TIME && horizontal_display_sync_pos_count < DISPLAY_TIME) begin
                horizontal_display_sync_pos_count <= horizontal_display_sync_pos_count + 1;
                horizontal_display_sync <= 1;
            end

            if (horizontal_display_sync_neg_count == BACK_PROCH + FRONT_PROCH + SYNC_TIME && horizontal_display_sync_pos_count == DISPLAY_TIME) begin
                horizontal_display_sync_neg_count <= 0;
                horizontal_display_sync_pos_count <= 0;
            end
        end
    end

endmodule