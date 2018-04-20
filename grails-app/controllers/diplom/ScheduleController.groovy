package diplom

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Secured('permitAll')
@Transactional(readOnly = true)
class ScheduleController {

    ScheduleService scheduleService

    UploadScheduleFileService uploadScheduleFileService

    def index(Integer page, Integer max) {
        List<Schedule> schedules = scheduleService.list(page, max)
        Integer scheduleCount = scheduleService.count()
        respond(schedules, model: [scheduleCount: scheduleCount])
    }

    def show(Schedule schedule) {
        respond(schedule)
    }

    def create() {
        respond(new Schedule(params))
    }

    @Transactional
    def save(Schedule schedule) {
        Schedule savedSchedule = scheduleService.save(schedule)

        respond(savedSchedule, status: CREATED, view: "/schedule/show")
    }

    def edit(Schedule schedule) {
        respond(schedule)
    }

    @Transactional
    def update(Schedule schedule) {
        Schedule updatedSchedule = scheduleService.update(schedule)

        respond(updatedSchedule, status: OK, view: "/schedule/show")
    }

    @Transactional
    def delete(Schedule schedule) {
        scheduleService.delete(schedule)

        redirect(controller: "schedule", action: "index", method: "GET", status: NO_CONTENT)
    }

    def uploadFile(ExcelFileCommand cmd) {
        if (cmd == null) {
            notFound()
            return
        }

        if (cmd.hasErrors()) {
            respond(cmd.errors, model: [schedule: cmd], view: 'editFile')
            return
        }

        def schedule = uploadScheduleFileService.uploadFile(cmd)

        if (schedule == null) {
            notFound()
            return
        }

        if (schedule.hasErrors()) {
            respond(schedule.errors, model: [schedule: schedule], view: 'editFile')
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'schedule.label', default: 'Schedule'), schedule.id])
                redirect schedule
            }
            '*' { respond schedule, [status: OK] }
        }
    }

    def loadFile(Schedule schedule) {
        File file = uploadScheduleFileService.loadFile(schedule)
        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "attachment;filename=${file.getName()}")
        response.outputStream << file.newInputStream() // Performing a binary stream copy
    }

    @Transactional(readOnly = true)
    def editFile(Schedule schedule) {
        respond schedule
    }
}
