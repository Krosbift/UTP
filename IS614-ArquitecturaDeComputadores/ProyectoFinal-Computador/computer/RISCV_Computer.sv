module RISCV_Computer (
  input  logic       clk_50MHz,
  input  logic       ps2_clk,
  input  logic       ps2_data,
  output logic [7:0] vga_r,
  output logic [7:0] vga_g,
  output logic [7:0] vga_b,
  output logic       vga_clk,
  output logic       vga_sync_n,
  output logic       vga_blank_n,
  output logic       vga_hs,
  output logic       vga_vs
);

  // DISPOSITIVOS DE ENTRADA Y SALIDA

  logic [31:0] VGA_AddressRd = 0;
  logic        PS2_wrreq = 0;
  logic [ 7:0] char = 0;

  PS2_Controller KeyBoard (
    .clk_50MHz(clk_50MHz),
    .ps2_clk(ps2_clk),
    .ps2_data(ps2_data),
    .char(char),
    .wrreq(PS2_wrreq)
  );

  VGA_Controller Screen (
    .clk_50MHz(clk_50MHz),
    .screen_char(IODataRead),
    .r(vga_r),
    .g(vga_g),
    .b(vga_b),
    .clk_25MHz(vga_clk),
    .sync_n(vga_sync_n),
    .blank_n(vga_blank_n),
    .hs(vga_hs),
    .vs(vga_vs),
    .AddressRd(VGA_AddressRd)
  );

  // ETAPA DE FETCH

  logic [31:0] PC_fe    = 32'b0;
  logic [31:0] PCInc_fe = 32'b0;
  logic [31:0] Inst_fe  = 32'b0;
  logic [31:0] MUXPREPC = 32'b0;

  always_comb begin
    MUXPREPC = NextPcSrc ? PCInc_fe : ALURes_ex;
  end

  always_ff @(posedge clk_50MHz) begin
    if (~HDUStall) begin
      PC_fe <= MUXPREPC; 
    end
  end

  always_comb begin
    PCInc_fe = PC_fe + 4;
  end

  IM InstructionMemory (
    .Address(PC_fe),
    .Instruction(Inst_fe)
  );

  // ETAPA DE DECODE

  logic [31:0] PCInc_de       = 32'b0;
  logic [31:0] PC_de          = 32'b0;
  logic [31:0] Inst_de        = 32'b0;
  logic        HDUStall       =  1'b0;
  logic        ALUASrc_de     =  1'b0;
  logic        ALUBSrc_de     =  1'b0;
  logic        DMWr_de        =  1'b0;
  logic        RUWr_de        =  1'b0;
  logic [ 1:0] RuDataWrSrc_de =  2'b0;
  logic [ 2:0] ImmSrc_de      =  3'b0;
  logic [ 2:0] DMCtrl_de      =  3'b0;
  logic [ 3:0] ALUOp_de       =  4'b0;
  logic [ 4:0] BrOp_de        =  5'b0;
  logic [31:0] RUrs1_de       = 32'b0;
  logic [31:0] RUrs2_de       = 32'b0;
  logic [31:0] ImmExt_de      = 32'b0;

  always_ff @(posedge clk_50MHz) begin
    if (NextPcSrc) begin
      PCInc_de <= 32'b0;
    end else if (~HDUStall) begin
      PCInc_de <= PCInc_fe
    end
  end

  always_ff @(posedge clk_50MHz) begin
    PC_de <= PC_fe;
  end

  always_ff @(posedge clk_50MHz) begin
    Inst_de <= Inst_fe;
  end

  CU ControlUnit (
    .OpCode(Inst_de[ 6: 0]),
    .Funct7(Inst_de[31:25]),
    .Funct3(Inst_de[14:12]),
    .ALUASrc_de(ALUASrc_de),
    .ALUBSrc_de(ALUBSrc_de),
    .DMWr_de(DMWr_de),
    .RUWr_de(RUWr_de),
    .RuDataWrSrc_de(RuDataWrSrc_de),
    .ImmSrc_de(ImmSrc_de),
    .DMCtrl_de(DMCtrl_de),
    .ALUOp_de(ALUOp_de),
    .BrOp_de(BrOp_de)
  );

  HDU HazardDetectionUnit (
    .DMRd_ex(DMRd_ex),
    .rd_ex(rd_ex),
    .rs1_de(Inst_de[19:15]),
    .rs2_de(Inst_de[24:20]),
    .HDUStall(HDUStall)
  );

  RU RegisterUnit (
    .clk(clk_50MHz),
    .RUWr_wb(RUWr_wb),
    .rs1_de(Inst_de[19:15]),
    .rs2_de(Inst_de[24:20]),
    .Rd(rd_wb),
    .DataWr(RUDATAMUX),
    .RUrs1(RUrs1_de),
    .RUrs2(RUrs2_de)
  );

  IG ImmediateGenerator (
    .VecImm(Inst_de[31:7]),
    .ImmSrc_de(ImmSrc_de),
    .ImmExt_de(ImmExt_de)
  );

  // ETAPA DE EXECUTE

  logic        ALUASrc_ex     =  1'b0;
  logic        ALUBSrc_ex     =  1'b0;
  logic        DMWr_ex        =  1'b0;
  logic        RUWr_ex        =  1'b0;
  logic [ 1:0] RuDataWrSrc_ex =  2'b0;
  logic [ 2:0] ImmSrc_ex      =  3'b0;
  logic [ 2:0] DMCtrl_ex      =  3'b0;
  logic [ 3:0] ALUOp_ex       =  4'b0;
  logic [ 4:0] BrOp_ex        =  5'b0;
  logic [31:0] PCInc_ex       = 32'b0;
  logic [31:0] PC_ex          = 32'b0;
  logic [31:0] RUrs1_ex       = 32'b0;
  logic [31:0] RUrs2_ex       = 32'b0;
  logic [31:0] ImmExt_ex      = 32'b0;
  logic [31:0] rd_ex          = 32'b0;
  logic [31:0] rs1_ex         = 32'b0;
  logic [31:0] rs2_ex         = 32'b0;
  logic [ 1:0] FUASrc         =  2'b0;
  logic [ 1:0] FUBSrc         =  2'b0;
  logic        NextPcSrc      =  1'b0;
  logic [31:0] MUXTRIPLEA     = 32'b0;
  logic [31:0] MUXTRIPLEB     = 32'b0;
  logic [31:0] MUXDOBLEA      = 32'b0;
  logic [31:0] MUXDOBLEB      = 32'b0;
  logic [31:0] ALURes_ex      = 32'b0;

  always_ff @(posedge clk_50MHz) begin
    if (HDUStall || NextPcSrc) begin
      ALUASrc_ex     <= 1'b0;
      ALUBSrc_ex     <= 1'b0;
      DMWr_ex        <= 1'b0;
      RUWr_ex        <= 1'b0;
      RuDataWrSrc_ex <= 2'b0;
      ImmSrc_ex      <= 3'b0;
      DMCtrl_ex      <= 3'b0;
      ALUOp_ex       <= 4'b0;
      BrOp_ex        <= 5'b0;
    end else begin
      ALUASrc_ex     <= ALUASrc_de;
      ALUBSrc_ex     <= ALUBSrc_de;
      DMWr_ex        <= DMWr_de;
      RUWr_ex        <= RUWr_de;
      RuDataWrSrc_ex <= RuDataWrSrc_de;
      ImmSrc_ex      <= ImmSrc_de;
      DMCtrl_ex      <= DMCtrl_de;
      ALUOp_ex       <= ALUOp_de;
      BrOp_ex        <= BrOp_de;
    end
  end

  always_ff @(posedge clk_50MHz) begin
    PCInc_ex <= PCInc_de;
  end

  always_ff @(posedge clk_50MHz) begin
    PC_ex <= PC_de;
  end

  always_ff @(posedge clk_50MHz) begin
    RUrs1_ex <= RUrs1_de;
  end

  always_ff @(posedge clk_50MHz) begin
    RUrs2_ex <= RUrs2_de;
  end

  always_ff @(posedge clk_50MHz) begin
    ImmExt_ex <= ImmExt_de;
  end

  always_ff @(posedge clk_50MHz) begin
    rd_ex <= Inst_de[11:7];
  end

  always_ff @(posedge clk_50MHz) begin
    rs1_ex <= Inst_de[19:15];
  end

  always_ff @(posedge clk_50MHz) begin
    rs2_ex <= Inst_de[24:20];
  end

  BU BranchUnit (
    .A(MUXTRIPLEA),
    .B(MUXTRIPLEB),
    .BrOp_ex(BrOp_ex),
    .NextPcSrc(NextPcSrc)
  );

  ALU ALU (
    .A(MUXDOBLEA),
    .B(MUXDOBLEB),
    .ALUOp_ex(ALUOp_ex),
    .ALURes_ex(ALURes_ex)
  );

  FU ForwardingUnit (
    .RUWr_me(RUWr_me),
    .RUWr_wb(RUWr_wb),
    .rd_me(rd_me),
    .rd_wb(rd_wb),
    .rs1_ex(rs1_ex),
    .rs2_ex(rs2_ex),
    .FUASrc(FUASrc),
    .FUBSrc(FUBSrc)
  );

  always_comb begin
    MUXTRIPLEA = FUASrc == 2'b00 ? RUrs1_ex :
                 FUASrc == 2'b01 ? ALURes_me :
                 FUASrc == 2'b10 ? RUDATAMUX :
                 0;
  end

  always_comb begin
    MUXTRIPLEB = FUBSrc == 2'b00 ? RUrs2_ex :
                 FUBSrc == 2'b01 ? ALURes_me : 
                 FUBSrc == 2'b10 ? RUDATAMUX :
                 0;
  end

  always_comb begin
    MUXDOBLEA = ALUASrc_ex ? MUXTRIPLEA : PC_ex;
  end

  always_comb begin
    MUXDOBLEB = ALUBSrc_ex ? MUXTRIPLEB : ImmExt_ex;
  end

  // ETAPA DE MEMORY

  logic        ALUASrc_me     =  1'b0;
  logic        ALUBSrc_me     =  1'b0;
  logic        DMWr_me        =  1'b0;
  logic        RUWr_me        =  1'b0;
  logic [ 1:0] RuDataWrSrc_me =  2'b0;
  logic [ 2:0] ImmSrc_me      =  3'b0;
  logic [ 2:0] DMCtrl_me      =  3'b0;
  logic [ 3:0] ALUOp_me       =  4'b0;
  logic [ 4:0] BrOp_me        =  5'b0;
  logic [31:0] PCInc_me       = 32'b0;
  logic [31:0] ALURes_me      = 32'b0;
  logic [31:0] RUrs2_me       = 32'b0;
  logic [31:0] rd_me          = 32'b0;
  logic [31:0] DMDataRd       = 32'b0;
  logic [ 7:0] IODataRead     =  8'b0; 
  logic        FIFOempty      =  1'b0;
  logic [ 7:0] FIFOBufferq    =  8'b0;
  logic        FIFOrdreq      =  1'b0;
  logic [31:0] ME_ControlA    = 32'b0;
  logic [31:0] ME_ControlB    = 32'b0;
  logic [31:0] ME_MUXA        = 32'b0;
  logic [31:0] MEMUXB         = 32'b0;

  // Declarations for improved I/O addressing logic
  logic        is_io_address_range;
  logic        is_cpu_mem_op_me;    // True if CPU is performing a load or store in ME stage
  logic        cpu_accessing_io_me; // True if CPU is accessing the I/O region in ME stage
  logic        DMWrAnd;             // To enable Data Memory write
  logic        IOWrEnable;          // To enable IORAM write by CPU
  logic [13:0] IOAddress;           // Address for IORAM

  always_ff @(posedge clk_50MHz) begin
    ALUASrc_me     <= ALUASrc_ex;
    ALUBSrc_me     <= ALUBSrc_ex;
    DMWr_me        <= DMWr_ex;
    RUWr_me        <= RUWr_ex;
    RuDataWrSrc_me <= RuDataWrSrc_ex;
    ImmSrc_me      <= ImmSrc_ex;
    DMCtrl_me      <= DMCtrl_ex;
    ALUOp_me       <= ALUOp_ex;
    BrOp_me        <= BrOp_ex;
  end

  always_ff @(posedge clk_50MHz) begin
    PCInc_me <= PCInc_ex;
  end

  always_ff @(posedge clk_50MHz) begin
    ALURes_me <= ALURes_ex;
  end

  always_ff @(posedge clk_50MHz) begin
    RUrs2_me <= RUrs2_ex;
  end

  always_ff @(posedge clk_50MHz) begin
    rd_me <= rd_ex;
  end

  assign is_io_address_range = ~(ALURes_me[10] & ALURes_me[9]);
  assign is_cpu_mem_op_me = DMWr_me | (RuDataWrSrc_me == 2'b01);
  assign cpu_accessing_io_me = is_cpu_mem_op_me & is_io_address_range;

  assign DMWrAnd = ~is_io_address_range & DMWr_me;
  assign IOWrEnable = is_io_address_range & DMWr_me;

  assign IOAddress = cpu_accessing_io_me ? ALURes_me[13:0] : VGA_AddressRd[13:0];

  always_comb begin
    FIFOrdreq = ~FIFOempty & ALURes_me[11];
  end

  DM DataMemory (
    .clk(clk_50MHz),
    .DMWr_me(DMWrAnd),
    .DMCtrl_me(DMCtrl_me),
    .Address(ALURes_me[11:0]),
    .DataWr(RUrs2_me),
    .DataRd(DMDataRd)
  );

  IORAM IORAM (
    .clk(clk_50MHz),
    .Address(IOAddress),
    .DataWrite(RUrs2_me),    
    .WrEnable(IOWrEnable),
    .DataRead(IODataRead)
  );

  FIFO FIFOCache (
    .clk(clk_50MHz),
    .data(char),
    .rdreq(FIFOrdreq),
    .wrreq(PS2_wrreq), 
    .empty(FIFOempty),
    .q(FIFOBufferq)
  );

  always_comb begin
    ME_ControlA = ALURes_me[13] & ALURes_me[12];
  end

  always_comb begin
    ME_ControlB = ALURes_me[14] & FIFOempty;
  end

  always_comb begin
    ME_MUXA = ME_ControlB ? FIFOBufferq : 0 ;
  end

  always_comb begin
    MEMUXB = ME_ControlA ? DMDataRd : ME_MUXA;
  end

  // ETAPA DE WRITE BACK

  logic        ALUASrc_wb     =  1'b0;
  logic        ALUBSrc_wb     =  1'b0;
  logic        DMWr_wb        =  1'b0;
  logic        RUWr_wb        =  1'b0;
  logic [ 1:0] RuDataWrSrc_wb =  2'b0;
  logic [ 2:0] ImmSrc_wb      =  3'b0;
  logic [ 2:0] DMCtrl_wb      =  3'b0;
  logic [ 3:0] ALUOp_wb       =  4'b0;
  logic [ 4:0] BrOp_wb        =  5'b0;
  logic [31:0] PCInc_wb       = 32'b0;
  logic [31:0] DMDataRD_wb    = 32'b0;
  logic [31:0] ALURes_wb      = 32'b0;
  logic [31:0] rd_wb          = 32'b0;
  logic [31:0] RUDATAMUX      = 32'b0;

  always_ff @(posedge clk_50MHz) begin
    ALUASrc_wb     <= ALUASrc_me;
    ALUBSrc_wb     <= ALUBSrc_me;
    DMWr_wb        <= DMWr_me;
    RUWr_wb        <= RUWr_me;
    RuDataWrSrc_wb <= RuDataWrSrc_me;
    ImmSrc_wb      <= ImmSrc_me;
    DMCtrl_wb      <= DMCtrl_me;
    ALUOp_wb       <= ALUOp_me;
    BrOp_wb        <= BrOp_me;
  end

  always_ff @(posedge clk_50MHz) begin
    PCInc_wb <= PCInc_me;
  end

  always_ff @(posedge clk_50MHz) begin
    DMDataRD_wb <= MEMUXB;
  end

  always_ff @(posedge clk_50MHz) begin
    ALURes_wb <= ALURes_me;
  end

  always_ff @(posedge clk_50MHz) begin
    rd_wb <= rd_me;
  end

  always_comb begin
    RUDATAMUX = RuDataWrSrc_wb == 2'b00 ? ALURes_wb :
                RuDataWrSrc_wb == 2'b01 ? DMDataRD_wb :
                RuDataWrSrc_wb == 2'b10 ? PCInc_wb :
                0;
  end

endmodule