package mpu

import chisel3._

class InstructionCache(entries: Int, width: Int) extends Module {
  val io = IO(new Bundle {
    val cache = VecInit.fill(entries) { Input(UInt(width.W)) }
  })
}
