package com.codewithdave.store.services;

import java.time.LocalDate;
import java.util.HashSet;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.codewithdave.store.entities.Address;
import com.codewithdave.store.entities.Category;
import com.codewithdave.store.entities.Product;
import com.codewithdave.store.entities.Profile;
import com.codewithdave.store.entities.User;
import com.codewithdave.store.repositories.AddressRepository;
import com.codewithdave.store.repositories.CategoryRepository;
import com.codewithdave.store.repositories.ProductRepository;
import com.codewithdave.store.repositories.ProfileRepository;
import com.codewithdave.store.repositories.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final EntityManager entityManager;

    // This tag makes the persistent context open for the entire method as opposed to 
    // each transactional instruction
    @Transactional
    public void showEntityStates(){
        var user = User.builder()
            .name("John Doe")
            .email("john@example.com")
            .password("password")
            .build();

        if (entityManager.contains(user)){
            System.out.println("Persistent");
        } else {
            System.out.println("Transient / Detached");
        }

        userRepository.save(user);

        if (entityManager.contains(user)){
            System.out.println("Persistent");
        } else {
            System.out.println("Transient / Detached");
        }
        
    }

    // Trying to access the user when the 1-to-1 mapping is lazy loading will throw an error
    // because the entity is detached so we aren't able to read this relationship from the db
    @Transactional
    public void showRelatedEntities(){
        var profile = profileRepository.findById(2L).orElseThrow();
        // var user = profile.getUser();
        // System.out.println(user.getName());
        System.out.println(profile.getBio());
    }

    public void fetchAddress(){
        var address = addressRepository.findById(1L).orElseThrow();

        System.out.println(address.getCity());
    }

    public void persistRelated(){
        var user = User.builder()
			.name("John Doe")
			.email("johnsmith@gmail.com")
			.password("password")
			.build();


        var address = Address.builder()
            .city("city")
            .state("state")
            .zip("zip")
            .street("street")
            .build();

            user.addAddress(address);
            userRepository.save(user);
    }

    @Transactional
    public void deleteRelated() {
        var user = userRepository.findById(5L).orElseThrow();
        Address address = user.getAddresses().get(0);
        user.removeAddress(address);
    }

    public void createProduct(){
        // Create product with dummy data
        var product = Product.builder()
            .name("Milk")
            .price(10)
            .description("description")
            .build();
        
        var category = Category.builder()
            .name("dairy")
            .build();

        category.addProduct(product);
        categoryRepository.save(category);
    }

    @Transactional
    public void newProductIntoCategory(){
        // Create another dairy product
        var product = Product.builder()
        .name("New product")
        .price(10)
        .description("description")
        .build();

        Integer id = 1;

        var category = categoryRepository.findById(id.byteValue()).orElseThrow();
        product.setCategory(category);
        productRepository.save(product);
    }

    @Transactional
    public void addToWishlist(){
        // Add all existing products to wishlist
        var user = userRepository.findById(1L).orElseThrow();
        productRepository.findAll().forEach(product -> user.addToWishlist(product));

        userRepository.save(user);
    }

    @Transactional
    public void deleteProduct() {
        productRepository.deleteById(4L);
    }

    @Transactional
    public void updateProductPrices(){
        productRepository.updatePriceByCategory(15, (byte)1);
    }

    @Transactional
    public void fetchProducts() {
        var product = new Product();
        product.setName("product");

        var matcher = ExampleMatcher.matching()
            .withIncludeNullValues()
            .withIgnorePaths("id", "description")
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product, matcher);
        var products = productRepository.findAll(example);
        products.forEach(p -> System.out.println(p));
    }

    @Transactional
    public void fetchUsers(){
        var users = userRepository.findAllWithAddresses();
        users.forEach( user -> {
            System.out.println(user);
            user.getAddresses().forEach(System.out::println);
        });
    }

    @Transactional
    public void addAddress(){
        var address = Address.builder()
            .city("city 3")
            .state("state")
            .zip("zip")
            .street("street")
            .build();

        userRepository.findById(2L).orElseThrow().addAddress(address);
    }
    
    @Transactional
    public void workWithProfiles(){
        var users = userRepository.findLoyalUsers(2);
        users.forEach(user -> {
            System.out.println(user.getId());
            System.out.println(user.getEmail());
        });
    }

    public void fetchProductsByCriteria(){
        var products = productRepository.findProductsByCriteria("product", 1, 10);
        products.forEach(System.out::println);
    }
}
