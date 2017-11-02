<h1> How to run the project </h1>

<ul>
<li> Clone the project into your machine using the cmd git clone https://github.com/Preethas/DiscountCalculator.git   </li>
<li> This project uses maven to build . Install Apache Maven 3.5 on your machine </li>
<li> 'cd' to directory com.socgen on a terminal </li>
<li> Run mvn clean install </li>
<li> The jar file for the project will be built </li>
<li> Run mvn exec:java -Dfilepath=[path to inventory file] </li>
<li> Sample - mvn exec:java -Dfilepath = "/Users/srinivasan/inventory.txt"  </li>

</ul>

<h1> To run the tests </h1>

<ul>
<li> On the terminal type mvn test </li>
</ul>

<h1> Assumptions </h1>

<p> 
  
  The input of an inventory item is like 1,Arrow,Shirts,800 . Shirts is a category but its hierarchy is unknown.
  It could be present under Men's wear or Women's wear or even under several sub categories. Here we assume that
  Category is unique.
    
  
</p>  

<p>
  The inventory file is loaded as an external input whereas the brands and categories are loaded internally
  by the program . So the program checks if the brands /categories mentioned in the file are present in the 
  default data loaded by the program.
</p>  


<h1>  Design </h1>

<p>
  <b>  Data Store </b>  :  It is the model of the application and is populated by the DataLoader.
</p>  

<p>
  <b>  IDataLoader </b>  :  It defines the interface that is used to load brands , categories and inventory into the DataStore
</p> 

<p>
  <b>  DefaultDataLoader </b> : The class loads some predefined data for brands and categories . Ideally this
  data will come from a DB . We can write different implementation of IDataLoader depending on where we want to load the data 
  from. Example - JsonDataLoader , SqlDataLoader
</p>  





