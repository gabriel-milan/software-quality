package org.airflyer.com.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BuyTicket.
 */
@Entity
@Table(name = "buy_ticket")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BuyTicket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "origem")
    private String origem;

    @Column(name = "destino")
    private String destino;

    @Column(name = "data_ida")
    private LocalDate dataIda;

    @Column(name = "data_volta")
    private LocalDate dataVolta;

    @Column(name = "companhia_aerea")
    private String companhiaAerea;

    @Column(name = "cartao_credito")
    private String cartaoCredito;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BuyTicket id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public BuyTicket name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigem() {
        return this.origem;
    }

    public BuyTicket origem(String origem) {
        this.origem = origem;
        return this;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return this.destino;
    }

    public BuyTicket destino(String destino) {
        this.destino = destino;
        return this;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getDataIda() {
        return this.dataIda;
    }

    public BuyTicket dataIda(LocalDate dataIda) {
        this.dataIda = dataIda;
        return this;
    }

    public void setDataIda(LocalDate dataIda) {
        this.dataIda = dataIda;
    }

    public LocalDate getDataVolta() {
        return this.dataVolta;
    }

    public BuyTicket dataVolta(LocalDate dataVolta) {
        this.dataVolta = dataVolta;
        return this;
    }

    public void setDataVolta(LocalDate dataVolta) {
        this.dataVolta = dataVolta;
    }

    public String getCompanhiaAerea() {
        return this.companhiaAerea;
    }

    public BuyTicket companhiaAerea(String companhiaAerea) {
        this.companhiaAerea = companhiaAerea;
        return this;
    }

    public void setCompanhiaAerea(String companhiaAerea) {
        this.companhiaAerea = companhiaAerea;
    }

    public String getCartaoCredito() {
        return this.cartaoCredito;
    }

    public BuyTicket cartaoCredito(String cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
        return this;
    }

    public void setCartaoCredito(String cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BuyTicket)) {
            return false;
        }
        return id != null && id.equals(((BuyTicket) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BuyTicket{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", origem='" + getOrigem() + "'" +
            ", destino='" + getDestino() + "'" +
            ", dataIda='" + getDataIda() + "'" +
            ", dataVolta='" + getDataVolta() + "'" +
            ", companhiaAerea='" + getCompanhiaAerea() + "'" +
            ", cartaoCredito='" + getCartaoCredito() + "'" +
            "}";
    }
}
