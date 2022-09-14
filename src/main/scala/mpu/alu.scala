package mpu

import chisel3._
import chisel3.util._
import chisel3.experimental._
import chisel3.experimental.BundleLiterals._

object ALUOps {
  val nop :: add :: sub :: and :: xor :: or :: load :: shr :: Nil = Enum(8)
}

class ALU(width: Int) extends Module {
  val io = IO(new Bundle {
    val op = Input(UInt(3.W))
    val x = Input(SInt(width.W))
    val y = Input(SInt(width.W))
    val res = Output(SInt(width.W))
  })

  val r = Wire(SInt(width.W))
  r := 0.S
  val x = io.x
  val y = io.y
  val op = io.op

  switch(op) {
    is(ALUOps.add) {
      r := x + y
    }
    is(ALUOps.sub) {
      r := x - y
    }
    is(ALUOps.and) {
      r := x & y
    }
    is(ALUOps.xor) {
      r := x ^ y
    }
    is(ALUOps.or) {
      r := x | y
    }
    is(ALUOps.load) {
      r := y
    }
    is(ALUOps.shr) {
      r := (x >> 1) & 0x7fffffff.S
    }
  }

  println("===In build===")
  println("op = ", op)
  println("x = ", x)
  println("y = ", y)
  println("r = ", r)

  printf("===In simulation===\n")
  printf(p"op = $op\n")
  printf(p"x = $x\n")
  printf(p"y = $y\n")
  printf(p"r = $r\n")

  io.res := r
}

class ALU64 extends Module {
  val io = IO(new Bundle {
    val op = Input(UInt(3.W))
    val x = Input(SInt(64.W))
    val y = Input(SInt(64.W))
    val res = Output(SInt(64.W))
  })

  val r = WireDefault(0.S(64.W))
  val x = io.x
  val y = io.y
  val op = io.op

  switch(op) {
    is(ALUOps.add) {
      r := x + y
    }
    is(ALUOps.sub) {
      r := x - y
    }
    is(ALUOps.and) {
      r := x & y
    }
    is(ALUOps.xor) {
      r := x ^ y
    }
    is(ALUOps.or) {
      r := x | y
    }
    is(ALUOps.load) {
      r := y
    }
    is(ALUOps.shr) {
      r := (x >> 1) & 0x7fffffff.S
    }
  }

  io.res := r
}

// ------------
// FPU
// ------------

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
