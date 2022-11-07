package org.acme.controller;

import org.acme.model.Categoria;

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

    @GET
    public List<Categoria> getAll(){
        return Categoria.listAll();
    }
}