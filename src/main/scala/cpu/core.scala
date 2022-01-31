package cpu

import chisel3._

import cpu.{ALU, ALUOps}
import fpu.{Float32, FPU, FPUOps}

// A CPU Core
// FPU Out line to RAM, ALU Out line to RAM
// Clock is implict so can keep updating the inputs each clock and pass to ALU, FPU, decoder, etc.
// RISC PIPELINE -> fetch, decode, execute, access, writeback
class CPUCore extends Module {
  // IO
  val io = IO(new Bundle {})

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
