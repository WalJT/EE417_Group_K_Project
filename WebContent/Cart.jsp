<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import = "java.io.*,java.util.*,java.sql.*"%>
<%@page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<!DOCTYPE html>
<html lang="en">

<script>
window.onload = function(e){
	if(localStorage.getItem('unique')!=null){
	var uni=localStorage.getItem('unique');
	var cart = JSON.parse(localStorage.getItem("Cart"));
	var prices = JSON.parse(localStorage.getItem("Price"));
	for(var i=0; i<uni;i++){
	var amount = parseInt(localStorage.getItem(String(cart[i])));
	total=parseInt(prices[i])*amount;
	if(cart[i].includes("camera")){var num =65;}
	else if(cart[i].includes("Phone")){var num =70;}
	else if(cart[i].includes("Electronic")){var num =45;}
	else if(cart[i].includes("LargeAppliance")){var num =15;}
	html=document.getElementById("items").innerHTML;
	html +='<table><div class = "newItem">'+'<tr>'+'<td style = "padding: 0 90px;">'+
	'<br>'+cart[i]+'<br>'
	+'<button class="removeButton">Remove</button>'+'</td>'+
	'<td style = "padding: 0 '+num+'px;"><input type="number" class="quantity" name="quantity" value="'+amount+'" min="1" max="500"></td>'+
	'<td style = "padding: 0 70px;" class="price">'+" "+prices[i]+" "+'</td>'+'<td style = "padding: 0 70px;"><input type="number" class="itemTotalPrice" value="'+total+'" disabled="true"></td>'
	+'</tr>'
	+'</div></table>';
				
	document.getElementById("items").innerHTML= html	
	}
	
	}
	
	
	
	
	
}
</script>

<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link href="cart_style.css" rel="stylesheet">

</head>
<body>
    <!-- Container -->
    <div class="container">
        <!-- Shopping cart -->
        <div class="cartContainer">

        <button class="checkoutButton">CHECKOUT ></button>

        <!--Table-->
        <table id="cart">

        <!--Table heading-->
        <tr>
            <th>Item</th>
            <th>QTY</th>
            <th>Price</th>
            <th>TOTAL PRICE</th>
        </tr>

        <!-- Product details-->
      	
       
      
       
      </table>
      <div id="items">
        
        
        </div>
       



        <!-- Total details -->
        <div class="totalDetails">
            <table id="cartTotal">
                <tr>
                    <td>Subtotal</td>
                    <td><input type="number" id="subTotalPrice" value="" disabled="true"></td>
                </tr>

                <tr>
                    <td>Tax</td>
                    <td></td>
                </tr>

                <tr>
                    <td>Total price</td>
                    <td><input type="number" id="TotalPrice" value="" disabled="true"></td>
                </tr>


            </table>

        </div>
	
        <button class="checkoutButton">CHECKOUT ></button>

        </div>

        <button class="contShoppingButton">< CONTINUE SHOPPING</button>

    </div>

    <script src="cart_js.js"></script>

</body>
</html>