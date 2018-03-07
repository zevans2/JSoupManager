import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

//Class holding SQL Functions to call from Groovy
    static def clearRecordsFromDB(){
        Connection connection = connect()
        def queryString = "DELETE FROM section"
        Statement stmt = connection.createStatement()
        println "Query string is \"$queryString"
        stmt.executeUpdate(queryString)
        connection.close()
    }

    static def connect(){
        String dbFile = "schedule.db"
        Connection connection = DriverManager.getConnection(dbFile)
        return connection
    }

    /* static def addSectionToDB(Section section){
         Connection connection = connect()
         def queryString = "CREATE "
     }
 */
