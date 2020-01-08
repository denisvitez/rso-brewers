package si.fri.rso.sn.brewers.services.beans;

import si.fri.rso.sn.brewers.models.entities.Brewer;
import si.fri.rso.sn.brewers.services.configuration.AppProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.List;
import java.util.logging.Logger;


@RequestScoped
public class BrewersBean {

    private Logger log = Logger.getLogger(BrewersBean.class.getName());

    @Inject
    private EntityManager em;

    @Inject
    private AppProperties appProperties;

    private Client httpClient;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
//        baseUrlFriends = "http://localhost:8081"; // only for demonstration
    }


    public List<Brewer> getBrewers() {
        TypedQuery<Brewer> query = em.createNamedQuery("Brewers.getAll", Brewer.class);
        System.out.println(query.toString());
        return query.getResultList();
    }

    public Brewer getBrewer(Integer brewerId) {

        Brewer item = em.find(Brewer.class, brewerId);

        if (item == null) {
            throw new NotFoundException();
        }
        return item;
    }

    public Brewer createBrewer(Brewer item) {
        try {
            beginTx();
            em.persist(item);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        return item;
    }

    public Brewer putBrewer(Integer itemId, Brewer item) {
        Brewer b = em.find(Brewer.class, itemId);
        if (b == null) {
            return null;
        }
        try {
            beginTx();
            item.setId(b.getId());
            item = em.merge(item);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }
        return item;
    }

    public boolean deleteBrewer(Integer itemId) {

        Brewer b = em.find(Brewer.class, itemId);

        if (b != null) {
            try {
                beginTx();
                em.remove(b);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else
            return false;

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }
}
