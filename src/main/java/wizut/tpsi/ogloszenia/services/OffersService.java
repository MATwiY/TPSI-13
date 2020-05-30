package wizut.tpsi.ogloszenia.services;

import org.springframework.stereotype.Service;
import wizut.tpsi.ogloszenia.jpa.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class OffersService {

    @PersistenceContext
    private EntityManager em;

    public CarManufacturer getCarManufacturer(int id){ return em.find(CarManufacturer.class, id);}

    public CarModel getCarModel(int id){ return em.find(CarModel.class, id);}

    public Offer getOffer(int id){ return em.find(Offer.class, id);}

    public List<CarManufacturer> getCarManufacturers(){
        String jpql = "select cm from CarManufacturer cm order by cm.name";
        TypedQuery<CarManufacturer> query = em.createQuery(jpql, CarManufacturer.class);
        List<CarManufacturer> result = query.getResultList();

        return result;
    }

    public List<BodyStyle> getBodyStyle(){
        String jpql = "select cm from BodyStyle cm order by cm.name";
        TypedQuery<BodyStyle> query = em.createQuery(jpql, BodyStyle.class);
        List<BodyStyle> result = query.getResultList();

        return result;
    }

    public List<FuelType> getFuelType(){
        String jpql = "select cm from FuelType cm order by cm.name";
        TypedQuery<FuelType> query = em.createQuery(jpql, FuelType.class);
        List<FuelType> result = query.getResultList();

        return result;
    }

    public List<CarModel> getCarModels(){
        String jpql = "select cm from CarModel cm order by cm.name";
        TypedQuery<CarModel> query = em.createQuery(jpql, CarModel.class);
        List<CarModel> result = query.getResultList();

        return result;
    }

    public List<CarModel> getCarModels(int manufacturerId){
        String jpql = "select cm from CarModel cm where cm.manufacturer.id = :id order by cm.name";
        TypedQuery<CarModel> query = em.createQuery(jpql, CarModel.class);
        query.setParameter("id", manufacturerId);

        return query.getResultList();
    }

    public List<Offer> getOffersByModel(int modelId){
        String jpql = "select cm from Offer cm where cm.model.id = :id order by cm.title";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        query.setParameter("id", modelId);

        return query.getResultList();
    }

    public List<Offer> getOffersByManufacturer(int manufacturerId){
        String jpql = "select cm from Offer cm where cm.model.manufacturer.id = :id order by cm.title";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        query.setParameter("id", manufacturerId);

        return query.getResultList();
    }

    public List<Offer> getOffers(){
        String jpql = "select cm from Offer cm order by cm.id";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        List<Offer> result = query.getResultList();

        return result;
    }
}