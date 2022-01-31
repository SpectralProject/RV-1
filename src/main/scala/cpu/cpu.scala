package cpu

import chisel3._

import cpu.{ALU, ALUOps, CPUCore}
import fpu.{Float32, FPU, FPUOps}

// The Entire CPU
// Instruction Line from RAM, Control Line to and from RAM
class CPU extends Module {
  // IO
  val io = IO(new Bundle {})

  // Should add 8-32 Cores. Min 1, max 64

  // When control line is on, send instruction to any core
  // unless the instruction itself says to specifically use a certain core / a core label has been set already
  // i.e. quick decode of the first 6 bits to see which core if there is an instruction to set a core for the next set of instructions

  // SO maybe fetch and decode here. Then send to a certain core or something
  // Then cache the instruction as it goes down and down

  // Apparently a prefetch instruction if you can lookahead in the code (PC) to see what instructions and data are needed
  // then can prefetch and cache a future pointer or value
  val core = Module(new CPUCore())

}
