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
    val idx = Wire(UInt())
    sm_list(idx).op := 2.U(3.W)
  }

  // how to index
  // val idx = Wire(UInt())
  // sm_list(idx).wen := true.B
}

class GPU extends Module {
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

object Main extends App {
  println("Creating a new GPU Core...")

  (new chisel3.stage.ChiselStage)
    .emitVerilog(new GPU(), Array("--target-dir", "generated"))

  println("Created a GPU Core!")
}
