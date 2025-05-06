package com.codewithdave.store.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Best practice to use @Column tag to define relationship to db column
    // This way, these parameter names can change without needing to edit the db

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    @ManyToMany
    @Builder.Default
    @JoinTable(
        name = "user_tags",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToOne(mappedBy = "user")
    @MapsId
    private Profile profile;

    // Builder.Default is used to make sure data parameters are initialized when using the builder
    // design pattern

    // @ToString provides a tostring method for the dev

    public void addAddress(Address address){
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAaddress(Address address){
        addresses.remove(address);
        address.setUser(null);
    }

    public void addTag(String tagName){
        Tag tag = new Tag(tagName);
        this.tags.add(tag);
		tag.getUsers().add(this);
    }

    public void removeTag(String tagName){
        Iterator<Tag> it = tags.iterator();
        Tag tag;
        while (it.hasNext()){
            tag = it.next();
            if (tagName.equals(tag.getName())){
                this.tags.remove(tag);
                tag.getUsers().remove(this);
                return;
            }
        }

        // Error, tag wasn't found or not successfully removed
    }
}
