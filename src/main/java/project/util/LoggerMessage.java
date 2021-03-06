package project.util;


public class LoggerMessage {
    private LoggerMessage() {
    }

    public static final String ERROR_PREAMBLE = "Error occurred trying to: ";
    public static final String SUCCESS_PREAMBLE = "Successful: ";
    public static final String SUCCESS_CONNECTION_FACTORY_INIT = SUCCESS_PREAMBLE + "init connection factory";
    public static final String ERROR_CONNECTION_FACTORY_INIT = ERROR_PREAMBLE + "init connection factory";
    public static final String ERROR_CONNECTION = ERROR_PREAMBLE + "create connection ";
    public static final String SUCCESS_CONNECTION = SUCCESS_PREAMBLE + "create connection ";
    public static final String ERROR_BEGIN_TRANSACTION = ERROR_PREAMBLE + "begin transaction";
    public static final String ERROR_ROLLBACK_TRANSACTION = ERROR_PREAMBLE + "rollback transaction";
    public static final String ERROR_CLOSE_CONNECTION = ERROR_PREAMBLE + "close connection";
    public static final String ERROR_COMMIT_TRANSACTION = ERROR_PREAMBLE + "commit transaction";
    public static final String ERROR_ENCRYPT = ERROR_PREAMBLE + "get encrypting algorithm";
    public static final String ERROR_INSERT_DISH = ERROR_PREAMBLE + "insert dish";
    public static final String SUCCESS_INSERT_DISH = SUCCESS_PREAMBLE + "insert dish: ";
    public static final String SUCCESS_DELETE_DISH = SUCCESS_PREAMBLE + "delete dish. Id: ";
    public static final String ERROR_DELETE_DISH = ERROR_PREAMBLE + "delete dish.";
    public static final String ERROR_GET_MENU = ERROR_PREAMBLE + "get menu";
    public static final String SUCCESS_GET_MENU = SUCCESS_PREAMBLE + "get menu";
    public static final String SUCCESS_INIT_MENU_DISH_DAO = SUCCESS_PREAMBLE + " init of MenuDishDAOOImpl";
    public static final String ERROR_INIT_MENU_DISH_DAO = ERROR_PREAMBLE + "init of MenuDishDAOImpl";
    public static final String SUCCESS_INIT_CLIENT_DAO = SUCCESS_PREAMBLE + "init of ClientDAOImpl";
    public static final String ERROR_INIT_CLIENT_DAO = ERROR_PREAMBLE + "init of ClientDAOImpl";
    public static final String ERROR_GET_CLIENT = ERROR_PREAMBLE + "get client by id";
    public static final String SUCCESS_GET_CLIENT = SUCCESS_PREAMBLE + "get client by id";
    public static final String SUCCESS_INSERT_USER = SUCCESS_PREAMBLE + "insert new user. Id: ";
    public static final String ERROR_INSERT_USER = ERROR_PREAMBLE + "insert user";
    public static final String SUCCESS_INIT = SUCCESS_PREAMBLE + "init";
    public static final String ERROR_INIT = ERROR_PREAMBLE + "init";
    public static final String SUCCESS_GET_BY_LOGIN = SUCCESS_PREAMBLE + "get by login.";
    public static final String ERROR_GET_BY_LOGIN = ERROR_PREAMBLE + "get by login.";
    public static final String ERROR_INSERT_CLIENT = ERROR_PREAMBLE + "insert client:";
    public static final String SUCCESS_INSERT_CLIENT = SUCCESS_PREAMBLE + "insert client";
    public static final String SUCCESS_INSERT_ORDER = SUCCESS_PREAMBLE + "insert order: ";
    public static final String ERROR_INSERT_ORDER = ERROR_PREAMBLE + "insert order";
    public static final String ERROR_INSERT_ORDER_DETAILS = ERROR_PREAMBLE + "insert order details";
    public static final String SUCCESS_INSERT_ORDER_DETAILS = SUCCESS_PREAMBLE + "insert order details";
    public static final String SUCCESS_UPDATE_ORDER_STATUS = SUCCESS_PREAMBLE + "update order status";
    public static final String ERROR_UPDATE_ORDER_STATUS = ERROR_PREAMBLE + "update order status";
    public static final String SUCCESS_GET_ORDER_BY_STATUS = "";
    public static final String ERROR_GET_ORDER_BY_STATUS = "";
    public static final String ERROR_GET_ORDERS_BY_STATUS = "";
    public static final String ERROR_INSERT_INVOICE = "";
    public static final String ERROR_UPDATE_INVOICE = "";
    public static final String ERROR_GET_ORDER = "";
    public static final String ERROR_GET_INVOICE = "";
    public static final String ERROR_GET_INVOICE_BY_STATUS = "";
    public static final String SUCCESS_INIT_INVOICE_DAO = "";
    public static final String ERROR_INIT_INVOICE_DAO = "";
    public static final String ERROR_ORDER_DAO_INIT = "";
    public static final String SUCCESS_ORDER_DAO_INIT = "";
    public static final String ERROR_GET_DISHES_BY_ORDER = "";
}
