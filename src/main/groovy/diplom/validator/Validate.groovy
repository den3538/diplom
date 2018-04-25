package diplom.validator

import grails.validation.ValidationException

class Validate {

    static void hasNoErrors(def commandOrDomain) throws ValidationException {
        commandOrDomain.validate()
        if (commandOrDomain.hasErrors()) {
            throw new ValidationException(commandOrDomain.toString(), commandOrDomain.errors)
        }
    }

    static void hasNoErrors(String message, def commandOrDomain) throws ValidationException {
        commandOrDomain.validate()
        if (commandOrDomain.hasErrors()) {
            throw new ValidationException(commandOrDomain.toString(), message)
        }
    }
}
