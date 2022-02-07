import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import cpu.{CPUCore}

class CoreTest extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "CPUCore"
  it should "Initialise" in {
    test(new CPUCore) { c => }
  }
}
