/**
 * 
 */

function getCookie(name) {
	  var str = name + "=";
	  var cstr = decodeURIComponent(document.cookie);
	  var c = cstr.split(';');
	  for(var i = 0; i <c.length; i++) {
	    var cookie = c[i];
	    while (cookie.charAt(0) == ' ') {
	      cookie  = cookie.substring(1);
	    }
	    if (cookie.indexOf(str) == 0) {
	      return cookie.substring(str.length, cookie.length);
	    }
	  }
	  return "";
	}
	
window.onload = function(e){
	if (getCookie("userID")!=null && getCookie("userID")!=''){
		document.getElementById("Login").style["display"] = "none";
		document.getElementById("SignUp").style["display"] = "none";
		document.getElementById("logout").style["display"] = "block";
	}
	if(getCookie("userID").toString()=="0"){
		document.getElementById("Admin").style["display"] = "block";
		}
	
	if(getCookie("userID").toString()!="0" && getCookie("userID")!=null && getCookie("userID")!=''){
		document.getElementById("User").style["display"] = "block";
	
	}
	if(localStorage.getItem("items")!=null){
			document.getElementById("EmptyCart").className = "fas fa-cart-plus fa-lg";
	}
	if(localStorage.getItem("product")!=null){
		if(document.getElementById("product")!=null){
		document.getElementById("product").innerHTML = localStorage.getItem("product");
		document.getElementById("price").innerHTML = "$ "+localStorage.getItem("product_price");}
	}
		
} 


function Reset(){
	var email=document.getElementById("username").value;
	if(email==null || email==""){alert("Please enter your email address to reset your password");}
	else{alert("A password reset link has been sent to "+email);}
	
}

