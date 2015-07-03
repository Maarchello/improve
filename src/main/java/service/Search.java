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
            //list.isEmpty()?list.addAll(listCat):list.retainAll(listCat);
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

       /*if(category.isEmpty() && name.isEmpty()){
           return getByPrice(priceMin, priceMax);
       }else if (category.isEmpty() && !name.isEmpty()){
           return getByNameAndPrice(name, priceMin, priceMax);
       }else if (!category.isEmpty() && name.isEmpty()){
            return getByCatAndPrice(category, priceMin, priceMax);
       }else if (!category.isEmpty() && !name.isEmpty()){
           return getByAllValues(category,name,priceMin,priceMax);
       }*/
        return list;
    }

    //Поиск по заданным критериям цен
    public List getByPrice(Integer priceMin, Integer priceMax){
        return  entityManager.createQuery("select p from Prod p where p.price between :priceMin and :priceMax")
                .setParameter("priceMin", priceMin)
                .setParameter("priceMax", priceMax)
                .getResultList();
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

    //Поиск по наименованию и максимально допустимой цене
    private List getByNameAndPriceMax(String name, Integer priceMax){
        return entityManager.createQuery("select e from Prod e where e.name= :name and e.price<= :priceMax")
                .setParameter("name", name)
                .setParameter("priceMax", priceMax)
                .getResultList();
    }

    //Поиск по наименованию и минимально допустимой цене
    private List getByNameAndPriceMin(String name, Integer priceMin){
        return entityManager.createQuery("select e from Prod e where e.name= :name and e.price>= :priceMin")
                .setParameter("name", name)
                .setParameter("priceMin", priceMin)
                .getResultList();
    }

    //Поиск по наименованию и заданным критериям цен
    public List getByNameAndPrice(String name, Integer priceMin, Integer priceMax){
        return entityManager.createQuery("select e from Prod e where e.name=:name or (e.name=:name and e.price between :priceMin and :priceMax)")
                .setParameter("name", name)
                .setParameter("priceMin", priceMin)
                .setParameter("priceMax", priceMax)
                .getResultList();
    }

    //Поиск по категории
    private List getByCat(String category){
        return entityManager.createQuery("select p from Prod p where p.cat.name= :cat")
                .setParameter("cat", category)
                .getResultList();
    }

    //Поиск по категории и максимально допустимой цене
    private List getByCatAndPriceMax(String category, Integer priceMax){
        return entityManager.createQuery("select p from Prod p where p.cat.name= :cat and p.price<= :priceMax")
                .setParameter("cat", category)
                .setParameter("priceMax", priceMax)
                .getResultList();
    }

    //Поиск по категории и минимально допустимой цене
    private List getByCatAndPriceMin(String category, Integer priceMin){
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
    private List getByCatAndName(String category, String name){
        return entityManager.createQuery("select p from Prod p where p.cat.name= :cat and p.name= :name")
                .setParameter("cat", category)
                .setParameter("name", name)
                .getResultList();
    }

    //Поиск по категории, наименованию и максимально допустимой цене
    private List getByCatAndNameAndPriceMax(String category, String name, Integer priceMax){
        return entityManager.createQuery("select p from Prod p where p.cat.name= :cat and p.name= :name and p.price<= :priceMax")
                .setParameter("cat", category)
                .setParameter("name", name)
                .setParameter("priceMax", priceMax)
                .getResultList();
    }

    //Поиск по категории, наименованию и минимально допустимой цене
    private List getByCatAndNameAndPriceMin(String category, String name, Integer priceMin){
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