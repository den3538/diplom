package diplom

interface UserService {
    List<User> list(Integer page, Integer max)

    User save(User user)

    User update(User user)

    void delete(User user)

    Integer count()
}