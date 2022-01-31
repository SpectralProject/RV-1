import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import cpu.{ALU, ALUOps}

class BasicTest extends AnyFlatSpec with ChiselScalatestTester {
  // test class body here

  test(new ALU(64)) { a =>
    a.io.op.poke(ALUOps.add)
    a.io.x.poke(1.S(64.W))
    a.io.y.poke(1.S(64.W))
    a.clock.step(1)
    a.io.res.expect(2.S(64.W))

    printf("printing during testing")
  }
}
