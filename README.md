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

