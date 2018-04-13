package diplom

import org.springframework.http.HttpStatus

class ErrorController {

    def index() {
        Exception exception = request.exception.cause

        logException(exception)

        String message = exception.message ?: "There are some errors. Try again later"

        HttpStatus httpStatus = handleException(exception)
        render(view: "/customError", model: [errorMessage: message], status: httpStatus)
    }

    HttpStatus handleException(CantFindException exception) {
        HttpStatus.BAD_REQUEST
    }

    HttpStatus handleException(CantUpdateException exception) {
        HttpStatus.FORBIDDEN
    }

    HttpStatus handleException(Exception exception) {
        HttpStatus.INTERNAL_SERVER_ERROR
    }

    private void logException(Exception exception) {
        log.info("ErrorController", exception)
    }
}
