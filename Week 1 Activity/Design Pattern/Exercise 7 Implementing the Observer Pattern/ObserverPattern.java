import java.util.ArrayList;
import java.util.List;

public interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}
public class StockMarket implements Stock {
    private List<Observer> observers;
    private double stockPrice;

    public StockMarket() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockPrice);
        }
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObservers();
    }
}
public interface Observer {
    void update(double stockPrice);
}
public class MobileApp implements Observer {
    @Override
    public void update(double stockPrice) {
        System.out.println("Mobile app: Stock price updated to " + stockPrice);
    }
}

public class WebApp implements Observer {
    @Override
    public void update(double stockPrice) {
        System.out.println("Web app: Stock price updated to " + stockPrice);
    }
}
public class ObserverPattern {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();
        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        stockMarket.setStockPrice(100.0);
        stockMarket.setStockPrice(120.0);

        stockMarket.deregisterObserver(mobileApp);
        stockMarket.setStockPrice(150.0);
    }
}