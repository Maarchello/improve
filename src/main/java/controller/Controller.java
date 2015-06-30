package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Prod;
import service.Search;

@WebServlet("/controller")

public class Controller extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        Search search = new Search();
        String category = req.getParameter("category").trim();
        String name = req.getParameter("name").trim();
        String priceMin = req.getParameter("priceFrom").trim();
        String priceMax = req.getParameter("priceTo").trim();
        Integer priceFrom=checkPrice(priceMin);
        Integer priceTo=checkPrice(priceMax);


        if (category.isEmpty() && name.isEmpty() && priceFrom==null && priceTo==null){
            req.setAttribute("havenot", "Введите хотя бы один критерий для поиска");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            //Поиск по максимально допустимой цене
        }else if (category.isEmpty() && name.isEmpty() && priceFrom==null && priceTo!=null){
            List<Prod> list = search.getByPriceMax(priceTo);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            //Поиск по минимально допустимой цене
        }else if (category.isEmpty() && name.isEmpty() && priceFrom!=null && priceTo==null){
            List<Prod> list = search.getByPriceMin(priceFrom);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            //Поиск по заданным критериям цен
        }else if (category.isEmpty() && name.isEmpty() && priceFrom!=null && priceTo!=null){
            List<Prod> list = search.getByPrice(priceFrom, priceTo);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            //Поиск по наименованию
        }else if (category.isEmpty() && !name.isEmpty() && priceFrom==null && priceTo==null){
            List<Prod> list = search.getByName(name);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            //Поиск по наименованию и максимально допустимой цене
        }else if (category.isEmpty() && !name.isEmpty() && priceFrom==null && priceTo!=null){
            List<Prod> list = search.getByNameAndPriceMax(name, priceTo);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            //Поиск по наименованию и минимально допустимой цене
        }else if (category.isEmpty() && !name.isEmpty() && priceFrom!=null && priceTo==null){
            List<Prod> list = search.getByNameAndPriceMin(name, priceFrom);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            //Поиск по наименованию и заданным критериям цен
        }else if (category.isEmpty() && !name.isEmpty() && priceFrom!=null && priceTo!=null){
            List<Prod> list = search.getByNameAndPrice(name, priceFrom, priceTo);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            //Поиск по категории
        }else if (!category.isEmpty() && name.isEmpty() && priceFrom==null && priceTo==null){
            List<Prod> list = search.getByCat(category);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            //Поиск по категории и максимально допустимой цене
        }else if (!category.isEmpty() && name.isEmpty() && priceFrom==null && priceTo!=null){
            List<Prod> list = search.getByCatAndPriceMax(category, priceTo);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            //Поиск по категории и минимально допустимой цене
        }else if (!category.isEmpty() && name.isEmpty() && priceFrom!=null && priceTo==null){
            List<Prod> list = search.getByCatAndPriceMin(category, priceFrom);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            //Поиск по категории и заданным критериям цен
        }else if (!category.isEmpty() && name.isEmpty() && priceFrom!=null && priceTo!=null){
            List<Prod> list = search.getByCatAndPrice(category, priceFrom, priceTo);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            //Поиск по категории и наименованию
        }else if (!category.isEmpty() && !name.isEmpty() && priceFrom==null && priceTo==null){
            List<Prod> list = search.getByCatAndName(category, name);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            //Поиск по категории, наименованию и максимально допустимой цене
        }else if (!category.isEmpty() && !name.isEmpty() && priceFrom==null && priceTo!=null){
            List<Prod> list = search.getByCatAndNameAndPriceMax(category, name, priceTo);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            //Поиск по категории, наименованию и минимально допустимой цене
        }else if (!category.isEmpty() && !name.isEmpty() && priceFrom!=null && priceTo==null){
            List<Prod> list = search.getByCatAndNameAndPriceMin(category,name,priceFrom);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            //Поиск по всем критериям
        }else if (!category.isEmpty() && !name.isEmpty() && priceFrom!=null && priceTo!=null){
            List<Prod> list = search.getByAllValues(category,name,priceFrom,priceTo);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
    }

    private Integer checkPrice(String value){
        Integer price;
        try{
            price= Integer.parseInt(value);
        }catch (NumberFormatException e){
            price = null;
        }
        return price;
    }
}
