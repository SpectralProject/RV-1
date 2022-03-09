package gpu

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class GPUTest extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "GPU"
  it should "Initialise" in {
    test(new GPU) { c => }
  }
}
