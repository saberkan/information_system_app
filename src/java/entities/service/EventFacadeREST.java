/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities.service;

import entities.Event;
import entities.User;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author salahddine
 */
@Stateless
@Path("entities.event")
public class EventFacadeREST extends AbstractFacade<Event> {
    @PersistenceContext(unitName = "information_system_appPU")
    private EntityManager em;

    public EventFacadeREST() {
        super(Event.class);
    }

    @POST
    @Override
    @Path("create")
    @Consumes({"application/xml", "application/json"})
    public void create(Event entity) {
        System.out.println("test");
        super.create(entity);
    }
    

    @POST
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Event entity) {
        Event event = super.find(id);
        event.setEventTitle(entity.getEventTitle());
        event.setEventDescription(entity.getEventDescription());
        event.setEventDate(entity.getEventDate());
        event.setEventImageLink(entity.getEventImageLink());
        super.edit(event);
    }

    
    @POST
    @Path("{eventId}/addsubscribers/{userId}")
    @Consumes({"application/xml", "application/json"})
    public void addSubscriber(@PathParam("eventId") Integer eventId, @PathParam("userId") Integer userId) {
        User user = getEntityManager().find(User.class, userId);
        Event entity = super.find(eventId);
        entity.getUserCollection().add(user);
        super.edit(entity);
    }

    @POST
    @Path("remove/{id}")
    public void remove(@PathParam("id") Integer id) {
        Event entity = super.find(id);
        User user = entity.getEventCreator();
        user.getEventCollection1().remove(entity);
        super.remove(super.find(id));
    }
    
    @POST
    @Path("{eventId}/removesubscribers/{userId}")
    public void removeSubscriber(@PathParam("eventId") Integer eventId, @PathParam("userId") Integer userId) {
        User user = getEntityManager().find(User.class, userId);
        Event entity = super.find(eventId);
        entity.getUserCollection().remove(user);
        super.edit(entity);
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Event find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("{id}/subscribers")
    @Produces({"application/xml", "application/json"})
    public Collection<User> findSubscribers(@PathParam("id") Integer id) {
        return super.find(id).getUserCollection();
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Event> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Event> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
