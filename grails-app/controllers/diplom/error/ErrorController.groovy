package diplom.error

import diplom.CantFindException
import diplom.CantUpdateException
import grails.validation.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError

class ErrorController {

    def messageSource

    private logException = { Exception exception ->
        log.info("ErrorController", exception)
    }

    def index() {
        Exception exception = request.exception.cause

        logException(exception)

        String message = exception.message ?: "There are some errors. Try again later"

        HttpStatus httpStatus = handleException(exception)
        render(view: "/customError", model: [errorMessage: message], status: httpStatus)
    }

    private HttpStatus handleException(CantFindException exception) {
        HttpStatus.BAD_REQUEST
    }

    private HttpStatus handleException(CantUpdateException exception) {
        HttpStatus.FORBIDDEN
    }

    private HttpStatus handleException(Exception exception) {
        HttpStatus.INTERNAL_SERVER_ERROR
    }

    def handleValidationException() {
        ValidationException validationException = request.exception.cause
        logException(validationException)

        String message = new ErrorMessageBuilder().buildValidationExceptionMessage(validationException)
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST
        render(view: "/customError", model: [errorMessage: message], status: httpStatus)
    }

    private class ErrorMessageBuilder {
        String buildValidationExceptionMessage(ValidationException exception) {
            StringBuilder sb = new StringBuilder()
            for(FieldError fieldError : exception.errors.fieldError){
                sb.append(messageSource.getMessage(fieldError, Locale.default))
            }
            sb.toString()
        }
    }
}
