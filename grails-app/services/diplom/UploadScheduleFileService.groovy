package diplom

interface UploadScheduleFileService {
    /**
     * Uploads file to server
     * @param excelFileCommand
     * @return
     */
    Schedule uploadFile(ExcelFileCommand excelFileCommand)

    /**
     * Loads file from server
     * @param schedule
     * @return
     */
    File loadFile(Schedule schedule)
}