package study.datajpa.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.criteria.*;

public class MemberSpec {

    public static Specification<Member> teamName(final String teamName) {
        return (Specification<Member>) (m, cq, cb) -> {

            if (StringUtils.isEmpty(teamName)) {
                return null;
            }

            /* Member, Team Join */
            Join<Member, Team> t = m.join("team", JoinType.INNER);
            return cb.equal(t.get("name"), teamName);
        };
    }

    public static Specification<Member> username(final String username) {
        return (Specification<Member>) (m, cq, cb) -> {
            if (StringUtils.isEmpty(username)) {
                return null;
            }

            return cb.equal(m.get("username"), username);
        };
    }
}
