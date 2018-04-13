package diplom

import groovy.transform.CompileStatic

@CompileStatic
class CantFindException extends RuntimeException {

    CantFindException(String message){
        super(message)
    }
}
