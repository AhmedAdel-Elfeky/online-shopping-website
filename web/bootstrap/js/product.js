/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




let totalPrice = 0;
let productInCart = 0;

function addToCart(price, id, name) {

    document.cookie = "id" + id + "=" +"1"+";path=/";
    productInCart = parseInt(document.getElementById("add-tocart").innerHTML) + 1;
    // document.getElementById("add-tocart").innerHTML = productInCart;
    totalPrice = parseInt(document.getElementById("total-price").innerHTML) + parseInt(price);
    document.getElementById("add-tocart").innerHTML = productInCart;
    document.getElementById("total-price").innerHTML = totalPrice;
    document.cookie = "productInCart=" + productInCart+";path=/";
    document.cookie = "totalPrice=" + totalPrice+";path=/";
    document.getElementById(id).onclick = null;
}










