package mpu

import chisel3._
import chisel3.util._
import chisel3.experimental._

// Fetch an instruction from the instruction buffer
class Fetch {
  val io = IO(new Bundle {
    val instruction = Input(UInt(32.W))
    val out = Output(UInt(32.W))
  })

  // pass onto out (decode)
}

class Decode {
  val io = IO(new Bundle {
    val instruction = Input(UInt(32.W))
    val out = Output(UInt(32.W))
  })
}
