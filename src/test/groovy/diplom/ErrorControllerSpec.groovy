package diplom

import diplom.error.ErrorController
import grails.test.mixin.TestFor
import org.springframework.http.HttpStatus
import spock.lang.Specification

@TestFor(ErrorController)
class ErrorControllerSpec extends Specification {

    def "should handle given exception"() {
        given:
            Exception exception = new Exception("Ignored message", initialException)
            model.errorMessage == message
            request.exception = exception
        when:
            controller.index()
        then:
            response.status == responseStatus
            model.errorMessage == message
        where:
            initialException                          | responseStatus                 | message
            new CantFindException("Cant find")        | HttpStatus.BAD_REQUEST.value() | "Cant find"
            new CantUpdateException("Cant update")    | HttpStatus.FORBIDDEN.value()   | "Cant update"
            new IOException("input output exception") | 500                            | "input output exception"
            new IOException()                         | 500                            | "There are some errors. Try again later"
    }
}
