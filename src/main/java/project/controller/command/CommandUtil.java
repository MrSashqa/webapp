package project.controller.command;

import project.controller.wrapper.RequestWrapper;
import project.model.entity.Dish.DishType;
import project.model.entity.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandUtil {


    public static User retrieveUser(RequestWrapper request) {
        String login = request.getRequestParameter("login");
        String password = request.getRequestParameter("password");
        User user = new User();
        user.setLogin(login == null ? "" : login);
        user.setPassword(password == null ? "" : password);
        return user;
    }

    //TODO: what if in two threads in each command will call this method ??? is it threat safe??
    public static Client retrieveClient(RequestWrapper request) {
        String firstName = request.getRequestParameter("firstName");
        String lastName = request.getRequestParameter("lastName");
        String phoneNumber = request.getRequestParameter("phoneNumber");
        String email = request.getRequestParameter("email");
        Client client = new Client();
        client.setFirstName(firstName == null ? "" : firstName);
        client.setLastName(lastName == null ? "" : lastName);
        client.setEmail(email == null ? "" : email);
        client.setPhoneNumber(phoneNumber == null ? "" : phoneNumber);
        return client;
    }

    //TODO: what if user choose nothing??? it all will crash!!!! how to protect my self ???
    public static Dish retrieveDish(RequestWrapper requestWrapper) {

        String type = requestWrapper.getRequestParameter("dishType");
        String name = requestWrapper.getRequestParameter("name");
        String stringPrice = requestWrapper.getRequestParameter("price");

        DishType dishType = null;
        double price = -1;

        try {
            dishType = type == null ? dishType : DishType.valueOf(type);
            price = Double.valueOf(stringPrice);
        } catch (NumberFormatException e) {
            //TODO: log this ??
        } catch (IllegalArgumentException e) {
            //?????
        }
        Dish dish = new Dish();
        dish.setName(name == null ? "" : name);
        dish.setPrice(price);
        dish.setType(dishType);

        return dish;
    }

    public static int retrieveDishId(RequestWrapper requestWrapper) {
        int id = -1;
        try {
            id = Integer.valueOf(requestWrapper.getRequestParameter("id"));
        } catch (NumberFormatException e) {

        }
        return id;
    }


    public static Order retrieveOrder(RequestWrapper requestWrapper) {
        Order order = new Order();
        Map<Dish, Integer> content = new HashMap<>();

        String[] dishIds = requestWrapper.getParameterValues("dishId");
        String[] quantities = requestWrapper.getParameterValues("quantity");
        Client client = new Client(-1);
        try {
            Map<Dish, Integer> retrieved = new HashMap<Dish, Integer>();
            User user = (User) requestWrapper.getAttributeFromSession("user");
            int clientID = user.getId();
            client.setId(clientID);
            for (int i = 0; i < dishIds.length; i++) {
                int dishId = Integer.parseInt(dishIds[i]);
                int quantity = Integer.parseInt(quantities[i]);
                retrieved.put(new Dish(dishId), quantity);
            }
            content.putAll(retrieved);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            // TODO : ???
        }
        order.setOrderContent(content);
        order.setClient(client);
        return order;
    }
}
