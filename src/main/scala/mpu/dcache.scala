package mpu

import chisel3._

// data cache, should be usually [addr: val] = [64bits, 64bits]
// normally frequently used constants or pointers
class DataCache(entries: Int, width: Int) extends Module {
  val io = IO(new Bundle {
    val cache = VecInit.fill(entries) { Input(UInt(width.W)) }
  })
}

// access the data cache. usually size = 1MB
class L2TLB extends Module {
  val io = IO(new Bundle {
    val cache = VecInit.fill(160000) { Input(UInt(64.W)) }
  })
}

// frontend, near instruction fetch unit
class uOpCache extends Module {
  val io = IO(new Bundle {
    val cache = VecInit.fill(200) { Input(UInt(64.W)) }
  })
}
