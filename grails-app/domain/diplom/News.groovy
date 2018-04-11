package diplom

class News {

    String name
    String description
    String content
    Date dateCreated
    Date lastUpdated

    static belongsTo = [author: User]

    static constraints = {
        name blank: false
        description blank: false
        content blank: false
    }

    static mapping = {
        autoTimestamp true
    }
}
