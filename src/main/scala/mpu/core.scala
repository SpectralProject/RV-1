package mpu

import chisel3._
import chisel3.util._
import chisel3.experimental._
import chisel3.experimental.BundleLiterals._

import mpu.{ALU, ALU64, ALUOps}
import mpu.{Float32, FPU, FPUOps}

// mostly just for storage
class RegisterFile(number: Int, width: Int) extends Module {
  val io = IO(new Bundle {
    val registers = VecInit.fill(number) { Input(UInt(width.W)) }
  })
}

// all the instructions
// idk if we can just compare it directly like this, but i dont see why
// maybe its less efficient when you implement it
object RV32I_Instruction extends ChiselEnum {
  val load = Value(0x03.U)
  val imm = Value(0x13.U)
  val auipc = Value(0x17.U)
  val store = Value(0x23.U)
  val reg = Value(0x33.U)
  val lui = Value(0x37.U)
  val br = Value(0x63.U)
  val jalr = Value(0x67.U)
  val jal = Value(0x6f.U)
}

class CoreInstructionDecode extends Module {}

// A CPU Core
class CPUCore extends Module {
  // IO
  val io = IO(new Bundle {})

  // Initialise stuff
  val registers = Module(new RegisterFile(31, 64))

  for (i <- 31 until 64) {
    registers.io.registers(i) := 0.U(64.W)
  }

  // Check any pending writebacks to RAM. Differences between L2 and L1 cache

  // Fetch Instruction

  // Cache the instruction maybe

  // Decode into inputs to ALU/FPU

  // ALUs
  val x = WireDefault(1.S(64.W))
  val y = WireDefault(1.S(64.W))
  // val res = WireDefault(1.S(64.W))
  // val res = 1.S(64.W)

  val alu = Module(new ALU(64))
  // initialise ALU inputs
  alu.io.op := ALUOps.add
  alu.io.x := x
  alu.io.y := y
  val res = alu.io.res

  printf("res is %d\n", alu.io.res)
  // println(p"res = $res")

  // FPU
  val a = Wire(new Float32)
  val b = Wire(new Float32)
  a.sign := false.B
  a.exponent := 10.U(8.W)
  a.significand := 50.U(23.W)
  b.sign := false.B
  b.exponent := 10.U(8.W)
  b.significand := 50.U(23.W)

  val fpu = Module(new FPU(32))
  // fpu inputs
  fpu.io.op := FPUOps.add
  fpu.io.x := a
  fpu.io.y := b

  // CACHE

  // MEMORY ACCESS -> Cache and RAM?

  // WRITEBACK -> write back values to cache

}
