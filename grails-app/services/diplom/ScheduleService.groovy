package diplom

interface ScheduleService {
    List<Schedule> list(Integer page, Integer max)

    Schedule save(Schedule schedule)

    Schedule update(Schedule schedule)

    void delete(Schedule schedule)

    Integer count()

    Schedule updateFile(Long scheduleId, Integer version, String fileName)
}