package diplom

import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes = 'username')
@ToString(includes = 'username', includeNames = true, includePackage = false)
class User implements Serializable {

    String name
    String secondName
    String surname
    String username
    String password
    String email

    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static hasMany = [postedNews: News, answeredQuestions: Faq]

    static constraints = {
        name blank: false
        secondName blank: false
        surname blank: false
        username blank: false, unique: true
        password blank: false, password: true
        email blank: false //todo validate email
    }

    static mapping = {
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

}
