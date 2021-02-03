package tv.codely.scala_http_api.entry_point.cli

import com.typesafe.config.ConfigFactory
import tv.codely.scala_http_api.module.shared.infrastructure.config.DbConfig

import java.io.File
import java.sql.{Connection, DriverManager}
import scala.io.Source.fromFile
import scala.util.Try
import scala.util.matching.Regex

object DbTablesCreator {

  private val databaseNameFromUrlRegex = new Regex("""\w+:\w+:\/\/\d+.\d+.\d+.\d+(?::\w+)?\/(\w+)""")

  //Case class que utiliza Scopt
  case class CommandConfig(
    tablesFolder: String = "database", //Carpetas donde estan los create tables de users y videos
    configFile: String = "application", //Fichero de configuacion general
    dbConfigKey: String = "database" //Fichero donde guardamos la informacion database.> database.cong
  )

  def main(args: Array[String]): Unit = {

    //Definimos el parseo de las 3 variables
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

    //Parseamos con los args que pasamos por parametrola case class
    parser.parse(args, CommandConfig()).fold(println("[ERROR] Invalid parameters")) { commandConfig =>
      //Obtenemos la configuracion de database metida previamente en una case class
      val dbConfig = DbConfig(ConfigFactory.load("application").getConfig("database"))
      //Obtenemos el nombre de la Base de datos que a su vez la obtenemos de la url
      val dbNameOption = for (grouped <- databaseNameFromUrlRegex findFirstMatchIn dbConfig.url) yield grouped group 1

      dbNameOption.fold(//Revisamos si existe el nombre de la Base de datos de la url
        println(s"[ERROR] We couldn't extract the DB name from the DB URL configuration parameter: ${dbConfig.url}")
      ) { dbName => //revisamos si existe el driver
        Try(Class.forName(dbConfig.driver)).toOption.fold(
          println(s"[ERROR] Invalid driver specified in the database config: ${dbConfig.driver}")
        ) { _ =>
          //damos de alta la conexion usando los parametros del fichero database.conf
          val connection:Connection = DriverManager.getConnection(dbConfig.url, dbConfig.user, dbConfig.password)

          //Creamos las tablas pasandole el nombre de la BD Obtenido del driver, la conexion y el nombre edel fichero database
          createTables(dbName, commandConfig.tablesFolder, connection:Connection)

          connection.close()
        }
      }
    }
  }

  private def createTables(dbName: String, tablesFolder: String, connection: Connection): Unit = {
    val tablesFolderFile = new File(tablesFolder)
    val tablesFiles      = tablesFolderFile.listFiles() //Listamos los ficheros con los create tables

    println(s"[INFO] Creating the following tables: ${tablesFiles.mkString(", ")}…")

    val createTablesQueries:Array[String] = tablesFiles.map(fromFile(_).getLines.mkString)//Mtemos los create tables en Arrays

    val applySchemaStatement = connection.createStatement

    applySchemaStatement.executeUpdate(s"USE $dbName;") //Nos conectamos a la BBDD
    createTablesQueries.foreach(applySchemaStatement.executeUpdate) //Los ejecutamos
  }
}

