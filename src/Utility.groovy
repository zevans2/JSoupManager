/*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

//Class holding SQL Functions to call from Groovy
class Utility {


    static def clearRecordsFromDB(){
        String dbFile = "jdbc:sqlite:schedule.db"
        Connection connection = DriverManager.getConnection(dbFile)

        def queryString = "DELETE FROM section"

        Statement statement = connection.createStatement()
        //println "Query string is \"$queryString

        statement.executeUpdate(queryString)

        connection.close()
    }


}
*/
