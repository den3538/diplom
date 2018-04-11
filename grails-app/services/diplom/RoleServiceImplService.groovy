package diplom

import grails.transaction.Transactional

@Transactional
class RoleServiceImplService implements RoleService {

    @Override
    List<Role> list(Integer page, Integer max) {
        Integer localPage = page ?: 0
        Integer localMax = Math.min(max ?: 10, 100)
        Role.list([max: localMax, offset: localPage * localMax])
    }

    @Override
    Role save(Role role) {
        role.save()
    }

    @Override
    Role update(Role role) {
        checkIfExists(role.id)
        role.save()
    }

    private void checkIfExists(Long id) {
        if (!Role.exists(id)) {
            //todo throw exception
        }
    }

    @Override
    void delete(Role role) {
        checkIfExists(role.id)
        role.delete()
    }

    @Override
    Integer count() {
        Role.count
    }
}
