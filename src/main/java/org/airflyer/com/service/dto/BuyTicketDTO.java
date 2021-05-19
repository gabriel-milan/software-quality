package org.airflyer.com.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link org.airflyer.com.domain.BuyTicket} entity.
 */
public class BuyTicketDTO implements Serializable {

    private Long id;

    private String name;

    private String origem;

    private String destino;

    private LocalDate dataIda;

    private LocalDate dataVolta;

    private String companhiaAerea;

    private String cartaoCredito;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getDataIda() {
        return dataIda;
    }

    public void setDataIda(LocalDate dataIda) {
        this.dataIda = dataIda;
    }

    public LocalDate getDataVolta() {
        return dataVolta;
    }

    public void setDataVolta(LocalDate dataVolta) {
        this.dataVolta = dataVolta;
    }

    public String getCompanhiaAerea() {
        return companhiaAerea;
    }

    public void setCompanhiaAerea(String companhiaAerea) {
        this.companhiaAerea = companhiaAerea;
    }

    public String getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(String cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BuyTicketDTO)) {
            return false;
        }

        BuyTicketDTO buyTicketDTO = (BuyTicketDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, buyTicketDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BuyTicketDTO{" +
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
