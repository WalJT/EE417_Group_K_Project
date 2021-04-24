<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import = "java.io.*,java.util.*,java.sql.*"%>
<%@page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<!DOCTYPE html>
<html lang="en">
<script src="https://kit.fontawesome.com/0207abde13.js"crossorigin="anonymous"></script>
<script src="User_js.js"></script>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>

    <link rel="stylesheet" type="text/css" href="user_style.css" />
   <script src="https://kit.fontawesome.com/0207abde13.js"crossorigin="anonymous"></script>

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    
<header>
    <div class="container webName">
        <div class="logo">
            <a href="home.html">
                <!-- <img src ="img/icon.jpg" width= 150px;> -->
                <div class="logo-text">
                    <p class="logo-content1"> Electronics<span class="logo-content2">Shopping</span></p>
                    <!-- <p class="logo-content2">Shopping</p> -->
                </div>
            </a>
        </div>
      
    </div>  <!--container/ brand-->
    <div class="container menu-nav">
        <div class="menu">
            <ul>
                <li><a href="home.html">Home</a></li>
                <li><a href="about.html">About </a></li>
                <li><a href="cart.html"><i id="EmptyCart" class="fas fa-shopping-cart fa-lg"></i></a></li>
                <li style=><a style="display:inline" href="LogoutHandler">Logout<i style="display:inline" class="fas fa-sign-out-alt fa-sm"> </i></a></li>
            </ul>

        </div>
        <div class="searchBar">
            <form>
                <div class="form-group">
                    <input type="text" class="form-control" name="search" placeholder="Search Here">
                    <i class="fa fa-search search-icon" aria-hidden="true"></i>
                </div>
            </form>

        </div>
    </div>
</header>

<div class="container">
    <main>
      <div class="small-nav">    <!--small tag-->
          <ul>
              <li><a href="index.html">Home</a></li>
              <li> / </li>
              <li><a href="Admin.html">Admin</a></li>
          </ul>
      </div> 
     <div>
       
<sql:setDataSource var = "table" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://groupk.ccuoxucn9lr2.us-east-2.rds.amazonaws.com:3306/GroupKDB"
         user = "groupkdb"  password = "groupk123"/><!-- NEEDS TO BE UPDATED FOR ACTUAL DATABASE -->
 
      <sql:query dataSource = "${table}" var = "result">
      SELECT id,firstname,lastname,email,psd,phone,adress,city,zipcode FROM GroupK_Accounts</sql:query><!-- NEEDS TO BE UPDATED FOR ACTUAL DATABASE -->
 
 
 <fieldset>
 <h>User Database</h>
      <table border = "1" width = "100%">
         <tr>
            <th>ID</th>
            <th>FIRSTNAME</th>
            <th>LASTNAME</th>
            <th>EMAIL</th>
            <th>PSD</th>
            <th>ADDRESS</th>
            <th>CITY</th>
            <th>ZIPCODE</th>
            
         </tr>
        <c:forEach var = "row" items = "${result.rows}">
            <tr>
               <td><c:out value = "${row.id}"/></td>
               <td><c:out value = "${row.firstname}"/></td>
               <td><c:out value = "${row.lastname}"/></td>
               <td><c:out value = "${row.email}"/></td>
               <td><c:out value = "${row.psd}"/></td>
               <td><c:out value = "${row.phone}"/></td>
               <td><c:out value = "${row.adress}"/></td>
               <td><c:out value = "${row.city}"/></td>
               <td><c:out value = "${row.zipcode}"/></td>
            </tr>
         </c:forEach>
      </table>
