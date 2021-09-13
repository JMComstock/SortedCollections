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
        System.out.println(jasonsBasket);

        sellItem(jasonsBasket, "juice", 4);
        sellItem(jasonsBasket, "cup", 12);
        sellItem(jasonsBasket, "bread", 1);
        System.out.println(jasonsBasket);
        System.out.println(stockList);

        stockList.Items().get("pumpkin").adjustStock(2000);
        stockList.get("pumpkin").adjustStock(-1000);
        System.out.println(stockList);
        for(Map.Entry<String, Double> price: stockList.PriceList().entrySet()) {
            System.out.println(price.getKey() + " costs " + price.getValue());
        }
     }

    public static int sellItem(Basket basket, String item, int quantity) {
        // retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(stockList.sellStock(item, quantity) != 0) {
            basket.addToBasket(stockItem, quantity);
            return quantity;
        }
        return 0;
    }
}
