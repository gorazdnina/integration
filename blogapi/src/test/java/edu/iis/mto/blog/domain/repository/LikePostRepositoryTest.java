package edu.iis.mto.blog.domain.repository;

import edu.iis.mto.blog.domain.model.AccountStatus;
import edu.iis.mto.blog.domain.model.BlogPost;
import edu.iis.mto.blog.domain.model.LikePost;
import edu.iis.mto.blog.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@DataJpaTest
public class LikePostRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LikePostRepository repository;

    private BlogPost blogPost;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setAccountStatus(AccountStatus.CONFIRMED);
        user.setEmail("123@gmail.com");

        blogPost = new BlogPost();
        blogPost.setEntry("entry");
        blogPost.setUser(user);
    }

    @Test
    void shouldFindSavedPost() {
        entityManager.persist(user);
        entityManager.persist(blogPost);

        LikePost likePost = new LikePost();
        likePost.setUser(user);
        likePost.setPost(blogPost);
        LikePost savedLikePost = repository.save(likePost);

        assertThat(savedLikePost.getId(), notNullValue() );
    }
}
