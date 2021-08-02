package com.rainmore.cms.domains.system;

import com.rainmore.cms.domains.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "test")
@Table(name = "test")
public class Test implements HasId<Integer> {

    private Integer id;
    private String name;
    private String text;

    @Id
    @Column(nullable = false)
    @GeneratedValue
    @Override
    public Integer getId() {
        return id;
    }


    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @NotEmpty
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
