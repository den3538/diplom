package diplom

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RoleController {

    RoleService roleService

    def index(Integer page, Integer max) {
        List<Role> roles = roleService.list(page, max)
        Integer roleCount = roleService.count()
        respond(roles, model: [roleCount: roleCount])
    }

    def show(Role role) {
        respond(role)
    }

    def create() {
        respond(new Role(params))
    }

    @Transactional
    def save(Role role) {
        Role savedRole = roleService.save(role)

        respond(savedRole, status: CREATED, view: "/role/show")
    }

    def edit(Role role) {
        respond(role)
    }

    @Transactional
    def update(Role role) {
        Role updatedRole = roleService.update(role)

        respond(updatedRole, status: OK, view: "/role/show")
    }

    @Transactional
    def delete(Role role) {
        roleService.delete(role)

        redirect(controller: "role", action: "index", method: "GET", status: NO_CONTENT)
    }

}
