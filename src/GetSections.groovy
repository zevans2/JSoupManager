import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class GetSections {

    static def getSections(dept, semester, writeToDB) {
        println "Processing $dept for semester $semester. Writing to database? $writeToDB"

        Section section = null

        def baseURL = "https://aps2.missouriwestern.edu/schedule/"
        Document document = Jsoup.parse(new File("CSMP.html"), "UTF-8", baseURL)

        Elements rows = document.select("tr")
        println "Found ${rows.size()} rows"

        if (rows.size() > 4) {
            rows.each { row ->
                def className = row.attr("class")
                Elements cells = row.select("td")

                if (className == "list_row") {
                    if (section != null) {
                        println "Section: $section"
                        //write to database
                        //if(writeToDB)
                        //write to DB
                    }

                    def cellCount = cells.size()
                    switch (cellCount) {
                        case 10:

                            //Full row
                            section = new Section()
                            section.department = semester
                            section.crn = cells.get(0).text().trim()

                            //Split up the courseID
                            def s = cells.get(1).text().trim()
                            println "Course is $s"
                            section.discipline = s.take(3)
                            section.courseNumber = s.drop(3)
                            println("Discipline is ${section.discipline} ${section.courseNumber}")

                            section.instructor = cells[9].text().trim()
                            println("Instructor ${section.instructor}")
                            section.room = cells[8].text().trim()

                            break
                        case 5:
                            section.room += "|" + cells[3].text().trim()
                            break
                        default:
                            println "DID NOT HANDLE ROWS WITH $cellCount cells"
                            System.exit(1)

                    }

                }//end of list_row
                else if (className == "detail_row") {
                    section.messages = ""
                    section.term = ""

                    Elements tags = row.select("*")
                    tags.each { tag ->
                        className = tag.attr("class").toString()
                        switch (className) {
                            case "course_messages":
                                section.messages += tag.text().trim()
                                break
                            case "course_ends":
                                def s = tag.text()
                                //section.endDate = s.drop("Course Ends: ".length())
                                section.endDate = (s=~/\d+\/\d+\/\d+/)[0]
                                section.endDate = section.endDate.trim()
                                println("END DATE: ${section.endDate}")
                                break

                            case "course_seats":
                                def contents = tag.text()
                                contents.replace("<br />", "")
                                String[] parts = contents.split(/\s+/)
                                if(parts.length > 6){
                                    def max = parts[2]
                                    def available = parts[6]
                                    section.maximumEnrollment = Integer.parseInt(max)
                                    section.seatsAvailable = Integer.parseInt(available)
                                    println "Seats: ${section.maximumEnrollment} and ${section.seatsAvailable}"
                                }


                                break
                        }//end switch
                    }
                } else {
                   /* println "UNKNOWN ROW CLASS $className"
                    System.exit(1)*/
                }
            }
        }
        //write to the database!
        println section.toString()
    }
}
