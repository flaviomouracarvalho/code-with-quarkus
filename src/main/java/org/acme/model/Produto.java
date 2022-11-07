package org.acme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Locale;
import java.util.Objects;

@Entity
public class Produto extends AbstractEntitty{
    @Column(unique = true, nullable = false)
    @NotBlank
    public String descricao;

    @ManyToOne
    @NotBlank
    public Categoria categoria;

    @NotNull
    @Positive
    public double preco;

    public void setNome(String descricao) {
        Objects.requireNonNull(descricao, "Nome não pode ser nulo");
        this.descricao = descricao.trim().toUpperCase().toUpperCase();
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
