package com.company;

import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 1.12, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.49, 10);
        stockList.addStock(temp);

        temp = new StockItem("pumpkin", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.45, 7);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 99.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 9.99, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        for(String s : stockList.Items().keySet()) {
            System.out.println(s);
        }

        Basket jasonsBasket = new Basket("Jason");

        sellItem(jasonsBasket, "pumpkin", 1);
        System.out.println(jasonsBasket);

        sellItem(jasonsBasket, "pumpkin", 1);
        System.out.println(jasonsBasket);

        sellItem(jasonsBasket, "pumpkin", 1);

        sellItem(jasonsBasket, "spanner", 5);
//        System.out.println(jasonsBasket);

        sellItem(jasonsBasket, "juice", 4);
        sellItem(jasonsBasket, "cup", 12);
        sellItem(jasonsBasket, "bread", 1);
//        System.out.println(jasonsBasket);
//        System.out.println(stockList);

        Basket basket = new Basket("customer");
        sellItem(basket,"cup", 100);
        sellItem(basket,"juice", 5);
        removeItem(basket,"cup", 1);
        System.out.println(basket);

        removeItem(jasonsBasket, "pumpkin", 1);
        removeItem(jasonsBasket, "cup", 9);
        removeItem(jasonsBasket, "pumpkin", 1);
        System.out.println("pumpkins removed: " + removeItem(jasonsBasket, "pumpkin", 1));  // should not remove item

        System.out.println(jasonsBasket);

        removeItem(jasonsBasket, "bread", 1);
        removeItem(jasonsBasket, "cup", 3);
        removeItem(jasonsBasket, "juice", 4);
        removeItem(jasonsBasket, "cup", 3);
        System.out.println(jasonsBasket);

        System.out.println("\nDisplay stock list before and after checkout");
        System.out.println(basket);
        System.out.println(stockList);
        checkOut(basket);
        System.out.println(basket);
        System.out.println(stockList);

        StockItem pumpkin = stockList.Items().get("pumpkin");
        if(pumpkin != null) {
            pumpkin.adjustStock(2000);
        }
        if(pumpkin != null) {
            stockList.get("pumpkin").adjustStock(-1000);
        }

        System.out.println(stockList);
//        for(Map.Entry<String, Double> price: stockList.PriceList().entrySet()) {
//            System.out.println(price.getKey() + " costs " + price.getValue());
//        }'

        checkOut(jasonsBasket);
        System.out.println(jasonsBasket);
     }

    public static int sellItem(Basket basket, String item, int quantity) {
        // retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(stockList.reserveStock(item, quantity) != 0) {
            return basket.addToBasket(stockItem, quantity);
        }
        return 0;
    }

    public static int removeItem(Basket basket, String item, int quantity) {
        // retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(basket.removeFromBasket(stockItem, quantity) == quantity) {
            return stockList.unreserveStock(item, quantity);
        }
        return 0;
    }

    public static void checkOut(Basket basket) {
        for(Map.Entry<StockItem, Integer> item : basket.Items().entrySet()) {
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }
}
