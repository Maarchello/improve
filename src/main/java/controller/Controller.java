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
        String priceFrom = req.getParameter("priceFrom").trim();
        String priceTo = req.getParameter("priceTo").trim();
        Integer priceMin=checkPrice(priceFrom);
        Integer priceMax=checkPrice(priceTo);

        try{
            if(category.isEmpty() && name.isEmpty() && priceMin==null && priceMax==null){
                req.setAttribute("havenot", "Введите хотя бы один критерий");
            }else {
                List<Prod> list = search.get(category, name, priceMin, priceMax);
                req.setAttribute("list", list);
            }
        }finally {
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
