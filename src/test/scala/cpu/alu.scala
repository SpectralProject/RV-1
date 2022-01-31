import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import cpu.{ALU, ALUOps}
import cpu.{CPUCore}

class BasicTest extends AnyFlatSpec with ChiselScalatestTester {
  // test class body here
  behavior of "CPUCore"
  it should "Initialise" in {
    test(new CPUCore) { c => }
  }

  behavior of "ALU"
  it should "Do basic arithmetic" in {
    test(new ALU(64)) { a =>
      a.io.op.poke(ALUOps.add)
      a.io.x.poke(1.S)
      a.io.y.poke(1.S)
      a.clock.step(1)
      a.io.res.expect(2.S)

      printf("printing during testing")
    }
  }

}
