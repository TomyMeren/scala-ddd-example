package tv.codely.scala_http_api.entry_point.cli

import com.typesafe.config.ConfigFactory

import scala.util.matching.Regex

object DbTablesCreatorTomy {
  private val databaseNameFromUrlRegex = new Regex("""\w+:\w+:\/\/\d+.\d+.\d+.\d+(?::\w+)?\/(\w+)""")

  case class CommandConfig(
    tablesFolder: String = "database",
    configFile: String = "application",
    dbConfigKey: String = "database"
  )

  def main(args: Array[String]): Unit = {
    val parser = new scopt.OptionParser[CommandConfig]("DbTablesCreator") {
      head("Build need enviroment to run tests", "1.0")

      opt[String]('f', "tablesFolder")
        .action((tablesFolder, currentConfig) => currentConfig.copy(tablesFolder = tablesFolder))
        .text("Folder containing all the `.sql` files with the `CREATE TABLE` definitions.")

      opt[String]('c', "configFile")
        .action((configFile, currentConfig) => currentConfig.copy(configFile = configFile))
        .text("Configuration file to use while searching for the DB connection parameters.")

      opt[String]('k', "dbConfigKey")
        .action((dbConfigKey, currentConfig) => currentConfig.copy(dbConfigKey = dbConfigKey))
        .text("The configuration key inside the configuration file where we'll find the DB connection parameters.")
    }

    parser.parse(args, CommandConfig()).fold(println("[ERROR] Invalid parameters")) { commandConfig =>
      println(ConfigFactory.load("application").getConfig("database"))
    }
  }
}

