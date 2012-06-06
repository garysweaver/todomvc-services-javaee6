package abrj;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@LocalBean
@Path("todos")
public class TodoService {

    @PersistenceContext(unitName = "TodoService", type = PersistenceContextType.TRANSACTION)
    EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Todo todo) {
        entityManager.persist(todo);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Todo read(@PathParam("id") long id) {
        return entityManager.find(Todo.class, id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Todo todo) {
        entityManager.merge(todo);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        Todo todo = read(id);
        if (todo != null) {
            entityManager.remove(todo);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> findTodos() {
        // note: it works even if you don't specify class
        return entityManager.createNamedQuery("findTodos", Todo.class).getResultList();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("owner/{owner}")
    public List<Todo> findTodos(@PathParam("owner") String owner) {
        // note: it works even if you don't specify class
        Query query = entityManager.createNamedQuery("findTodosByOwner", Todo.class);
        query.setParameter("owner", owner);
        return query.getResultList();
    }
}