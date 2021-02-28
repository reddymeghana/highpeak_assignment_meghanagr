import java.io.*;
import java.util.*;
class Goodie {
  String name;
  int price;

  public Goodie(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String toString() { 
      return this.name + ": " + this.price;
  }
}

public class Main {
  public static void main(String[] args) throws Exception {
    FileInputStream fis=new FileInputStream("input.txt"); //input file      
    Scanner sc=new Scanner(fis);
    int n = Integer.parseInt(sc.nextLine().split(": ")[1]);
    sc.nextLine(); sc.nextLine(); sc.nextLine();

    ArrayList<Goodie> goodies_items = new ArrayList<Goodie>();

    while(sc.hasNextLine())  
    {
      String current[] = sc.nextLine().split(": ");
      goodies_items.add(new Goodie(current[0], Integer.parseInt(current[1])));
    }
    sc.close();

    Collections.sort(goodies_items, new Comparator<Goodie>(){
      public int compare(Goodie a, Goodie b) { 
        return a.price - b.price; 
      } 
    });
    //calculating differnces between highest and lowest price
    int mindiff = goodies_items.get(goodies_items.size()-1).price;
    int minindex = 0;
    for(int i=0;i<goodies_items.size()-n+1;i++) {
      int diff = goodies_items.get(n+i-1).price-goodies_items.get(i).price;

      if(diff<=mindiff) {
        mindiff = diff;
        minindex = i;
      }
    }
    //output file
    FileWriter fw = new FileWriter("output.txt");
    fw.write("Here the goodies that are selected for distribution are:\n\n");
    for(int i=minindex;i<minindex + n; i++) {
      fw.write(goodies_items.get(i).toString() + "\n");
    }

    fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + mindiff);
	  fw.close();
  }
}
