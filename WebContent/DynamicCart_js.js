/**
 * 
 */

function addItem(){
	if(localStorage.getItem("items")==null){
		localStorage.setItem("items",0);
		document.getElementById("EmptyCart").className = "fas fa-cart-plus fa-lg";
	}
	else if(localStorage.getItem("items")!=null){
		var items = parseInt(localStorage.getItem("items"));
		items = items + 1;
		localStorage.setItem("items",items);		
	}
}


function Add_to_Cart(item,price){
	addItem();
	if(localStorage.getItem("Cart")==null){
		var cart =[];
		var prices =[];		
		cart[0]=item;
		prices[0]=price;	
		localStorage.setItem(String(item),1);
		localStorage.setItem('unique',1);
		localStorage.setItem("Cart", JSON.stringify(cart));
		localStorage.setItem("Price", JSON.stringify(prices));
	}
	else if(localStorage.getItem("items")!=null){			
		var items = localStorage.getItem("items");
		var cart = JSON.parse(localStorage.getItem("Cart"));
		var prices = JSON.parse(localStorage.getItem("Price"));
		
		for(var i=0; i<=items;i++){
			if(localStorage.getItem(String(item))==null){
				localStorage.setItem(String(item),1);
				var uni = parseInt(localStorage.getItem('unique'));
				uni=uni+1;
				localStorage.setItem('unique',uni);
				cart[uni-1]= item;
				localStorage.setItem("Cart", JSON.stringify(cart));
				prices[uni-1]=price;
				localStorage.setItem("Price", JSON.stringify(prices));
				break;}							
			else if(localStorage.getItem(String(item))!=null){
					if(String(cart[i])==String(item)){
					var amount = parseInt(localStorage.getItem(String(item)));			
					amount = amount+1;					
					localStorage.setItem(String(item),amount);
					break;
				}
			}
		}
		
	}		
}

//function Product(item,price,img){
function Product(item,price){
	localStorage.setItem("product", item);
	localStorage.setItem("product_price", price);
	//localStorage.setItem("product_img", img);
		
}
