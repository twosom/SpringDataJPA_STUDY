package study.datajpa.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter @Setter @ToString(of = {"id", "name"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
