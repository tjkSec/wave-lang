import java.io.File

class FileDetails(filePath: String) {
  // Default fields (privy)
  private var fileSize: Double = 0
  this.setFileSize()

  def setFileSize(): Unit = {
    val file: File = new File(this.filePath)
    if(file.exists()) {
      // Kilobytes
      this.fileSize = file.length() / 1024
    } else {
      this.fileSize = fileSize
      println("Error 404: File doesn't exist.")
    }
  }

  def getFileSize: String = {
    s"${this.fileSize.toString} KB"
  }

  def getFilePath: String = {
    this.filePath
  }

}