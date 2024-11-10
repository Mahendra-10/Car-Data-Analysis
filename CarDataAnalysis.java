import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 * Car Data Analysis: Project 3 Starter Code.
 *
 * Gateway Programming: Java
 * Johns Hopkins University
 * Spring 2023
 * 500.112 Gateway Computing: Java Spring 2023
 * Mahendra Shahi, mshahi2@jhu.edu, 5CE48B, 3/9/2023.
 * The project lets the user pick a car of their choice. 
 * Users can get a car based on various Mileage, Price
 * Brand.They can get a car from the main data 
 * or they pick a car from the specific brand.
 * The length of the word can be either 4 or 6. 
 */
public class CarDataAnalysis {

   // menu options
   static final int BRAND_QUERY = 1;
   static final int TWO_HIGHEST_PRICES_QUERY = 2;
   static final int RANGE_QUERY = 3;
   static final int BEST_VALUE_QUERY = 4;
   static final int QUIT = 5;

   // column index constants for car data file
   static final int INDEX = 0;
   static final int BRAND = 2;
   static final int YEAR = 4;
   static final int MILEAGE = 6;
   static final int PRICE = 1;


   /**
    * Print the program menu to the console.
    */
   public static void printMenu() {
   
      System.out.printf("[%d]: Average price of brand.\n", BRAND_QUERY);
      System.out.printf("[%d]: Two highest prices.\n",
             TWO_HIGHEST_PRICES_QUERY);
      System.out.printf("[%d]: Average price in year and mileage range.\n",
             RANGE_QUERY);
      System.out.printf("[%d]: Best value.\n", BEST_VALUE_QUERY);
      System.out.printf("[%d]: Quit.\n", QUIT);
      System.out.print("Please select an option: ");
      
   
   }
  

   /**
    * Counts the number of lines in a given plain-text file.
    * @param filename The file whose lines are to be counted.
    * @return the number of lines in the file.
    * @throws IOException
    */
   public static int countFileLines(String filename)
                                    throws IOException {
     
      FileInputStream fileInStream = 
         new FileInputStream(filename);                               
   
      Scanner inFs = new Scanner(fileInStream);    
      int count = 0;
      while (inFs.hasNextLine()) {
         inFs.nextLine();
         count++;
              
      }
      return count;
   
   }
   
    /**
   * This method asks for the brand name from the user
   * then asks users for brand csv file.
   * The method then creates a separate file with
   * all the categorys available. 
   * @param keyboard A player's guess or the empty string.
   * @param indexes this is to iterate through the database.
   * @param mileages array
   * @param prices array
   * @param years array
   * @param brands array
   * 
   */
   public static void brandQuery(Scanner keyboard, int[] indexes, 
           int[] prices, 
             String[] brands, int[] years,
               double[] mileages)throws FileNotFoundException {
   
    
      System.out.println("Please enter a card brand: ");
      String carBrand = keyboard.next();
      int count = 0;
      double totalPrice = 0;
      double price = 0;
      
      System.out.println("Please enter an output filename: ");
   
      String fileName = keyboard.next();
   
      FileOutputStream fileStream = new FileOutputStream(fileName);
           
      PrintWriter outFS = new PrintWriter(fileStream);
   
      for (int i = 0; i < indexes.length; ++i) {
         if (carBrand.equalsIgnoreCase(brands[i])) {
            price = price + prices[i];
         
         
            outFS.print(indexes[i] + ", ");
         
            outFS.print(brands[i] + ", ");
            outFS.print(years[i] + ", ");
            outFS.print(mileages[i] + ", ");
            outFS.print(prices[i]);
            outFS.println();
            
            count = count + 1;
            totalPrice = price / count;
         
            
         }
      }
          
      
      System.out.println("There are " + count + " matching entries for brand " 
         + carBrand  + " with an average price of");
      System.out.printf("$%.2f", totalPrice);
   
   
   
      outFS.close();
   }
   
   
   /**
   * This prints the first and 2nd 
   * highest car price in the data.
   * @param indexes the number of character in the correct word.
   * @param price array
   * @keyboard readers the userinput
   */
   public static void highestPrice(int[] price, int[] indexes) {
   
      double firstMax = price[0];
      double secondMax = price[0];
      for (int i = 0; i < indexes.length; ++i) {
         if (firstMax < price[i]) {
            firstMax = price[i];
         }
         else if (price[i] < firstMax && secondMax < price[i]) {
            secondMax = price[i];
         }
      
         
      
      
      }
      System.out.println("The two highest prices are $" + firstMax 
            + " and $" + secondMax);
   
   
   }
  
