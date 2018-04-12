package diplom

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ScheduleController {

    ScheduleService scheduleService

    def index(Integer page, Integer max) {
        List<Schedule> schedules = scheduleService.list(page, max)
        Integer scheduleCount = scheduleService.count()
        respond schedules, model:[scheduleCount: scheduleCount]
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
}
