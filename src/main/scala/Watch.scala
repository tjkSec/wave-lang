import java.io._
import java.nio.file.{WatchEvent, _}
import java.util.concurrent.TimeUnit
import java.nio.file.FileSystems
import java.io.BufferedWriter
import java.io.FileWriter
import scala.collection.mutable.ListBuffer
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardWatchEventKinds

object Watch {
  def detect(): Unit = {
    println("\n------------------------")
    println(main.splash("File change noticed, will now continue to write file...", "black"))
  }

  def write(filePath: String): Unit = {
    var fileSeparator: String = "/"
    if(filePath.contains("/")) {
      fileSeparator = "/"
    } else if(filePath.contains("\\")) {
      fileSeparator = "\\"
    }

    val file: File = new File(filePath.substring(0, filePath.lastIndexOf(fileSeparator))
      + fileSeparator + getFileName(filePath).substring(0, getFileName(filePath).lastIndexOf(".")) + ".css")
    val bufferedWriter = new BufferedWriter(new FileWriter(file))
    val file_contents = new String(Files.readAllBytes(Paths.get(filePath)))
    bufferedWriter.write("")
    bufferedWriter.flush()
    bufferedWriter.write(syntax(file_contents))
    bufferedWriter.flush()
    bufferedWriter.close()
<<<<<<< HEAD
=======
    Syntax.printVars()
>>>>>>> cd36009f6f694215de33aa835284e0c612699df9
    println(main.splash("Success!", "blue") + main.splash(" File has been updated.", "white"))
    println("------------------------")
  }

  def syntax(wave_code: String): String = {
    Syntax.compile(wave_code)
  }

  def loadFile(filePath: String, fileProperties: FileDetails): Unit = {
    var fileSeparator: String = "/"
    if(filePath.contains("/")) {
      fileSeparator = "/"
    } else if(filePath.contains("\\")) {
      fileSeparator = "\\"
    }

    val file: File = new File(filePath.substring(0, filePath.lastIndexOf(fileSeparator))
      + fileSeparator + getFileName(filePath).substring(0, getFileName(filePath).lastIndexOf(".")) + ".css")
    val bufferedWriter = new BufferedWriter(new FileWriter(file))

    try {
      val file_contents = new String(Files.readAllBytes(Paths.get(filePath)))
      bufferedWriter.write(syntax(file_contents))
      bufferedWriter.flush()
    } catch {
      case err: Exception => {
        println("Error writing file.")
        err.printStackTrace()
      }
    } finally {
      bufferedWriter.flush()
      bufferedWriter.close()
    }
    println(
      main.splash("Now watching \"", "blue") + main.splash(fileProperties.getFilePath, "black") + main.splash("\"", "blue")
        + main.splash(" (" + fileProperties.getFileSize + ")", "blue")
    )
    watchFile(filePath, fileProperties)
  }

  def getFileName(filePath: String): String = {
    var fileSeparator: String = "/"
    if(filePath.contains("/")) {
      fileSeparator = "/"
    } else if(filePath.contains("\\")) {
      fileSeparator = "\\"
    }
    filePath.substring(filePath.lastIndexOf(fileSeparator) + 1, filePath.length)
  }

  def watchFile(filePath: String, fileProperties: FileDetails): Unit = {
    var fileSeparator: String = "/"
    if(filePath.contains("/")) {
      fileSeparator = "/"
    } else if(filePath.contains("\\")) {
      fileSeparator = "\\"
    }

    val dir_path: String = filePath.substring(0, filePath.lastIndexOf(fileSeparator))
    val path: Path = Paths.get(dir_path)
    val fileName: String = getFileName(filePath)
    val watchService: WatchService = path.getFileSystem.newWatchService
    path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY)
    while(true) {
      val watchKey = watchService.poll(2000, TimeUnit.HOURS)
      if(watchKey != null) {
        watchKey.pollEvents.stream.forEach(event => {
          if(event.context.toString == fileName.toString) {
            detect()
            write(filePath)
          } else {
            ;
          }
        })
      }
      watchKey.reset()
    }
  }

}
