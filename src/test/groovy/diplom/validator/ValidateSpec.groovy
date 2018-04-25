package diplom.validator

import diplom.ScheduleCommand
import spock.lang.Specification

import grails.validation.ValidationException

class ValidateSpec extends Specification {

    def "should throw validation exception"() {
        given:
            ScheduleCommand scheduleCommand = new ScheduleCommand()
        when:
            Validate.hasNoErrors(scheduleCommand)
        then:
            thrown(ValidationException)
    }

    def "shouldn't throw validation exception"() {
        given:
            ScheduleCommand someObject = Mock(ScheduleCommand) {
                1 * hasErrors() >> false
            }
        when:
            Validate.hasNoErrors(someObject)
        then:
            1 * someObject.validate()
    }

}
