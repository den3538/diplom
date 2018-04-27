package diplom

class Photo {

    byte[] file

//    static belongsTo = [news: News]

    static constraints = {
        file nullable: false
    }
}
