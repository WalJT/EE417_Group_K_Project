<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import = "java.io.*,java.util.*,java.sql.*"%>
<%@page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<!DOCTYPE html>
<html lang="en">
    <script src="https://kit.fontawesome.com/0207abde13.js"crossorigin="anonymous"></script>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>

    <link rel="stylesheet" type="text/css" href="user_style.css" />
   

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<script src="User_js.js"></script>
<body onload='Profile()'>
    
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
                <li style="float: right" class="nav-item">
                        <a class="nav-link text-light"  href="cart.html"><i id="EmptyCart" class="fas fa-shopping-cart fa-lg"></i></a>
               </li>
            </ul>

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
              <li><a href="user.html">Profile</a></li>
          </ul>
      </div> 
      
      
      <sql:setDataSource var = "table" driver = "com.mysql.cj.jdbc.Driver"
        url = "jdbc:mysql://localhost:3306/Mysql"
         user = "root"  password = "root"/><!-- NEEDS TO BE UPDATED FOR ACTUAL DATABASE -->
 
 		<p id="var"></p>
      <sql:query dataSource = "${table}" var = "result">
      SELECT id,firstname,lastname,email,psd,phone,adress,city,zipcode FROM sys.GroupK_Accounts WHERE id=?
       <sql:param value = "${user}"/>
      </sql:query><!-- NEEDS TO BE UPDATED FOR ACTUAL DATABASE -->
      
      
     <div class="account-page">
        
         <div class="account-detail">

             <h4>Customer Details</h4>
             <div class="customer-detail">
                 <form action="UpdateUserDetails" method="post" class="customer-form">
                 <c:forEach var="row" items="${result.rows}">
                     <div class="form-inline">
                        <div class="form-group">
                            <label >First Name</label>
                            <input type="text" name="fname" value=""+${row.firstname}>
                        </div>
                        <div class="form-group">
                           <label >Last Name</label>
                           <input type="text" name="lname" value=""+${row.lastname}>
                       </div>
                     </div>
                     
                    
                    <div class="form-group">
                        <label > Country</label>
                       <select name="country" id="">
                        <option value="">USA</option>
                           <option value="" >India</option>
                           <option value="" >UK</option>
                           <option value="" >Australia</option>
                       </select>
                    </div>
                    <div class="form-group">
                        <label > City </label>
                       <input type="text" name="lname" value=""+${row.city}>
                    </div>
                    <div class="form-group">
                        <label>Address</label>
                        <input type="text" name="lname" value=""+${row.adress}>
                    </div>
                    <h4> Contact Details</h4>
                    <div class="form-inline">
                       <div class="form-group">
                           <label >Zip Code</label>
                           <<input type="text" name="lname" value=""+${row.zipcode}>
                       </div>
                       <div class="form-group">
                          <label >Mobile</label>
                          <input type="tel" name="mobile" value=""+${row.phone}>
                      </div>
                    </div>
                    <h4> Account Details</h4>
                    <div class="form-inline">
                       <div class="form-group">
                           <label >Email ID</label>
                           <input type="text" id="email" name="email_id" value=""+${row.email}>
                       </div>
                       <div class="form-group">
                          <label >Password</label>
                          <input type="password" name="password" value=""+${row.psd}>
                      </div>
                    </div>
</c:forEach>
                    
                    <div class="form-group">
                        <label></label>
                        <input type="submit" name="Save" value="Save Changes">
                    </div>
                 </form>
             </div>

         </div>

         <div class="profile">
            <div class="profile-detail">           
            </div>
            <ul>
                <li><a href="user.html" class="active"> <span> < </span> Account </a> </li>
                <li><a href="#">  <span> < </span>Change password </a> </li>
                <li><a href="cart.html"> <span> < </span>My Orders  </a> </li>
                <li><a href="LogoutHandler"><span> < </span>Logout </a> </li>
            </ul>
         </div>
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
