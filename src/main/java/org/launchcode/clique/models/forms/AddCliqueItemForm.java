package org.launchcode.clique.models.forms;

import org.launchcode.clique.models.Clique;
import org.launchcode.clique.models.Post;

import javax.validation.constraints.NotNull;

/**
 * Created by Jaurron on 7/11/2017.
 */
public class AddCliqueItemForm {

    @NotNull
    private int cliqueId;

    @NotNull
    private int postId;

    private Clique clique;

    private Iterable<Post> posts;

    public int getCliqueId() {
        return cliqueId;
    }

    public void setCliqueId(int cliqueId) {
        this.cliqueId = cliqueId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Clique getClique() {
        return clique;
    }

    public void setClique(Clique clique) {
        this.clique = clique;
    }

    public Iterable<Post> getPosts() {
        return posts;
    }

    public void setPosts(Iterable<Post> posts) {
        this.posts = posts;
    }

    public AddCliqueItemForm(){

    }

    public AddCliqueItemForm(Clique clique, Iterable<Post> posts){
        this.clique = clique;
        this.posts = posts;
    }
}
