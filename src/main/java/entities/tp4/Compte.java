package entities.tp4;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "compte")
public class Compte {
    public static final String COLUMN_ID_NAME = "id";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private Integer id;

    @Column(name = "numero")
    private String numero;

    @Column(name = "solde")
    private double solde;

    @OneToMany(mappedBy = "compte")
    private List<Operation> operations;

    @ManyToMany
    @JoinTable(name = "client_compte",
            joinColumns = @JoinColumn(name = "idCompte", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "idClient", referencedColumnName = "id")
    )
    private List<Client> clients;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}