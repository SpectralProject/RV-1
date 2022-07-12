package gpu

import chisel3._
import chisel3.util._
import chisel3.experimental._
import chisel3.experimental.BundleLiterals._

class INT32Unit extends Module {}
class FP32Unit extends Module {}

// ! just hardcode the stuff in
class StreamingCore() extends Module {
  // needs int32 and fp32 (GPU specific ISA that kinda looks like riscv32imf)
  val fp32_unit = Module(new FP32Unit)
  val int32_unit = Module(new INT32Unit)

  class InstructionFetcher
  class Decoder
  class ResultQueue
}

class SM(n: Int) extends Module {
  val io = IO(new Bundle {
    val op = Input(UInt(3.W))
  })
  // val sp_list = VecInit.fill(n) { Module(new StreamingCore) }

}

// each cluster contains 30 SMs
class GPUCluster(n: Int) extends Module {
  val sm_list = VecInit.fill(n) { Module(new SM(30)).io }

  for (i <- 0 until n) {
    sm_list(i).op := 2.U(3.W)
  }

  // how to index
  // val idx = Wire(UInt())
  // sm_list(idx).wen := true.B
}

// SIMD on an SM?
// so you have a warp that contains 1 instruction
// and 32 data units. Each unit prob like 32-bits or 64-bits
// These data units are "logical threads" of data in VRAM prob
// Which you can load into a physical reg within the SM first
// may need up to 32 LD/ST units

// Spectre PT (prototype)
// use warps as well. Or kernels
// 1 way scheduling
// 1 queue for all warps to enter the GPU from the microcontroller/VRAM
// each warp is executed in sequence or out of order if possible. But if isb/dsb is specified, make sure to execute in order
// simple branch prediction and instruction fetch based on most recent request for data read/write and branch
// hardly any cache. Maybe like 1KB L1-L2 built into the HDL for FPGA, scaled up if needed
// "64 bit" addressing. Can address up to 64 bits of memory but is configured with 2GB VRAM by default. Uses virtual memory through a L1/L2 TLBs built into the SM and cluster. Should be configured by the CPU for something like vulkan or multiple apps

class GPU extends Module {
  // BUS to microcontroller (STM32)
  // BUS TO VRAM (2GB/parameterised)
  val io = IO(new Bundle {})

  // clusters, or 'cores'
  val cluster_1 = Module(new GPUCluster(4))
  val cluster_2 = Module(new GPUCluster(4))
  val cluster_3 = Module(new GPUCluster(4))
  val cluster_4 = Module(new GPUCluster(4))

  // also has access to very fast L2 cache (compared to vram)
  class L2DCache(n_mb: Int) extends Module

  val l2_cache = Module(new L2DCache(1))

  class DRAM(n_gb: Int) extends Module

  val vram = Module(new DRAM(10))

}

class RTCore extends Module {
  // FP12 calculations on normalised verts, a clip space at a time
  // up to an accuracy of 7 decimal places
}

class ThreadBlock {
  // N RT Cores per thread block in a grid like manner
  // grid topology is very cool
}

class RTU extends Module {
  val io = IO(new Bundle{})
  // includes thread blocks of RT cores in a grid topology
}

object Main extends App {
  println("Creating a new GPU Core...")

  (new chisel3.stage.ChiselStage)
    .emitVerilog(new GPU(), Array("--target-dir", "generated"))

  println("Created a GPU Core!")
}
