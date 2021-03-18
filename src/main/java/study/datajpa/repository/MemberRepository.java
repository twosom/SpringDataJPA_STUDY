package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom, JpaSpecificationExecutor {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3HelloBy();

    //    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);

    @Query("select m " +
            "from Member m " +
            "where m.username = :username " +
            "and m.age = :age")
    List<Member> findUser(@Param("username") String username,
                          @Param("age") int age);

    @Query("select m.username " +
            "from Member m")
    List<String> findUsernameList();

    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) " +
            "from Member m join m.team t ")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    List<Member> findListByUsername(String username);   //컬렉션

    Member findMemberByUsername(String username);   //단건

    Optional<Member> findOptionalByUsername(String username); //단건 Optional

    @Query(value = "select m from Member m left join m.team t where m.age = :age", countQuery = "select count(m) from Member m")
    Page<Member> findByAge(@Param("age") int age, Pageable pageable);


    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);


    @Query("select m from Member m left join fetch m.team t")
    List<Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    //    @EntityGraph(attributePaths = {"team"})
    @EntityGraph(value = "Member.all")
    List<Member> findEntityGraphByUsername(@Param("username") String username);


    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);

    List<UsernameOnly> findProjectionsByUsername(@Param("username") String username);

    <T> List<T> findDtoProjectionsByUsername(@Param("username") String username, Class<T> type);


    @Query(value = "select username from member where username = ?", nativeQuery = true)
    Member findByNativeQuery(String username);


    /**
     * Native Query 를 사용할 때는 반환값의 Interface 의 getXXX 의 xxx 와 조회할 필드 이름을 <br/>
     * alias 를 이용해서 일치시킨다.<br/>
     * 페이징 기능도 사용할 경우에는 countQuery 도 따로 작성을 해주고, 마지막에 nativeQuery = true 값을 주면 된다.
     */
    @Query(value = "select m.member_id as id, m.username, t.name as teamName " +
            "from member m left join team t",
            countQuery = "select count(*) from member",
            nativeQuery = true)
    Page<MemberProjection> findByNativeProjection(Pageable pageable);
}
