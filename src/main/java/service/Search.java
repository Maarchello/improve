package service;


import model.Prod;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Search {
    public EntityManager entityManager = Persistence.createEntityManagerFactory("FIG").createEntityManager();

    public List get(String category, String name, Integer priceMin, Integer priceMax){
        if(category.isEmpty() && name.isEmpty() && priceMin==null && priceMax!=null){
            return getByPriceMax(priceMax);
        }else if (category.isEmpty() && name.isEmpty() && priceMin!=null && priceMax==null){
            return getByPriceMin(priceMin);
        }else if (category.isEmpty() && name.isEmpty() && priceMin!=null && priceMax!=null){
            return getByPrice(priceMin, priceMax);
        }else if (category.isEmpty() && !name.isEmpty() && priceMin==null && priceMax==null){
            return getByName(name);
        }else if (category.isEmpty() && !name.isEmpty() && priceMin==null && priceMax!=null){
            return getByNameAndPriceMax(name, priceMax);
        }else if (category.isEmpty() && !name.isEmpty() && priceMin!=null && priceMax==null){
            return getByNameAndPriceMin(name, priceMin);
        }else if (category.isEmpty() && !name.isEmpty() && priceMin!=null && priceMax!=null){
            return getByNameAndPrice(name, priceMin, priceMax);
        }else if (!category.isEmpty() && name.isEmpty() && priceMin==null && priceMax==null){
            return getByCat(category);
        }else if (!category.isEmpty() && name.isEmpty() && priceMin==null && priceMax!=null){
            return getByCatAndPriceMax(category,priceMax);
        }else if (!category.isEmpty() && name.isEmpty() && priceMin!=null && priceMax==null){
            return getByCatAndPriceMin(category,priceMin);
        }else if (!category.isEmpty() && name.isEmpty() && priceMin!=null && priceMax!=null){
            return getByCatAndPrice(category,priceMin,priceMax);
        }else if (!category.isEmpty() && !name.isEmpty() && priceMin==null && priceMax==null){
            return getByCatAndName(category,name);
        }else if (!category.isEmpty() && !name.isEmpty() && priceMin==null && priceMax!=null){
            return getByCatAndNameAndPriceMax(category,name,priceMax);
        }else if (!category.isEmpty() && !name.isEmpty() && priceMin!=null && priceMax==null){
            return getByCatAndNameAndPriceMin(category,name,priceMin);
        }else if (!category.isEmpty() && !name.isEmpty() && priceMin!=null && priceMax!=null) {
            return getByAllValues(category, name, priceMin, priceMax);
        }
        return null;
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

    //Поиск по заданным критериям цен
    private List getByPrice(Integer priceFrom, Integer priceTo){
        return entityManager.createQuery("select e from Prod e where e.price between :priceFrom and :priceTo")
                .setParameter("priceFrom", priceFrom)
                .setParameter("priceTo", priceTo)
                .getResultList();
    }

    //Поиск по наименованию
    private List getByName(String name){
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
    private List getByNameAndPrice(String name, Integer priceMin, Integer priceMax){
        return entityManager.createQuery("select e from Prod e where e.name= :name and e.price between :priceMin and :priceMax")
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
    private List getByCatAndPrice(String category, Integer priceMin, Integer priceMax){
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
    private List getByAllValues(String category, String name, Integer priceMin, Integer priceMax){
        return entityManager.createQuery("select p from Prod p where p.cat.id= :cat and p.name= :name and p.price between :priceMin and :priceMax")
                .setParameter("cat", category)
                .setParameter("name", name)
                .setParameter("priceMin", priceMin)
                .setParameter("priceMax", priceMax)
                .getResultList();
    }

}