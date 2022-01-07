package entities.tp4;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table(name = "compte")
public class AbstractCompte {
    public static final String COLUMN_ID_NAME = "id";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private Integer id;

    @Column(name = "numero")
    private String numero;

    @Column(name = "solde")
    private Double solde = 0.0;

    @OneToMany(mappedBy = "compte")
    private List<Operation> operations;

    @ManyToMany
    @JoinTable(name = "client_compte",
            joinColumns = @JoinColumn(name = "idCompte", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "idClient", referencedColumnName = "id")
    )
    private List<Client> clients = new ArrayList<>();


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

    public Double getSolde() {
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

    public void addClient(Client client) {
        this.clients.add(client);
    }
}