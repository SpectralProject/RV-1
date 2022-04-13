package mpu

import chisel3._

object Main extends App {
  println("Creating a new CPU Core...")

  (new chisel3.stage.ChiselStage)
    .emitVerilog(new CPU(), Array("--target-dir", "generated"))

  println("Created a CPU Core!")
}
