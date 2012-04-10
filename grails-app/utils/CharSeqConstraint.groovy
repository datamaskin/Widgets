
/**
 * Created by IntelliJ IDEA.
 * User: david
 * Date: 4/10/12
 * Time: 11:36 AM
 * To change this template use File | Settings | File Templates.
 */
class CharSeqConstraint {
    def validate = { val, obj ->
        log.info "CharSeqConstraint val [$val]"
        log.info "CharSeqConstraint password [$obj.password]"
        def p = params
        log.info "CharSeqConstraint p value [$p]"
    }
}
