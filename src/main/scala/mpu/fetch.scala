package mpu

import chisel3._
import chisel3.util._
import chisel3.experimental._

object Opcode extends ChiselEnum {
  // ARITHMETIC
  val add = Value(0x0.U)
  val sub = Value(0x1.U)
  // BITWISE
  val and = Value(0x2.U)
  val or = Value(0x3.U)
  // LOAD/STORE
  val load_byte = Value(0x04.U)
  val store_byte = Value(0x23.U)
  // BRANCH/JUMP
  val br = Value(0x63.U)
  val jal = Value(0x6f.U)
  // MISC
  val nop = Value(0x7f.U)
}

// Since chisel allows implicit conversion, is a decoder even necessary?
// I guess maybe we can let chisel decide how best to generate the output from the implicit conversion

// Fetch an instruction from the instruction buffer
class Fetch {
  val io = IO(new Bundle {
    val instruction = Input(UInt(32.W))
    val out = Output(UInt(32.W))
  })

  // pass onto out (decode)
  io.out := io.instruction
}

class Decode {
  val io = IO(new Bundle {
    val instruction = Input(UInt(32.W))
    val out = Output(Opcode())
  })

  // determine if it is an ALU op, a branch/jump, or noop
  io.out := io.instruction
}
