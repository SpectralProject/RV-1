package fpu

import chisel3._
import chisel3.util._
import chisel3.experimental._
import chisel3.experimental.BundleLiterals._

import fpu.Float32

object FPUOps extends ChiselEnum {
  val nop, add, sub, mult, div, nil = Value
}

// size, usually 16, 32, 64. Default FP32, FP64 not required and FP16 is good but performance increase isnt worth precision and range decrease
// maybe in some time fp64 would make sense as a default for a FPU
class FPU(width: Int) extends Module {
  val io = IO(new Bundle {
    val op = Input(FPUOps())
    val x = Input(new Float32)
    val y = Input(new Float32)
    val res = Output(new Float32)
  })

  // add, sub, mult, divide
  val r = Wire(new Float32)
  r.sign := false.B
  r.exponent := 10.U(8.W)
  r.significand := 50.U(23.W)

  io.res := r
}
