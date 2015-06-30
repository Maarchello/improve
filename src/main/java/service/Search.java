package service;


import model.Prod;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class Search {
    public EntityManager entityManager = Persistence.createEntityManagerFactory("FIG").createEntityManager();

    public Prod get(long id){
        return entityManager.find(Prod.class, id);
    }

    //Поиск по максимально допустимой цене
    public List getByPriceMax(Integer priceMax){
        return entityManager.createQuery("select e from Prod e where e.price<= :priceMax")
                .setParameter("priceMax", priceMax)
                .getResultList();
    }

    //Поиск по минимально допустимой цене
    public List getByPriceMin(Integer priceMin){
        return entityManager.createQuery("select e from Prod e where e.price>= :priceMin")
                .setParameter("priceMin", priceMin)
                .getResultList();
    }

    //Поиск по заданным критериям цен
    public List getByPrice(Integer priceFrom, Integer priceTo){
        return entityManager.createQuery("select e from Prod e where e.price between :priceFrom and :priceTo")
                .setParameter("priceFrom", priceFrom)
                .setParameter("priceTo", priceTo)
                .getResultList();
    }

    //Поиск по наименованию
    public List getByName(String name){
        return entityManager.createQuery("select e from Prod e where e.name= :name")
                .setParameter("name", name)
                .getResultList();
    }

    //Поиск по наименованию и максимально допустимой цене
    public List getByNameAndPriceMax(String name, Integer priceMax){
        return entityManager.createQuery("select e from Prod e where e.name= :name and e.price<= :priceMax")
                .setParameter("name", name)
                .setParameter("priceMax", priceMax)
                .getResultList();
    }

    //Поиск по наименованию и минимально допустимой цене
    public List getByNameAndPriceMin(String name, Integer priceMin){
        return entityManager.createQuery("select e from Prod e where e.name= :name and e.price>= :priceMin")
                .setParameter("name", name)
                .setParameter("priceMin", priceMin)
                .getResultList();
    }

    //Поиск по наименованию и заданным критериям цен
    public List getByNameAndPrice(String name, Integer priceMin, Integer priceMax){
        return entityManager.createQuery("select e from Prod e where e.name= :name and e.price between :priceMin and :priceMax")
                .setParameter("name", name)
                .setParameter("priceMin", priceMin)
                .setParameter("priceMax", priceMax)
                .getResultList();
    }

    //Поиск по категории
    public List getByCat(String category){
        return entityManager.createQuery("select p from Prod p where p.cat.name= :cat")
                .setParameter("cat", category)
                .getResultList();
    }

    //Поиск по категории и максимально допустимой цене
    public List getByCatAndPriceMax(String category, Integer priceMax){
        return entityManager.createQuery("select p from Prod p where p.cat.name= :cat and p.price<= :priceMax")
                .setParameter("cat", category)
                .setParameter("priceMax", priceMax)
                .getResultList();
    }

    //Поиск по категории и минимально допустимой цене
    public List getByCatAndPriceMin(String category, Integer priceMin){
        return entityManager.createQuery("select p from Prod p where p.cat.name= :cat and p.price>= :priceMin")
                .setParameter("cat", category)
                .setParameter("priceMin", priceMin)
                .getResultList();
    }

    //Поиск по категории и заданным критериям цен
    public List getByCatAndPrice(String category, Integer priceMin, Integer priceMax){
        return entityManager.createQuery("select p from Prod p where p.cat.name= :cat and p.price between :priceMin and :priceMax")
                .setParameter("cat", category)
                .setParameter("priceMin", priceMin)
                .setParameter("priceMax", priceMax)
                .getResultList();
    }

    //Поиск по категории и наименованию
    public List getByCatAndName(String category, String name){
        return entityManager.createQuery("select p from Prod p where p.cat.name= :cat and p.name= :name")
                .setParameter("cat", category)
                .setParameter("name", name)
                .getResultList();
    }

    //Поиск по категории, наименованию и максимально допустимой цене
    public List getByCatAndNameAndPriceMax(String category, String name, Integer priceMax){
        return entityManager.createQuery("select p from Prod p where p.cat.name= :cat and p.name= :name and p.price<= :priceMax")
                .setParameter("cat", category)
                .setParameter("name", name)
                .setParameter("priceMax", priceMax)
                .getResultList();
    }

    //Поиск по категории, наименованию и минимально допустимой цене
    public List getByCatAndNameAndPriceMin(String category, String name, Integer priceMin){
        return entityManager.createQuery("select p from Prod p where p.cat.id= :cat and p.name= :name and p.price>= :priceMin")
                .setParameter("cat", category)
                .setParameter("name", name)
                .setParameter("priceMin", priceMin)
                .getResultList();
    }

    //Поиск по всем критериям
    public List getByAllValues(String category, String name, Integer priceMin, Integer priceMax){
        return entityManager.createQuery("select p from Prod p where p.cat.id= :cat and p.name= :name and p.price between :priceMin and :priceMax")
                .setParameter("cat", category)
                .setParameter("name", name)
                .setParameter("priceMin", priceMin)
                .setParameter("priceMax", priceMax)
                .getResultList();
    }


}