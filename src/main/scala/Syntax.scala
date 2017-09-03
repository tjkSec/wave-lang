import java.util.regex.{Matcher, Pattern}
import java.util

import scala.util.matching.Regex


object Syntax {
  private val variables = new util.ArrayList[String]
  def compile(wave_code: String): String = {
    variables.clear()
    val variableRe = "let(.*?);".r
    for(m <- variableRe.findAllIn(wave_code)) {
      variables.add(m.toString.replaceAll(";", ""))
    }
    var replacements = Map(
      // Shorthands
      "--fixed" -> "!important",
      // Comments
      "(?m)^let.*" -> "/* ~ Wave Variable ~ */",
    )
    for(variable <- variables.toArray) {
      val x = variable.toString.split(" ")
      val full = variable.toString.substring(variable.toString.lastIndexOf("=") + 2, variable.toString.length)
      replacements += x(1) -> full
      replacements.foldLeft(wave_code){case (z, (s,r)) => z.replaceAll(s, r)}
    }
    val existance1 = replacements.foldLeft(wave_code){case (z, (s,r)) => z.replaceAll(s, r)}
    val presets = Map(
      "material.indigo500" -> "#3E4F55"
    )
    presets.foldLeft(existance1) {case(z, (s,r)) => z.replaceAll(s, r)}
  }
}

