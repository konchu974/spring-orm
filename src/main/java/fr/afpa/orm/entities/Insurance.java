package fr.afpa.orm.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "insurance")
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String Name;

    @ManyToMany
    @JoinTable(name = "souscri",
            joinColumns = @JoinColumn( name = "id_insurance" ),
            inverseJoinColumns = @JoinColumn( name = "id_client" ) )
    private List<Client> clients = new ArrayList<>();


    public Insurance() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
