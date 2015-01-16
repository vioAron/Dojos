package example

import common._

object Movie {
  def parseIntProperty(s : String ) : Int = {
    s.split(":")(1).trim().toInt
  }
  
  def play(content: String) :  (Int, List[List[String]])= {
    if (content.isEmpty()) {
      throw new Exception("Empty!");
    } else
    {
      val lines = content.trim()
                        .lines
                        .map { l => l.trim() }
                        .toList
      
      val header = lines.take(3)
      
      val frameRate = parseIntProperty(header(0))
      val height = parseIntProperty(header(1))
      val width = parseIntProperty(header(2))
      
      val frameLines = lines.drop(3)
      
      def accumulate (accumulator:(Int, List[String]), line : String) = {
         val frameNo = accumulator._1;
         val frameValue = accumulator._2;
         
         if (line == "frame")
         {
           (frameNo + 1, frameValue)
         } else
         {
           (frameNo, frameValue ++ List(line))
         }
      }

      val frames = frameLines.scanLeft (-1, List[String]()) (accumulate)
                             .groupBy (x => x._1)
                             .mapValues(frame => frame.map( x => x._2))
                               
      val processedFrames = frames.map {frame => frame.drop(1)}
                               .toList
        
      (frameRate, processedFrames)
    }
  }
}
