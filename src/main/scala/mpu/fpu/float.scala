package fpu

import chisel3._

// fp32 data type
class Float32 extends Bundle {
  val sign = Bool()
  val exponent = UInt(8.W)
  val significand = UInt(23.W)
}

// fp16, for fast calcs. More appropriate for GPU
class Float16 extends Bundle {
  val sign = Bool()
  val exponent = UInt(5.W)
  val significand = UInt(10.W)
}
