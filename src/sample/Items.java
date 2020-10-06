package sample;

public class Items {
        private String nameItem, nameStore;
        private int amountOfItem;
        private double price;

        public Items(){
        }

        public Items(String name, double pr, int num, String nS){
            this.nameItem = name;
            this.price = pr;
            this.amountOfItem = num;
            this.nameStore = nS;
            //summary();
        }
        public String summary(){
            System.out.println(" *** Information about item: " );
            System.out.println("Name: "+ Items.this.nameItem);
            System.out.println("Price: "+ Items.this.price);
            System.out.println("Amount of item: "+ Items.this.amountOfItem);
            System.out.println("Store: "+ Items.this.nameStore);

            return "Name: "+this.nameItem + "\nPrice: "+ this.price + "\nQuantity: "
                    + this.amountOfItem + "\nStore: " +this.nameStore;
        }

        public String getNameItem(){
        return nameItem;
    }
        public double getPrice() { return price; }
        public int getAmountOfItem() { return amountOfItem;}
        public String getNameStore(){return nameStore;}

        public void setAmountIfItem(int am){
            this.amountOfItem = am;
        }

}
