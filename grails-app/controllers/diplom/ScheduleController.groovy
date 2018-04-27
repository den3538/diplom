package diplom

import diplom.validator.Validate
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Secured('permitAll')
@Transactional(readOnly = true)
class ScheduleController {

    ScheduleService scheduleService

    UploadScheduleFileService uploadScheduleFileService

    @Secured('permitAll')
    def index(Integer page, Integer max) {
        List<Schedule> schedules = scheduleService.list(page, max)
        Integer scheduleCount = scheduleService.count()
        respond(schedules, model: [scheduleCount: scheduleCount])
    }

    @Secured('permitAll')
    def show(Schedule schedule) {
        respond(schedule)
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def create() {
        respond(new Schedule(params))
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    @Transactional
    def save(ScheduleCommand scheduleCommand) {
        Validate.hasNoErrors(scheduleCommand)

        String fileName = uploadScheduleFileService.uploadFile(scheduleCommand.uploadedFile)

        Schedule savedSchedule = scheduleService.save(scheduleCommand.getYear(), scheduleCommand.getTetrameter(), fileName)

        respond(savedSchedule, status: CREATED, view: "/schedule/show")
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    def edit(Schedule schedule) {
        respond(schedule)
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    @Transactional
    def update(Schedule schedule) {
        Schedule updatedSchedule = scheduleService.update(schedule)

        respond(updatedSchedule, status: OK, view: "/schedule/show")
    }

    @Secured(['ROLE_USER', 'ROLE_ADMIN'])
    @Transactional
    def delete(Schedule schedule) {
        scheduleService.delete(schedule)

        redirect(controller: "schedule", action: "index", method: "GET", status: NO_CONTENT)
    }

}
