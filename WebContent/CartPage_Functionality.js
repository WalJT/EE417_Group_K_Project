/**
 * 
 */

function Check(amount,item){
	var uni=localStorage.getItem('unique');
	var cart = JSON.parse(localStorage.getItem("Cart"));
	var q = document.getElementById(amount.trim()).value;
	var c = localStorage.getItem(item.trim());
	
	if(c!=q){
			localStorage.setItem(item,q);
	}
	
	var quantity=[];
		
	for(var i=0; i<uni;i++){
				var amount = parseInt(localStorage.getItem(String(cart[i])));
				quantity[i]=amount;
	}
		document.getElementById("quantity").value=quantity;

}




window.onload = function(e){
	if(localStorage.getItem('unique')!=null){
		var uni=localStorage.getItem('unique');
		var cart = JSON.parse(localStorage.getItem("Cart"));
		var prices = JSON.parse(localStorage.getItem("Price"));
		document.getElementById("order").value = cart;	
		var quantity=[];
		html=document.getElementById("cart").innerHTML;
		html='<tr><th>Item</th><th>QTY</th><th>Price</th><th>TOTAL PRICE</th></tr>'
			for(var i=0; i<uni;i++){
				var amount = parseInt(localStorage.getItem(String(cart[i])));
				quantity[i]=amount;
				total=parseInt(prices[i])*amount;				
				html +='<div class = "newItem"><tr id="'+cart[i]+'">'+
				'<td>'+cart[i]+'<button class="removeButton" onclick=Remove('+'"'+cart[i]+'"'+')><i style="color:red" class="fas fa-trash"></i></button></td>'+
				'<td><input type="number" class="quantity" onclick=Check('+'"quantity'+i+'","'+cart[i]+'"'+') name="quantity" id="quantity'+i+'" value="'+amount+'" min="1" max="500"></td>'+
				'<td class="price">'+" "+prices[i]+" "+'</td>'+'<td><input type="number" class="itemTotalPrice" value="'+total+'" disabled="true"></td>'
				+'</tr>'
				+'</div>';
				
				document.getElementById("cart").innerHTML= html;
	}
		document.getElementById("quantity").value=quantity;
		
		
	}
	
		
	
}

function Remove(item){
	var prod = item.toString();
	prod=prod.trim();
	
	var uni=localStorage.getItem('unique');
	var cart = JSON.parse(localStorage.getItem("Cart"));
	var prices = JSON.parse(localStorage.getItem("Price"));
	
	for(var i=0; i<uni;i++){
		if(cart[i]==prod){
			cart.splice(i,1);
			prices.splice(i,1);
			uni=uni-1;
			localStorage.setItem("unique", uni);
			localStorage.setItem("Cart", JSON.stringify(cart));
			localStorage.setItem("Price", JSON.stringify(prices));
			break;
		}		
}	
	
	localStorage.removeItem(prod.trim());
	document.getElementById(prod.trim()).remove();
	
}

function Checkout(){
if(getCookie("userEmail")!=null && getCookie("userEmail")!=''){
			var user=getCookie("userEmail");
			document.getElementById("userEmail").value=user;
			document.getElementById("cartform").submit();
			alert("Your Order was Placed "+user);
			
		}
		
	else{	alert("Please Login to Continue to Checkout");}
	
}
