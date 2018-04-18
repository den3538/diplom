package diplom

import grails.transaction.Transactional

@Transactional
class RoleServiceImplService implements RoleService {

    UserRoleService userRoleService

    private checkIfExists = { Long id ->
        if (!Role.exists(id)) {
            throw new CantFindException("Can't find requested role")
        }
    }

    @Override
    List<Role> list(Integer page, Integer max) {
        Integer localPage = page ?: 0
        Integer localMax = PageUtil.getMaxValue(max)
        Role.list([max: localMax, offset: localPage * localMax])
    }

    @Override
    Role save(Role role) {
        role.save()
        //todo save userRoles
    }

    @Override
    Role update(Role role) {
        checkIfExists(role.id)
        //todo update userRoles
        role.save()
    }

    @Override
    void delete(Role role) {
        checkIfExists(role.id)
        userRoleService.deleteAll(role)
        role.delete()
    }

    @Override
    Integer count() {
        Role.count
    }
}
