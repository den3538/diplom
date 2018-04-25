package diplom

import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional
class UploadScheduleFileServiceImplService implements UploadScheduleFileService {

    ScheduleService scheduleService

    private getSchedulePath = { ->
        new File(".").getCanonicalPath() + System.getProperty('file.separator') + "schedule"
    }

    private createDir = { String path ->
        File file = new File(path)
        if (!file.exists()) {
            file.mkdirs()
        }
    }

    private checkIfFileNotExists = { String path, String fileName ->
        String filePath = path + System.getProperty('file.separator') + fileName
        File file = new File(filePath)
        if (file.exists()) {
            log.error("File already exists")
            //todo throw exception
        }
    }

    private checkIfFileExists = { String path, String fileName ->
        String filePath = path + System.getProperty('file.separator') + fileName
        File file = new File(filePath)
        if (!file.exists()) {
            log.error("File doesn't exist")
            //todo throw exception
        }
    }

    private getRealFileName = { MultipartFile multipartFile ->
        multipartFile.originalFilename.substring(multipartFile.originalFilename.lastIndexOf('\\') + 1)
    }

    private saveFile = { String path, MultipartFile multipartFile ->
        String filePath = path + System.getProperty('file.separator') + getRealFileName(multipartFile)
        File file = new File(filePath)
        if (file.createNewFile()) {
            FileOutputStream fos = new FileOutputStream(file)
            fos.write(multipartFile.getBytes())
            fos.close()
        } else {
            log.error("Can't create file")
            //todo throw exception
        }
    }

    private loadFile = { String path, String fileName ->
        String filePath = path + System.getProperty('file.separator') + fileName
        new File(filePath)
    }

    @Override
    String uploadFile(MultipartFile multipartFile) {
        String schedulePath = getSchedulePath()
        createDir(schedulePath)
        checkIfFileNotExists(schedulePath, multipartFile.originalFilename)
        saveFile(schedulePath, multipartFile)
        getRealFileName(multipartFile)
    }

    @Override
    File loadFile(Schedule schedule) {
        String schedulePath = getSchedulePath()
        checkIfFileExists(schedulePath, schedule.fileName)
        loadFile(schedulePath, schedule.fileName)
    }
}
