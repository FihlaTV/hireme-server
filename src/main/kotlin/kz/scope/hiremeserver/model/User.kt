package kz.scope.hiremeserver.model

import kz.scope.hiremeserver.model.audit.DateAudit
import org.hibernate.annotations.NaturalId
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import kotlin.collections.ArrayList

/**
 * Created by scope team on 01/08/17.
 */

@Entity
@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = ["username"]), UniqueConstraint(columnNames = ["email"])])
class User() : DateAudit() {
    constructor(fullname: String, username: String, email: String, password: String) : this() {
        this.fullname = fullname
        this.username = username
        this.email = email
        this.password = password
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @NotBlank
    @Size(max = 40)
    lateinit var fullname: String

    @NotBlank
    @Size(max = 15)
    lateinit var username: String

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    lateinit var email: String

    @NotBlank
    @Size(max = 100)
    lateinit var password: String

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "role_id")])
    var roles: Set<Role> = HashSet()

    @OneToMany(mappedBy = "user")
    var employerInfos: List<EmployerInfo> = ArrayList()
}