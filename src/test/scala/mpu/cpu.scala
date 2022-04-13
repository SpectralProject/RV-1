import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import mpu.{CPU}

class CPUTest extends AnyFlatSpec with ChiselScalatestTester {
  // test class body here
  behavior of "CPU"
  it should "Initialise" in {
    test(new CPU) { c => }
  }

}
