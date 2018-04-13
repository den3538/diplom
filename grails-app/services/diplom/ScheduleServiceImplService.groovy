package diplom

import grails.transaction.Transactional

@Transactional
class ScheduleServiceImplService implements ScheduleService {

    @Override
    List<Schedule> list(Integer page, Integer max) {
        Integer localPage = page ?: 0
        Integer localMax = Math.min(max ?: 10, 100)
        Schedule.list([max: localMax, offset: localPage * localMax])
    }

    @Override
    Schedule save(Schedule schedule) {
        schedule.save()
    }

    @Override
    Schedule update(Schedule schedule) {
        checkIfExists(schedule.id)
        schedule.save()
    }

    private void checkIfExists(Long id) {
        if (!Schedule.exists(id)) {
            throw new CantFindException("Can't find requested schedule!")
        }
    }

    @Override
    void delete(Schedule schedule) {
        checkIfExists(schedule.id)
        schedule.delete()
    }

    @Override
    Integer count() {
        Schedule.count
    }
}
