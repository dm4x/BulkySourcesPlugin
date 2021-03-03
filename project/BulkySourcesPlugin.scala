import java.nio.charset.Charset
import sbt._
import Keys.sources

object BulkySourcesPlugin extends AutoPlugin {

  val bulkyThresholdInLines = settingKey[Int]("Number of lines to be checked in sources")
  val bulkySources = taskKey[Seq[(Int, File)]]("Task to check number of lines")

  def checkSources(sources: Seq[File], threshold: Int): Seq[(Int, File)] = {
    for {
      source <- sources
      lines = sbt.IO.readLines(source, Charset.defaultCharset()).length
      if lines >= threshold
    } yield (lines, source)
  }

  override lazy val projectSettings: Seq[Setting[_]] = Seq(
    bulkyThresholdInLines := 100,

    bulkySources := checkSources((Compile / sources).value, bulkyThresholdInLines.value).sorted.reverse,
    (Test / bulkySources) := checkSources((Test / sources).value, bulkyThresholdInLines.value).sorted.reverse

  )
}
