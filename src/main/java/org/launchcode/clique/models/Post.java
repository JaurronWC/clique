package org.launchcode.clique.models;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


/**
 * Created by Jaurron on 7/10/2017.
 */

@Entity
public class Post {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String title;

    @NotNull
    @Size(min=1, message = "Contents cannot be empty!")
    private String content;

    @ManyToOne
    private Users user;

    @ManyToMany(mappedBy = "posts")
    private List<Clique> cliques;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post(){}

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

   public Users getUser() {
        return user;
   }

   public void setUser(Users user){
        this.user = user;
   }
}
