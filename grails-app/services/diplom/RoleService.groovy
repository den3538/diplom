package diplom

interface RoleService {
    List<Role> list(Integer page, Integer max)

    Role save(Role role)

    Role update(Role role)

    void delete(Role role)

    Integer count()
}