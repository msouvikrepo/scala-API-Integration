import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import spray.json.{JsValue, JsonParser}

import java.time.{ZoneId, ZonedDateTime}

@RunWith(classOf[JUnitRunner])
class AccountImportEmailsJobCommandJsonFormatSpec extends DefaultFlatSpec {

  val accountImportEmailsJobCommandJsonFormat = AccountImportEmailsJobCommandJsonFormat.build()

  it should "convert a json of ImportSentEmailsCommandJsonFormat" in {

    val (jsonInput, expectedImportEmailsCommand) = AccountImportEmailsJobCommandJsonFormatSpec.defaultJsonFixture

    val actualImportEmailsCommand = accountImportEmailsJobCommandJsonFormat.read(jsonInput)

    expectedImportEmailsCommand shouldEqual actualImportEmailsCommand
  }
}

object AccountImportEmailsJobCommandJsonFormatSpec {

  def defaultJsonFixture(): (JsValue, AccountImportEmailsJobCommand) = {

    val europeZone = ZoneId.of("Europe/Paris")
    val endDate = ZonedDateTime.now(europeZone)
    val startDate = endDate.minusMonths(1)
    val accountId = Id[Account]()
    val targetTopicId: Some[Id[Topic]] = Some(Id[Topic]("test"))
    val folders = Seq(FolderPath("a"), FolderPath("b"))

    val importEmailsCommand: AccountImportEmailsJobCommand = account.AccountImportEmailsJobCommand(
      startDate,
      endDate,
      accountId,
      folders,
      targetTopicId,
      ShowMessageInStatisticStatus.Active
    )

    // @formatter:off
    val jsonString = JsonParser(
      s"""
         |{
         | "${AccountImportEmailsJobJsonFormat.startDate}": "${startDate}",
         | "${AccountImportEmailsJobJsonFormat.endDate}": "${endDate}",
         | "${ImportEmailsJobJsonFormat.accountId}": "${accountId.value}",
         | "${AccountImportEmailsJobJsonFormat.folders}": [${folders.map(_.value).mkString("\"", "\",\"", "\"")}],
         | ${targetTopicId.map{ id => s""""${ImportEmailsJobJsonFormat.targetTopicId}": "${id.value}","""}.getOrElse("")}
         | "${ImportEmailsJobJsonFormat.showMessageInStatisticStatus}": "${importEmailsCommand.showMessageInStatisticStatus.toString}"
         |}
""".stripMargin
    )
    // @formatter:on

    (jsonString, importEmailsCommand)
  }
}