window.setInterval(updatePrices ,100);

/***Listen to remove buttons***/
var removeButton = document.getElementsByClassName('removeButton')
console.log(removeButton)
for(var i=0; i< removeButton.length ; i++){
    var button = removeButton[i]
    button.addEventListener("click",function(event){
        var clicked = event.target
        clicked.parentElement.parentElement.remove()
    })
}

/***Update prices in cart***/
    var subTotalPrice = 0;
    var nameIDs = document.getElementsByClassName('quantity')
    var priceRead = document.getElementsByClassName('price')
    var totalPriceIDs = document.getElementsByClassName('itemTotalPrice')

function updatePrices() {
    for (var j = 0; j < nameIDs.length; j++) {
        var price = parseFloat(priceRead.item(j).innerHTML.replace('S', ''))

        /*getting quantity*/
        var input = nameIDs[j]
        var idName = "quantity" + j.toString()
        input.setAttribute('id', idName)
        var quantity = document.getElementById(idName).value;

        var itemSubTotal = quantity * price;

        /*getting sub total price*/
        var itemTotalPrice = totalPriceIDs[j]
        var idName = "itemTotalPrice" + j.toString()
        itemTotalPrice.setAttribute('id', idName)
        document.getElementById(idName).value = itemSubTotal;
        subTotalPrice += itemSubTotal;

        /*update subtotal price*/
        document.getElementById('subTotalPrice').value = parseFloat(subTotalPrice);
    }
    subTotalPrice = 0;
}


