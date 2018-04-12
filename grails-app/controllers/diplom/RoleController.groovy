package diplom

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RoleController {

    RoleService roleService

    @Secured("ROLE_ADMIN")
    def index(Integer page, Integer max) {
        List<Role> roles = roleService.list(page, max)
        Integer roleCount = roleService.count()
        respond(roles, model: [roleCount: roleCount])
    }

    @Secured("ROLE_ADMIN")
    def show(Role role) {
        respond(role)
    }

    @Secured("ROLE_ADMIN")
    def create() {
        respond(new Role(params))
    }

    @Secured("ROLE_ADMIN")
    @Transactional
    def save(Role role) {
        Role savedRole = roleService.save(role)

        respond(savedRole, status: CREATED, view: "/role/show")
    }

    @Secured("ROLE_ADMIN")
    def edit(Role role) {
        respond(role)
    }

    @Secured("ROLE_ADMIN")
    @Transactional
    def update(Role role) {
        Role updatedRole = roleService.update(role)

        respond(updatedRole, status: OK, view: "/role/show")
    }

    @Secured("ROLE_ADMIN")
    @Transactional
    def delete(Role role) {
        roleService.delete(role)

        redirect(controller: "role", action: "index", method: "GET", status: NO_CONTENT)
    }

}
