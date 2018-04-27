package diplom

class News {

    String name
    Photo photo
    String description
    String content
    Date dateCreated
    Date lastUpdated

//    static hasOne = [photo: Photo]

    static belongsTo = [author: User]

    static constraints = {
        name blank: false
        photo nullable: false
        description blank: false
        content blank: false
    }

    static mapping = {
        autoTimestamp true
    }
}
