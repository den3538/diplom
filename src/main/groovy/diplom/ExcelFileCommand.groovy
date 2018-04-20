package diplom

import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class ExcelFileCommand implements Validateable {
    private static final List<String> availableExtensions = ['xls', 'xlsx', 'csv']

    MultipartFile uploadedFile
    Long id
    Integer version

    static constraints = {
        id nullable: false
        version nullable: false
        uploadedFile validator: { val, obj ->
            if (val == null) {
                return false
            }

            if (val.empty) {
                return false
            }

            availableExtensions.any({ extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension)
            })
        }
    }
}
