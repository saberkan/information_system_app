/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author salahddine
 */
@Entity
@Table(name = "information_system_db.event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e"),
    @NamedQuery(name = "Event.findByEventId", query = "SELECT e FROM Event e WHERE e.eventId = :eventId"),
    @NamedQuery(name = "Event.findByEventTitle", query = "SELECT e FROM Event e WHERE e.eventTitle = :eventTitle"),
    @NamedQuery(name = "Event.findByEventImageLink", query = "SELECT e FROM Event e WHERE e.eventImageLink = :eventImageLink"),
    @NamedQuery(name = "Event.findByEventDate", query = "SELECT e FROM Event e WHERE e.eventDate = :eventDate")})
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "event_id")
    private Integer eventId;
    @Size(max = 50)
    @Column(name = "event_title")
    private String eventTitle;
    @Lob
    @Size(max = 65535)
    @Column(name = "event_description")
    private String eventDescription;
    @Lob
    @Size(max = 65535)
    @Column(name = "event_image_link")
    private String eventImageLink;
    @Column(name = "event_date")
    @Temporal(TemporalType.DATE)
    private Date eventDate;
    @ManyToMany(mappedBy = "eventCollection")
    private Collection<User> userCollection;
    @JoinColumn(name = "event_creator", referencedColumnName = "user_id")
    @ManyToOne
    private User eventCreator;

    public Event() {
    }

    public Event(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventImageLink() {
        return eventImageLink;
    }

    public void setEventImageLink(String eventImageLink) {
        this.eventImageLink = eventImageLink;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    //@XmlTransient
    public Collection<User> getUserCollection() {
        System.out.println(userCollection.toString());
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    public User getEventCreator() {
        return eventCreator;
    }

    public void setEventCreator(User eventCreator) {
        this.eventCreator = eventCreator;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventId != null ? eventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.eventId == null && other.eventId != null) || (this.eventId != null && !this.eventId.equals(other.eventId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Event[ eventId=" + eventId + " ]";
    }
    
}
