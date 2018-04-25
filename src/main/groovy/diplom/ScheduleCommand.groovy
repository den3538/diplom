package diplom

import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class ScheduleCommand implements Validateable {
    private static final List<String> availableExtensions = ['xls', 'xlsx', 'csv']

    MultipartFile uploadedFile
    Integer tetrameter
    Integer year

    static constraints = {
        tetrameter nullable: false, min: 1, max: 4
        year nullable: false, min: 2018

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
