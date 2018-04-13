package diplom

import groovy.transform.CompileStatic

@CompileStatic
class CantUpdateException extends RuntimeException {

    CantUpdateException(String message){
        super(message)
    }
}