   /**
   * Prints the amount of entries available based on the user year bounds
   * and mileages bounds.  
   * @param keyboard A player's guess or the empty string.
   * @param indexes the number of character in the correct word.
   * @param mileages array
   * @param prices array
   * @param years array
   * 
   */
   public static void avgPricenMil(Scanner keyboard, int[] indexes, 
        int[] prices, double[] mileages, int[] years) {
   
      double price = 0;
      double avgPrice = 0;
      
      System.out.println("Please enter the year lower bound:");
      int lowerBound = keyboard.nextInt();
      
      System.out.println("Please enter the year upper bound:");
      int higherBound = keyboard.nextInt();
      
      System.out.println("Please enter the mileage lower bound: ");
      int lowerMileage = keyboard.nextInt();
      
      System.out.println("Please enter the mileage upper bound: ");
      int higherMileage = keyboard.nextInt();
      int entries = 0;
   
      for (int i = 0; i < indexes.length; ++i) {
      
      
         if (years[i] >= lowerBound && years[i] <= higherBound 
                          && mileages[i] >= lowerMileage && mileages[i]
                                                <= higherMileage) {
            ++entries;
            price = price + prices[i];
            avgPrice = price / entries;
         
          
         }
       
      
      }
      
      System.out.print("There are " + entries + 
         " matching entries for year range [" 
         + lowerBound + "," + higherBound + "]"  
         + "and mileage range [" + lowerMileage + "," 
         + higherMileage + "] with an average price of "); 
   
      System.out.printf("$%.2f", avgPrice);
   
   }
   
   
  /**
   * This method prints the brand and it's year 
   * which has the best value
   * based on the user input of lower mileage and lower price.
   * @param keyboard gets the user response.
   * @param indexes loops through the array.
   * @param mileages array
   * @param prices array
   * @param years araay
   * @param brands array
   * 
   */
   public static void bestvalue(Scanner keyboard, int[] indexes, 
           double[] mileages, int[] prices, 
          int[] years, String[]brands) {
   
   
          
      System.out.println("Please enter lower mileage threshold: ");
      double lowerMileage = keyboard.nextDouble();
   
      System.out.println("Please enter lower price threshold: ");
      int lowerPrice = keyboard.nextInt();
      
      
      int indexI = 0;
      double maxValue = 0;
      
      for (int i = 0;  i < indexes.length; ++i) {
         if (mileages [i] > lowerMileage && lowerPrice < prices[i]) {
            double bestValue = formula(i, years, mileages, prices);
           
              
            if (maxValue < bestValue) {
               maxValue = bestValue;
               indexI = i;
            
               
            }
                
                              
         }
        
         
      }
      
      System.out.println("The best-value entry with more than " + lowerMileage +
         " and a price higher than $" + lowerPrice + " is a " 
         + years[indexI] + " " + brands[indexI] + " with " 
         + mileages[indexI] + " miles for a price of $" 
                        + prices[indexI] + ".");
   
         
      
      
   }
      
   
   /**
    * This method provides the formula 
    * for finding the best value
    * in the list.
    * @param i get the value at this index
    * @param years the years array
    * @param mileages the mileages array
    * @param prices the prices array
    * @return It returns the formula for best value .
    * 
    * 
    */
   public static double formula(int i, int years[], 
                double mileages[], int prices[]) {
   
      double valueFormula = years[i] - mileages[i]
                   / 13500.0 - prices[i] / 1900.0;
   
      return valueFormula;
   
   }  
   
  /**
    * Drive the Car Data Analysis program.
    * @param args This program does not take commandline arguments.
    * @throws IOException
    */
   public static void main(String[] args) throws IOException {
   
      // output purpose
      System.out.println("Welcome to the car dataset analysis program.");
   
      // get input filename (e.g. "USA_cars_datasets.csv")
      System.out.print("Please enter input csv filename: ");
      Scanner keyboard = new Scanner(System.in);
      String filename = keyboard.nextLine();
   
      // count the number of rows in the file (ignore headers line)
      int rowCount = countFileLines(filename) - 1;
      System.out.println("File has " + rowCount + " entries.");
      System.out.println();
   
      int[] indexes = new int[rowCount];
      int[] prices = new int[rowCount];
      String[]brands = new String[rowCount]; 
      int[] years = new int[rowCount];
      double[] mileages = new double[rowCount];
      
      FileInputStream fileInputStream = new FileInputStream(filename);
      Scanner fileScanner = new Scanner(fileInputStream);
      
      //Discard the header line
      
      //put this in helper method.
      fileScanner.nextLine();
      int nextIndex = 0;
      
      while (fileScanner.hasNextLine()) {
      
         String line = fileScanner.nextLine();
      
         String[] lineParts = line.split(",");
      
         String indexString = lineParts[INDEX];
         int index = Integer.parseInt(indexString);
         indexes[nextIndex] = index;
      
         String priceString = lineParts[PRICE];
         int price = Integer.parseInt(priceString);
         prices[nextIndex] = price;
      
         String brand = lineParts[BRAND];
         brands[nextIndex] = brand;
         
         String yearString = lineParts[YEAR];
         int year = Integer.parseInt(yearString);
         years[nextIndex] = year;
         
         String mileageString = lineParts[MILEAGE];
         double mileage = Double.parseDouble(mileageString);
         mileages[nextIndex] = mileage;
         
         nextIndex++;
      
      
      }
   
   
   
     
      // while the user doesn't choose to quit...
      int option = 0;
      while (option != QUIT) {
      
         // display the menu and get an option
         printMenu();
         option = keyboard.nextInt();
      
         switch (option) {
            case BRAND_QUERY:
               brandQuery(keyboard, indexes, prices, brands, years, mileages);
               break;
            case TWO_HIGHEST_PRICES_QUERY:
               highestPrice(prices, indexes);
               break;
            case RANGE_QUERY:
               avgPricenMil(keyboard, indexes, prices, mileages, years);
               break;
            case BEST_VALUE_QUERY:
               bestvalue(keyboard, indexes, mileages, prices, years, brands);
               break;
            case QUIT:
               System.out.println("Thank you for using the program!");
               break;
            default:
               System.out.println("Invalid option.");
         
         }
      
         // leave empty line for next printing of menu
         System.out.println();
         
      
      }
   
   }
  
  
  
   
   
}
   
 
   
   


