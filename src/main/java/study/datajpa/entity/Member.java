package study.datajpa.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(of = {"id", "username", "age"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedQuery(
        name = "Member.findByUsername",
        query = "select m from Member m where m.username = :username"
)
@NamedEntityGraph(name = "Member.all", attributeNodes = @NamedAttributeNode("team"))
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    //==연관관계 메소드==//

    /**
     * 만약 member 안에 있는 team 객체의 members 안에<br/>
     * 이 member가 포함되어 있다면 지우고 시작한다.
     */
    public void changeTeam(Team team) {
        if (getTeam() != null && getTeam().getMembers().contains(this)) {
            this.team.getMembers().remove(this);
        }
        this.team = team;
        team.getMembers().add(this);
    }


    /* 가능한 한 Setter 는 배재해야한다. */
    public void changeUsername(String username) {
        this.username = username;
    }
}
