package diplom

interface UserRoleService {

    UserRole save(User user, Role role)

    boolean delete(User user, Role role)

    /**
     * Deletes all records for given user
     * @param user
     * @return
     */
    boolean deleteAll(User user)

    /**
     * Deletes all records for given role
     * @param role
     * @return
     */
    boolean deleteAll(Role role)
}
