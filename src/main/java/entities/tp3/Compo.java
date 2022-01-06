package entities.tp3;

import javax.persistence.*;

@Entity
@Table(name = "COMPO")
public class Compo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ID_LIV", nullable = false)
    private Integer idLiv;

    @Column(name = "ID_EMP", nullable = false)
    private Integer idEmp;

    public Integer getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Integer idEmp) {
        this.idEmp = idEmp;
    }

    public Integer getIdLiv() {
        return idLiv;
    }

    public void setIdLiv(Integer idLiv) {
        this.idLiv = idLiv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}