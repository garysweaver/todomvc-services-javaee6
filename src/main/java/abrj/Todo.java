package abrj;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@Entity
@NamedQueries({
        // Feel free to use constants for named query names and perhaps the param names. Leaving out to reduce LOC.
        // Can add more here as needed...
        @NamedQuery(name = "findTodosByOwner", query = "SELECT t FROM Todo t WHERE t.owner = :owner")
})
@XmlRootElement
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    // not null is just in case you can use it. JPA way is @Column(nullable = false)
    @NotNull(message = "title should not be null")
    @Column(nullable = false)
    private String title;

    // Intentionally using primitive to force to default (to false in this case since it is a boolean). Another two ways
    // to default a value are to use @Column(columnDefinition="... default some_value") (which is DB-specific) or to
    // provide default value when defining the member (and potentially to make it use default value in the setter if
    // null.
    @Column
    private boolean completed;

    // @XmlTransient to hide the owner
    // Default is "guest". Maybe we'll generate something unique and store in cookie, since this isn't stored locally
    // like a lot of other TodoMVC examples, so people might not know they are sharing data.
    @Column
    @XmlTransient
    private String owner = "guest";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
