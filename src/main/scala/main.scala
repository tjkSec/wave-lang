import java.io.File

// NOTE RUNNING WITH FOLLOWING PARAMETERS
/*
 --watch C:\Users\trent\Desktop\Wave\src\test\testFile.wave
 */

object main {
  def splash(text: String, color: String): String = {
    val span = 27.toChar
    val reset = "[0m"
    color.toLowerCase match {
      case "black"  =>  span + "[30m" + text + span + reset
      case "red"    =>  span + "[31m" + text + span + reset
      case "green"  =>  span + "[32m" + text + span + reset
      case "yellow" =>  span + "[33m" + text + span + reset
      case "blue"   =>  span + "[34m" + text + span + reset
      case "purple" =>  span + "[35m" + text + span + reset
      case "cyan"   =>  span + "[36m" + text + span + reset
      case "white"  =>  span + "[37m" + text + span + reset
    }
  }
  def err(errType: Int): Unit = {
    errType match {
      case 0 => println("Wave Error: Sorry I didn't understand the parameters given.")
      case 1 => println("Wave Error: Sorry I need a file ending with .wave to compile.")
    }
    System.exit(0)
  }
  def main(args: Array[String]): Unit = {
    if(args(1).substring(args(1).lastIndexOf("."), args(1).length) != ".wave") {
      err(1)
    }
    else if(args.length <= 1 || args.length > 2 ) {
      err(0)
    }
    else if(args(0) == "--watch") {
      val file = loadedFileProperties(args(1))
      // Load in file...
      Watch.loadFile(file.getFilePath, loadedFileProperties(file.getFilePath))
    } else {
      err(0)
    }
  }
  def loadedFileProperties(path: String): FileDetails = {
    new FileDetails(path)
  }
}
