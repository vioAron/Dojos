package example

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ListsSuite extends FunSuite {

  test("empty input is invalid"){
    intercept[Exception]{ Movie.play("")}
  }
  
  test("header only is valid"){
    assert((4,List.empty) === Movie.play(
"""
frame-rate: 4
height: 3
width: 3
"""))
  }
  
  test("frame-rate is parsed correctly"){
    assert((1,List.empty) === Movie.play(
"""
frame-rate: 1
height: 3
width: 3
"""))
  }
  
  test("frame is parsed correctly"){
    assert((1,List(List("x"))) === Movie.play(
"""
frame-rate: 1
height: 1
width: 1
frame
x
"""))
}

  test("2x2 frame is parsed correctly"){
    assert((1,List(List("x_","_x"))) === Movie.play(
"""
frame-rate: 1
height: 2
width: 2
frame
x_
_x
"""))
  }
    
  test("2 frames are parsed correctly"){
    assert((1,List(List("x"), List("x"))) === Movie.play(
"""
frame-rate: 1
height: 1
width: 1
frame
x
frame
x
"""))
}

  test("delta frame is parsed correctly"){
    assert((1,List(List("x"),List("x"))) === Movie.play(
"""
frame-rate: 1
height: 1
width: 1
frame
x
delta
"""))
}

}
