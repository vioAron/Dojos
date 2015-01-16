package example


object Main extends App {
  val input = """
frame-rate: 4
height: 3
width: 3
frame
x__
___
___
frame
___
x__
___
frame
___
___
x__""";
  
  Movie.play(input);
}