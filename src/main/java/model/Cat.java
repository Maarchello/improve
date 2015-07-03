package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cat")
public class Cat {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cat")
    private List<Prod> set = new ArrayList<Prod>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Prod> getSet() {
        return set;
    }

    public void setSet(List<Prod> set) {
        this.set = set;
    }

}
