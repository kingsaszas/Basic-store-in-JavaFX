package sample;

import sample.Items;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Stores {

    private String nameOfStore;
    private String city;
    private double maxCapacity;
    private List<Items> itemStore;
    private double currentCapacity;
    private double fillInPercentage;
    
    String str;

    public List<Items> getLista() {
        return itemStore;
    }

    public Stores(String name, String city, double maxcap){
        this.nameOfStore = name;
        this.city = city;
        this.maxCapacity = maxcap;
        itemStore =  new ArrayList<Items>();
        currentCapacity = 0;
    }

        public void addProduct(Items it){
            if((currentCapacity + it.getAmountOfItem()) <= maxCapacity){
                itemStore.add(it);
                currentCapacity += it.getAmountOfItem();

                System.out.println("Item added: "+ it.getNameItem());
            }
            else{
                System.out.println("There is no more space for this item (mass: "+it.getAmountOfItem()+ ")");
                System.out.println("Status of the store: |maxCapacity: "+ maxCapacity + " |currentCapacity: "+ currentCapacity);
            }
        }

        public void removeProduct(Items it){
            currentCapacity -= it.getAmountOfItem();
            itemStore.remove(it);
            System.out.println("Removed item: "+it.getNameItem()+". Current capacity of store: "+ currentCapacity);

        }


        public String searchByProduct(String nameProdukt){
            System.out.println("\nShow items with name: "+ nameProdukt);
            for(Items rzecz: itemStore) {
                if (rzecz.getNameItem() == nameProdukt) {
                    str = rzecz.summary();
                }
            }
            return str;
        }


        public String summary(){
            fillInPercentage = ((currentCapacity/maxCapacity) * 100);
            fillInPercentage = BigDecimal.valueOf(fillInPercentage)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();

            System.out.println(" ** Information about store: ");
            System.out.println("Name of Store: "+ nameOfStore);
            System.out.println("City: " + city);
            System.out.println("Max Capacity : "+maxCapacity+" kg");
            System.out.println("Fill in percentage: "+ fillInPercentage+" %");

            return "Information about store:\nName of Store: "+ nameOfStore + "\nCity: " + this.city + "\nmax capacity: "
                    + this.maxCapacity + "\nFill in percentage: "+ this.fillInPercentage+ "%";
        }

        public double getCurrentCapacity(){
            return currentCapacity;
        }
        public String getNameOfStore(){ return nameOfStore; }
        public String getCity(){ return city; }
        public double getFillInPercentage() { return fillInPercentage; }

        public double setFillInPercentage(Items it, double liczba){         //przeszukuje liste z rzeczami danego magazynu, jak znajdzie, to zmniejsza o podaną ilość zapełniene magazynu
        for(Items cos: itemStore){
            if(cos.getNameItem().equals(it.getNameItem())){
                this.currentCapacity -= liczba;
                this.fillInPercentage = (((this.currentCapacity)/this.maxCapacity) * 100);
                System.out.println("zapelnienie % : "+ fillInPercentage + " obecne zapelnienie magazynu '"+nameOfStore+"': "+ currentCapacity);
            }
        }
            return this.fillInPercentage;
        }

}