</fieldset>
	
	<sql:setDataSource var = "table2" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://groupk.ccuoxucn9lr2.us-east-2.rds.amazonaws.com:3306/GroupKDB"
         user = "groupkdb"  password = "groupk123"/><!-- NEEDS TO BE UPDATED FOR ACTUAL DATABASE -->
 
      <sql:query dataSource = "${table2}" var = "result2">
      SELECT gid,gname,price,amount FROM Goods</sql:query><!-- NEEDS TO BE UPDATED FOR ACTUAL DATABASE -->
 
 
  <br>
 <fieldset>
 <h>Goods Stock</h>
      <table border = "1" width = "100%">
         <tr>
            <th>GID</th>
            <th>GNAME</th> 
            <th>PRICE</th>  
            <th>AMOUNT</th>            
         </tr>
        <c:forEach var = "row2" items = "${result2.rows}">
            <tr>
               <td><c:out value = "${row2.gid}"/></td>
               <td><c:out value = "${row2.gname}"/></td>
               <td><c:out value = "${row2.price}"/></td>
               <td><c:out value = "${row2.amount}"/></td>

            </tr>
         </c:forEach>
      </table>
    </fieldset>   
    
    
    
    
    
    <sql:setDataSource var = "table3" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://groupk.ccuoxucn9lr2.us-east-2.rds.amazonaws.com:3306/GroupKDB"
         user = "groupkdb"  password = "groupk123"/><!-- NEEDS TO BE UPDATED FOR ACTUAL DATABASE -->
 
      <sql:query dataSource = "${table3}" var = "result3">
      SELECT id,order_id,gid,gname,price,amount FROM order_item</sql:query><!-- NEEDS TO BE UPDATED FOR ACTUAL DATABASE -->
 
  <br>
 <fieldset>
 <h>Ordered Items</h>
      <table border = "1" width = "100%">
         <tr>
         	<th>ID</th>
         	<th>ORDER ID</th>           
            <th>GID</th>
            <th>GNAME</th> 
            <th>PRICE</th>  
            <th>AMOUNT</th> 
            
         </tr>
        <c:forEach var = "row3" items = "${result3.rows}">
            <tr>
               <td><c:out value = "${row3.id}"/></td>
               <td><c:out value = "${row3.order_id}"/></td>
               <td><c:out value = "${row3.gid}"/></td>
               <td><c:out value = "${row3.gname}"/></td>
               <td><c:out value = "${row3.price}"/></td>
               <td><c:out value = "${row3.amount}"/></td>
            </tr>
         </c:forEach>
      </table>
</fieldset>

 <sql:setDataSource var = "table4" driver = "com.mysql.jdbc.Driver"
        url = "jdbc:mysql://groupk.ccuoxucn9lr2.us-east-2.rds.amazonaws.com:3306/GroupKDB"
         user = "groupkdb"  password = "groupk123"/><!-- NEEDS TO BE UPDATED FOR ACTUAL DATABASE -->
 
      <sql:query dataSource = "${table4}" var = "result4">
      SELECT order_id,id,firstname,lastname,email,psd,phone,adress,city,zipcode FROM orders</sql:query><!-- NEEDS TO BE UPDATED FOR ACTUAL DATABASE -->
 
 <br>
 <fieldset>
 <h>Orders</h>
      <table border = "1" width = "100%">
         <tr>
         	<th>ORDER ID</th>
            <th>ID</th>
            <th>FIRSTNAME</th>
            <th>LASTNAME</th>
            <th>EMAIL</th>
            <th>PSD</th>
            <th>ADDRESS</th>
            <th>CITY</th>
            <th>ZIPCODE</th>
            
         </tr>
        <c:forEach var = "row4" items = "${result4.rows}">
            <tr>
               <td><c:out value = "${row4.order_id}"/></td>
               <td><c:out value = "${row4.id}"/></td>
               <td><c:out value = "${row4.firstname}"/></td>
               <td><c:out value = "${row4.lastname}"/></td>
               <td><c:out value = "${row4.email}"/></td>
               <td><c:out value = "${row4.psd}"/></td>
               <td><c:out value = "${row4.phone}"/></td>
               <td><c:out value = "${row4.adress}"/></td>
               <td><c:out value = "${row4.city}"/></td>
               <td><c:out value = "${row4.zipcode}"/></td>
            </tr>
         </c:forEach>
      </table>
</fieldset>
    
     </div>
</main>
</div> 
<footer class="footer-distributed">
    <div class="footer-left">

        <h3>ELECTRONICS<span>Shopping</span></h3>

        <p class="footer-links">
            <a href="#" class="link-1">Home</a>

            <a href="#">Blog</a>

            <a href="#">Pricing</a>

            <a href="#">About</a>

            <a href="#">Faq</a>

            <a href="#">Contact</a>
        </p>

        <p class="footer-company-name">Company Name </p>
    </div>

    <div class="footer-center">

        <div>
            <i class="fa fa-map-marker"></i>
            <p><span>dublin </span> IRELAND</p>
        </div>

        <div>
            <i class="fa fa-phone"></i>
            <p>+35312345678</p>
        </div>

        <div>
            <i class="fa fa-envelope"></i>
            <p><a href="mailto:support@company.com">support@company.com</a></p>
        </div>

    </div>

    <div class="footer-right">

        <p class="footer-company-about">
            <span>About the company</span> .
        </p>

        <div class="footer-icons">

            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-linkedin"></i></a>
            <a href="#"><i class="fa fa-github"></i></a>

        </div>

    </div>
</footer>

</body>
</html>
