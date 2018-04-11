package diplom

class Schedule {

    Integer tetrameter
    Integer year
    String fileName
    Date dateCreated
    Date lastUpdated

    static constraints = {
        tetrameter nullable: false, min: 1, max: 4
        year nullable: false, min: 2018
        fileName blank: false
    }

    static mapping = {
        autoTimestamp true
    }
}
