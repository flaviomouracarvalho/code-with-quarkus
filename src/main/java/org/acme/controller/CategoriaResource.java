package org.acme.controller;

import io.quarkus.hibernate.orm.panache.runtime.JpaOperations;
import org.acme.model.Categoria;

import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/categoria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @GET
    @Path("{id}")
    public Categoria getById(@PathParam("id") long id) {
        return Categoria.findById(id);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") long id) {
        System.out.println("Excluindo " + id);
        Categoria.deleteById(id);
    }

    @Transactional
    @POST
    public void insert(Categoria categoria) {

        Categoria.persist(categoria);
    }

    @Transactional
    @Path("/{id}")
    @PUT
    public void update(@PathParam("id") Long id, Categoria categoria) {
        /*var categoriaLocalizada = (Categoria)Categoria.findById(id);
        categoriaLocalizada.descricao = categoria.descricao;
        categoriaLocalizada.sigla = categoria.sigla;
        categoriaLocalizada.persist();*/
        try {
            var em = JpaOperations.INSTANCE.getEntityManager();
            em.merge(categoria);
        } catch (OptimisticLockException e){
            throw  new WebApplicationException(
                    "Registro alterado por outro usuário"
            );
        }
    }

    @GET
    public List<Categoria> getAll(){

        return Categoria.listAll();
    }
}