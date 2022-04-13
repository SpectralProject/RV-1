package mpu

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

// import cpu.{ALU, ALU64, ALUOps}

class ALUTest extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "ALU"
  it should "Do basic arithmetic" in {
    test(new ALU(64)) { a =>
      // Not sure what builder context means here, tests failing
      a.io.op.poke(ALUOps.add)
      a.io.x.poke(1.S)
      a.io.y.poke(1.S)
      a.clock.step(1)
      a.io.res.expect(2.S)

      // printf("printing during testing")
    }
  }

  behavior of "ALU64"
  it should "should do basic arithmetic" in {
    test(new ALU64) { a =>
      // a.io.op.poke(ALUOps.add)
      // a.io.x.poke(1.S)
      // a.io.y.poke(1.S)
      // a.clock.step(1)
      // a.io.res.expect(2.S)
    }
  }

}
