# RV1 CPU

A RISCV CPU constructed from chisel3. Would be uploaded to an FPGA to test stuff out. Can be converted to Verilog/Sysverilog and into a schematic. Then you could write out the schematic to a PCB which is cool.

Run `sbt run` to see the default output. To simulate, run the tests `sbt test`. Better, convert to verilog and run on verilator.

## Includes GPU RV1

Uses the spectro gpu ISA.
