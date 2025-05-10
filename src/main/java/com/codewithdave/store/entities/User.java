package com.codewithdave.store.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
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

    // @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    // private Profile profile;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @Builder.Default
    @JoinTable(
        name = "wishlist",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> wishlist = new HashSet<>();

    // Builder.Default is used to make sure data parameters are initialized when using the builder
    // design pattern

    // @ToString provides a tostring method for the dev

    @Override
    public String toString() {
        return getClass().getSimpleName() + "( " +
            "id = " + id + ", " +
            "name = " + name + ", " +
            "email = " + email + " )";
    }

    public void addAddress(Address address){
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Address address){
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


    public void addToWishlist(Product product){
        this.wishlist.add(product);
    }

    public void removeFromWishlist(Product product){
        this.wishlist.remove(product);
    }
}
