package service;


import model.Prod;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.*;

public class Search {

    public EntityManager entityManager = Persistence.createEntityManagerFactory("FIG").createEntityManager();

    public List get(String category, String name, Integer priceMin, Integer priceMax){
        List<Prod> list= new ArrayList<Prod>();

        if (!category.isEmpty()){
            List<Prod> listCat = getByCat(category);
            if(list.isEmpty()) list.addAll(listCat);
            else list.retainAll(listCat);
        }
        if (!name.isEmpty()){
            List<Prod> listName = getByName(name);
            if(list.isEmpty()) list.addAll(listName);
            else list.retainAll(listName);
        }
        if (priceMin!=null){
            List<Prod> listMin = getByPriceMin(priceMin);
            if(list.isEmpty()) list.addAll(listMin);
            else list.retainAll(listMin);
        }
        if (priceMax!=null){
            List<Prod> listMax = getByPriceMax(priceMax);
            if(list.isEmpty()) list.addAll(listMax);
            else list.retainAll(listMax);
        }

        return list;
    }



    //Поиск по максимально допустимой цене
    private List getByPriceMax(Integer priceMax){
        return entityManager.createQuery("select e from Prod e where e.price<= :priceMax")
                .setParameter("priceMax", priceMax)
                .getResultList();
    }

    //Поиск по минимально допустимой цене
    private List getByPriceMin(Integer priceMin){
        return entityManager.createQuery("select e from Prod e where e.price>= :priceMin")
                .setParameter("priceMin", priceMin)
                .getResultList();
    }


    //Поиск по наименованию
    public List getByName(String name){
        return entityManager.createQuery("select e from Prod e where e.name= :name")
                .setParameter("name", name)
                .getResultList();
    }


    //Поиск по категории
    private List getByCat(String category){
        return entityManager.createQuery("select p from Prod p where p.cat.name= :cat")
                .setParameter("cat", category)
                .getResultList();
    }



}