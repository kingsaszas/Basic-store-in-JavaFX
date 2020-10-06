package sample;

import java.util.ArrayList;
import java.util.List;

//KLASA DO TWORZENIA MAGAZYNOW I PRODUKTOW
public class DataGenerator {
    public List<Stores> listWithStores;

    public DataGenerator(){
        listWithStores = new ArrayList<>();
    }

    List<Items> wszystkieprodukty = new ArrayList<>(); //tu robie liste na ktora wrzucam listy z kazdego magazynu
    public void addItemsToStore() {

        Stores s1 = new Stores("Store One", "Los Angeles", 150);
        Stores s2 = new Stores("Store Two", "California", 200);
        Stores s3 = new Stores("Store Three", "Los Santos", 55);

        listWithStores.add(s1);
        listWithStores.add(s2);
        listWithStores.add(s3);


        s1.addProduct(new Items("blanket", 55.49, 10, "Store One"));
        s2.addProduct(new Items("chair", 20, 40, "Store Two"));
        s1.addProduct(new Items("wardrobe", 290.90, 10, "Store One"));
        s3.addProduct(new Items("bed", 499.99, 5, "Store Three"));
        s1.addProduct(new Items("table", 69.90, 3, "Store One"));
        s2.addProduct(new Items("carpet", 222, 12, "Store Two"));
        s3.addProduct(new Items("table big", 120, 2, "Store Three"));
        s3.addProduct(new Items("bookshelf",30,5,"Store Three"));

        for(Items cos : s1.getList())
            wszystkieprodukty.add(cos);
        for(Items cos : s2.getList())
            wszystkieprodukty.add(cos);
        for(Items cos : s3.getList())
            wszystkieprodukty.add(cos);

        //s1.summary();
    }

    public List<Items> Wszystkieprodukty(){
        return wszystkieprodukty;
    }           //zwraca liste z wszystkimi produktami

    public List<Stores> AllStores(){
        return listWithStores;
    }


}
