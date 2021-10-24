package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {

    ArrayList<String> groceryList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        //gets session
        HttpSession session = request.getSession();

        //sets local vars to session values
        String username = (String) session.getAttribute("sessionUser");
        ArrayList<String> sessionList = (ArrayList<String>) session.getAttribute("sessionList");

        //checks if username is null if true forces register
        if (username == null || username.equals("")) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);  //loads shopping list page
            return;
        }

        request.setAttribute("username", username); //sets usernane to display on web page
        request.setAttribute("groceryList", sessionList); //sets list to display

        if (request.getQueryString() != null) { //checking if user selected to logout
            if (request.getQueryString().equals("logout")) {
                groceryList.clear();//clears array
                session.invalidate(); //destroying session
                request.setAttribute("message", "You have sucessfully logged out."); //setting message to notify user of logout
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response); //loading login page
                return;

            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);  //loads Shopping list page
        return;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        HttpSession session = request.getSession(); //getting session

        String action = request.getParameter("action"); //grabs value of action

//        System.out.println(action);
        if (action.equals("register")) { //checks value of action
            String username = request.getParameter("username"); //fills login page input boxes

            if (username == null || username.equals("")) { //checking user entered username and pass
                request.setAttribute("message", "Please enter your username");
                //display form again
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                //after reload stop rest of execution
                return;
            }

            request.setAttribute("username", username);//setting values of username
            session.setAttribute("sessionUser", username); //storing username in session

        } else if (action.equals("add")) {//checks if action is add
            String username = (String) session.getAttribute("sessionUser");//gets values of username in session
            request.setAttribute("username", username);//setting value of username

            String addItem = request.getParameter("item");//gets values user entered

//            System.out.println("additem equals: " + addItem);
            if (addItem != null && !addItem.equals("")) {//makes sure user entered a value

                groceryList.add(addItem);//adds item to array
            }

            request.setAttribute("groceryList", groceryList);//displays list on page    
            session.setAttribute("sessionList", groceryList);//stores list in session

        } else if (action.equals("delete")) {
            String username = (String) session.getAttribute("sessionUser"); //gets value of username from session
            request.setAttribute("username", username);//setting value of username

            String selected = request.getParameter("selectedItem");//grabs value of selected radio button

            if (selected != null && !selected.equals("")) { //checks if a radio butto was selected
                groceryList.remove(selected);//removes selected value from array
            }

            request.setAttribute("groceryList", groceryList);//Dispays list on page 
            session.setAttribute("sessionList", groceryList);//Stores array in session 

        }

        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response); //loads shopping list page
        return;

    }

}
